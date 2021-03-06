package by.teplouhova.chemist.dao.impl;

import by.teplouhova.chemist.dao.UserDAO;
import by.teplouhova.chemist.dao.DAOException;
import by.teplouhova.chemist.entity.impl.RoleType;
import by.teplouhova.chemist.entity.impl.User;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * The Class UserDAOImpl.
 */
public class UserDAOImpl extends UserDAO {

    /**
     * The Constant SQL_SELECT_USER_BY_ID.
     */
    private final static String SQL_SELECT_USER_BY_ID =
            "SELECT user.u_user_id,user.u_name, user.u_surname,user.u_login,user.u_password,user.u_role,user.u_phone, user.u_account FROM chemist.user WHERE user.u_user_id=?";

    /**
     * The Constant SQL_INSERT_USER.
     */
    private final static String SQL_INSERT_USER =
            "INSERT INTO chemist.user (u_name, u_surname, u_login, u_password, u_account, u_phone, u_role) VALUES ( ?, ?, ?,MD5(?), ?, ?,?)";

    /**
     * The Constant SQL_UPDATE_USER.
     */
    private final static String SQL_UPDATE_USER =
            "UPDATE chemist.user SET u_name=?,u_surname=?,u_login=?, u_password=?, u_account=? ," +
                    "u_role =?, u_phone=? " +
                    "WHERE u_user_id=?";

    /**
     * The Constant SQL_UPDATE_USER_PASSWORD.
     */
    private final static String SQL_UPDATE_USER_PASSWORD =
            "UPDATE chemist.user SET u_name=?,u_surname=?,u_login=?, u_password=MD5(?), u_account=? ," +
                    "u_role =?, u_phone=? " +
                    "WHERE u_user_id=?";

    /**
     * The Constant SQL_SELECT_USER_BY_LOGIN.
     */
    private final static String SQL_SELECT_USER_BY_LOGIN =
            "SELECT user.u_user_id,user.u_name, user.u_surname,user.u_login,user.u_password,user.u_role,user.u_phone, user.u_account FROM chemist.user WHERE user.u_login=?";

    /**
     * The Constant SQL_SELECT_USER_BY_LOGIN_PASSWORD.
     */
    public final static String SQL_SELECT_USER_BY_LOGIN_PASSWORD =
            "SELECT user.u_user_id,user.u_name, user.u_surname,user.u_login,user.u_password,user.u_role,user.u_phone, user.u_account FROM chemist.user WHERE user.u_login=? AND u_password=MD5(?)";

    /**
     * The Constant SQL_SELECT_ACCOUNT_BY_USER_ID.
     */
    public final static String SQL_SELECT_ACCOUNT_BY_USER_ID = "SELECT u_account FROM user WHERE u_user_id=?";

    /**
     * The Constant SQL_SELECT_USER_BY_ROLE.
     */
    public final static String SQL_SELECT_USER_BY_ROLE = "SELECT u_user_id, u_name , u_surname, u_phone FROM user " +
            "WHERE u_role=? ORDER BY u_surname,u_name LIMIT ?,? ";

    /**
     * The Constant SQL_SELECT_COUNT_USER_BY_ROLE.
     */
    public final static String SQL_SELECT_COUNT_USER_BY_ROLE = "SELECT count(u_name) AS count FROM user " +
            "WHERE u_role=?";


    /**
     * Find by id.
     *
     * @param id the id
     * @return the user
     * @throws DAOException the DAO exception
     */
    @Override
    public User findById(long id) throws DAOException {
        PreparedStatement st = null;
        User user = null;
        try {
            st = connection.prepareStatement(SQL_SELECT_USER_BY_ID);
            st.setLong(1, id);
            ResultSet result = st.executeQuery();
            if (result.next()) {
                user = new User();
                user.setUserId(result.getLong("u_user_id"));
                user.setName(result.getString("u_name"));
                user.setSurname(result.getString("u_surname"));
                user.setLogin(result.getString("u_login"));
                user.setPassword(result.getString("u_password"));
                user.setAccount(result.getBigDecimal("u_account"));
                user.setPhone(result.getString("u_phone"));
                user.setRole(RoleType.valueOf(result.getString("u_role").toUpperCase()));
            }

        } catch (SQLException e) {
            throw new DAOException("Exception in create findById", e);
        } finally {
            close(st);
        }

        return user;
    }

