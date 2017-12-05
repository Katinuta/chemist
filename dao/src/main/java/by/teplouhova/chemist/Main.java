package by.teplouhova.chemist;

import by.teplouhova.chemist.impl.User;
import by.teplouhova.chemist.impl.UserDAO;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Main {
    public static void main(String [] args){
//     ConnectionPool connectionPool=ConnectionPool.getInstance();
//        System.out.println(connectionPool);
//
//        try {
//            ProxyConnection connection= connectionPool.getConnection();
//            Statement statement=connection.createStatement();
//           ResultSet res=statement.executeQuery("SELECT user.u_name FROM chemist.user WHERE user.u_user_id=1");
//           while(res.next()){
//               System.out.println(res.getString(1));
//           }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        User user=new User("Мила","Тутар","mila","mila",new BigDecimal(90), RoleEnum.CLIENT,"7534215");
       UserDAO dao=new UserDAO();
       new TransactionManager().beginTransaction(dao);
       dao.create(user);

//        System.out.println(user);
    }
}
