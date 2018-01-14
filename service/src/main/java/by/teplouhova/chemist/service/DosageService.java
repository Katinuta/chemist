package by.teplouhova.chemist.service;

import by.teplouhova.chemist.dao.DosageDAO;
import by.teplouhova.chemist.dao.ReleaseFormDAO;
import by.teplouhova.chemist.dao.TransactionManager;
import by.teplouhova.chemist.dao.exception.DAOException;
import by.teplouhova.chemist.dao.factory.DAOFactory;
import by.teplouhova.chemist.entity.impl.ReleaseForm;

import java.util.ArrayList;
import java.util.HashSet;

public class DosageService {

    public ArrayList<String> getDosageUnits() throws ServiceException {
        TransactionManager manager=new TransactionManager();
        DosageDAO dao= DAOFactory.getDAOFactory().getDosageDAO();

        manager.beginTransaction(dao);
        ArrayList<String> dosageUnits;
        try {
            dosageUnits=dao.findDosageUnits();

        } catch (DAOException e) {
            throw new ServiceException(""+e);
        }finally {
            manager.endTransaction();
        }
        return dosageUnits;
    }
}
