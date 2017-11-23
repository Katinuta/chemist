package by.teplouhova.chemist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static final int POOL_SIZE = 15;
    private static ReentrantLock lock = new ReentrantLock();
    private BlockingQueue<ProxyConnection> connections;
    private static ConnectionPool pool;
    private static AtomicBoolean isExistPool = new AtomicBoolean(false);


    private ConnectionPool() {

        connections = new ArrayBlockingQueue<ProxyConnection>(POOL_SIZE);
        for (int index = 0; index < POOL_SIZE; index++) {
            connections.offer(new ProxyConnection());
        }

    }

    public static ConnectionPool getInstance() {
        if (!isExistPool.get()) {
            try {
                lock.lock();
                if (pool != null) {
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
            connection = connections.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void releaseConnection(ProxyConnection connection) {
        try {
            if(!connection.getAutoCommit()){
                connection.setAutoCommit(true);
            }
            connections.offer(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closePoolConnections(){
        connections.forEach(connection -> {
            try {
                connection.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }


}
