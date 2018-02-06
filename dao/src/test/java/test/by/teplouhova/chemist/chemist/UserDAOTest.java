package test.by.teplouhova.chemist.chemist;

import by.teplouhova.chemist.dao.impl.UserDAOImpl;
import by.teplouhova.chemist.entity.impl.RoleType;
import by.teplouhova.chemist.entity.impl.User;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
//@Ignore
//@Test(groups = "database")
public class UserDAOTest {

    private Connection connection;
    private UserDAOImpl mySqlUserDAO;

//    @BeforeClass
    public void setUp(){
        mySqlUserDAO =new UserDAOImpl();
        ResourceBundle bundle = ResourceBundle.getBundle("testdatabase");
        String url = bundle.getString("url") + "?" +
                "useUnicode=" + bundle.getString("useUnicode") + "&" +
                "characterEncoding=" + bundle.getString("characterEncoding");
        Properties properties = new Properties();
        properties.setProperty("user", bundle.getString("user"));
        properties.setProperty("input.password", bundle.getString("input.password"));
        properties.setProperty("useSSL", bundle.getString("useSSL"));
        try {
            connection = DriverManager.getConnection(url, properties);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

//    @DataProvider(name = "user")
    public Object[][] createUser(){
        return new Object[][]{
                {
                    2,new User(2,"Ольга","Гутар","ольга","ольга",new BigDecimal(50), RoleType.CLIENT,"2478557"), true
                },
                {
                        3,new User(3,"Ольга","Гутар","ольга","ольга",new BigDecimal(100), RoleType.CLIENT,"2478557"), false
                }

        };
    }

//    @Test(dataProvider = "user")
    public void findUserByIdTest(long id,User expected, boolean result){
      //  User actual= mySqlUserDAO.findById(id);
      //  assertEquals(expected.equals(actual),result);
    }

}
