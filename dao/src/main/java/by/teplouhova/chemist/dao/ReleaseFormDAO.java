package by.teplouhova.chemist.dao;

import by.teplouhova.chemist.dao.exception.DAOException;
import by.teplouhova.chemist.entity.impl.ReleaseForm;

import java.util.ArrayList;
import java.util.HashSet;

public abstract class ReleaseFormDAO extends AbstractDAO<ReleaseForm> {
    public abstract ArrayList<ReleaseForm> findAll() throws DAOException;
}
