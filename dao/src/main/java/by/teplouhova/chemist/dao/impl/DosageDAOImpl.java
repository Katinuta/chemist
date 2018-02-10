package by.teplouhova.chemist.dao.impl;

import by.teplouhova.chemist.dao.DosageDAO;
import by.teplouhova.chemist.dao.DAOException;
import by.teplouhova.chemist.entity.impl.Dosage;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * The Class DosageDAOImpl extends from DosageDAO
 */
public class DosageDAOImpl extends DosageDAO {

    /** The Constant SQL_INSERT_DOSAGE. */
    private final static String SQL_INSERT_DOSAGE =
            "INSERT INTO chemist.dosage (d_dosage_size, d_dosage_unit) VALUES ( ?, ?)";

    /** The Constant SQL_SELECT_UNITS_DOSAGE. */
    private final static String SQL_SELECT_UNITS_DOSAGE=
            "SELECT DISTINCT dosage.d_dosage_unit FROM chemist.dosage ORDER BY d_dosage_unit";

    /** The Constant SQL_SELECT_DOSAGE_BY_SIZE_UNIT. */
    private final static String SQL_SELECT_DOSAGE_BY_SIZE_UNIT=
            "SELECT d_dosage_id,d_dosage_size,d_dosage_unit FROM chemist.dosage " +
                    "WHERE d_dosage_size=? AND d_dosage_unit=?";

    /**
     * Find by id dosage.
     *
     * @param id the id
     * @return the dosage
     */
    @Override
    public Dosage findById(long id) {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates the dosage.
     *
     * @param entity the dosage
     * @throws DAOException the DAO exception
     */
    @Override
    public void create(Dosage entity) throws DAOException {
        PreparedStatement statement=null;
        try {
            statement=connection.prepareStatement(SQL_INSERT_DOSAGE,PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setBigDecimal(1,entity.getSize());
            statement.setString(2,entity.getUnit());
            statement.executeUpdate();
            ResultSet result=statement.getGeneratedKeys();
            if(result.next()){
                entity.setDosageId(result.getLong(1));
            }
        } catch (SQLException e) {
            throw new DAOException("Exception in method create",e) ;
        }finally {
            close(statement);
        }

    }

    /**
     * Update dosage.
     *
     * @param entity the dosage
     */
    @Override
    public void update(Dosage entity) {
        throw new UnsupportedOperationException();
    }

    /**
     * Find dosage units.
     *
     * @return the array list
     * @throws DAOException the DAO exception
     */
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
            throw new DAOException("Exception in method findDosageUnits",e);
        }finally {
            close(statement);
        }
        return dosageUnits;
    }

    /**
     * Find id  of dosage by size unit.
     *
     * @param dosageSize the dosage size
     * @param dosageUnit the dosage unit
     * @return the dosage
     * @throws DAOException the DAO exception
     */
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
            throw new DAOException("Exception in method findIdBySizeUnit",e);
        }finally {
            close(statement);
        }

        return dosage;
    }
}
