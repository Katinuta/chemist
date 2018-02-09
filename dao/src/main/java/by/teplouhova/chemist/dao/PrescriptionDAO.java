package by.teplouhova.chemist.dao;

import by.teplouhova.chemist.entity.impl.Prescription;

import java.util.List;

/**
 * The Class PrescriptionDAO.
 */
public abstract class PrescriptionDAO extends AbstractDAO<Prescription> {

    /**
     * Find prescription by client id.
     *
     * @param clientId the client id
     * @return the list
     * @throws DAOException the DAO exception
     */
    public abstract List<Prescription> findPrescriptionByClientId(long clientId) throws DAOException;

    /**
     * Find prescription by doctor id.
     *
     * @param doctorId the doctor id
     * @return the list
     * @throws DAOException the DAO exception
     */
    public abstract List<Prescription> findPrescriptionByDoctorId(long doctorId) throws DAOException;

    /**
     * Find prescrip by doctor id extand.
     *
     * @param doctorId the doctor id
     * @return the list
     * @throws DAOException the DAO exception
     */
    public abstract List<Prescription> findPrescripByDoctorIdExtand(long doctorId) throws DAOException;

}
