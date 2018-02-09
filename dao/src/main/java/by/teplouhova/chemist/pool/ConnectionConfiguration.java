package by.teplouhova.chemist.pool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static by.teplouhova.chemist.pool.ConfigurationConstant.*;

/**
 * The Class ConnectionConfiguration.
 */
class ConnectionConfiguration {

    /** The Constant LOGGER. */
    private final static Logger LOGGER = LogManager.getLogger();

    /** The pool max active. */
    private final int POOL_MAX_ACTIVE;

    /** The pool init size. */
    private final int POOL_INIT_SIZE;

    /** The url. */
    private final String URL;

    /** The user. */
    private final String USER;

    /** The password. */
    private final String PASSWORD;

    /** The Constant configuration. */
    private final static ConnectionConfiguration configuration = new ConnectionConfiguration();

    /**
     * Instantiates a new connection configuration.
     */
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

    /**
     * Gets the configuration.
     *
     * @return the configuration
     */
    public static ConnectionConfiguration getConfiguration() {
        return configuration;
    }

    /**
     * Gets the connection.
     *
     * @return the connection
     */
    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Connection is not created " + URL);
        }
        return connection;
    }

    /**
     * Gets the pool max active.
     *
     * @return the pool max active
     */
    public int getPOOL_MAX_ACTIVE() {
        return POOL_MAX_ACTIVE;
    }

    /**
     * Gets the pool init size.
     *
     * @return the pool init size
     */
    public int getPOOL_INIT_SIZE() {
        return POOL_INIT_SIZE;
    }
}