package by.teplouhova.chemist.dao.mysql;

import by.teplouhova.chemist.dao.UserDAO;
import by.teplouhova.chemist.entity.impl.RoleType;
import by.teplouhova.chemist.dao.exception.DAOException;
import by.teplouhova.chemist.pool.ConnectionPool;
import by.teplouhova.chemist.entity.impl.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MySqlUserDAO extends UserDAO {
    private final static Logger LOGGER= LogManager.getLogger();
    private final static String SQL_SELECT_USER_BY_ID =
            "SELECT user.u_user_id,user.u_name, user.u_surname,user.u_login,user.u_password,user.u_role,user.u_phone, user.u_account FROM chemist.user WHERE user.u_user_id=?";

    private final static String SQL_INSERT_USER =
            "INSERT INTO chemist.user (u_name, u_surname, u_login, u_password, u_account, u_phone, u_role) VALUES ( ?, ?, ?,MD5(?), ?, ?,?)";

    private final static String SQL_UPDATE_USER =
            "UPDATE chemist.user SET u_name =?, u_surname=?, u_login=?, u_password=?, u_account=?, u_phone=? WHERE u_user_id=?";

    private final static String SQL_SELECT_USER_BY_LOGIN =
            "SELECT user.u_user_id,user.u_name, user.u_surname,user.u_login,user.u_password,user.u_role,user.u_phone, user.u_account FROM chemist.user WHERE user.u_login=?";

   public final static String SQL_SELECT_USER_BY_LOGIN_PASSWORD =
            "SELECT user.u_user_id,user.u_name, user.u_surname,user.u_login,user.u_password,user.u_role,user.u_phone, user.u_account FROM chemist.user WHERE user.u_login=? AND u_password=MD5(?)";


    public MySqlUserDAO() {

    }

    @Override
    public User findById(long id) {
        PreparedStatement st = null;
        User user = null;
//        try {
//            st = connection.prepareStatement(SQL_SELECT_USER_BY_ID);
//            st.setLong(1, id);
//            ResultSet result = st.executeQuery();
//            if (result.next()) {
//                user = new User();
//                user.setUserId(result.getLong("u_user_id"));
//                user.setName(result.getString("u_name"));
//                user.setSurname(result.getString("u_surname"));
//                user.setLogin(result.getString("u_login"));
//                user.setPassword(result.getString("u_password"));
//                user.setAccount(result.getBigDecimal("u_account"));
//                user.setPhone(result.getString("u_phone"));
//                user.setRole(RoleType.valueOf(result.getString("u_role").toUpperCase()));
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        close(st);
//        ConnectionPool.getInstance().releaseConnection(connection);
        return user;
    }

    @Override
    public void create(User entity) throws DAOException {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(SQL_INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, entity.getName());
            st.setString(2, entity.getSurname());
            st.setString(3, entity.getLogin());
            st.setString(4, entity.getPassword());
            st.setBigDecimal(5, entity.getAccount());
            st.setString(6, entity.getPhone());
            st.setString(7, entity.getRole().getName());
            st.execute();
            ResultSet result = st.getGeneratedKeys();
//            if (result.next()) {
//                entity.setUserId(result.getLong(1));
//            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(st);
            ConnectionPool.getInstance().releaseConnection(connection);
        }


    }

    @Override
    public void update(User entity) throws DAOException {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(SQL_UPDATE_USER);
            st.setString(1, entity.getName());
            st.setString(2, entity.getSurname());
            st.setString(3, entity.getLogin());
            st.setString(4, entity.getPassword());
            st.setBigDecimal(5, entity.getAccount());
            st.setString(6, entity.getPhone());
            st.setLong(7, entity.getUserId());
            st.executeUpdate();
        } catch (SQLException e) {
           throw new DAOException(e);
        } finally {
            close(st);

        }

    }

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
                user.setPassword(result.getString("u_password"));
//                user.setAccount(result.getBigDecimal("u_account"));
                user.setPhone(result.getString("u_phone"));
                user.setRole(RoleType.valueOf(result.getString("u_role").toUpperCase()));
            }

        } catch (SQLException e) {
           throw new DAOException(e);
        } finally {
            close(st);
        }


        return user;
    }

    public User findByLoginPassword(String login, String password) throws DAOException {
        PreparedStatement statement = null;
        User user=null;
        try {
            statement=connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN_PASSWORD);
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet result=statement.executeQuery();
            if(result.next()){
                user=new User();
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

           throw new DAOException(e);
        }finally {
            close(statement);

        }
        return user;
    }


}
