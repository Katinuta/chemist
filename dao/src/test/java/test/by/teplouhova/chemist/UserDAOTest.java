package test.by.teplouhova.chemist;

import by.teplouhova.chemist.RoleEnum;
import by.teplouhova.chemist.dao.impl.User;
import by.teplouhova.chemist.dao.impl.UserDAO;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Test(groups = "database")
public class UserDAOTest {

    private Connection connection;
    private UserDAO userDAO;

    @BeforeClass
    public void setUp(){
        userDAO=new UserDAO();
        ResourceBundle bundle = ResourceBundle.getBundle("testdatabase");
        String url = bundle.getString("url") + "?" +
                "useUnicode=" + bundle.getString("useUnicode") + "&" +
                "characterEncoding=" + bundle.getString("characterEncoding");
        Properties properties = new Properties();
        properties.setProperty("user", bundle.getString("user"));
        properties.setProperty("password", bundle.getString("password"));
        properties.setProperty("useSSL", bundle.getString("useSSL"));
        try {
            connection = DriverManager.getConnection(url, properties);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @DataProvider(name = "user")
    public Object[][] createUser(){
        return new Object[][]{
                {
                    2,new User(2,"Ольга","Гутар","ольга","ольга",new BigDecimal(50), RoleEnum.CLIENT,"2478557"), true
                },
                {
                        3,new User(3,"Ольга","Гутар","ольга","ольга",new BigDecimal(100), RoleEnum.CLIENT,"2478557"), false
                }

        };
    }

    @Test(dataProvider = "user")
    public void findUserByIdTest(long id,User expected, boolean result){
        User actual=userDAO.findById(id);
        assertEquals(expected.equals(actual),result);
    }

}
