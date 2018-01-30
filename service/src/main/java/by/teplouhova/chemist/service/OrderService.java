package by.teplouhova.chemist.service;

import by.teplouhova.chemist.dao.*;
import by.teplouhova.chemist.dao.exception.DAOException;
import by.teplouhova.chemist.dao.factory.DAOFactory;
import by.teplouhova.chemist.entity.impl.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.soap.Detail;
import java.math.BigDecimal;
import java.util.List;

public class OrderService {
    private final static Logger LOGGER = LogManager.getLogger();

    public List<Order> getUserOrders(long userId) throws ServiceException {
        List<Order> orders;
        TransactionManager manager = new TransactionManager();
        OrderDAO orderDAO = DAOFactory.getDAOFactory().getOrderDAO();
        manager.beginTransaction(orderDAO);
        try {
            orders = orderDAO.findOrdersByClientId(userId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return orders;
    }
public Order getById(long orderId ) throws ServiceException {
        TransactionManager manager=new TransactionManager();
        OrderDAO orderDAO=DAOFactory.getDAOFactory().getOrderDAO();
        OrderDetailDAO orderDetailDAO=DAOFactory.getDAOFactory().getOrderDetailDAO();
        manager.beginTransaction(orderDAO);
    Order order=null;
    try {
       order=orderDAO.findById(orderId);
       manager.beginTransaction(orderDetailDAO);
        List<OrderDetail> details=orderDetailDAO.findAllByOrderId(order.getOrderId());
       order.setDetails(details);
    } catch (DAOException e) {
        throw new ServiceException(e);
    }finally {
        manager.endTransaction();
    }
    return order;
}
    public void create(Order order) throws ServiceException {
        TransactionManager manager = new TransactionManager();
        OrderDAO orderDAO = DAOFactory.getDAOFactory().getOrderDAO();
        OrderDetailDAO orderDetailDAO = DAOFactory.getDAOFactory().getOrderDetailDAO();
        UserDAO userDAO = DAOFactory.getDAOFactory().getUserDAO();
        MedicineDAO medicineDAO = DAOFactory.getDAOFactory().getMedicineDAO();

        manager.beginTransaction(orderDAO, orderDetailDAO, userDAO, medicineDAO);
        try {
            User user = order.getUser();
            BigDecimal balance = userDAO.findBalanceByUserId(user.getUserId());
            BigDecimal newBalance = balance.subtract(order.getTotal());
            user.setAccount(newBalance);
            userDAO.update(user);
            order.setStatus(OrderStatusType.PAID);
            List<OrderDetail> details = order.getDetails();
            orderDAO.create(order);
            order = orderDAO.findById(order.getOrderId());

            for (OrderDetail detail : details) {
                detail.setOrder(order);
                Medicine medicine = detail.getMedicine();
                medicine = medicineDAO.findByIdEdit(medicine.getMedicineId());
                medicine.setQuantityPackages(medicine.getQuantityPackages()- detail.getQuantity());
                medicineDAO.update(medicine);
                orderDetailDAO.create(detail);

            }

            order.setDetails(details);
            manager.commit();
        } catch (DAOException e) {
            manager.rollback();
            throw new ServiceException(e);
        } finally {
            manager.endTransaction();
        }

    }
}
