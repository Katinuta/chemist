package by.teplouhova.chemist.dao;

import by.teplouhova.chemist.entity.impl.Order;

import java.util.List;
/**
 * The Class OrderDAO.
 */
public abstract class OrderDAO extends AbstractDAO<Order> {

    /**
     * Find orders by client id.
     *
     * @param id the id
     * @return the list
     * @throws DAOException the DAO exception
     */
    public abstract List<Order> findOrdersByClientId(long id) throws DAOException;


}
