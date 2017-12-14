package by.teplouhova.chemist.dao;

import by.teplouhova.chemist.dao.impl.UserDAO;
import by.teplouhova.chemist.dao.pool.ConnectionPool;
import by.teplouhova.chemist.dao.pool.ProxyConnection;
import by.teplouhova.chemist.entity.RoleEnum;
import by.teplouhova.chemist.entity.impl.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MAin {
    public static void main(String [] args){
       ProxyConnection connection= ConnectionPool.getInstance().getConnection();
        PreparedStatement st = null;
        User user = null;
        try {
            st = connection.prepareStatement(UserDAO.SQL_FIND_USER_BY_LOGIN_PASSWORD);
            st.setString(1, "lev");
            st.setString(2, "lev");
            ResultSet result = st.executeQuery();
            if (result.next()) {
                user = new User();
                user.setUsedId(result.getLong("u_user_id"));
                user.setName(result.getString("u_name"));
                user.setSurname(result.getString("u_surname"));
                user.setLogin(result.getString("u_login"));
                user.setPassword(result.getString("u_password"));
                user.setAccount(result.getBigDecimal("u_account"));
                user.setPhone(result.getString("u_phone"));
                user.setRole(RoleEnum.valueOf(result.getString("u_role").toUpperCase()));
            }
            System.out.println(user);
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally{

            }
        }

}
