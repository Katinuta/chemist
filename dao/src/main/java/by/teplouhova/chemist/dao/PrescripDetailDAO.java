package by.teplouhova.chemist.dao;

import by.teplouhova.chemist.dao.exception.DAOException;
import by.teplouhova.chemist.entity.impl.PrescriptionDetail;

import java.util.List;

public abstract class PrescripDetailDAO extends AbstractDAO<PrescriptionDetail> {

    public  abstract List<PrescriptionDetail> findAllByPrescriptionId(long id) throws DAOException;
}
