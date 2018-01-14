package by.teplouhova.chemist.service;

import by.teplouhova.chemist.dao.PrescriptionDAO;
import by.teplouhova.chemist.dao.TransactionManager;
import by.teplouhova.chemist.dao.exception.DAOException;
import by.teplouhova.chemist.dao.factory.DAOFactory;
import by.teplouhova.chemist.entity.impl.Medicine;
import by.teplouhova.chemist.entity.impl.Order;
import by.teplouhova.chemist.entity.impl.Prescription;
import by.teplouhova.chemist.entity.impl.PrescriptionStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.HashSet;

public class ClientService {
    private static final Logger LOGGER= LogManager.getLogger();
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

    public Order buyMedicines(HashMap<Medicine,Integer> cart){
        //TransactionManager
        return null;
    }

    public void sendExtandPrescription(long id) throws ServiceException {
        TransactionManager manager=new TransactionManager();
        PrescriptionDAO prescriptionDAO=DAOFactory.getDAOFactory().getPrescriptionDAO();
        manager.beginTransaction(prescriptionDAO);
        try {
            Prescription prescription=prescriptionDAO.findById(id);
            prescription.setStatus(PrescriptionStatus.EXTAND);
            prescriptionDAO.update(prescription);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }finally {
          manager.endTransaction();
        }
    }


}
