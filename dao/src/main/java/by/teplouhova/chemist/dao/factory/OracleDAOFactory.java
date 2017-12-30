package by.teplouhova.chemist.dao.factory;

import by.teplouhova.chemist.dao.MedicineDAO;
import by.teplouhova.chemist.dao.PrescriptionDAO;
import by.teplouhova.chemist.dao.UserDAO;
import by.teplouhova.chemist.dao.oracle.OracleMedicineDAO;
import by.teplouhova.chemist.dao.oracle.OracleUserDAO;

public class OracleDAOFactory extends DAOFactory {
    @Override
    public UserDAO getUserDAO() {
        return new OracleUserDAO();
    }

    @Override
    public MedicineDAO getMedicineDAO() {
        return new OracleMedicineDAO();
    }

    @Override
    public PrescriptionDAO getPrescriptionDAO() {
        return null;
    }
}
