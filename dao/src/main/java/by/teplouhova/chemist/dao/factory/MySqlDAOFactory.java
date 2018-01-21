package by.teplouhova.chemist.dao.factory;

import by.teplouhova.chemist.dao.*;
import by.teplouhova.chemist.dao.mysql.*;

public class MySqlDAOFactory extends DAOFactory {

    private  UserDAO userDAO=new MySqlUserDAO();
    private  MedicineDAO medicineDAO=new MySqlMedicineDAO();
    private PrescriptionDAO prescriptionDAO=new MySqlPrescriptionDAO();
    private OrderDAO orderDAO=new MySqlOrderDAO();
    private ReleaseFormDAO releaseFormDAO=new MySqlReleaseFormDAO();
    private ProducerDAO producerDAO=new MySqlProducerDAO();
    private DosageDAO dosageDAO=new MySqlDosageDAO();
    private PrescripDetailDAO prescripDetailDAO=new MySqlPrescripDetailDAO();
    private OrderDetailDAO orderDetailDAO= new MySqlOrderDetailDAO();

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
        return this.orderDAO;
    }

    @Override
    public ReleaseFormDAO getReleaseFormDAO() {
        return this.releaseFormDAO;
    }

    @Override
    public ProducerDAO getProducerDAO() {
        return this.producerDAO;
    }

    @Override
    public DosageDAO getDosageDAO() {
        return this.dosageDAO;
    }

    @Override
    public PrescripDetailDAO getPrescripDetailDAO() {
        return this.prescripDetailDAO;
    }

    @Override
    public OrderDetailDAO getOrderDetailDAO() {
        return this.orderDetailDAO;
    }
}
