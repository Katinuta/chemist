package by.teplouhova.chemist.dao.oracle;

import by.teplouhova.chemist.dao.PrescriptionDAO;
import by.teplouhova.chemist.dao.exception.DAOException;
import by.teplouhova.chemist.entity.impl.Prescription;

import java.util.HashSet;
import java.util.List;

public class OraclePrescriptionDAO extends PrescriptionDAO {
    @Override
    public HashSet<Prescription> findPrescriptionByClientId(long clientId) throws DAOException {
        return null;
    }

    @Override
    public List<Prescription> findPrescriptionByDoctorId(long doctorId) {
        return null;
    }

    @Override
    public Prescription findById(long id) {
        return null;
    }

    @Override
    public void create(Prescription entity) throws DAOException {

    }

    @Override
    public void update(Prescription entity) throws DAOException {

    }
}
