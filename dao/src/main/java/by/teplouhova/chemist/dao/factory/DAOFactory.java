package by.teplouhova.chemist.dao.factory;

import by.teplouhova.chemist.dao.MedicineDAO;
import by.teplouhova.chemist.dao.PrescriptionDAO;
import by.teplouhova.chemist.dao.UserDAO;
import by.teplouhova.chemist.entity.impl.Prescription;

public abstract class DAOFactory {

    private static DAOFactory factory;

    protected DAOFactory() {
    }

    public abstract UserDAO getUserDAO();

    public abstract MedicineDAO getMedicineDAO();

    public abstract PrescriptionDAO getPrescriptionDAO();

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
