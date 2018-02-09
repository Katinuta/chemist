package by.teplouhova.chemist.pool;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

import static by.teplouhova.chemist.pool.ConfigurationConstant.POOL_INIT_SIZE_KEY;
import static by.teplouhova.chemist.pool.ConfigurationConstant.POOL_MAX_ACTIVE_KEY;


/**
 * The Class ConfigurationManager.
 */
public class ConfigurationManager {

    /**
     * The Constant LOGGER.
     */
    private final static Logger LOGGER = LogManager.getLogger();

    /**
     * The default pool init size.
     */
    private final String DEFAULT_POOL_INIT_SIZE = "10";

    /**
     * The default pool max active.
     */
    private final String DEFAULT_POOL_MAX_ACTIVE = "20";

    /**
     * The property to set up   database connection.
     */
    private Properties property;

    /**
     * The Constant manager.
     */
    private final static ConfigurationManager manager = new ConfigurationManager();

    /**
     * Instantiates a new configuration manager.
     */
    private ConfigurationManager() {
        try {
            property = new Properties();
            property.load(ConfigurationManager.class.getClassLoader().getResourceAsStream("database.properties"));
        } catch (IOException e) {
            LOGGER.log(Level.FATAL, "Configuration file of database isn't found");
            new RuntimeException("Configuration file of database isn't found", e);
        }

    }

    /**
     * Gets the single instance of ConfigurationManager.
     *
     * @return single instance of ConfigurationManager
     */
    public static ConfigurationManager getInstance() {
        return manager;
    }

    /**
     * Gets value from property.
     *
     * @param key the key
     * @return the string
     */
    public String getString(String key) {
        String value;

        switch (key) {
            case POOL_MAX_ACTIVE_KEY:
                value = property.getProperty(key, DEFAULT_POOL_MAX_ACTIVE);
                value = value != null ? value : DEFAULT_POOL_MAX_ACTIVE;
                break;
            case POOL_INIT_SIZE_KEY:
                value = property.getProperty(key, DEFAULT_POOL_INIT_SIZE);
                value = value != null ? value : DEFAULT_POOL_INIT_SIZE;
                break;
            default:
                value = property.getProperty(key);
                break;
        }

        return value;
    }
}