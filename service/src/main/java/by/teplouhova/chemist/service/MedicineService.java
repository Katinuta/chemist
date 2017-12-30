package by.teplouhova.chemist.service;

import by.teplouhova.chemist.dao.factory.DAOFactory;
import by.teplouhova.chemist.dao.MedicineDAO;
import by.teplouhova.chemist.dao.TransactionManager;
import by.teplouhova.chemist.dao.exception.DAOException;
import by.teplouhova.chemist.entity.impl.Medicine;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

public class MedicineService {
    private final static Logger LOGGER = LogManager.getLogger();

    public Set<Medicine> getMedicinesByName(String name) throws ServiceException {
        TransactionManager manager = new TransactionManager();
        MedicineDAO medicineDAO = DAOFactory.getDAOFactory().getMedicineDAO();
       Set<Medicine> medicines;
        manager.beginTransaction(medicineDAO);
        try {
            medicines = medicineDAO.findMedicineByName(name);
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            manager.endTransaction();
        }
        return medicines;
    }

    public Set<Medicine> getMedicines() throws ServiceException {
        TransactionManager manager = new TransactionManager();
        MedicineDAO medicineDAO = DAOFactory.getDAOFactory().getMedicineDAO();
        manager.beginTransaction(medicineDAO);
        Set<Medicine> medicines;
        try {
            medicines = medicineDAO.findAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            manager.endTransaction();
        }
        return medicines;
    }

}
