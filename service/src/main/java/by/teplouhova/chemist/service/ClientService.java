package by.teplouhova.chemist.service;

import by.teplouhova.chemist.dao.PrescriptionDAO;
import by.teplouhova.chemist.dao.TransactionManager;
import by.teplouhova.chemist.dao.exception.DAOException;
import by.teplouhova.chemist.dao.factory.DAOFactory;
import by.teplouhova.chemist.entity.impl.Prescription;

import java.util.HashSet;
import java.util.Set;

public class ClientService {

    public HashSet<Prescription> getClientPrescriptions(long userId) throws ServiceException {
        TransactionManager transaction = new TransactionManager();
        PrescriptionDAO prescriptionDAO = DAOFactory.getDAOFactory().getPrescriptionDAO();
        transaction.beginTransaction(prescriptionDAO);
        HashSet<Prescription> prescriptions;
        try {
            prescriptions=prescriptionDAO.findPrescriptionByClientId(userId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }finally{
            transaction.endTransaction();
        }
        return prescriptions;

    }
}
