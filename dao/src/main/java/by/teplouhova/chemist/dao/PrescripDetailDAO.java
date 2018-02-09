package by.teplouhova.chemist.dao;

import by.teplouhova.chemist.entity.impl.PrescriptionDetail;

import java.util.List;

/**
 * The Class PrescripDetailDAO.
 */
public abstract class PrescripDetailDAO extends AbstractDAO<PrescriptionDetail> {

    /**
     * Find all by prescription id.
     *
     * @param id the id
     * @return the list
     * @throws DAOException the DAO exception
     */
    public  abstract List<PrescriptionDetail> findAllByPrescriptionId(long id) throws DAOException;

    /**
     * Find by prescrip id medicine id.
     *
     * @param prescriptionId the prescription id
     * @param medicineId the medicine id
     * @return the prescription detail
     * @throws DAOException the DAO exception
     */
    public abstract PrescriptionDetail findByPrescripIdMedicineId(long prescriptionId, long medicineId) throws DAOException;

    /**
     * Find by user id medicine id.
     *
     * @param userId the user id
     * @param medicineId the medicine id
     * @return the prescription detail
     * @throws DAOException the DAO exception
     */
    public abstract PrescriptionDetail findByUserIdMedicineId(long userId, long medicineId) throws DAOException;
}
