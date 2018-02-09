package by.teplouhova.chemist.dao;

import by.teplouhova.chemist.entity.impl.OrderDetail;

import java.util.List;
/**
 * The Class OrderDetailDAO.
 */
public abstract class OrderDetailDAO extends AbstractDAO<OrderDetail> {

    /**
     * Find all by order id.
     *
     * @param orderId the order id
     * @return the list
     * @throws DAOException the DAO exception
     */
    public abstract List<OrderDetail> findAllByOrderId(long orderId) throws DAOException;
}
