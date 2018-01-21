package by.teplouhova.chemist.dao.factory;

import by.teplouhova.chemist.dao.*;
import by.teplouhova.chemist.dao.mysql.MySqlMedicineDAO;
import by.teplouhova.chemist.dao.mysql.MySqlPrescriptionDAO;
import by.teplouhova.chemist.dao.mysql.MySqlUserDAO;
import by.teplouhova.chemist.dao.oracle.OracleMedicineDAO;
import by.teplouhova.chemist.dao.oracle.OraclePrescriptionDAO;
import by.teplouhova.chemist.dao.oracle.OracleReleaseFormDAO;
import by.teplouhova.chemist.dao.oracle.OracleUserDAO;

public class OracleDAOFactory extends DAOFactory {

    private UserDAO userDAO = new OracleUserDAO();
    private MedicineDAO medicineDAO = new OracleMedicineDAO();
    private PrescriptionDAO prescriptionDAO = new OraclePrescriptionDAO();
    private ReleaseFormDAO releaseFormDAO=new OracleReleaseFormDAO();


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

    @Override
    public OrderDAO getOrderDAO() {
        return null;
    }

    @Override
    public ReleaseFormDAO getReleaseFormDAO() {
        return this.releaseFormDAO;
    }

    @Override
    public ProducerDAO getProducerDAO() {
        return null;
    }

    @Override
    public DosageDAO getDosageDAO() {
        return null;
    }

    @Override
    public PrescripDetailDAO getPrescripDetailDAO() {
        return null;
    }

    @Override
    public OrderDetailDAO getOrderDetailDAO() {
        return null;
    }
}