    /**
     * Creates the.
     *
     * @param entity the entity
     * @throws DAOException the DAO exception
     */
    @Override
    public void create(User entity) throws DAOException {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(SQL_INSERT_USER);
            st.setString(1, entity.getName());
            st.setString(2, entity.getSurname());
            st.setString(3, entity.getLogin());
            st.setString(4, entity.getPassword());
            st.setBigDecimal(5, entity.getAccount());
            st.setString(6, entity.getPhone());
            st.setString(7, entity.getRole().getName());
            st.execute();

        } catch (SQLException e) {
            throw new DAOException("Exception in create method", e);
        } finally {
            close(st);
        }

    }

    /**
     * Update.
     *
     * @param entity the entity
     * @throws DAOException the DAO exception
     */
    @Override
    public void update(User entity) throws DAOException {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(SQL_UPDATE_USER);
            st.setString(1, entity.getName());
            st.setString(2, entity.getSurname());
            st.setString(3, entity.getLogin());
            st.setString(4, entity.getPassword());
            st.setString(7, entity.getPhone());
            st.setString(6, entity.getRole().getName());
            st.setBigDecimal(5, entity.getAccount());
            st.setLong(8, entity.getUserId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception in method update", e);
        } finally {
            close(st);

        }

    }

    /**
     * Update password.
     *
     * @param entity the entity
     * @throws DAOException the DAO exception
     */
    public void updatePassword(User entity) throws DAOException {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(SQL_UPDATE_USER_PASSWORD);
            st.setString(4, entity.getPassword());
            st.setBigDecimal(5, entity.getAccount());
            st.setString(1, entity.getName());
            st.setString(2, entity.getSurname());
            st.setString(3, entity.getLogin());

            st.setString(7, entity.getPhone());
            st.setString(6, entity.getRole().getName());
            st.setLong(8, entity.getUserId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception in method updatePassword", e);
        } finally {
            close(st);

        }

    }

    /**
     * Find by login.
     *
     * @param login the login
     * @return the user
     * @throws DAOException the DAO exception
     */
    public User findByLogin(String login) throws DAOException {
        PreparedStatement st = null;
        User user = null;
        try {
            st = connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN);
            st.setString(1, login);
            ResultSet result = st.executeQuery();
            if (result.next()) {
                user = new User();
                user.setUserId(result.getLong("u_user_id"));
                user.setName(result.getString("u_name"));
                user.setSurname(result.getString("u_surname"));
                user.setLogin(result.getString("u_login"));
                user.setPhone(result.getString("u_phone"));
                user.setRole(RoleType.valueOf(result.getString("u_role").toUpperCase()));
            }

        } catch (SQLException e) {
            throw new DAOException("Exception in method findByLogin", e);
        } finally {
            close(st);
        }


        return user;
    }

    /**
     * Find by login password.
     *
     * @param login    the login
     * @param password the password
     * @return the user
     * @throws DAOException the DAO exception
     */
    public User findByLoginPassword(String login, String password) throws DAOException {
        PreparedStatement statement = null;
        User user = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN_PASSWORD);
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                user = new User();
                user.setUserId(result.getLong("u_user_id"));
                user.setName(result.getString("u_name"));
                user.setLogin(result.getString("u_login"));
                user.setSurname(result.getString("u_surname"));

                user.setPhone(result.getString("u_phone"));
                user.setRole(RoleType.valueOf(result.getString("u_role").toUpperCase()));
            }

        } catch (SQLException e) {
            throw new DAOException("Exception in method findByLoginPassword", e);
        } finally {
            close(statement);
        }
        return user;
    }

    /**
     * Find balance by user id.
     *
     * @param userId the user id
     * @return the big decimal
     * @throws DAOException the DAO exception
     */
    public BigDecimal findBalanceByUserId(long userId) throws DAOException {
        PreparedStatement statement = null;
        BigDecimal balance = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_ACCOUNT_BY_USER_ID);
            statement.setLong(1, userId);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                balance = result.getBigDecimal("u_account");
            }
        } catch (SQLException e) {
            throw new DAOException("Exception in method findBalanceByUserId", e);
        } finally {
            close(statement);
        }
        return balance;
    }

    /**
     * Find by role.
     *
     * @param role  the role
     * @param begin the begin
     * @param end   the end
     * @return the list
     * @throws DAOException the DAO exception
     */
    @Override
    public List<User> findByRole(String role, int begin, int end) throws DAOException {
        List<User> users = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_USER_BY_ROLE);
            statement.setString(1, role);
            statement.setInt(2, begin);
            statement.setInt(3, end);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                User user = new User();
                user.setUserId(result.getLong("u_user_id"));
                user.setName(result.getString("u_name"));
                user.setSurname(result.getString("u_surname"));
                user.setPhone(result.getString("u_phone"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DAOException("Exception in method findByRole", e);
        } finally {
            close(statement);
        }
        return !users.isEmpty() ? users : null;
    }

    /**
     * Gets the count by role.
     *
     * @param role the role
     * @return the count by role
     * @throws DAOException the DAO exception
     */
    public int getCountByRole(String role) throws DAOException {
        int count = 0;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_COUNT_USER_BY_ROLE);
            statement.setString(1, role);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                count = result.getInt("count");
            }
        } catch (SQLException e) {
            throw new DAOException("Exception in method getCountByRole", e);
        } finally {
            close(statement);
        }
        return count;
    }


}