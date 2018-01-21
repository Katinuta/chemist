package by.teplouhova.chemist.dao;

import by.teplouhova.chemist.dao.exception.DAOException;
import by.teplouhova.chemist.entity.impl.Prescription;

import java.util.HashSet;
import java.util.List;

public abstract class PrescriptionDAO extends AbstractDAO<Prescription> {
    public abstract List<Prescription> findPrescriptionByClientId(long clientId) throws DAOException;
    public abstract List<Prescription> findPrescriptionByDoctorId(long doctorId) throws DAOException;


}
