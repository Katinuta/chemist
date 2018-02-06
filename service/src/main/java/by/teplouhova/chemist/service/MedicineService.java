package by.teplouhova.chemist.service;

import by.teplouhova.chemist.dao.DosageDAO;
import by.teplouhova.chemist.dao.MedicineDAO;
import by.teplouhova.chemist.dao.TransactionManager;
import by.teplouhova.chemist.dao.DAOException;
import by.teplouhova.chemist.entity.impl.Dosage;
import by.teplouhova.chemist.entity.impl.Medicine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;

public class MedicineService {
    private  static final int  RECORDS_PER_PAGE=10;
    private static final Logger LOGGER = LogManager.getLogger();
    private MedicineDAO medicineDAO;
    private DosageDAO dosageDAO;

    public MedicineService(MedicineDAO medicineDAO, DosageDAO dosageDAO) {
        this.medicineDAO = medicineDAO;
        this.dosageDAO = dosageDAO;
    }

    public ArrayList<Medicine> getMedicinesByName(String name) throws ServiceException {
        TransactionManager manager = new TransactionManager();
        ArrayList<Medicine> medicines;
        manager.beginTransaction(medicineDAO);
        try {
            medicines = medicineDAO.findByName(name);
        } catch (DAOException e) {
            throw new ServiceException("Medicine is not found by name: "+name,e);
        } finally {
            manager.endTransaction();
        }
        return medicines;
    }

    public ArrayList<Medicine> getMedicinesPerPage(int currentPage, int[] countPages, boolean isRelevance) throws ServiceException {
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(medicineDAO);
        ArrayList<Medicine> medicines;
        try {
            int begin=(currentPage-1)*RECORDS_PER_PAGE;
            if(isRelevance){
                medicines=medicineDAO.findAllByRelevance(begin,RECORDS_PER_PAGE);
                countPages[0]= (int) Math.ceil(medicineDAO.getCountByNameByRelevance()/(double)RECORDS_PER_PAGE);
            }else {
                medicines = medicineDAO.findAll(begin,RECORDS_PER_PAGE);
                countPages[0]= (int) Math.ceil(medicineDAO.getCountByName()/(double)RECORDS_PER_PAGE);
            }

        } catch (DAOException e) {
            throw new ServiceException("Medicines on page are not found page "+ currentPage,e);
        } finally {
            manager.endTransaction();
        }
        return medicines;
    }

      public Medicine getMedicine(long id) throws ServiceException {

        TransactionManager manager=new TransactionManager();
        manager.beginTransaction(medicineDAO);
        Medicine medicine;
        try {
            medicine = medicineDAO.findById(id);
            if(medicine==null){
                throw new ServiceException("Medicine  is not found by id: "+id);
            }
        } catch (DAOException e) {
            throw new ServiceException("Medicine  is not found by id: "+id,e);
        }finally {
            manager.endTransaction();
        }

        return medicine;
    }

    public int getMedicineBalance(long id) throws ServiceException {
        TransactionManager manager=new TransactionManager();
        manager.beginTransaction(medicineDAO);
        int balance;
        try {
            balance=medicineDAO.getBalanceById(id);
        } catch (DAOException e) {
            throw new ServiceException("Medicine balance is not found by id: "+id,e);
        }finally {
            manager.endTransaction();
        }
        return balance;
    }

    public  Medicine getMedicineEdit(long id) throws ServiceException {
        TransactionManager manager=new TransactionManager();
        manager.beginTransaction(medicineDAO);
        Medicine medicine;
        try {
            medicine=medicineDAO.findByIdEdit(id);
            if(medicine==null){
                throw new ServiceException("Medicine for editing is not found by id: "+id);
            }
        } catch (DAOException e) {
            throw new ServiceException("Medicine for editing is not found by id: "+id,e);
        }finally {
            manager.endTransaction();
        }
        return medicine;
    }


