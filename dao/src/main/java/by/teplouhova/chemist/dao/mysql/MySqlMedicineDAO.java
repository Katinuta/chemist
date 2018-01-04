package by.teplouhova.chemist.dao.mysql;

import by.teplouhova.chemist.dao.AbstractDAO;
import by.teplouhova.chemist.dao.MedicineDAO;
import by.teplouhova.chemist.dao.exception.DAOException;
import by.teplouhova.chemist.entity.impl.Medicine;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class MySqlMedicineDAO extends MedicineDAO {
    private final static Logger LOGGER = LogManager.getLogger();

    private static final String SELECT_MEDICINE_BY_NAME = "SELECT m_medicine_id, CONCAT(medicine.m_name,' ',release_form.rf_name,' ', " +
            "IFNULL( IF(dosage.d_dosage_size > 1, " +
            " FLOOR(dosage.d_dosage_size), dosage.d_dosage_size),''), " +
            "IFNULL(dosage.d_dosage_unit,'' ),' ', " +
            "           IF(unit_in_package.un_unit_in_package_name ='шт', " +
            "CONCAT('N',  medicine.m_quantity_in_pack), " +
            "CONCAT(medicine.m_quantity_in_pack, unit_in_package.un_unit_in_package_name,' N1'))) " +
            "AS fullname,m_price, m_quantity_packages,m_is_need_recipe " +
            "FROM chemist.medicine " +
            "JOIN chemist.release_form USING(rf_release_form_id) " +
            "    JOIN chemist.unit_in_package USING(un_unit_in_package_id) " +
            "LEFT JOIN chemist.dosage USING(d_dosage_id) " +
            "    WHERE m_name=? AND m_quantity_packages>0 " +
            "ORDER BY medicine.m_name";
    private static final String SELECT_ALL_MEDICINES="SELECT m_medicine_id, CONCAT(medicine.m_name,' ',release_form.rf_name,' ', \n" +
            "IFNULL( IF(dosage.d_dosage_size > 1, " +
            "            FLOOR(dosage.d_dosage_size), dosage.d_dosage_size),''), " +
            "            IFNULL(dosage.d_dosage_unit,'' ),' ', \n" +
            "                       IF(unit_in_package.un_unit_in_package_name ='шт', " +
            "            CONCAT('N',  medicine.m_quantity_in_pack), " +
            "            CONCAT(medicine.m_quantity_in_pack, unit_in_package.un_unit_in_package_name,' N1'))) " +
            "            AS fullname,m_price, m_quantity_packages,m_is_need_recipe " +
            "            FROM chemist.medicine " +
            "            JOIN chemist.release_form USING(rf_release_form_id) " +
            "           JOIN chemist.unit_in_package USING(un_unit_in_package_id) " +
            "            LEFT JOIN chemist.dosage USING(d_dosage_id) " +
            "            WHERE m_quantity_packages>0 " +
            "                              ORDER BY fullname " +
            "                  LIMIT ?,?;";
    private static final String SELECT_MEDICINE_BY_ID="SELECT m_medicine_id, CONCAT(medicine.m_name,' ',release_form.rf_name,' ', "+
            "IFNULL( IF(dosage.d_dosage_size > 1, "+
            "            FLOOR(dosage.d_dosage_size), dosage.d_dosage_size),''), "+
            "            IFNULL(dosage.d_dosage_unit,'' ),' ', "+
            "                       IF(unit_in_package.un_unit_in_package_name ='шт', "+
            "            CONCAT('N',  medicine.m_quantity_in_pack), "+
            "            CONCAT(medicine.m_quantity_in_pack, unit_in_package.un_unit_in_package_name,' N1'))) "+
            "            AS fullname,m_price, m_quantity_packages,m_is_need_recipe "+
            "            FROM chemist.medicine "+
            "            JOIN chemist.release_form USING(rf_release_form_id) "+
            "           JOIN chemist.unit_in_package USING(un_unit_in_package_id) "+
            "            LEFT JOIN chemist.dosage USING(d_dosage_id) "+
            "           WHERE m_medicine_id=?";
    private static final String SELECT_ALL_MEDICINES_COUNT_BY_NAME="SELECT count(m_name)  as count FROM chemist.medicine WHERE m_quantity_packages>0";

    @Override
    public Medicine findById(long id) throws DAOException {
        PreparedStatement statement = null;
        Medicine medicine=null;
        try {
            statement = connection.prepareStatement(SELECT_MEDICINE_BY_ID);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                medicine = new Medicine();
                medicine.setMedicineId(result.getLong("m_medicine_id"));
                medicine.setName(result.getString("fullname"));
                medicine.setPrice(result.getBigDecimal("m_price"));
                medicine.setQuantityPackages(result.getInt("m_quantity_packages"));
                medicine.setNeedRecipe(result.getBoolean("m_is_need_recipe"));

            }
            LOGGER.log(Level.DEBUG, medicine);
        } catch (SQLException e) {
            throw new DAOException("" + e);
        } finally {
            close(statement);

        }
        return medicine;
    }

    @Override
    public void create(Medicine entity) {

    }

    @Override
    public void update(Medicine entity) {

    }

    public ArrayList<Medicine> findMedicineByName(String name) throws DAOException {
        PreparedStatement statement=null ;
        ArrayList<Medicine> medicines=new ArrayList<>();
        try {
            statement=connection.prepareStatement(SELECT_MEDICINE_BY_NAME);
            statement.setString(1,name);
            ResultSet result=statement.executeQuery();
            while(result.next()){
                Medicine medicine=new Medicine();
                medicine.setMedicineId(result.getLong("m_medicine_id"));
                medicine.setName(result.getString("fullname"));
                medicine.setPrice(result.getBigDecimal("m_price"));
                medicine.setQuantityPackages(result.getInt("m_quantity_packages"));
                medicine.setNeedRecipe(result.getBoolean("m_is_need_recipe"));
                medicines.add(medicine);
            }
            if(medicines.isEmpty()){
                medicines=null;
            }
        } catch (SQLException e) {
            throw new DAOException(""+ e);
        } finally {
            close(statement);
        }
//        medicines.sort(Comparator.comparing(Medicine::getName));
        return medicines;
    }

    @Override
    public ArrayList<Medicine> findAll(int begin, int end) throws DAOException {
        PreparedStatement statement ;
        ArrayList<Medicine> medicines=new ArrayList<>();
        try {
            statement=connection.prepareStatement(SELECT_ALL_MEDICINES);
            statement.setInt(1,begin);
            statement.setInt(2,end);
            ResultSet result=statement.executeQuery();
            while(result.next()){
                Medicine medicine=new Medicine();
                medicine.setMedicineId(result.getLong("m_medicine_id"));
                medicine.setName(result.getString("fullname"));
                medicine.setPrice(result.getBigDecimal("m_price"));
                medicine.setQuantityPackages(result.getInt("m_quantity_packages"));
                medicine.setNeedRecipe(result.getBoolean("m_is_need_recipe"));
                medicines.add(medicine);
            }
            if(medicines.isEmpty()){
                medicines=null;
            }
        } catch (SQLException e) {
            throw new DAOException("findAll(int begin, int end)"+e);
        }
        return medicines;
    }

    @Override
    public int getMedicineCountByName() throws DAOException {

        Statement statement;
        Integer count=0;
        try {
            statement =connection.createStatement();
            ResultSet result=statement.executeQuery(SELECT_ALL_MEDICINES_COUNT_BY_NAME);
            if(result.next()){
                count=result.getInt("count");
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return count;
    }
}
