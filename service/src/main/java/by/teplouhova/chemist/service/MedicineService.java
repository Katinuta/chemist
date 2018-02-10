package by.teplouhova.chemist.service;

import by.teplouhova.chemist.dao.DosageDAO;
import by.teplouhova.chemist.dao.MedicineDAO;
import by.teplouhova.chemist.dao.TransactionManager;
import by.teplouhova.chemist.dao.DAOException;
import by.teplouhova.chemist.dao.factory.DAOFactory;
import by.teplouhova.chemist.entity.impl.Dosage;
import by.teplouhova.chemist.entity.impl.Medicine;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;


/**
 * The Class MedicineService.
 */
public class MedicineService {

    /**
     * The Constant RECORDS_PER_PAGE.
     */
    private static final int RECORDS_PER_PAGE = 10;


    /**
     * The medicine DAO.
     */
    private MedicineDAO medicineDAO;

    /**
     * The dosage DAO.
     */
    private DosageDAO dosageDAO;

    /**
     * Instantiates a new medicine service.
     */
    public MedicineService() {
        medicineDAO = DAOFactory.getDAOFactory().getMedicineDAO();
        dosageDAO = DAOFactory.getDAOFactory().getDosageDAO();
    }

    /**
     * Gets the medicines by name.
     *
     * @param name the name
     * @return the medicines by name
     * @throws ServiceException the service exception
     */
    public ArrayList<Medicine> getMedicinesByName(String name) throws ServiceException {
        TransactionManager manager = new TransactionManager();
        ArrayList<Medicine> medicines;
        manager.beginTransaction(medicineDAO);
        try {
            medicines = medicineDAO.findByName(name);
        } catch (DAOException e) {
            throw new ServiceException("Medicine is not found by name: " + name, e);
        } finally {
            manager.endTransaction();
        }
        return medicines;
    }

    /**
     * Gets the medicines per page.
     *
     * @param currentPage the current page
     * @param countPages  the count pages
     * @param isRelevance the is relevance
     * @return the medicines per page
     * @throws ServiceException the service exception
     */
    public ArrayList<Medicine> getMedicinesPerPage(int currentPage, int[] countPages, boolean isRelevance) throws ServiceException {
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(medicineDAO);
        ArrayList<Medicine> medicines;
        try {
            int begin = (currentPage - 1) * RECORDS_PER_PAGE;
            if (isRelevance) {
                medicines = medicineDAO.findAllByRelevance(begin, RECORDS_PER_PAGE);
                countPages[0] = (int) Math.ceil(medicineDAO.getCountByNameByRelevance() / (double) RECORDS_PER_PAGE);
            } else {
                medicines = medicineDAO.findAll(begin, RECORDS_PER_PAGE);
                countPages[0] = (int) Math.ceil(medicineDAO.getCountByName() / (double) RECORDS_PER_PAGE);
            }

        } catch (DAOException e) {
            throw new ServiceException("Medicines on page are not found page " + currentPage, e);
        } finally {
            manager.endTransaction();
        }
        return medicines;
    }

    public Medicine getMedicine(long id) throws ServiceException {

        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(medicineDAO);
        Medicine medicine;
        try {
            medicine = medicineDAO.findById(id);
            if (medicine == null) {
                throw new ServiceException("Medicine  is not found by id: " + id);
            }
        } catch (DAOException e) {
            throw new ServiceException("Medicine  is not found by id: " + id, e);
        } finally {
            manager.endTransaction();
        }

        return medicine;
    }

    /**
     * Gets the medicine.
     *
     * @param id the id
     * @return the medicine
     * @throws ServiceException the service exception
     */
    public int getMedicineBalance(long id) throws ServiceException {
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(medicineDAO);
        int balance;
        try {
            balance = medicineDAO.getBalanceById(id);
        } catch (DAOException e) {
            throw new ServiceException("Medicine balance is not found by id: " + id, e);
        } finally {
            manager.endTransaction();
        }
        return balance;
    }

    /**
     * Gets the medicine balance.
     *
     * @param id the id
     * @return the medicine balance
     * @throws ServiceException the service exception
     */
    public Medicine getMedicineEdit(long id) throws ServiceException {
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(medicineDAO);
        Medicine medicine;
        try {
            medicine = medicineDAO.findByIdEdit(id);
            if (medicine == null) {
                throw new ServiceException("Medicine for editing is not found by id: " + id);
            }
        } catch (DAOException e) {
            throw new ServiceException("Medicine for editing is not found by id: " + id, e);
        } finally {
            manager.endTransaction();
        }
        return medicine;
    }

