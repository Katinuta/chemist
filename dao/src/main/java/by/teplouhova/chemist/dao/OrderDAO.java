package by.teplouhova.chemist.dao;

import by.teplouhova.chemist.entity.impl.Order;

import java.util.List;

public abstract class OrderDAO extends AbstractDAO<Order> {
    public abstract List<Order> findOrdersByClientId(long id) throws DAOException;


}