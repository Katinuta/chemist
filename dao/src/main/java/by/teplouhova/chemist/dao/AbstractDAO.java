package by.teplouhova.chemist.dao;

import by.teplouhova.chemist.entity.Entity;
import by.teplouhova.chemist.dao.exception.DAOException;
import by.teplouhova.chemist.pool.ProxyConnection;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbstractDAO <T extends Entity>  {
    private static final Logger LOGGER= LogManager.getLogger();

    protected ProxyConnection connection;
    public abstract T findById(long id) throws DAOException;
    public abstract void create(T entity) throws DAOException;
    public abstract void update(T entity) throws DAOException;


    void setConnection(ProxyConnection connection) {
        this.connection = connection;
    }

    public void close(Statement st)  {
        if(st!=null){
            try {
                st.close();
            } catch (SQLException e) {
            LOGGER.catching(e);
            }
        }
    }
}
