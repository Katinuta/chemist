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

public class ClientService {

    private PrescriptionDAO prescriptionDAO;
    private OrderDAO orderDAO;
    private UserDAO userDAO;

    public ClientService() {
        prescriptionDAO = DAOFactory.getDAOFactory().getPrescriptionDAO();
        orderDAO=DAOFactory.getDAOFactory().getOrderDAO();
        userDAO= DAOFactory.getDAOFactory().getUserDAO();
    }


    public List<Prescription> getPrescriptions(long userId) throws ServiceException {
        TransactionManager transaction = new TransactionManager();
        transaction.beginTransaction(prescriptionDAO);
        List<Prescription> prescriptions;
        try {
            prescriptions = prescriptionDAO.findPrescriptionByClientId(userId);
            if(prescriptions==null){
                throw new ServiceException("Prescriptions is not found");
            }
        } catch (DAOException e) {

            throw new ServiceException(e);
        } finally {
            transaction.endTransaction();
        }
        return prescriptions;

    }

    public List<Order> getUserOrders(long userId) throws ServiceException {
        List<Order> orders;
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(orderDAO);
        try {
            orders = orderDAO.findOrdersByClientId(userId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return orders;
    }

    public void sendExtandPrescription(long id) throws ServiceException {
        TransactionManager manager = new TransactionManager();

        manager.beginTransaction(prescriptionDAO);
        try {
            Prescription prescription = prescriptionDAO.findById(id);
            prescription.setStatus(PrescriptionStatus.EXTEND);
            prescriptionDAO.update(prescription);
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            manager.endTransaction();
        }
    }

    public BigDecimal getBalanceAccount(long userId) throws ServiceException {
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(userDAO);
        BigDecimal balance;
        try {
            balance = userDAO.findBalanceByUserId(userId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            manager.endTransaction();
        }
        return balance;
    }

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
