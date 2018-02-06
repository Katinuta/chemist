package by.teplouhova.chemist.pool;

import by.teplouhova.chemist.dao.manager.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static by.teplouhova.chemist.pool.ConfigurationConstant.*;

class ConnectionConfiguration {

    private final static Logger LOGGER = LogManager.getLogger();

    private final int POOL_MAX_ACTIVE;
    private final int POOL_INIT_SIZE;
    private final String URL;
    private final String USER;
    private final String PASSWORD;
    private final static ConnectionConfiguration configuration = new ConnectionConfiguration();

    private ConnectionConfiguration() {
        POOL_INIT_SIZE = Integer.parseInt(ConfigurationManager.getInstance().getString(POOL_INIT_SIZE_KEY));
        POOL_MAX_ACTIVE = Integer.parseInt(ConfigurationManager.getInstance().getString(POOL_MAX_ACTIVE_KEY));
        URL = ConfigurationManager.getInstance().getString(ConfigurationConstant.URL_KEY) + "?" +
                "useUnicode=" + ConfigurationManager.getInstance().getString(ConfigurationConstant.USE_UNICODE_KEY) + "&" +
                "characterEncoding=" + ConfigurationManager.getInstance().getString(CHARACTER_ENCOD_KEY) + "&" +
                "useSSL=" + ConfigurationManager.getInstance().getString(USE_SSL_KEY);

        USER = ConfigurationManager.getInstance().getString(USER_KEY);
        PASSWORD = ConfigurationManager.getInstance().getString(PASSWORD_KEY);
    }

    public static ConnectionConfiguration getConfiguration() {
        return configuration;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

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
