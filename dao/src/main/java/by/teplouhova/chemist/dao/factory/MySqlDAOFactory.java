package by.teplouhova.chemist.dao.factory;

import by.teplouhova.chemist.dao.*;
import by.teplouhova.chemist.dao.impl.*;

public class MySqlDAOFactory extends DAOFactory {

    private  UserDAO userDAO=new UserDAOImpl();
    private  MedicineDAO medicineDAO=new MedicineDAOImpl();
    private PrescriptionDAO prescriptionDAO=new PrescriptionDAOImpl();
    private OrderDAO orderDAO=new OrderDAOImpl();
    private ReleaseFormDAO releaseFormDAO=new ReleaseFormDAOImpl();
    private ProducerDAO producerDAO=new ProducerDAOImpl();
    private DosageDAO dosageDAO=new DosageDAOImpl();
    private PrescripDetailDAO prescripDetailDAO=new PrescripDetailDAOImpl();
    private OrderDetailDAO orderDetailDAO= new OrderDetailDAOImpl();

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
