package by.teplouhova.chemist.service;

import by.teplouhova.chemist.dao.OrderDAO;
import by.teplouhova.chemist.dao.TransactionManager;
import by.teplouhova.chemist.dao.exception.DAOException;
import by.teplouhova.chemist.dao.factory.DAOFactory;
import by.teplouhova.chemist.entity.impl.Order;

import java.util.List;

public class OrderService {
    public List<Order> getUserOrders(long userId) throws ServiceException {
        List<Order> orders;
        TransactionManager manager=new TransactionManager();
        OrderDAO orderDAO= DAOFactory.getDAOFactory().getOrderDAO();
        manager.beginTransaction(orderDAO);
        try {
            orders=orderDAO.findOrdersByClientId(userId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return orders;
    }
 }
