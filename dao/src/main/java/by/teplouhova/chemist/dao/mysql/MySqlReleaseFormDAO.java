package by.teplouhova.chemist.dao.mysql;

import by.teplouhova.chemist.dao.ReleaseFormDAO;
import by.teplouhova.chemist.dao.exception.DAOException;
import by.teplouhova.chemist.entity.impl.ReleaseForm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

public class MySqlReleaseFormDAO extends ReleaseFormDAO {

    private static final String SQL_SELECT_ALL_RELEASE_FORM="SELECT rf_release_form_id,rf_name FROM chemist.release_form  " +
            "ORDER BY rf_name";

    @Override
    public ArrayList<ReleaseForm> findAll() throws DAOException {
        ArrayList<ReleaseForm> releaseFormSet=new ArrayList<>();
        PreparedStatement statement=null;
        try {
            statement=connection.prepareStatement(SQL_SELECT_ALL_RELEASE_FORM);
            ResultSet result=statement.executeQuery();
            while(result.next()){
                ReleaseForm form=new ReleaseForm();
                form.setReleaseFormId(result.getLong("rf_release_form_id"));
                form.setName(result.getString("rf_name"));
                releaseFormSet.add(form);
            }
            if(releaseFormSet.isEmpty()){
                return null;
            }
        } catch (SQLException e) {
            throw new DAOException(""+ e);
        }finally {
            close(statement);
        }
        return releaseFormSet;
    }

    @Override
    public ReleaseForm findById(long id) throws DAOException {
        return null;
    }

    @Override
    public void create(ReleaseForm entity) throws DAOException {

    }

    @Override
    public void update(ReleaseForm entity) throws DAOException {

    }
}
