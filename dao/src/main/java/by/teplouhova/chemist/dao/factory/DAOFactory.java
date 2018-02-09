package by.teplouhova.chemist.dao.factory;

import by.teplouhova.chemist.dao.*;

/**
 * Abstract class factory for creating DAO objects.
 */
public abstract class DAOFactory {

    /** The DAO factory. */
    private static DAOFactory factory;

    /**
     * Instantiates a new DAO factory.
     */
    protected DAOFactory() {
    }

    /**
     * Gets the user DAO.
     *
     * @return the user DAO
     */
    public abstract UserDAO getUserDAO();
    /**
     * Gets the medicineDAO.
     *
     * @return the medicineDAO
     */
    public abstract MedicineDAO getMedicineDAO();
    /**
     * Gets the PrescriptionDAO.
     *
     * @return the PrescriptionDAO
     */
    public abstract PrescriptionDAO getPrescriptionDAO();
    /**
     * Gets the OrderDAO.
     *
     * @return the OrderDAO
     */
    public abstract OrderDAO getOrderDAO();
    /**
     * Gets the ReleaseFormDAO.
     *
     * @return the ReleaseFormDAO
     */
    public abstract ReleaseFormDAO getReleaseFormDAO();
    /**
     * Gets the ProducerDAO.
     *
     * @return the ProducerDAO
     */
    public abstract ProducerDAO getProducerDAO();
    /**
     * Gets the  DosageDAO.
     *
     * @return the  DosageDAO
     */
    public abstract DosageDAO getDosageDAO();
    /**
     * Gets the  PrescripDetailDAO.
     *
     * @return the  PrescripDetailDAO
     */

    public abstract PrescripDetailDAO getPrescripDetailDAO();
    /**
     * Gets the   OrderDetailDAO .
     *
     * @return the   OrderDetailDAO
     */
    public abstract OrderDetailDAO getOrderDetailDAO();
    /**
     * Gets the DAO factory.
     *
     * @param type the type of database management system
     * @return the DAO factory
     */

    public static DAOFactory getDAOFactory(String type) {
        if (factory == null) {
            switch (type.toUpperCase()) {
                case "MYSQL":
                    factory = new MySqlDAOFactory();
                    break;
                case "ORACLE":
                    factory = new OracleDAOFactory();
                    break;
                default:
                    new UnsupportedOperationException();
            }
        }
        return factory;
    }
    /**
     * Gets the DAO factory.
     *
     * @return the DAO factory to database management system MySql
     */
    public static DAOFactory getDAOFactory() {
        if (factory == null) {
            factory = new MySqlDAOFactory();
        }
        return factory;
    }
}
