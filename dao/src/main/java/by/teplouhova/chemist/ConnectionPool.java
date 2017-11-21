package by.teplouhova.chemist;

import java.sql.Connection;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static final int POOL_SIZE=15;
    private static ReentrantLock lock=new ReentrantLock();
    private static Semaphore semaphore=new Semaphore(POOL_SIZE,true);


    private ConnectionPool(){

    }

    public static Connection getConnection(){
        return null;
    }

    
}
