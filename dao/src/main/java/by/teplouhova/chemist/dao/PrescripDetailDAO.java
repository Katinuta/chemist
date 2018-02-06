package by.teplouhova.chemist.dao;

import by.teplouhova.chemist.entity.impl.PrescriptionDetail;

import java.util.List;

public abstract class PrescripDetailDAO extends AbstractDAO<PrescriptionDetail> {

    public  abstract List<PrescriptionDetail> findAllByPrescriptionId(long id) throws DAOException;
    public abstract PrescriptionDetail findByPrescripIdMedicineId(long prescriptionId, long medicineId) throws DAOException;
    public abstract PrescriptionDetail findByUserIdMedicineId(long userId, long medicineId) throws DAOException;
}
