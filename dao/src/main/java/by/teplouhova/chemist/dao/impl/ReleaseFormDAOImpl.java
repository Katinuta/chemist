package by.teplouhova.chemist.dao.impl;

import by.teplouhova.chemist.dao.ReleaseFormDAO;
import by.teplouhova.chemist.dao.DAOException;
import by.teplouhova.chemist.entity.impl.ReleaseForm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The Class ReleaseFormDAOImpl.
 */
public class ReleaseFormDAOImpl extends ReleaseFormDAO {

    /**
     * The Constant SQL_SELECT_ALL_RELEASE_FORM.
     */
    private static final String SQL_SELECT_ALL_RELEASE_FORM = "SELECT rf_release_form_id,rf_name FROM chemist.release_form  " +
            "ORDER BY rf_name";

    /**
     * Find all.
     *
     * @return the array list
     * @throws DAOException the DAO exception
     */
    @Override
    public ArrayList<ReleaseForm> findAll() throws DAOException {
        ArrayList<ReleaseForm> releaseFormSet = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_ALL_RELEASE_FORM);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                ReleaseForm form = new ReleaseForm();
                form.setReleaseFormId(result.getLong("rf_release_form_id"));
                form.setName(result.getString("rf_name"));
                releaseFormSet.add(form);
            }
            if (releaseFormSet.isEmpty()) {
                return null;
            }
        } catch (SQLException e) {
            throw new DAOException("Exception in method findAll", e);
        } finally {
            close(statement);
        }
        return releaseFormSet;
    }

    /**
     * Find by id.
     *
     * @param id the id
     * @return the release form
     * @throws DAOException the DAO exception
     */
    @Override
    public ReleaseForm findById(long id) throws DAOException {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates the.
     *
     * @param entity the entity
     * @throws DAOException the DAO exception
     */
    @Override
    public void create(ReleaseForm entity) throws DAOException {
        throw new UnsupportedOperationException();
    }

    /**
     * Update.
     *
     * @param entity the entity
     * @throws DAOException the DAO exception
     */
    @Override
    public void update(ReleaseForm entity) throws DAOException {
        throw new UnsupportedOperationException();
    }
}
