package by.teplouhova.chemist.dao.factory;

import by.teplouhova.chemist.dao.MedicineDAO;
import by.teplouhova.chemist.dao.PrescriptionDAO;
import by.teplouhova.chemist.dao.UserDAO;
import by.teplouhova.chemist.dao.mysql.MySqlPrescriptionDAO;
import by.teplouhova.chemist.dao.mysql.MySqlMedicineDAO;
import by.teplouhova.chemist.dao.mysql.MySqlUserDAO;

public class MySqlDAOFactory extends DAOFactory {
    @Override
    public UserDAO getUserDAO() {
        return new MySqlUserDAO();
    }

    @Override
    public MedicineDAO getMedicineDAO() {
        return new MySqlMedicineDAO();
    }

    @Override
    public PrescriptionDAO getPrescriptionDAO() {
        return new MySqlPrescriptionDAO();
    }
}
