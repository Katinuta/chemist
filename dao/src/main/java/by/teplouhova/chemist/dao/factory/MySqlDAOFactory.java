package by.teplouhova.chemist.dao.factory;

import by.teplouhova.chemist.dao.*;
import by.teplouhova.chemist.dao.impl.*;

/**
 * Factory  for creating DAO objects to database management system MySql
 */
public class MySqlDAOFactory extends DAOFactory {

    /** The user DAO. */
    private  UserDAO userDAO=new UserDAOImpl();

    /** The medicine DAO. */
    private  MedicineDAO medicineDAO=new MedicineDAOImpl();

    /** The prescription DAO. */
    private PrescriptionDAO prescriptionDAO=new PrescriptionDAOImpl();

    /** The order DAO. */
    private OrderDAO orderDAO=new OrderDAOImpl();

    /** The release form DAO. */
    private ReleaseFormDAO releaseFormDAO=new ReleaseFormDAOImpl();

    /** The producer DAO. */
    private ProducerDAO producerDAO=new ProducerDAOImpl();

    /** The dosage DAO. */
    private DosageDAO dosageDAO=new DosageDAOImpl();

    /** The prescription detail DAO. */
    private PrescripDetailDAO prescripDetailDAO=new PrescripDetailDAOImpl();

    /** The order detail DAO. */
    private OrderDetailDAO orderDetailDAO= new OrderDetailDAOImpl();

    /**
     * Gets the user DAO.
     *
     * @return the user DAO
     */
    @Override
    public UserDAO getUserDAO() {
        return this.userDAO;
    }

    /**
     * Gets the medicine DAO.
     *
     * @return the medicine DAO
     */
    @Override
    public MedicineDAO getMedicineDAO() {
        return this.medicineDAO;
    }

    /**
     * Gets the prescription DAO.
     *
     * @return the prescription DAO
     */
    @Override
    public PrescriptionDAO getPrescriptionDAO() {
        return this.prescriptionDAO;
    }

    /**
     * Gets the order DAO.
     *
     * @return the order DAO
     */
    @Override
    public OrderDAO getOrderDAO() {
        return this.orderDAO;
    }

    /**
     * Gets the release form DAO.
     *
     * @return the release form DAO
     */
    @Override
    public ReleaseFormDAO getReleaseFormDAO() {
        return this.releaseFormDAO;
    }

    /**
     * Gets the producer DAO.
     *
     * @return the producer DAO
     */
    @Override
    public ProducerDAO getProducerDAO() {
        return this.producerDAO;
    }

    /**
     * Gets the dosage DAO.
     *
     * @return the dosage DAO
     */
    @Override
    public DosageDAO getDosageDAO() {
        return this.dosageDAO;
    }

    /**
     * Gets the prescrip detail DAO.
     *
     * @return the prescrip detail DAO
     */
    @Override
    public PrescripDetailDAO getPrescripDetailDAO() {
        return this.prescripDetailDAO;
    }

    /**
     * Gets the order detail DAO.
     *
     * @return the order detail DAO
     */
    @Override
    public OrderDetailDAO getOrderDetailDAO() {
        return this.orderDetailDAO;
    }
}
