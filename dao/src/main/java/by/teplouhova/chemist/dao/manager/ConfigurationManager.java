package by.teplouhova.chemist.dao.manager;


import by.teplouhova.chemist.dao.constant.DAOConstant;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

public class ConfigurationManager {
    private final static Logger LOGGER = LogManager.getLogger();

    private final String DEFAULT_POOL_INIT_SIZE = "10";
    private final String DEFAULT_POOL_MAX_ACTIVE = "20";
    private Properties property;

    private final static ConfigurationManager manager = new ConfigurationManager();

    private ConfigurationManager() {
        try {
            property=new Properties();
            property.load(ConfigurationManager.class.getClassLoader().getResourceAsStream("database.properties"));
        } catch (IOException e) {
            LOGGER.log(Level.FATAL, "Configuration file of database didn't find");
            new RuntimeException("Configuration file of database didn't find", e);
        }

    }

    public static ConfigurationManager getInstance() {
        return manager;
    }

    public String getString(String key) {
        String value = null;

            switch(key){
                case DAOConstant.POOL_MAX_ACTIVE_KEY:
                    value=property.getProperty(key,DEFAULT_POOL_MAX_ACTIVE);
                    break;
                case DAOConstant.POOL_INIT_SIZE_KEY:
                    value = property.getProperty(key,DEFAULT_POOL_INIT_SIZE);
                    break;
                default:
                    value = property.getProperty(key);
                    break;
            }


        return value;
    }
}
