package by.teplouhova.chemist.service;

import by.teplouhova.chemist.dao.DosageDAO;
import by.teplouhova.chemist.dao.TransactionManager;
import by.teplouhova.chemist.dao.DAOException;
import by.teplouhova.chemist.dao.factory.DAOFactory;

import java.util.ArrayList;

/**
 * The Class DosageService.
 */
public class DosageService {

    /**
     * Gets the dosage units.
     *
     * @return the dosage units
     * @throws ServiceException the service exception
     */
    public ArrayList<String> getDosageUnits() throws ServiceException {
        TransactionManager manager = new TransactionManager();
        DosageDAO dao = DAOFactory.getDAOFactory().getDosageDAO();

        manager.beginTransaction(dao);
        ArrayList<String> dosageUnits;
        try {
            dosageUnits = dao.findDosageUnits();
        } catch (DAOException e) {
            throw new ServiceException("Dosage units is not found", e);
        } finally {
            manager.endTransaction();
        }
        return dosageUnits;
    }
}