    public ArrayList<String> getUnitsInPackage() throws ServiceException {
        TransactionManager manager=new TransactionManager();
        manager.beginTransaction(medicineDAO);
        ArrayList<String> unitsInPack;
        try {
            unitsInPack=medicineDAO.findUnitsInPack();
            if(unitsInPack==null){
                throw new ServiceException("Name units in package is not found");
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }finally {
            manager.endTransaction();
        }
        return unitsInPack;
    }
    public HashSet<Long> getMedicinesId() throws ServiceException {
        TransactionManager manager=new TransactionManager();
        manager.beginTransaction(medicineDAO);
        HashSet<Long> setIds;
        try {
            setIds=medicineDAO.findAllId();
            if(setIds==null){
                throw new ServiceException("Id of medicines are not found");
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }finally {
            manager.endTransaction();
        }
        return setIds;
    }

    public void update(Medicine medicine) throws ServiceException {
        TransactionManager manager=new TransactionManager();
        manager.beginTransaction(medicineDAO,dosageDAO);
        try{
            BigDecimal dosageSize=medicine.getDosage().getSize();
            String dosageUnit=medicine.getDosage().getUnit();

            if(dosageUnit!=null&&dosageSize!=null){
                Dosage dosage = dosageDAO.findIdBySizeUnit(dosageSize, dosageUnit);
                if ( dosage==null) {
                    dosage=new Dosage(dosageSize, dosageUnit);
                    dosageDAO.create(dosage);
                }
                medicine.setDosage(dosage);
            }

            medicineDAO.update(medicine);
            manager.commit();

        }catch (DAOException e){
            manager.rollback();
            throw new ServiceException("Medicine is not updated: "+medicine.getMedicineId(), e);
        }finally{
            manager.endTransaction();
        }

    }
    public void create(Medicine medicine) throws ServiceException {
        TransactionManager manager = new TransactionManager();

        try {
            manager.beginTransaction(medicineDAO,dosageDAO);
            BigDecimal dosageSize = medicine.getDosage().getSize();
            String dosageUnit = medicine.getDosage().getUnit();
            if(dosageUnit!=null&&dosageSize!=null){
                Dosage dosage = dosageDAO.findIdBySizeUnit(dosageSize, dosageUnit);
                if (dosage == null) {
                    dosage=new Dosage(dosageSize, dosageUnit);
                    dosageDAO.create(dosage);
                }
                medicine.setDosage(dosage);
            }
            medicineDAO.create(medicine);
            manager.commit();

        } catch (DAOException e) {
            manager.rollback();
            throw new ServiceException("Medicine is not added " , e);
        } finally {
            manager.endTransaction();
        }
    }


    public void  delete (Medicine medicine) throws ServiceException {
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(medicineDAO);
        try {
            medicine=medicineDAO.findByIdEdit(medicine.getMedicineId());
            if(medicine!=null){
                medicine.setDeleted(true);
                medicineDAO.update(medicine);
            }else{
                throw new ServiceException("Medicine for deleting is not found");
            }
        } catch (DAOException e) {
            throw new ServiceException("Medicine is not deleted",e);
        }finally {
            manager.endTransaction();
        }
    }

    public ArrayList<Medicine> getMedicinesByPrescrip(boolean isNeedPrescription) throws ServiceException {
        TransactionManager manager = new TransactionManager();
        ArrayList<Medicine> medicines;
        manager.beginTransaction(medicineDAO);
        try {
            medicines = medicineDAO.findByPrescripNeed(isNeedPrescription);
            if(medicines==null){
                //todo check  need
                throw new ServiceException("Medicines by prescription are not found");
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            manager.endTransaction();
        }
        return medicines;
    }

    public boolean isCountAvailable(long medicineId,int count) throws ServiceException {
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(medicineDAO);
        try {
            int balance=medicineDAO.getBalanceById(medicineId);
            if(balance<count){
                return false;
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }finally {
            manager.endTransaction();
        }
        return true;
    }

}
