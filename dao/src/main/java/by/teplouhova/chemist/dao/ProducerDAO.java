package by.teplouhova.chemist.dao;

import by.teplouhova.chemist.dao.exception.DAOException;
import by.teplouhova.chemist.entity.impl.Producer;
import by.teplouhova.chemist.entity.impl.ReleaseForm;

import java.util.ArrayList;
import java.util.HashSet;

public abstract class ProducerDAO extends AbstractDAO<Producer> {
    public abstract ArrayList<Producer> findAll() throws DAOException;
}
