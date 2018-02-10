package by.teplouhova.chemist.dao;


import by.teplouhova.chemist.pool.ConnectionPool;
import by.teplouhova.chemist.pool.ProxyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.Arrays;

/**
 * The Class TransactionManager contains methods to manage operations with database .
 */
public class TransactionManager {

    /** The Constant LOGGER. */
    private final static Logger LOGGER= LogManager.getLogger();

    /** The connection. */
    private ProxyConnection connection= ConnectionPool.getInstance().getConnection();

    /**
     * Begin transaction.
     *
     * @param dao the dao
     * @param listDAO the list DAO
     */
    public void beginTransaction(AbstractDAO dao, AbstractDAO... listDAO) {
        try {
            connection.setAutoCommit(false);
            dao.setConnection(connection);
            Arrays.stream(listDAO).forEach(itemDAO ->
                    itemDAO.setConnection(connection)
            );
        } catch (SQLException e) {
            LOGGER.catching(e);
        }
    }

    /**
     * Begin transaction for simple operation for DAO object. Set connection.
     *
     * @param dao the dao
     */
    public void beginTransaction(AbstractDAO dao){
        dao.setConnection(connection);
    }



    /**
     * Commit.
     */
    public void commit(){
        try {
            connection.commit();
        } catch (SQLException e) {
            LOGGER.catching(e);
        }
    }

    /**
     * Rollback.
     */
    public void rollback(){
        try {
            connection.rollback();
        } catch (SQLException e) {
            LOGGER.catching(e);
        }
    }

    /**
     * End transaction.
     */
    public void endTransaction(){
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            LOGGER.catching(e);
        }
        ConnectionPool.getInstance().releaseConnection(connection);
    }
}
