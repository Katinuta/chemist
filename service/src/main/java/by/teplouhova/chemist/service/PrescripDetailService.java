package by.teplouhova.chemist.service;

import by.teplouhova.chemist.dao.PrescripDetailDAO;
import by.teplouhova.chemist.dao.PrescriptionDAO;
import by.teplouhova.chemist.dao.TransactionManager;
import by.teplouhova.chemist.dao.exception.DAOException;
import by.teplouhova.chemist.dao.factory.DAOFactory;
import by.teplouhova.chemist.entity.impl.PrescriptionDetail;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PrescripDetailService {
    private static final Logger LOGGER=  LogManager.getLogger();

    public PrescriptionDetail getPrescripDetail(long id) throws ServiceException {
        TransactionManager manager = new TransactionManager();
        PrescripDetailDAO prescripDetailDAO = DAOFactory.getDAOFactory().getPrescripDetailDAO();
        manager.beginTransaction(prescripDetailDAO);
        PrescriptionDetail detail = null;
        try {
            detail=prescripDetailDAO.findById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            manager.endTransaction();
        }
        return detail;
    }
    public PrescriptionDetail updatePrescripDetail(PrescriptionDetail detail) throws ServiceException {
        TransactionManager manager = new TransactionManager();
        PrescripDetailDAO prescripDetailDAO = DAOFactory.getDAOFactory().getPrescripDetailDAO();
        PrescriptionDAO prescriptionDAO=DAOFactory.getDAOFactory().getPrescriptionDAO();
        manager.beginTransaction(prescripDetailDAO,prescriptionDAO);

        try {
            prescripDetailDAO.update(detail);
            prescriptionDAO.update(detail.getPrescription());
            manager.commit();
        } catch (DAOException e) {
            manager.rollback();
            throw new ServiceException(e);
        } finally {
            manager.endTransaction();
        }
        return detail;
    }

}
