package by.teplouhova.chemist.dao;

import by.teplouhova.chemist.dao.exception.DAOException;
import by.teplouhova.chemist.entity.impl.Prescription;

import java.util.HashSet;

public abstract class PrescriptionDAO extends AbstractDAO<Prescription> {
    public abstract HashSet<Prescription> findPrescriptionByClientId(long clientId) throws DAOException;
}
