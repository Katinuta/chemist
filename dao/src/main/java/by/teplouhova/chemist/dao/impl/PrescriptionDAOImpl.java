package by.teplouhova.chemist.dao.impl;

import by.teplouhova.chemist.dao.PrescriptionDAO;
import by.teplouhova.chemist.dao.DAOException;
import by.teplouhova.chemist.entity.impl.Prescription;
import by.teplouhova.chemist.entity.impl.PrescriptionStatus;
import by.teplouhova.chemist.entity.impl.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionDAOImpl extends PrescriptionDAO {

    private static final Logger LOGGER = LogManager.getLogger();


    private static final String SELECT_PRESCRIPTION_BY_CLIENT_ID =
            "SELECT p_prescription_id,p_date_begin,p_date_end, u_doctor_id, user.u_name, user.u_surname, p_status FROM chemist.prescription " +
                    "JOIN chemist.user ON prescription.u_doctor_id=user.u_user_id " +
                    "WHERE prescription.u_user_id=?";

    private static final String SQL_SELECT_PRESCRIPTION_BY_ID =
            "SELECT prescription.u_user_id, p_prescription_id,p_date_begin,p_date_end, u_doctor_id," +
                    "doctor.u_name AS doctorname, doctor.u_surname AS doctorsurename, " +
                    "client.u_name AS clientname, client.u_surname AS clientsurname, p_status " +
                    "FROM chemist.prescription " +
                    "JOIN chemist.user doctor ON prescription.u_doctor_id=doctor.u_user_id " +
                    "JOIN chemist.user client ON prescription.u_user_id=client.u_user_id " +
                    "WHERE p_prescription_id=?";

    private static final  String SQL_UPDATE_PRESCRIPTION=
            "UPDATE chemist.prescription SET u_user_id=?, p_date_begin=?, p_date_end=?, u_doctor_id=?, p_status=? " +
            " WHERE p_prescription_id=?";
    private static final String SQL_SELECT_PRESCRIPTION_BY_DOCTOR_ID=
            "SELECT p_prescription_id,p_date_begin,p_date_end,u_doctor_id,prescription.u_user_id, " +
                    "user.u_name, user.u_surname, p_status FROM chemist.prescription " +
                    "JOIN chemist.user ON prescription.u_user_id=user.u_user_id " +
                    "WHERE prescription.u_doctor_id=? ORDER BY p_status";
    private static final  String SQL_SELECT_PRESCRIP_BY_DOCTOR_ID_EXTAND="SELECT p_prescription_id,p_date_begin,p_date_end,u_doctor_id,prescription.u_user_id, " +
            "user.u_name, user.u_surname, p_status FROM chemist.prescription " +
            "JOIN chemist.user ON prescription.u_user_id=user.u_user_id " +
            "WHERE prescription.u_doctor_id=? AND prescription.p_status=?  ORDER BY p_date_begin ";

    private static final  String SQL_INSERT_PRESCRIPTION=
            "INSERT INTO chemist.prescription (u_user_id,p_date_begin,p_date_end,u_doctor_id,p_status) VALUES ( ?,?,?,?,?)";
    public List<Prescription> findPrescriptionByClientId(long clientId) throws DAOException {
        List<Prescription> prescriptions = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SELECT_PRESCRIPTION_BY_CLIENT_ID);
            statement.setLong(1, clientId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Prescription prescription = new Prescription();
                prescription.setPrescriptionId(result.getLong("p_prescription_id"));
                prescription.setDateBegin(result.getDate("p_date_begin").toLocalDate());
                prescription.setDateEnd(result.getDate("p_date_end").toLocalDate());
                User doctor = new User();
                doctor.setUserId(result.getLong("u_doctor_id"));
                doctor.setName(result.getString("u_name"));
                doctor.setSurname(result.getString("u_surname"));
                prescription.setStatus(PrescriptionStatus.valueOf(result.getString("p_status").toUpperCase()));
                prescription.setDoctor(doctor);
                prescriptions.add(prescription);
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(statement);
        }
        return !prescriptions.isEmpty()?prescriptions:null;
    }

    @Override
    public List<Prescription> findPrescriptionByDoctorId(long doctorId) throws DAOException {
        PreparedStatement statement=null;
        List<Prescription> prescriptions=new ArrayList<>();
        try {
            statement=connection.prepareStatement(SQL_SELECT_PRESCRIPTION_BY_DOCTOR_ID);
            statement.setLong(1, doctorId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Prescription prescription = new Prescription();
                prescription.setPrescriptionId(result.getLong("p_prescription_id"));
                User client = new User();
                client.setUserId(result.getLong("u_user_id"));
                client.setName(result.getString("u_name"));
                client.setSurname(result.getString("u_surname"));
                prescription.setDateBegin(result.getDate("p_date_begin").toLocalDate());
                prescription.setDateEnd(result.getDate("p_date_end").toLocalDate());
                prescription.setStatus(PrescriptionStatus.valueOf(result.getString("p_status").toUpperCase()));
                prescription.setClient(client);
                User doctor = new User();
                doctor.setUserId(result.getLong("u_doctor_id"));
                prescription.setDoctor(doctor);
                prescriptions.add(prescription);
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(statement);
        }
        return !prescriptions.isEmpty()?prescriptions:null;

    }

    @Override
    public List<Prescription> findPrescripByDoctorIdExtand(long doctorId) throws DAOException {
        PreparedStatement statement=null;
        List<Prescription> prescriptions=new ArrayList<>();
        try {
            statement=connection.prepareStatement(SQL_SELECT_PRESCRIP_BY_DOCTOR_ID_EXTAND);
            statement.setLong(1, doctorId);
            statement.setString(2,PrescriptionStatus.EXTEND.name().toLowerCase());
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Prescription prescription = new Prescription();
                prescription.setPrescriptionId(result.getLong("p_prescription_id"));
                User client = new User();
                client.setUserId(result.getLong("u_user_id"));
                client.setName(result.getString("u_name"));
                client.setSurname(result.getString("u_surname"));
                prescription.setClient(client);
                prescription.setDateBegin(result.getDate("p_date_begin").toLocalDate());
                prescription.setDateEnd(result.getDate("p_date_end").toLocalDate());
                prescription.setStatus(PrescriptionStatus.valueOf(result.getString("p_status").toUpperCase()));
                User doctor = new User();
                doctor.setUserId(result.getLong("u_doctor_id"));
                prescription.setDoctor(doctor);
                prescriptions.add(prescription);
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(statement);
        }
        return !prescriptions.isEmpty()?prescriptions:null;

    }

    @Override
    public Prescription findById(long id) throws DAOException {
        PreparedStatement statement = null;
        Prescription prescription = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_PRESCRIPTION_BY_ID);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                prescription = new Prescription();
                prescription.setPrescriptionId(result.getLong("p_prescription_id"));
                User doctor = new User();
                doctor.setUserId(result.getLong("u_doctor_id"));
                doctor.setName(result.getString("doctorname"));
                doctor.setSurname(result.getString("doctorsurename"));
                prescription.setDoctor(doctor);
                User client=new User(result.getLong("u_user_id"));
                client.setName(result.getString("clientname"));
                client.setSurname(result.getString("clientsurname"));
                prescription.setClient(client);
                prescription.setDateBegin(result.getDate("p_date_begin").toLocalDate());
                prescription.setDateEnd(result.getDate("p_date_end").toLocalDate());
                prescription.setStatus(PrescriptionStatus.valueOf(result.getString("p_status").toUpperCase()));
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(statement);
        }
        return prescription;
    }

    @Override
    public void create(Prescription entity) throws DAOException {
        PreparedStatement statement=null;
        try {
            statement=connection.prepareStatement(SQL_INSERT_PRESCRIPTION,PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setLong(1,entity.getClient().getUserId());
            statement.setDate(2, Date.valueOf(entity.getDateBegin()));
            statement.setDate(3, Date.valueOf(entity.getDateEnd()));
            statement.setLong(4,entity.getDoctor().getUserId());
            statement.setString(5,entity.getStatus().getNameStatus());
            statement.executeUpdate();
            ResultSet result=statement.getGeneratedKeys();
            if (result.next()) {
                entity.setPrescriptionId(result.getLong(1));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }finally {
            close(statement);
        }


    }

    @Override
    public void update(Prescription entity) throws DAOException {
        PreparedStatement statement=null;
        try {
            statement= connection.prepareStatement(SQL_UPDATE_PRESCRIPTION);
            statement.setLong(1,entity.getClient().getUserId());
            statement.setDate(2, Date.valueOf(entity.getDateBegin()));
            statement.setDate(3, Date.valueOf(entity.getDateEnd()));
            statement.setLong(4,entity.getDoctor().getUserId());
            statement.setString(5,entity.getStatus().getNameStatus());
            statement.setLong(6,entity.getPrescriptionId());
            statement.execute();
        } catch (SQLException e) {
            throw new DAOException("Exception in update method",e);
        }finally {
            close(statement);
        }
    }
}
