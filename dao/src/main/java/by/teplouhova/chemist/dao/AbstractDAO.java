package by.teplouhova.chemist.dao;

import by.teplouhova.chemist.entity.Entity;
import by.teplouhova.chemist.pool.ProxyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * The Class AbstractDAO.
 *
 * @param <T> the generic type
 */
public abstract class AbstractDAO<T extends Entity> {

    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * The connection.
     */
    protected ProxyConnection connection;

    /**
     * Find by id.
     *
     * @param id the id
     * @return the t
     * @throws DAOException the DAO exception
     */
    public abstract T findById(long id) throws DAOException;

    /**
     * Creates the.
     *
     * @param entity the entity
     * @throws DAOException the DAO exception
     */
    public abstract void create(T entity) throws DAOException;

    /**
     * Update.
     *
     * @param entity the entity
     * @throws DAOException the DAO exception
     */
    public abstract void update(T entity) throws DAOException;


    /**
     * Sets the connection.
     *
     * @param connection the new connection
     */
    void setConnection(ProxyConnection connection) {
        this.connection = connection;
    }

    /**
     * Close connection.
     *
     * @param st the st
     */
    public void close(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                LOGGER.catching(e);
            }
        }
    }
}
