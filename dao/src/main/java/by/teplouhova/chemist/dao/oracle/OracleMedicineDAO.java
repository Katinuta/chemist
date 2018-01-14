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
    public ArrayList<Medicine> findByName(String name) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public ArrayList<Medicine> findAll(int bedin, int end) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getCountByName() throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getBalanceById(long id) throws DAOException {
        return 0;
    }

    @Override
    public Medicine findByIdEdit(long id) throws DAOException {
        return null;
    }

    @Override
    public ArrayList<String> findUnitsInPack() throws DAOException {
        return null;
    }

    @Override
    public HashSet<Long> findAllId() throws DAOException {
        return null;
    }

    @Override
    public void delete(Medicine medicine) throws DAOException {

    }
}
