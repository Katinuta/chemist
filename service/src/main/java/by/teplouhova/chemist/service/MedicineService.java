package by.teplouhova.chemist.service;

import by.teplouhova.chemist.dao.factory.DAOFactory;
import by.teplouhova.chemist.dao.MedicineDAO;
import by.teplouhova.chemist.dao.TransactionManager;
import by.teplouhova.chemist.dao.exception.DAOException;
import by.teplouhova.chemist.entity.impl.Medicine;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MedicineService {
    private final static Logger LOGGER = LogManager.getLogger();
    private  static final int  RECORDS_PER_PAGE=15;

    public ArrayList<Medicine> getMedicinesByName(String name) throws ServiceException {
        TransactionManager manager = new TransactionManager();
        MedicineDAO medicineDAO = DAOFactory.getDAOFactory().getMedicineDAO();
        ArrayList<Medicine> medicines;
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

    public ArrayList<Medicine> getMedicines(int numberPage,int[] countPages) throws ServiceException {
        TransactionManager manager = new TransactionManager();
        MedicineDAO medicineDAO = DAOFactory.getDAOFactory().getMedicineDAO();
        manager.beginTransaction(medicineDAO);
        ArrayList<Medicine> medicines;
        try {
            int begin=(numberPage-1)*RECORDS_PER_PAGE;

            medicines = medicineDAO.findAll(begin,RECORDS_PER_PAGE);
            countPages[0]= (int) Math.ceil(medicineDAO.getMedicineCountByName()/(double)RECORDS_PER_PAGE);
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            manager.endTransaction();
        }
        return medicines;
    }

//    public int getCountPages(){
//
//    }

}
