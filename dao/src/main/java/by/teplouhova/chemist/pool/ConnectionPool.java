package by.teplouhova.chemist.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The Class ConnectionPool.
 */
public class ConnectionPool {

    private final static Logger LOGGER = LogManager.getLogger();

    /**
     * The pool max active.
     */
    private final int POOL_MAX_ACTIVE;

    /**
     * The pool init size.
     */
    private final int POOL_INIT_SIZE;

    /**
     * The lock.
     */
    private static ReentrantLock lock = new ReentrantLock();

    /**
     * The pool.
     */
    private static ConnectionPool pool;

    /**
     * The is exist pool.
     */
    private static AtomicBoolean isExistPool = new AtomicBoolean(false);

    /**
     * The connections.
     */
    private BlockingQueue<ProxyConnection> connections;


    /**
     * Instantiates a new connection pool.
     */
    private ConnectionPool() {
        POOL_MAX_ACTIVE = ConnectionConfiguration.getConfiguration().getPOOL_MAX_ACTIVE();
        POOL_INIT_SIZE = ConnectionConfiguration.getConfiguration().getPOOL_INIT_SIZE();
        connections = new ArrayBlockingQueue<>(POOL_MAX_ACTIVE);
        for (int index = 1; index <= POOL_INIT_SIZE; index++) {
            Connection connection = ConnectionConfiguration.getConfiguration().getConnection();
            if (connection != null) {
                connections.offer(new ProxyConnection(connection));
            }
            if (index == POOL_INIT_SIZE) {
                if (connections.size() != POOL_INIT_SIZE) {
                    index = POOL_INIT_SIZE - connections.size();
                }
            }

        }

    }

    /**
     * Gets the single instance of ConnectionPool.
     *
     * @return single instance of ConnectionPool
     */
    public static ConnectionPool getInstance() {
        if (!isExistPool.get()) {
            try {
                lock.lock();
                if (pool == null) {
                    pool = new ConnectionPool();
                    isExistPool.set(true);
                }
            } finally {
                lock.unlock();
            }
        }

        return pool;
    }

    /**
     * Gets the connection.
     *
     * @return the connection
     */
    public ProxyConnection getConnection() {
        ProxyConnection connection = null;
        try {
            if (connections.remainingCapacity() == POOL_MAX_ACTIVE - POOL_INIT_SIZE && connections.isEmpty()) {
                for (int index = 1; index <= POOL_INIT_SIZE; index++) {
                    Connection newConnection = ConnectionConfiguration.getConfiguration().getConnection();
                    if (newConnection != null) {
                        connections.offer(new ProxyConnection(newConnection));
                    }
                    if (index == POOL_INIT_SIZE) {
                        if (connections.size() != POOL_INIT_SIZE) {
                            index = POOL_INIT_SIZE - connections.size();
                        }
                    }

                }
            }
            connection = connections.take();
        } catch (InterruptedException e) {
            LOGGER.catching(e);
        }
        return connection;
    }

    /**
     * Release connection.
     *
     * @param connection the connection
     */
    public void releaseConnection(ProxyConnection connection) {
        try {
            if (!connection.getAutoCommit()) {
                connection.setAutoCommit(true);
            }
            connections.offer(connection);
        } catch (SQLException e) {
            LOGGER.catching(e);
        }
    }

    /**
     * Close pool connections.
     */
    public void closePoolConnections() {
        connections.forEach(connection -> {
            try {
                connection.closeConnection();
            } catch (SQLException e) {
                LOGGER.catching(e);
            }
        });
    }

    /**
     * Size.
     *
     * @return the int size of pool
     */
    public int size() {
        return connections.size();
    }

}
