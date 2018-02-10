package by.teplouhova.chemist.service;

import by.teplouhova.chemist.dao.OrderDAO;
import by.teplouhova.chemist.dao.PrescriptionDAO;
import by.teplouhova.chemist.dao.TransactionManager;
import by.teplouhova.chemist.dao.UserDAO;
import by.teplouhova.chemist.dao.DAOException;
import by.teplouhova.chemist.dao.factory.DAOFactory;
import by.teplouhova.chemist.entity.impl.Order;
import by.teplouhova.chemist.entity.impl.Prescription;
import by.teplouhova.chemist.entity.impl.PrescriptionStatus;

import java.math.BigDecimal;
import java.util.List;

/**
 * The Class ClientService.
 */
public class ClientService {
    /**
     * The prescription DAO.
     */
    private PrescriptionDAO prescriptionDAO;

    /**
     * The order DAO.
     */
    private OrderDAO orderDAO;

    /**
     * The user DAO.
     */
    private UserDAO userDAO;

    /**
     * Instantiates a new client service.
     */
    public ClientService() {
        prescriptionDAO = DAOFactory.getDAOFactory().getPrescriptionDAO();
        orderDAO = DAOFactory.getDAOFactory().getOrderDAO();
        userDAO = DAOFactory.getDAOFactory().getUserDAO();
    }

    /**
     * Gets the prescriptions.
     *
     * @param userId the user id
     * @return the prescriptions
     * @throws ServiceException the service exception
     */
    public List<Prescription> getPrescriptions(long userId) throws ServiceException {
        TransactionManager transaction = new TransactionManager();
        transaction.beginTransaction(prescriptionDAO);
        List<Prescription> prescriptions;
        try {
            prescriptions = prescriptionDAO.findPrescriptionByClientId(userId);
        } catch (DAOException e) {

            throw new ServiceException("Prescriptions are not found", e);
        } finally {
            transaction.endTransaction();
        }
        return prescriptions;

    }

    /**
     * Gets the user orders.
     *
     * @param userId the user id
     * @return the user orders
     * @throws ServiceException the service exception
     */
    public List<Order> getUserOrders(long userId) throws ServiceException {
        List<Order> orders;
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(orderDAO);
        try {
            orders = orderDAO.findOrdersByClientId(userId);

        } catch (DAOException e) {
            throw new ServiceException("Orders are not found", e);
        }
        return orders;
    }

    /**
     * Checks if is have enough money.
     *
     * @param clientId the client id
     * @param sum      the sum
     * @return true, if is have enough money
     * @throws ServiceException the service exception
     */

    public boolean isHaveEnoughMoney(long clientId, BigDecimal sum) throws ServiceException {
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(userDAO);
        BigDecimal balance;
        try {
            balance = userDAO.findBalanceByUserId(clientId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            manager.endTransaction();
        }
        return balance != null ? balance.compareTo(sum) != -1 : false;
    }

}
