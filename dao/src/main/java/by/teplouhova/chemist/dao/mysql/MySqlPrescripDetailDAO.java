package by.teplouhova.chemist.dao.mysql;

import by.teplouhova.chemist.dao.PrescripDetailDAO;
import by.teplouhova.chemist.dao.exception.DAOException;
import by.teplouhova.chemist.entity.impl.Medicine;
import by.teplouhova.chemist.entity.impl.Prescription;
import by.teplouhova.chemist.entity.impl.PrescriptionDetail;
import by.teplouhova.chemist.entity.impl.PrescriptionStatus;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlPrescripDetailDAO extends PrescripDetailDAO {
    private static final Logger LOGGER=  LogManager.getLogger();

    private static final String SQL_SELECT_ALL_PRESCRIP_DETAIL_BY_PRESRIP_ID =
            "SELECT pd_record_id,pd_quantity_pack,m_medicine_id,p_prescription_id, pd_status, " +
                    "CONCAT(medicine.m_name,' ',release_form.rf_name,' ', " +
                    "            IFNULL( IF(dosage.d_dosage_size > 1, " +
                    "            FLOOR(dosage.d_dosage_size), dosage.d_dosage_size),''), " +
                    "            IFNULL(dosage.d_dosage_unit,'' ),' ', " +
                    "            IF(m_unit_in_package ='шт', " +
                    "            CONCAT('N',  medicine.m_quantity_in_pack), " +
                    "            CONCAT(medicine.m_quantity_in_pack, medicine.m_unit_in_package,' N1')))  " +
                    "            AS fullname " +
                    "FROM chemist.prescription_detail " +
                    "JOIN chemist.medicine USING(m_medicine_id) " +
                    "JOIN chemist.release_form USING(rf_release_form_id) " +
                    "LEFT JOIN chemist.dosage USING(d_dosage_id)" +
                    "WHERE prescription_detail.p_prescription_id = ? ";
    private static final String SQL_UPDATE_PRESCRIPTION_DETAIL =
            "UPDATE prescription_detail SET pd_quantity_pack=?,p_prescription_id=?," +
                    "m_medicine_id=?, pd_status=? WHERE pd_record_id=? ";
    private static final String SQL_SELECT_PRESCRIP_DEYAIL_BY_ID=
            "SELECT pd_record_id,pd_quantity_pack,p_prescription_id,m_medicine_id,pd_status " +
                    " FROM chemist.prescription_detail " +
                    " WHERE pd_record_id=? ";
    private static final  String SQL_SELECT_BY_PRESCRIP_ID_AND_MEDICINE_ID=
            "SELECT pd_record_id,pd_quantity_pack,p_prescription_id,m_medicine_id,pd_status " +
            " FROM chemist.prescription_detail " +
            " WHERE m_medicine_id=? AND prescription_detail.p_prescription_id = ? ";

    @Override
    public PrescriptionDetail findById(long id) throws DAOException {
        PreparedStatement statement = null;
        PrescriptionDetail detail=null;
        try {
            statement=connection.prepareStatement(SQL_SELECT_PRESCRIP_DEYAIL_BY_ID);
            statement.setLong(1,id);
            ResultSet result=statement.executeQuery();
            if(result.next()){
                detail=new PrescriptionDetail();
                detail.setDetailId(result.getLong("pd_record_id"));
                detail.setQuantityPack(result.getInt("pd_quantity_pack"));
                detail.setStatus(PrescriptionStatus.valueOf(result.getString("pd_status").toUpperCase()));
                detail.setPrescription(new Prescription(result.getLong("p_prescription_id")));
                detail.setMedicine(new Medicine(result.getLong("m_medicine_id")));

            }

        } catch (SQLException e) {
            throw new DAOException(e);
        }finally {
            close(statement);
        }
        return detail;
    }

    @Override
    public void create(PrescriptionDetail entity) throws DAOException {

    }

    @Override
    public void update(PrescriptionDetail entity) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_UPDATE_PRESCRIPTION_DETAIL);

            statement.setInt(1,entity.getQuantityPack());
            statement.setLong(2, entity.getPrescription().getPrescriptionId());
            statement.setLong(3,entity.getMedicine().getMedicineId());
            statement.setString(4,entity.getStatus().name().toLowerCase());
            statement.setLong(5, entity.getDetailId());
            statement.execute();

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(statement);
        }
    }

    @Override
    public List<PrescriptionDetail> findAllByPrescriptionId(long id) throws DAOException {
        PreparedStatement statement = null;
        List<PrescriptionDetail> details = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_SELECT_ALL_PRESCRIP_DETAIL_BY_PRESRIP_ID);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                PrescriptionDetail detail = new PrescriptionDetail();
                detail.setDetailId(result.getLong("pd_record_id"));
                detail.setQuantityPack(result.getInt("pd_quantity_pack"));
                detail.setStatus(PrescriptionStatus.valueOf(result.getString("pd_status").toUpperCase()));
                Medicine medicine=new Medicine(result.getLong("m_medicine_id"));
                medicine.setName(result.getString("fullname"));
                detail.setMedicine(medicine);
                detail.setPrescription(new Prescription(result.getLong("p_prescription_id")));
                details.add(detail);
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(statement);
        }

        return !details.isEmpty() ? details : null;
    }

    public PrescriptionDetail findByPrescripIdMedicineId(long prescriptionId, long medicineId) throws DAOException {
        PrescriptionDetail detail=null;
        PreparedStatement statement=null;
        try {
            statement=connection.prepareStatement(SQL_SELECT_BY_PRESCRIP_ID_AND_MEDICINE_ID);
            statement.setLong(1,prescriptionId);
            statement.setLong(2,medicineId);
            ResultSet result=statement.executeQuery();
            if(result.next()){
                detail=new PrescriptionDetail();
                detail.setDetailId(result.getLong("pd_record_id"));
                detail.setQuantityPack(result.getInt("pd_quantity_pack"));
                detail.setMedicine(new Medicine(result.getLong("m_medicine_id")));
                detail.setStatus(PrescriptionStatus.valueOf(result.getString("pd_status").toUpperCase()));
                detail.setPrescription(new Prescription(result.getLong("p_prescription_id")));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }finally {
            close(statement);
        }

        return detail;

    }
}
