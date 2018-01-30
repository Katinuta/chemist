package by.teplouhova.chemist.dao;


import by.teplouhova.chemist.pool.ConnectionPool;
import by.teplouhova.chemist.pool.ProxyConnection;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.Arrays;

public class TransactionManager {

    private final static Logger LOGGER= LogManager.getLogger();

    private ProxyConnection connection= ConnectionPool.getInstance().getConnection();

    public void beginTransaction(AbstractDAO dao, AbstractDAO... listDAO) {
        try {

            connection.setAutoCommit(false);
            dao.setConnection(connection);
            Arrays.stream(listDAO).forEach(itemDAO -> {
                itemDAO.setConnection(connection);
            });
        } catch (SQLException e) {
           LOGGER.log(Level.ERROR,"Transaction wasn't begun " ,e);
        }
    }

    public void beginTransaction(AbstractDAO dao){

        dao.setConnection(connection);
    }

    

    public void commit(){
        try {
            connection.commit();
        } catch (SQLException e) {
          LOGGER.log(Level.ERROR,"Transaction wasn't commited ",e);
        }
    }

    public void rollback(){
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void endTransaction(){
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionPool.getInstance().releaseConnection(connection);
    }
}
