package by.teplouhova.chemist.dao.mysql;

import by.teplouhova.chemist.dao.PrescriptionDAO;
import by.teplouhova.chemist.dao.exception.DAOException;
import by.teplouhova.chemist.entity.impl.Prescription;
import by.teplouhova.chemist.entity.impl.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public class MySqlPrescriptionDAO extends PrescriptionDAO {

    private static final Logger LOGGER= LogManager.getLogger();


    private static final String SELECT_PRESCRIPTION_BY_CLIENT_ID =
            "SELECT r_recipe_id,r_date_begin,r_date_end, u_doctor_id, user.u_name, user.u_surname, r_is_active FROM chemist.recipe " +
                    "JOIN chemist.user ON recipe.u_doctor_id=user.u_user_id " +
                    "WHERE recipe.u_user_id=?";

    public HashSet<Prescription> findPrescriptionByClientId(long clientId) throws DAOException {
        HashSet<Prescription> prescriptions = new HashSet<>();
        PreparedStatement statement=null;
        try {
            statement = connection.prepareStatement(SELECT_PRESCRIPTION_BY_CLIENT_ID);
            statement.setLong(1, clientId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Prescription prescription=new Prescription();
                prescription.setPrescriptionId(result.getLong("r_recipe_id"));
                prescription.setDateBegin(result.getDate("r_date_begin").toLocalDate());
                prescription.setDateEnd(result.getDate("r_date_end").toLocalDate());
                LOGGER.log(Level.INFO,result.getDate("r_date_end").toLocalDate());
                User doctor=new User();
                doctor.setUsedId(result.getLong("u_doctor_id"));
                doctor.setName(result.getString("u_name"));
                doctor.setSurname(result.getString("u_surname"));
                prescription.setActive(result.getBoolean("r_is_active"));
                prescription.setDoctor(doctor);
                prescriptions.add(prescription);
            }
            if(prescriptions.isEmpty()){
                prescriptions=null;
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        }finally {
            close(statement);
        }
        return prescriptions;
    }

    @Override
    public Prescription findById(long id) {
        return null;
    }

    @Override
    public void create(Prescription entity) {

    }

    @Override
    public void update(Prescription entity) {

    }
}
