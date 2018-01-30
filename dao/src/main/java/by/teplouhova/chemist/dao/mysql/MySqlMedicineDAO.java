package by.teplouhova.chemist.dao.mysql;

import by.teplouhova.chemist.dao.MedicineDAO;
import by.teplouhova.chemist.dao.exception.DAOException;
import by.teplouhova.chemist.entity.impl.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.*;

public class MySqlMedicineDAO extends MedicineDAO {
    private final static Logger LOGGER = LogManager.getLogger();

    private static final String SQL_SELECT_MEDICINE_BY_NAME = "SELECT m_medicine_id, CONCAT(medicine.m_name,' ',release_form.rf_name,' ', " +
            "IFNULL( IF(dosage.d_dosage_size > 1, " +
            "FLOOR(dosage.d_dosage_size), dosage.d_dosage_size),''), " +
            "IFNULL(dosage.d_dosage_unit,'' ),' ', " +
            "IF(m_unit_in_package ='шт', " +
            "CONCAT('N',  medicine.m_quantity_in_pack), " +
            "CONCAT(medicine.m_quantity_in_pack, medicine.m_unit_in_package,' N1')))  " +
            "AS fullname,m_price, m_quantity_packages,m_is_need_prescrip,p_name " +
            "FROM chemist.medicine " +
            "JOIN chemist.release_form USING(rf_release_form_id) " +
            "LEFT JOIN chemist.dosage USING(d_dosage_id) " +
            "JOIN chemist.producer USING(p_producer_id)  " +
            "    WHERE m_name=?  " +
            "ORDER BY medicine.m_name";
    private static final String SQL_SELECT_ALL_MEDICINES = "SELECT m_medicine_id, CONCAT(medicine.m_name,' ',release_form.rf_name,' ', \n" +
            "IFNULL( IF(dosage.d_dosage_size > 1, " +
            "            FLOOR(dosage.d_dosage_size), dosage.d_dosage_size),''), " +
            "            IFNULL(dosage.d_dosage_unit,'' ),' ', " +
            "                        IF(m_unit_in_package ='шт', " +
            "            CONCAT('N',  medicine.m_quantity_in_pack), " +
            "            CONCAT(medicine.m_quantity_in_pack, medicine.m_unit_in_package,' N1')))  " +
            "            AS fullname,m_price, m_quantity_packages,m_is_need_prescrip, p_name, m_is_deleted" +
            "            FROM chemist.medicine " +
            "            JOIN chemist.release_form USING(rf_release_form_id) " +
            "            JOIN chemist.producer USING(p_producer_id) " +
            "            LEFT JOIN chemist.dosage USING(d_dosage_id) " +
            "             " +
            "                              ORDER BY fullname " +
            "                  LIMIT ?,?;";
    private static final String SQL_SELECT_ALL_MEDICINES_BY_RELEVANCE=
            "SELECT m_medicine_id, CONCAT(medicine.m_name,' ',release_form.rf_name,' ', \n" +
            "IFNULL( IF(dosage.d_dosage_size > 1, " +
            "            FLOOR(dosage.d_dosage_size), dosage.d_dosage_size),''), " +
            "            IFNULL(dosage.d_dosage_unit,'' ),' ', " +
            "                        IF(m_unit_in_package ='шт', " +
            "            CONCAT('N',  medicine.m_quantity_in_pack), " +
            "            CONCAT(medicine.m_quantity_in_pack, medicine.m_unit_in_package,' N1')))  " +
            "            AS fullname,m_price, m_quantity_packages,m_is_need_prescrip, p_name, m_is_deleted" +
            "            FROM chemist.medicine " +
            "            JOIN chemist.release_form USING(rf_release_form_id) " +
            "            JOIN chemist.producer USING(p_producer_id) " +
            "            LEFT JOIN chemist.dosage USING(d_dosage_id) " +
            " WHERE m_quantity_packages>0 AND m_is_deleted=b'0'"+
            "                              ORDER BY fullname " +
            "                  LIMIT ?,?;";
    private static final String SQL_SELECT_MEDICINE_BY_ID =
            "SELECT m_medicine_id, CONCAT(medicine.m_name,' ',release_form.rf_name,' ', " +
            "IFNULL( IF(dosage.d_dosage_size > 1, " +
            "            FLOOR(dosage.d_dosage_size), dosage.d_dosage_size),''), " +
            "            IFNULL(dosage.d_dosage_unit,'' ),' ', " +
            "                        IF(m_unit_in_package ='шт', " +
            "            CONCAT('N',  medicine.m_quantity_in_pack), " +
            "            CONCAT(medicine.m_quantity_in_pack, medicine.m_unit_in_package,' N1')))  " +
            "            AS fullname,m_price, m_quantity_packages,m_is_need_prescrip, p_name " +
            "            FROM chemist.medicine " +
            "            JOIN chemist.release_form USING(rf_release_form_id) " +
            "            JOIN chemist.producer USING(p_producer_id)  " +
            "            LEFT JOIN chemist.dosage USING(d_dosage_id) " +
            "           WHERE m_medicine_id=?  ";
    private static final String SQL_SELECT_ALL_MEDICINES_COUNT_BY_NAME =
            "SELECT count(m_name)  as count FROM chemist.medicine ";
    private static final String SQL_SELECT_ALL_MEDICINES_COUNT_BY_RELEVANCE=
            "SELECT count(m_name)  as count FROM chemist.medicine WHERE m_quantity_packages>0 AND m_is_deleted=b'0'";
    private static final String SQL_SELECT_BALANCE_MEDICINE_BY_ID =
            "SELECT m_quantity_packages as balance FROM chemist.medicine WHERE m_medicine_id=?";
    private static final String SQL_SELECT_BY_MEDICINE_ID_EDIT = "SELECT m_medicine_id,d_dosage_unit,m_name,m_price,m_quantity_packages,m_is_need_prescrip," +
            "m_quantity_in_pack,m_analog_id,m_unit_in_package, " +
            "rf_release_form_id,rf_name, " +
            "d_dosage_id,d_dosage_size,d_dosage_unit, " +
            "p_producer_id,p_name " +
            "FROM chemist.medicine " +
            "JOIN producer USING(p_producer_id) " +
            "JOIN release_form USING(rf_release_form_id) " +
            "LEFT JOIN dosage USING(d_dosage_id) " +
            " WHERE m_medicine_id=?";
    private static final String SQL_SELECT_ALL_MEDICINES_ID = "SELECT m_medicine_id FROM chemist.medicine";
    private static final String SQL_SELECT_ALL_UNITS_IN_PACK =
            "SELECT DISTINCT m_unit_in_package FROM chemist.medicine";
    private static final String SQL_UPDATE_MEDICINE =
            "UPDATE chemist.medicine SET m_name=?, m_price=?, m_quantity_packages=?," +
                    "m_is_need_prescrip=?, m_quantity_in_pack=?, rf_release_form_id=?, " +
                    "p_producer_id=?, d_dosage_id= ?, m_analog_id=?, m_unit_in_package=?" +
                    "m_is_deleted=?"+
                    " WHERE m_medicine_id =?";

