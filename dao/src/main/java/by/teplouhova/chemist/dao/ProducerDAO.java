package by.teplouhova.chemist.dao;

import by.teplouhova.chemist.entity.impl.Producer;

import java.util.ArrayList;

public abstract class ProducerDAO extends AbstractDAO<Producer> {
    public abstract ArrayList<Producer> findAll() throws DAOException;
}
