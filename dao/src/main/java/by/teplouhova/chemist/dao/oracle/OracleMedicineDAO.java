package by.teplouhova.chemist.dao.oracle;

import by.teplouhova.chemist.dao.MedicineDAO;
import by.teplouhova.chemist.dao.exception.DAOException;
import by.teplouhova.chemist.entity.impl.Medicine;

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
    public HashSet<Medicine> findMedicineByName(String name) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public HashSet<Medicine> findAll() throws DAOException {
        throw new UnsupportedOperationException();
    }
}
