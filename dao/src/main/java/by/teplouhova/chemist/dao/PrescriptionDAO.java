package by.teplouhova.chemist.dao;

import by.teplouhova.chemist.entity.impl.Prescription;

import java.util.List;

public abstract class PrescriptionDAO extends AbstractDAO<Prescription> {
    public abstract List<Prescription> findPrescriptionByClientId(long clientId) throws DAOException;
    public abstract List<Prescription> findPrescriptionByDoctorId(long doctorId) throws DAOException;
    public abstract List<Prescription> findPrescripByDoctorIdExtand(long doctorId) throws DAOException;

}
