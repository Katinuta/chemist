package by.teplouhova.chemist.dao.manager;


import by.teplouhova.chemist.dao.constant.DAOConstant;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class DatabaseConfigManager {
    private final static Logger LOGGER = LogManager.getLogger();

    private final String DEFAULT_POOL_INIT_SIZE = "10";
    private final String DEFAULT_POOL_MAX_ACTIVE = "20";
    private ResourceBundle bundle;
    private final static DatabaseConfigManager manager = new DatabaseConfigManager();

    private DatabaseConfigManager() {
        try {

            bundle = ResourceBundle.getBundle("database");

        } catch (MissingResourceException e) {
            LOGGER.log(Level.FATAL, "Configuration file of database didn't find");
            new RuntimeException("Configuration file of database didn't find", e);
        }

    }

    public static DatabaseConfigManager getInstance() {
        return manager;
    }

    public String getString(String key) {
        String value = null;
        try {
            value = bundle.getString(key);
            if (DAOConstant.POOL_INIT_SIZE_KEY.equals(key) && DAOConstant.EMPTY_STRING.equals(value)) {
                value = DEFAULT_POOL_INIT_SIZE;
            }
            if(DAOConstant.POOL_MAX_ACTIVE_KEY.equals(key)&&DAOConstant.EMPTY_STRING.equals(value)){
                value=DEFAULT_POOL_MAX_ACTIVE;
            }

        } catch (MissingResourceException e) {
            if (DAOConstant.POOL_INIT_SIZE_KEY.equals(key)) {
                value = DEFAULT_POOL_INIT_SIZE;
            }else if(DAOConstant.POOL_MAX_ACTIVE_KEY.equals(key)){
                value=DEFAULT_POOL_MAX_ACTIVE;
            }else{
                LOGGER.log(Level.ERROR,"Value of property " + key+"didn't find ");
              //  new RuntimeException("");
            }

        }
        return value;
    }
}
