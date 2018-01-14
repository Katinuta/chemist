package by.teplouhova.chemist.dao.mysql;

import by.teplouhova.chemist.dao.AbstractDAO;
import by.teplouhova.chemist.dao.DosageDAO;
import by.teplouhova.chemist.dao.exception.DAOException;
import by.teplouhova.chemist.entity.impl.Dosage;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

public class MySqlDosageDAO extends DosageDAO {
    private final static String SQL_INSERT_DOSAGE =
            "INSERT INTO chemist.dosage (d_dosage_size, d_dosage_unit) VALUES ( ?, ?)";
    private final static String SQL_SELECT_UNITS_DOSAGE="SELECT DISTINCT dosage.d_dosage_unit FROM chemist.dosage " +
            "ORDER BY d_dosage_unit";
    private final static String SQL_SELECT_DOSAGE_BY_SIZE_UNIT=
            "SELECT d_dosage_id,d_dosage_size,d_dosage_unit FROM chemist.dosage " +
                    "WHERE d_dosage_size=? AND d_dosage_unit=?";

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

    @Override
    public ArrayList<String> findDosageUnits() throws DAOException {
        PreparedStatement statement=null;
        ArrayList<String> dosageUnits=new ArrayList<>();
        try {
            statement=connection.prepareStatement(SQL_SELECT_UNITS_DOSAGE);
            ResultSet result=statement.executeQuery();
            while(result.next()){
                dosageUnits.add(result.getString("d_dosage_unit"));
            }
            if(dosageUnits.isEmpty()){
                return null;
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }finally {
            close(statement);
        }
        return dosageUnits;
    }

    @Override
    public Dosage findIdBySizeUnit(BigDecimal dosageSize, String dosageUnit) throws DAOException {
        PreparedStatement statement=null;
        Dosage dosage=null;
        try {
            statement=connection.prepareStatement(SQL_SELECT_DOSAGE_BY_SIZE_UNIT);
            statement.setBigDecimal(1,dosageSize);
            statement.setString(2,dosageUnit);
            ResultSet result=statement.executeQuery();
            if(result.next()){
                dosage=new Dosage();
                dosage.setDosageId(result.getLong("d_dosage_id"));
                dosage.setSize(result.getBigDecimal("d_dosage_size"));
                dosage.setUnit(result.getString("d_dosage_unit"));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }finally {
            close(statement);
        }

        return dosage;
    }
}