    private static final String SQL_INSERT_MEDICINE =
            "INSERT INTO chemist.medicine " +
                    "(m_name, m_price, m_quantity_packages, m_is_need_prescrip, m_quantity_in_pack, " +
                    "rf_release_form_id, p_producer_id,d_dosage_id,m_analog_id, m_unit_in_package) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
    private static final String SQL_SELECT_BY_IS_NEED_PRESCRIPTION="SELECT m_medicine_id, CONCAT(medicine.m_name,' ',release_form.rf_name,' ', " +
            "IFNULL( IF(dosage.d_dosage_size > 1, " +
            "            FLOOR(dosage.d_dosage_size), dosage.d_dosage_size),''), " +
            "            IFNULL(dosage.d_dosage_unit,'' ),' ', " +
            "                        IF(m_unit_in_package ='шт', " +
            "            CONCAT('N',  medicine.m_quantity_in_pack), " +
            "            CONCAT(medicine.m_quantity_in_pack, medicine.m_unit_in_package,' N1')))  " +
            "            AS fullname " +
            "            FROM chemist.medicine " +
            "            JOIN chemist.release_form USING(rf_release_form_id) " +
            "            JOIN chemist.producer USING(p_producer_id)  " +
            "            LEFT JOIN chemist.dosage USING(d_dosage_id) " +
            "           WHERE m_is_need_prescrip=? " +
            "ORDER BY fullname";

