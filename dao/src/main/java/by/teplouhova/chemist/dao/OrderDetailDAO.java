package by.teplouhova.chemist.dao;

import by.teplouhova.chemist.entity.impl.OrderDetail;

import java.util.List;

public abstract class OrderDetailDAO extends AbstractDAO<OrderDetail> {
    public abstract List<OrderDetail> findAllByOrderId(long orderId) throws DAOException;
}
