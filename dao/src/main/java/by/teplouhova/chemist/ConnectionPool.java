package by.teplouhova.chemist;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
  //  private static final int POOL_SIZE = 15;
    private static ReentrantLock lock = new ReentrantLock();
    private static ConnectionPool pool;
    private static AtomicBoolean isExistPool = new AtomicBoolean(false);
    private BlockingQueue<ProxyConnection> connections;



    private ConnectionPool() {
        ResourceBundle bundle = ResourceBundle.getBundle("database");
        String url = bundle.getString("url") + "?" +
                "useUnicode=" + bundle.getString("useUnicode") + "&" +
                "characterEncoding=" + bundle.getString("characterEncoding");
        String user=bundle.getString("user");
        String password=bundle.getString("password");
        int poolInitSize=Integer.parseInt(bundle.getString("initialSize"));
        int poolMaxActive=Integer.parseInt(bundle.getString("maxActive"));
        connections = new ArrayBlockingQueue<>(poolMaxActive);
       while (connections.remainingCapacity()!=poolInitSize) {
            try {
                connections.offer(new ProxyConnection(DriverManager.getConnection(url,user,password)));
            } catch (SQLException e) {
                e.printStackTrace();
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
                e.printStackTrace();
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

    @Override
    public String toString() {
        return "ConnectionPool{" +
                "connections=" + connections +
                '}';
    }
}