    @Override
    public Medicine findById(long id) throws DAOException {
        PreparedStatement statement = null;
        Medicine medicine = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_MEDICINE_BY_ID);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                medicine = new Medicine();
                medicine.setMedicineId(result.getLong("m_medicine_id"));
                medicine.setName(result.getString("fullname"));
                medicine.setPrice(result.getBigDecimal("m_price"));
                Producer producer=new Producer();
                producer.setName(result.getString("p_name"));
                medicine.setProducer(producer);
                medicine.setQuantityPackages(result.getInt("m_quantity_packages"));
                medicine.setNeedRecipe(result.getBoolean("m_is_need_prescrip"));

            }

        } catch (SQLException e) {
            throw new DAOException("Error in findById ", e);
        } finally {
            close(statement);

        }
        return medicine;
    }

    @Override
    public void create(Medicine entity) throws DAOException {

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_INSERT_MEDICINE);
            statement.setString(1, entity.getName());
            statement.setBigDecimal(2, entity.getPrice());
            statement.setLong(6, entity.getReleaseForm().getReleaseFormId());
            statement.setLong(7, entity.getProducer().getProducerId());
            statement.setInt(3, entity.getQuantityPackages());
            statement.setBoolean(4, entity.getIsNeedRecipe());
            statement.setInt(5, entity.getQuantityInPackage());
            if (entity.getDosage().getDosageId() != 0) {
                statement.setLong(8, entity.getDosage().getDosageId());
            } else {
                statement.setNull(8, Types.NULL);
            }
            if (entity.getAnalog().getMedicineId() != 0) {
                statement.setLong(9, entity.getAnalog().getMedicineId());
            } else {
                statement.setNull(9, Types.NULL);
            }
            statement.setString(10, entity.getUnitInPackage().name().toLowerCase());

            statement.execute();

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(statement);
        }
    }

    @Override
    public void update(Medicine entity) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_UPDATE_MEDICINE);
            statement.setString(1, entity.getName());
            statement.setBigDecimal(2, entity.getPrice());
            statement.setInt(3, entity.getQuantityPackages());
            statement.setBoolean(4, entity.getIsNeedRecipe());
            statement.setInt(5, entity.getQuantityInPackage());
            statement.setLong(6, entity.getReleaseForm().getReleaseFormId());
            statement.setLong(7, entity.getProducer().getProducerId());
            if (entity.getDosage().getDosageId() != 0) {
                statement.setLong(8, entity.getDosage().getDosageId());
            } else {
                statement.setNull(8, Types.NULL);
            }
            if (entity.getAnalog().getMedicineId() != 0) {
                statement.setLong(9, entity.getAnalog().getMedicineId());
            } else {
                statement.setNull(9, Types.NULL);
            }
            statement.setString(10, entity.getUnitInPackage().name().toLowerCase());
            statement.setBoolean(11,entity.getIsDeleted());
            statement.setLong(12, entity.getMedicineId());
            statement.execute();

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(statement);
        }


    }

    public ArrayList<Medicine> findByName(String name) throws DAOException {
        PreparedStatement statement = null;
        ArrayList<Medicine> medicines = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_SELECT_MEDICINE_BY_NAME);
            statement.setString(1, name);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Medicine medicine = new Medicine();
                medicine.setMedicineId(result.getLong("m_medicine_id"));
                medicine.setName(result.getString("fullname"));
                Producer producer=new Producer();
                producer.setName(result.getString("p_name"));
                medicine.setProducer(producer);
                medicine.setNeedRecipe(result.getBoolean("m_is_need_prescrip"));
                medicine.setPrice(result.getBigDecimal("m_price"));
                medicine.setQuantityPackages(result.getInt("m_quantity_packages"));
                medicines.add(medicine);
            }

        } catch (SQLException e) {
            throw new DAOException("" + e);
        } finally {
            close(statement);
        }
//        medicines.sort(Comparator.comparing(Medicine::getName));
        return !medicines.isEmpty()?medicines:null;
    }

    @Override
    public ArrayList<Medicine> findAll(int begin, int end) throws DAOException {
        PreparedStatement statement = null;
        ArrayList<Medicine> medicines = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_SELECT_ALL_MEDICINES);
            statement.setInt(1, begin);
            statement.setInt(2, end);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Medicine medicine = new Medicine();
                medicine.setMedicineId(result.getLong("m_medicine_id"));
                medicine.setName(result.getString("fullname"));
                Producer producer=new Producer();
                producer.setName(result.getString("p_name"));
                medicine.setProducer(producer);
                medicine.setQuantityPackages(result.getInt("m_quantity_packages"));
                medicine.setNeedRecipe(result.getBoolean("m_is_need_prescrip"));
                medicine.setPrice(result.getBigDecimal("m_price"));
                medicine.setDeleted(result.getBoolean("m_is_deleted"));
                medicines.add(medicine);
            }
