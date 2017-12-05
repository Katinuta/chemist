package by.teplouhova.chemist.pool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private final static Logger LOGGER = LogManager.getLogger();

    private final int POOL_MAX_ACTIVE;
    private final int POOL_INIT_SIZE;
    private final String URL;
    private final String USER;
    private final String PASSWORD;
    private static ReentrantLock lock = new ReentrantLock();
    private static ConnectionPool pool;
    private static AtomicBoolean isExistPool = new AtomicBoolean(false);
    private BlockingQueue<ProxyConnection> connections;


    private ConnectionPool() {
        ResourceBundle bundle = ResourceBundle.getBundle("database");
        URL = bundle.getString("url") + "?" +
                "useUnicode=" + bundle.getString("useUnicode") + "&" +
                "characterEncoding=" + bundle.getString("characterEncoding")+ "&" +
                "useSSL=" + bundle.getString("useSSL");

        USER = bundle.getString("user");
        PASSWORD = bundle.getString("password");
        POOL_INIT_SIZE = Integer.parseInt(bundle.getString("initialSize"));
        POOL_MAX_ACTIVE = Integer.parseInt(bundle.getString("maxActive"));
        connections = new ArrayBlockingQueue<>(POOL_MAX_ACTIVE);
        while (connections.remainingCapacity() != POOL_MAX_ACTIVE - POOL_INIT_SIZE) {
            try {
                connections.offer(new ProxyConnection(DriverManager.getConnection(URL, USER, PASSWORD)));
            } catch (SQLException e) {
                LOGGER.log(Level.ERROR, "Connection is not created " + URL);
            }
        }

    }

    public static ConnectionPool getInstance() {
        if (!isExistPool.get()) {
            try {
                lock.lock();
                if (pool == null) {
                    DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                    pool = new ConnectionPool();
                    isExistPool.set(true);
                }
            } catch (SQLException e) {
                LOGGER.log(Level.FATAL, "Database driver is not registered ");
                new RuntimeException(e);
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
                while (connections.remainingCapacity() != 0) {
                    try {
                        connections.offer(new ProxyConnection(DriverManager.getConnection(URL, USER, PASSWORD)));
                    } catch (SQLException e) {
                        LOGGER.log(Level.ERROR, "Connection is not created " + URL);
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
            e.printStackTrace();
        }
    }

    public void closePoolConnections() {
        connections.forEach(connection -> {
            try {
                connection.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public String toString() {
        return "ConnectionPool{" +
                "connections=" + connections +
                '}';
    }
}
