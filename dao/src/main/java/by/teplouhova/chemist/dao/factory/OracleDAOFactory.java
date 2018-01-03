package by.teplouhova.chemist.dao.factory;

import by.teplouhova.chemist.dao.MedicineDAO;
import by.teplouhova.chemist.dao.PrescriptionDAO;
import by.teplouhova.chemist.dao.UserDAO;
import by.teplouhova.chemist.dao.mysql.MySqlMedicineDAO;
import by.teplouhova.chemist.dao.mysql.MySqlPrescriptionDAO;
import by.teplouhova.chemist.dao.mysql.MySqlUserDAO;
import by.teplouhova.chemist.dao.oracle.OracleMedicineDAO;
import by.teplouhova.chemist.dao.oracle.OraclePrescriptionDAO;
import by.teplouhova.chemist.dao.oracle.OracleUserDAO;

public class OracleDAOFactory extends DAOFactory {

    private UserDAO userDAO = new OracleUserDAO();
    private MedicineDAO medicineDAO = new OracleMedicineDAO();
    private PrescriptionDAO prescriptionDAO = new OraclePrescriptionDAO();

    @Override
    public UserDAO getUserDAO() {
        return this.userDAO;
    }

    @Override
    public MedicineDAO getMedicineDAO() {
        return this.medicineDAO;
    }

    @Override
    public PrescriptionDAO getPrescriptionDAO() {
        return this.prescriptionDAO;
    }
}
