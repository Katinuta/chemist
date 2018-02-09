package by.teplouhova.chemist.dao;

import by.teplouhova.chemist.entity.impl.Producer;

import java.util.ArrayList;

/**
 * The Class ProducerDAO.
 */
public abstract class ProducerDAO extends AbstractDAO<Producer> {

    /**
     * Find all.
     *
     * @return the array list
     * @throws DAOException the DAO exception
     */
    public abstract ArrayList<Producer> findAll() throws DAOException;
}
