package by.teplouhova.chemist.dao;

import by.teplouhova.chemist.dao.exception.DAOException;
import by.teplouhova.chemist.entity.impl.Medicine;

import java.util.HashSet;
import java.util.Set;

public abstract class MedicineDAO extends AbstractDAO<Medicine> {
    public abstract Set<Medicine> findMedicineByName(String name) throws DAOException;
    public abstract Set<Medicine> findAll() throws DAOException;
}
