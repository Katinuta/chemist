package by.teplouhova.chemist.dao.oracle;

import by.teplouhova.chemist.dao.MedicineDAO;
import by.teplouhova.chemist.dao.exception.DAOException;
import by.teplouhova.chemist.entity.impl.Medicine;

import java.util.ArrayList;
import java.util.HashSet;

public class OracleMedicineDAO extends MedicineDAO {
    @Override
    public Medicine findById(long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void create(Medicine entity) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Medicine entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ArrayList<Medicine> findMedicineByName(String name) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public ArrayList<Medicine> findAll(int bedin, int end) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getMedicineCountByName() throws DAOException {
        throw new UnsupportedOperationException();
    }
}
