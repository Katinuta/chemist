package by.teplouhova.chemist.pool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private final static Logger LOGGER = LogManager.getLogger();

    private final int POOL_MAX_ACTIVE;
    private final int POOL_INIT_SIZE;
    private static ReentrantLock lock = new ReentrantLock();
    private static ConnectionPool pool;
    private static AtomicBoolean isExistPool = new AtomicBoolean(false);
    private BlockingQueue<ProxyConnection> connections;


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
            LOGGER.log(Level.ERROR, "Thread is interrupted");
        }
        return connection;
    }

    public void releaseConnection(ProxyConnection connection) {
        try {
            if (!connection.getAutoCommit()) {
                connection.setAutoCommit(true);
            }

            connections.offer(connection);
        } catch (SQLException e) {
            LOGGER.log(Level.WARN, "Connection is not released", e);
        }
    }

    public void closePoolConnections() {
        connections.forEach(connection -> {
            try {
                connection.closeConnection();
            } catch (SQLException e) {
                LOGGER.log(Level.ERROR, "Connection didn't close", e);
            }
        });
    }

}
