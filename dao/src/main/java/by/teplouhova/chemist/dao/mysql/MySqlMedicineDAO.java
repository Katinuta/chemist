package by.teplouhova.chemist.dao.mysql;

import by.teplouhova.chemist.dao.AbstractDAO;
import by.teplouhova.chemist.dao.MedicineDAO;
import by.teplouhova.chemist.dao.exception.DAOException;
import by.teplouhova.chemist.entity.impl.Medicine;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class MySqlMedicineDAO extends MedicineDAO {

    private static final String SELECT_MEDICINE_BY_NAME = "SELECT m_medicine_id, CONCAT(medicine.m_name,' ',release_form.rf_name,' ', " +
            "IFNULL( IF(dosage.d_dosage_size > 1, " +
            " FLOOR(dosage.d_dosage_size), dosage.d_dosage_size),''), " +
            "IFNULL(dosage.d_dosage_unit,'' ),' ', " +
            "           IF(unit_in_package.un_unit_in_package_name ='шт', " +
            "CONCAT('N',  medicine.m_quantity_in_pack), " +
            "CONCAT(medicine.m_quantity_in_pack, unit_in_package.un_unit_in_package_name,' N1'))) " +
            "AS fullname,m_price, m_quantity_packages,m_is_need_recipe " +
            "FROM chemist.medicine " +
            "JOIN chemist.release_form USING(rf_release_form_id)" +
            "    JOIN chemist.unit_in_package USING(un_unit_in_package_id)" +
            "LEFT JOIN chemist.dosage USING(d_dosage_id)" +
            "    WHERE m_name=?" +
            "ORDER BY medicine.m_name";
    private static final String SELECT_ALL_MEDICINES="SELECT m_medicine_id, CONCAT(medicine.m_name,' ',release_form.rf_name,' ', " +
            "IFNULL( IF(dosage.d_dosage_size > 1, " +
            " FLOOR(dosage.d_dosage_size), dosage.d_dosage_size),''), " +
            "IFNULL(dosage.d_dosage_unit,'' ),' ', " +
            "           IF(unit_in_package.un_unit_in_package_name ='шт', " +
            "CONCAT('N',  medicine.m_quantity_in_pack), " +
            "CONCAT(medicine.m_quantity_in_pack, unit_in_package.un_unit_in_package_name,' N1'))) " +
            "AS fullname,m_price, m_quantity_packages,m_is_need_recipe " +
            "FROM chemist.medicine " +
            "JOIN chemist.release_form USING(rf_release_form_id)" +
            "    JOIN chemist.unit_in_package USING(un_unit_in_package_id)" +
            "LEFT JOIN chemist.dosage USING(d_dosage_id)" +
                   "ORDER BY medicine.m_name";

    @Override
    public Medicine findById(long id) {
        return null;
    }

    @Override
    public void create(Medicine entity) {

    }

    @Override
    public void update(Medicine entity) {

    }

    public Set<Medicine> findMedicineByName(String name) throws DAOException {
        PreparedStatement statement=null ;
        Set<Medicine> medicines=new TreeSet<>(Comparator.comparing(Medicine::getName));
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
        return medicines;
    }

    @Override
    public Set<Medicine> findAll() throws DAOException {
        Statement statement ;
        Set<Medicine> medicines=new TreeSet<>(Comparator.comparing(Medicine::getName));
        try {
            statement=connection.createStatement();
            ResultSet result=statement.executeQuery(SELECT_ALL_MEDICINES);
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
            throw new DAOException(e);
        }
        return medicines;
    }
}
