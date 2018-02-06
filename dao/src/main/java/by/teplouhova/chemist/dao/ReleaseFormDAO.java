package by.teplouhova.chemist.dao;

import by.teplouhova.chemist.entity.impl.ReleaseForm;

import java.util.ArrayList;

public abstract class ReleaseFormDAO extends AbstractDAO<ReleaseForm> {
    public abstract ArrayList<ReleaseForm> findAll() throws DAOException;
}
