package by.teplouhova.chemist.dao.manager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ResourceBundle;

public class ConfigurationManager {
    private final static Logger LOGGER = LogManager.getLogger();

    private ResourceBundle bundle;
    private static ConfigurationManager manager=new ConfigurationManager();

    private ConfigurationManager() {
        bundle=ResourceBundle.getBundle("database");
    }

    public ResourceBundle getBundle() {
        return bundle;
    }
}
