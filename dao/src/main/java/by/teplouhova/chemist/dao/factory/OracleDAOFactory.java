package by.teplouhova.chemist.dao.factory;

import by.teplouhova.chemist.dao.*;

public class OracleDAOFactory extends DAOFactory {

    private UserDAO userDAO;
    private MedicineDAO medicineDAO;
    private PrescriptionDAO prescriptionDAO ;
    private ReleaseFormDAO releaseFormDAO;


    @Override
    public UserDAO getUserDAO() {
        throw new UnsupportedOperationException();
    }

    @Override
    public MedicineDAO getMedicineDAO() {
        throw new UnsupportedOperationException();
    }

    @Override
    public PrescriptionDAO getPrescriptionDAO() {
        throw new UnsupportedOperationException();
    }

    @Override
    public OrderDAO getOrderDAO() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ReleaseFormDAO getReleaseFormDAO() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ProducerDAO getProducerDAO() {
        throw new UnsupportedOperationException();
    }

    @Override
    public DosageDAO getDosageDAO() {
        throw new UnsupportedOperationException();
    }

    @Override
    public PrescripDetailDAO getPrescripDetailDAO() {
        throw new UnsupportedOperationException();
    }

    @Override
    public OrderDetailDAO getOrderDetailDAO() {
        throw new UnsupportedOperationException();
    }
}
