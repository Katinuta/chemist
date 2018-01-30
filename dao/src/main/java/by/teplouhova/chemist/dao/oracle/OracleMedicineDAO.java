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
    public ArrayList<Medicine> findAllByRelevance(int begin, int end) throws DAOException {
        return null;
    }

    @Override
    public int getCountByName() throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getCountByNameByRelevance() throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getBalanceById(long id) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Medicine findByIdEdit(long id) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public ArrayList<String> findUnitsInPack() throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public HashSet<Long> findAllId() throws DAOException {
        throw new UnsupportedOperationException();
    }



    @Override
    public ArrayList<Medicine> findByPrescripNeed(boolean isNeedPrescrip) throws DAOException {
        return null;
    }
}
