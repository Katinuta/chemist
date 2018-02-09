package by.teplouhova.chemist.manager;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public enum MessageManager {


    EN("MessagesBundle", new Locale("en", "EN")),
    RU("MessagesBundle", new Locale("ru", "RU"));

    private ResourceBundle bundle;

    MessageManager(String baseName, Locale locale) {
        try {
            this.bundle = ResourceBundle.getBundle(baseName, locale);
        } catch (MissingResourceException e) {
            LogManager.getLogger().log(Level.FATAL, "Property file  of messages isn't found");
            throw new RuntimeException("Property file  of messages isn't found", e);
        }


    }

    public ResourceBundle getBundle() {
        return bundle;
    }
}
