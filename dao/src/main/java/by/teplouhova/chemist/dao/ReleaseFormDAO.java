package by.teplouhova.chemist.dao;

import by.teplouhova.chemist.entity.impl.ReleaseForm;

import java.util.ArrayList;

/**
 * The Class ReleaseFormDAO.
 */
public abstract class ReleaseFormDAO extends AbstractDAO<ReleaseForm> {

    /**
     * Find all.
     *
     * @return the array list
     * @throws DAOException the DAO exception
     */
    public abstract ArrayList<ReleaseForm> findAll() throws DAOException;
}