LOGGER.log(Level.DEBUG , medicines.size());
        } catch (SQLException e) {
            throw new DAOException("findAll(int begin, int end)" + e);
        } finally {
            close(statement);
        }
        return !medicines.isEmpty()?medicines:null;
    }

    @Override
    public ArrayList<Medicine> findAllByRelevance(int begin, int end) throws DAOException {
        PreparedStatement statement = null;
        ArrayList<Medicine> medicines = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_SELECT_ALL_MEDICINES_BY_RELEVANCE);
            statement.setInt(1, begin);
            statement.setInt(2, end);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Medicine medicine = new Medicine();
                medicine.setMedicineId(result.getLong("m_medicine_id"));
                medicine.setName(result.getString("fullname"));
                Producer producer=new Producer();
                producer.setName(result.getString("p_name"));
                medicine.setProducer(producer);
                medicine.setNeedRecipe(result.getBoolean("m_is_need_prescrip"));
                medicine.setPrice(result.getBigDecimal("m_price"));
                medicine.setDeleted(result.getBoolean("m_is_deleted"));
                medicine.setQuantityPackages(result.getInt("m_quantity_packages"));
                medicines.add(medicine);
            }

        } catch (SQLException e) {
            throw new DAOException("Exception in findAllByRelevance method", e);
        } finally {
            close(statement);
        }
        return !medicines.isEmpty()?medicines:null;
    }


    @Override
    public int getCountByName() throws DAOException {

        Statement statement = null;
        Integer count = 0;
        try {
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(SQL_SELECT_ALL_MEDICINES_COUNT_BY_NAME);
            if (result.next()) {
                count = result.getInt("count");
            }

        } catch (SQLException e) {
            throw new DAOException("Exception in getCountByName method",e);
        } finally {
            close(statement);
        }
        return count;
    }

    @Override
    public int getCountByNameByRelevance() throws DAOException {
        Statement statement = null;
        Integer count = 0;
        try {
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(SQL_SELECT_ALL_MEDICINES_COUNT_BY_RELEVANCE);
            if (result.next()) {
                count = result.getInt("count");
            }

        } catch (SQLException e) {
            throw new DAOException("Exception in getCountByNameByRelevance method",e);
        } finally {
            close(statement);
        }
        return count;
    }

    @Override
    public int getBalanceById(long id) throws DAOException {
        PreparedStatement statement = null;
        int balance = 0;
        try {
            statement = connection.prepareStatement(SQL_SELECT_BALANCE_MEDICINE_BY_ID);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                balance = result.getInt("balance");
            }
        } catch (SQLException e) {
            throw new DAOException("Error in method getBalanceById" + e);
        } finally {
            close(statement);
        }

        return balance;
    }

    @Override
    public Medicine findByIdEdit(long id) throws DAOException {
        PreparedStatement statement = null;
        Medicine medicine = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_BY_MEDICINE_ID_EDIT);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                medicine = new Medicine();
                medicine.setMedicineId(result.getLong(("m_medicine_id")));
                medicine.setName(result.getString("m_name"));
                medicine.setNeedRecipe(result.getBoolean("m_is_need_prescrip"));
                medicine.setPrice(result.getBigDecimal("m_price"));
                medicine.setQuantityPackages(result.getInt("m_quantity_packages"));
                medicine.setQuantityInPackage(result.getInt("m_quantity_in_pack"));
                medicine.setAnalog(new Medicine(result.getLong(("m_analog_id"))));
                medicine.setDosage(new Dosage(result.getLong("d_dosage_id"),
                        result.getBigDecimal("d_dosage_size"), result.getString("d_dosage_unit")));
                medicine.setReleaseForm(new ReleaseForm(result.getLong("rf_release_form_id"),
                        result.getString("rf_name")));
                medicine.setProducer(new Producer(result.getLong("p_producer_id"),
                        result.getString("p_name")));
                medicine.setUnitInPackage(UnitInPackage.valueOf(result.getString("m_unit_in_package").toUpperCase()));

            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(statement);
        }
        return medicine;
    }

    public ArrayList<String> findUnitsInPack() throws DAOException {
        ArrayList<String> unitsInPack = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_ALL_UNITS_IN_PACK);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                unitsInPack.add(result.getString("m_unit_in_package"));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(statement);
        }
        return !unitsInPack.isEmpty()?unitsInPack:null;
    }

    @Override
    public HashSet<Long> findAllId() throws DAOException {
        HashSet<Long> setIds = new HashSet<>();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_ALL_MEDICINES_ID);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                setIds.add(result.getLong("m_medicine_id"));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(statement);
        }
        return !setIds.isEmpty()?setIds:null;
    }


    @Override
    public ArrayList<Medicine> findByPrescripNeed(boolean isNeedPrescrip) throws DAOException {
        PreparedStatement statement = null;
       ArrayList<Medicine> medicines =new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_SELECT_BY_IS_NEED_PRESCRIPTION);
            statement.setBoolean(1, isNeedPrescrip);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
               Medicine medicine = new Medicine();
                medicine.setMedicineId(result.getLong("m_medicine_id"));
                medicine.setName(result.getString("fullname"));
                medicines.add(medicine);
            }
LOGGER.log(Level.DEBUG,medicines.size());
        } catch (SQLException e) {
            throw new DAOException("Error in findByPrescripNeed ", e);
        } finally {
            close(statement);

        }
        return !medicines.isEmpty()?medicines:null;
    }


}
