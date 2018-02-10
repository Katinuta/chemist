package by.teplouhova.chemist.manager;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
/**
 * The Enum MessageManager.
 */
public enum MessageManager {


    /** The en. */
    EN("MessagesBundle", new Locale("en", "EN")),

    /** The ru. */
    RU("MessagesBundle", new Locale("ru", "RU"));

    /** The bundle. */
    private ResourceBundle bundle;

    /**
     * Instantiates a new message manager.
     *
     * @param baseName the base name
     * @param locale the locale
     */
    MessageManager(String baseName, Locale locale) {
        try {
            this.bundle = ResourceBundle.getBundle(baseName, locale);
        } catch (MissingResourceException e) {
            LogManager.getLogger().log(Level.FATAL, "Property file  of messages isn't found");
            throw new RuntimeException("Property file  of messages isn't found", e);
        }


    }

    /**
     * Gets the bundle.
     *
     * @return the bundle
     */
    public ResourceBundle getBundle() {
        return bundle;
    }
}
