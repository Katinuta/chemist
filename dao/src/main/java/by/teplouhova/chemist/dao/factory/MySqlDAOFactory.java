package by.teplouhova.chemist.dao.factory;

import by.teplouhova.chemist.dao.MedicineDAO;
import by.teplouhova.chemist.dao.PrescriptionDAO;
import by.teplouhova.chemist.dao.UserDAO;
import by.teplouhova.chemist.dao.mysql.MySqlPrescriptionDAO;
import by.teplouhova.chemist.dao.mysql.MySqlMedicineDAO;
import by.teplouhova.chemist.dao.mysql.MySqlUserDAO;

public class MySqlDAOFactory extends DAOFactory {

    private  UserDAO userDAO=new MySqlUserDAO();
    private  MedicineDAO medicineDAO=new MySqlMedicineDAO();
    private PrescriptionDAO prescriptionDAO=new MySqlPrescriptionDAO();

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
