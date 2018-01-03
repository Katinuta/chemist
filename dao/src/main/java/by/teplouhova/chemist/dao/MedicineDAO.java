package by.teplouhova.chemist.dao;

import by.teplouhova.chemist.dao.exception.DAOException;
import by.teplouhova.chemist.entity.impl.Medicine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class MedicineDAO extends AbstractDAO<Medicine> {
    public abstract ArrayList<Medicine> findMedicineByName(String name) throws DAOException;
    public abstract ArrayList<Medicine> findAll(int begin, int end) throws DAOException;
    public abstract int getMedicineCountByName()throws DAOException;
}
