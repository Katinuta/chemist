package by.teplouhova.chemist.dao.factory;

import by.teplouhova.chemist.dao.*;

public abstract class DAOFactory {

    private static DAOFactory factory;

    protected DAOFactory() {
    }

    public abstract UserDAO getUserDAO();

    public abstract MedicineDAO getMedicineDAO();
    public abstract PrescriptionDAO getPrescriptionDAO();
    public abstract OrderDAO getOrderDAO();
    public abstract ReleaseFormDAO getReleaseFormDAO();
    public abstract ProducerDAO getProducerDAO();
    public abstract DosageDAO getDosageDAO();
    public abstract PrescripDetailDAO getPrescripDetailDAO();


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

    public static DAOFactory getDAOFactory() {
        if (factory == null) {
            factory = new MySqlDAOFactory();
        }
        return factory;
    }
}
