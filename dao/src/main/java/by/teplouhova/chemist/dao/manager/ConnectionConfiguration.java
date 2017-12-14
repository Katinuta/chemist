package by.teplouhova.chemist.dao.manager;

import by.teplouhova.chemist.dao.constant.DAOConstant;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionConfiguration {

    private final static Logger LOGGER= LogManager.getLogger();

    private final int POOL_MAX_ACTIVE;
    private final int POOL_INIT_SIZE;
    private final String URL;
    private final String USER;
    private final String PASSWORD;
    private final static ConnectionConfiguration configuration=new ConnectionConfiguration();

    private ConnectionConfiguration() {
        POOL_INIT_SIZE = Integer.parseInt(ConfigurationManager.getInstance().getString(DAOConstant.POOL_INIT_SIZE_KEY));
        POOL_MAX_ACTIVE = Integer.parseInt(ConfigurationManager.getInstance().getString(DAOConstant.POOL_MAX_ACTIVE_KEY));
        URL = ConfigurationManager.getInstance().getString(DAOConstant.URL_KEY) + "?" +
                "useUnicode=" + ConfigurationManager.getInstance().getString("useUnicode") + "&" +
                "characterEncoding=" + ConfigurationManager.getInstance().getString("characterEncoding")+ "&" +
                "useSSL=" + ConfigurationManager.getInstance().getString("useSSL");

        USER = ConfigurationManager.getInstance().getString("user");
        PASSWORD = ConfigurationManager.getInstance().getString("password");
    }

    public static ConnectionConfiguration getConfiguration() {
        return configuration;
    }

    public Connection getConnection(){
        Connection connection=null;
        try {
            connection= DriverManager.getConnection(URL,USER,PASSWORD);

        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Connection is not created " + URL);
        }
        return connection;
    }

    public int getPOOL_MAX_ACTIVE() {
        return POOL_MAX_ACTIVE;
    }

    public int getPOOL_INIT_SIZE() {
        return POOL_INIT_SIZE;
    }
}
