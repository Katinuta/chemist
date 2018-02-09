package by.teplouhova.chemist.dao.factory;

import by.teplouhova.chemist.dao.*;
/**
 * A factory for creating DAO objects to database management system ORACLE
 */
public class OracleDAOFactory extends DAOFactory {

    /** The user DAO. */
    private UserDAO userDAO;

    /** The medicine DAO. */
    private MedicineDAO medicineDAO;

    /** The prescription DAO. */
    private PrescriptionDAO prescriptionDAO ;

    /** The release form DAO. */
    private ReleaseFormDAO releaseFormDAO;


    /**
     * Gets the user DAO.
     *
     * @return the user DAO
     */
    @Override
    public UserDAO getUserDAO() {
        throw new UnsupportedOperationException();
    }

    /**
     * Gets the medicine DAO.
     *
     * @return the medicine DAO
     */
    @Override
    public MedicineDAO getMedicineDAO() {
        throw new UnsupportedOperationException();
    }

    /**
     * Gets the prescription DAO.
     *
     * @return the prescription DAO
     */
    @Override
    public PrescriptionDAO getPrescriptionDAO() {
        throw new UnsupportedOperationException();
    }

    /**
     * Gets the order DAO.
     *
     * @return the order DAO
     */
    @Override
    public OrderDAO getOrderDAO() {
        throw new UnsupportedOperationException();
    }

    /**
     * Gets the release form DAO.
     *
     * @return the release form DAO
     */
    @Override
    public ReleaseFormDAO getReleaseFormDAO() {
        throw new UnsupportedOperationException();
    }

    /**
     * Gets the producer DAO.
     *
     * @return the producer DAO
     */
    @Override
    public ProducerDAO getProducerDAO() {
        throw new UnsupportedOperationException();
    }

    /**
     * Gets the dosage DAO.
     *
     * @return the dosage DAO
     */
    @Override
    public DosageDAO getDosageDAO() {
        throw new UnsupportedOperationException();
    }

    /**
     * Gets the prescrip detail DAO.
     *
     * @return the prescrip detail DAO
     */
    @Override
    public PrescripDetailDAO getPrescripDetailDAO() {
        throw new UnsupportedOperationException();
    }

    /**
     * Gets the order detail DAO.
     *
     * @return the order detail DAO
     */
    @Override
    public OrderDetailDAO getOrderDetailDAO() {
        throw new UnsupportedOperationException();
    }
}

