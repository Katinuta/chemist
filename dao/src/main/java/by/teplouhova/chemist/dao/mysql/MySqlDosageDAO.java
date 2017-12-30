package by.teplouhova.chemist.dao.mysql;

import by.teplouhova.chemist.dao.AbstractDAO;
import by.teplouhova.chemist.dao.exception.DAOException;
import by.teplouhova.chemist.entity.impl.Dosage;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySqlDosageDAO extends AbstractDAO<Dosage> {
    private final static String SQL_INSERT_DOSAGE =
            "INSERT INTO chemist.dosage (d_dosage_size, d_dosage_unit) VALUES ( ?, ?)";

    @Override
    public Dosage findById(long id) {
        return null;
    }

    @Override
    public void create(Dosage entity) throws DAOException {
        PreparedStatement statement=null;
        try {
            statement=connection.prepareStatement(SQL_INSERT_DOSAGE);
            statement.setBigDecimal(1,entity.getSize());
            statement.setString(2,entity.getUnit());
        } catch (SQLException e) {
            throw new DAOException(e) ;
        }finally {
            close(statement);
        }

    }

    @Override
    public void update(Dosage entity) {

    }
}
