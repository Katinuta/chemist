package by.teplouhova.chemist.service;

import by.teplouhova.chemist.dao.*;
import by.teplouhova.chemist.dao.factory.DAOFactory;
import by.teplouhova.chemist.dao.exception.DAOException;
import by.teplouhova.chemist.entity.impl.Dosage;
import by.teplouhova.chemist.entity.impl.Medicine;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;

public class MedicineService {
    private final static Logger LOGGER = LogManager.getLogger();
    private  static final int  RECORDS_PER_PAGE=15;

    public ArrayList<Medicine> getMedicinesByName(String name) throws ServiceException {
        TransactionManager manager = new TransactionManager();
        MedicineDAO medicineDAO = DAOFactory.getDAOFactory().getMedicineDAO();
        ArrayList<Medicine> medicines;
        manager.beginTransaction(medicineDAO);
        try {
            medicines = medicineDAO.findByName(name);
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
            countPages[0]= (int) Math.ceil(medicineDAO.getCountByName()/(double)RECORDS_PER_PAGE);
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            manager.endTransaction();
        }
        return medicines;
    }

      public Medicine getMedicine(long id) throws ServiceException {

        TransactionManager manager=new TransactionManager();
        MedicineDAO dao= DAOFactory.getDAOFactory().getMedicineDAO();
        manager.beginTransaction(dao);
        Medicine medicine;
        try {
            medicine = dao.findById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }finally {
            manager.endTransaction();
        }

        return medicine;
    }

    public int getMedicineBalance(long id) throws ServiceException {
        TransactionManager manager=new TransactionManager();
        MedicineDAO medicineDAO=DAOFactory.getDAOFactory().getMedicineDAO();
        manager.beginTransaction(medicineDAO);
        int balance;
        try {
            balance=medicineDAO.getBalanceById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }finally {
            manager.endTransaction();
        }
        return balance;
    }

    public  Medicine getMedicineEdit(long id) throws ServiceException {
        TransactionManager manager=new TransactionManager();
        MedicineDAO medicineDAO=DAOFactory.getDAOFactory().getMedicineDAO();
        manager.beginTransaction(medicineDAO);
        Medicine medicine=null;
        try {
            medicine=medicineDAO.findByIdEdit(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }finally {
            manager.endTransaction();
        }
        return medicine;
    }


    public ArrayList<String> getUnitsInPackage() throws ServiceException {
        TransactionManager manager=new TransactionManager();
        MedicineDAO medicineDAO=DAOFactory.getDAOFactory().getMedicineDAO();
        manager.beginTransaction(medicineDAO);
        ArrayList<String> unitsInPack;
        try {
            unitsInPack=medicineDAO.findUnitsInPack();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }finally {
            manager.endTransaction();
        }
        return unitsInPack;
    }
    public HashSet<Long> getMedicinesId() throws ServiceException {
        TransactionManager manager=new TransactionManager();
        MedicineDAO medicineDAO=DAOFactory.getDAOFactory().getMedicineDAO();
        manager.beginTransaction(medicineDAO);
        HashSet<Long> setIds;
        try {
            setIds=medicineDAO.findAllId();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }finally {
            manager.endTransaction();
        }
        return setIds;
    }

    public void update(Medicine medicine) throws ServiceException {
        TransactionManager manager=new TransactionManager();
        MedicineDAO medicineDAO=DAOFactory.getDAOFactory().getMedicineDAO();
        DosageDAO dosageDAO=DAOFactory.getDAOFactory().getDosageDAO();
        manager.beginTransaction(medicineDAO,dosageDAO);
        try{
            BigDecimal dosageSize=medicine.getDosage().getSize();

            String dosageUnit=medicine.getDosage().getUnit();
            Dosage dosage;
            if(dosageSize==null&&dosageUnit==null){
                dosage=new Dosage();
            }else{
                dosage=dosageDAO.findIdBySizeUnit(dosageSize,dosageUnit);
                if(dosage==null){
                    dosageDAO.create(new Dosage(dosageSize,dosageUnit));
                    dosage=dosageDAO.findIdBySizeUnit(dosageSize,dosageUnit);
                }
            }
            medicine.setDosage(dosage);
            medicineDAO.update(medicine);
            manager.commit();

        }catch (DAOException e){
            manager.rollback();
            throw new ServiceException("Medicine is not updated: "+medicine.getMedicineId()+ e);
        }finally{
            manager.endTransaction();
        }

    }
    public void create(Medicine medicine) throws ServiceException {
        TransactionManager manager = new TransactionManager();
        MedicineDAO medicineDAO = DAOFactory.getDAOFactory().getMedicineDAO();
        DosageDAO dosageDAO = DAOFactory.getDAOFactory().getDosageDAO();
        manager.beginTransaction(medicineDAO, dosageDAO);
        try {
            BigDecimal dosageSize = medicine.getDosage().getSize();
            String dosageUnit = medicine.getDosage().getUnit();
            Dosage dosage = dosageDAO.findIdBySizeUnit(dosageSize, dosageUnit);

            if (dosage == null) {
                dosageDAO.create(new Dosage(dosageSize, dosageUnit));
                dosage = dosageDAO.findIdBySizeUnit(dosageSize, dosageUnit);
            }
            medicine.setDosage(dosage);
            medicineDAO.create(medicine);
            manager.commit();

        } catch (DAOException e) {
            manager.rollback();
            throw new ServiceException("Medicine is not created: " + e);
        } finally {
            manager.endTransaction();
        }
    }


    public void  delete (Medicine medicine) throws ServiceException {
        TransactionManager manager = new TransactionManager();
        MedicineDAO medicineDAO = DAOFactory.getDAOFactory().getMedicineDAO();
        manager.beginTransaction(medicineDAO);
        try {
            medicineDAO.delete(medicine);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }finally {
            manager.endTransaction();
        }
    }




}