    /**
     * Gets the units in package.
     *
     * @return the units in package
     * @throws ServiceException the service exception
     */
    public ArrayList<String> getUnitsInPackage() throws ServiceException {
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(medicineDAO);
        ArrayList<String> unitsInPack;
        try {
            unitsInPack = medicineDAO.findUnitsInPack();
            if (unitsInPack == null) {
                throw new ServiceException("Name units in package is not found");
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            manager.endTransaction();
        }
        return unitsInPack;
    }

    /**
     * Gets the medicines id.
     *
     * @return the medicines id
     * @throws ServiceException the service exception
     */
    public HashSet<Long> getMedicinesId() throws ServiceException {
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(medicineDAO);
        HashSet<Long> setIds;
        try {
            setIds = medicineDAO.findAllId();
            if (setIds == null) {
                throw new ServiceException("Id of medicines are not found");
            }
        } catch (DAOException e) {
            throw new ServiceException("Id of medicines are not found", e);
        } finally {
            manager.endTransaction();
        }
        return setIds;
    }

    /**
     * Update.
     *
     * @param medicine the medicine
     * @throws ServiceException the service exception
     */
    public void update(Medicine medicine) throws ServiceException {
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(medicineDAO, dosageDAO);
        try {
            BigDecimal dosageSize = medicine.getDosage().getSize();
            String dosageUnit = medicine.getDosage().getUnit();

            if (dosageUnit != null && dosageSize != null) {
                Dosage dosage = dosageDAO.findIdBySizeUnit(dosageSize, dosageUnit);
                if (dosage == null) {
                    dosage = new Dosage(dosageSize, dosageUnit);
                    dosageDAO.create(dosage);
                }
                medicine.setDosage(dosage);
            }

            medicineDAO.update(medicine);
            manager.commit();

        } catch (DAOException e) {
            manager.rollback();
            throw new ServiceException("Medicine is not updated: " + medicine.getMedicineId(), e);
        } finally {
            manager.endTransaction();
        }

    }

    /**
     * Creates the.
     *
     * @param medicine the medicine
     * @throws ServiceException the service exception
     */
    public void create(Medicine medicine) throws ServiceException {
        TransactionManager manager = new TransactionManager();

        try {
            manager.beginTransaction(medicineDAO, dosageDAO);
            BigDecimal dosageSize = medicine.getDosage().getSize();
            String dosageUnit = medicine.getDosage().getUnit();
            if (dosageUnit != null && dosageSize != null) {
                Dosage dosage = dosageDAO.findIdBySizeUnit(dosageSize, dosageUnit);
                if (dosage == null) {
                    dosage = new Dosage(dosageSize, dosageUnit);
                    dosageDAO.create(dosage);
                }
                medicine.setDosage(dosage);
            }
            medicineDAO.create(medicine);
            manager.commit();

        } catch (DAOException e) {
            manager.rollback();
            throw new ServiceException("Medicine is not added ", e);
        } finally {
            manager.endTransaction();
        }
    }

    /**
     * Delete.
     *
     * @param medicine the medicine
     * @throws ServiceException the service exception
     */
    public void delete(Medicine medicine) throws ServiceException {
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(medicineDAO);
        try {
            medicine = medicineDAO.findByIdEdit(medicine.getMedicineId());
            if (medicine != null) {
                medicine.setDeleted(true);
                medicineDAO.update(medicine);
            } else {
                throw new ServiceException("Medicine for deleting is not found");
            }
        } catch (DAOException e) {
            throw new ServiceException("Medicine is not deleted", e);
        } finally {
            manager.endTransaction();
        }
    }

    /**
     * Gets the medicines by prescription.
     *
     * @param isNeedPrescription the is need prescription
     * @return the medicines by prescription
     * @throws ServiceException the service exception
     */
    public ArrayList<Medicine> getMedicinesByPrescrip(boolean isNeedPrescription) throws ServiceException {
        TransactionManager manager = new TransactionManager();
        ArrayList<Medicine> medicines;
        manager.beginTransaction(medicineDAO);
        try {
            medicines = medicineDAO.findByPrescripNeed(isNeedPrescription);
            if (medicines == null) {
                throw new ServiceException("Medicines by prescription are not found");
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            manager.endTransaction();
        }
        return medicines;
    }


    /**
     * Checks if is count available.
     *
     * @param medicineId the medicine id
     * @param count      the count
     * @return true, if is count available
     * @throws ServiceException the service exception
     */
    public boolean isCountAvailable(long medicineId, int count) throws ServiceException {
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(medicineDAO);
        try {
            int balance = medicineDAO.getBalanceById(medicineId);
            if (balance < count) {
                return false;
            }
        } catch (DAOException e) {
            throw new ServiceException("Can't check medicine availability ", e);
        } finally {
            manager.endTransaction();
        }
        return true;
    }

}
