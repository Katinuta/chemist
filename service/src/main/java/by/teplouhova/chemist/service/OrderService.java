package by.teplouhova.chemist.service;

import by.teplouhova.chemist.dao.OrderDAO;
import by.teplouhova.chemist.dao.OrderDetailDAO;
import by.teplouhova.chemist.dao.TransactionManager;
import by.teplouhova.chemist.dao.UserDAO;
import by.teplouhova.chemist.dao.exception.DAOException;
import by.teplouhova.chemist.dao.factory.DAOFactory;
import by.teplouhova.chemist.entity.impl.Order;
import by.teplouhova.chemist.entity.impl.OrderDetail;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.soap.Detail;
import java.util.List;

public class OrderService {
    private final static Logger LOGGER = LogManager.getLogger();
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

    public void create(Order order) throws ServiceException {
        TransactionManager manager =new TransactionManager();
        OrderDAO orderDAO=DAOFactory.getDAOFactory().getOrderDAO();
        OrderDetailDAO orderDetailDAO=DAOFactory.getDAOFactory().getOrderDetailDAO();
        UserDAO userDAO=DAOFactory.getDAOFactory().getUserDAO();
        manager.beginTransaction(orderDAO,orderDetailDAO,userDAO);
        try {
            orderDAO.create(order);
            order=orderDAO.findById(order.getOrderId());
            List<OrderDetail> details =order.getDetails();

            for (OrderDetail detail:details ) {
                detail.setOrder(order);
                orderDetailDAO.create(detail);
            }
            order.setDetails(details);

            manager.commit();
        } catch (DAOException e) {
            manager.rollback();
            throw new ServiceException(e);
        }finally {
            manager.endTransaction();
        }

    }
 }
