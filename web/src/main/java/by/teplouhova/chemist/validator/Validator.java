package by.teplouhova.chemist.validator;

import by.teplouhova.chemist.manager.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Class Validator.
 */
public class Validator {

    private static final Logger LOGGER = LogManager.getLogger();

    /** The bundle. */
    private ResourceBundle bundle;

    /** The errors. */
    private HashMap<String, String> errors;

    /**
     * Instantiates a new validator.
     */
    public Validator() {
        bundle = MessageManager.EN.getBundle();
        errors = new HashMap<>();
    }

    /**
     * Instantiates a new validator.
     *
     * @param bundle the bundle
     */
    public Validator(ResourceBundle bundle) {
        this.bundle = bundle;
        errors = new HashMap<>();
    }

    /**
     * Checks if is valid.
     *
     * @param paramsMap the params map
     * @return true, if is valid
     */
    public boolean isValid(HashMap<String, String> paramsMap) {

        Set<Map.Entry<String, String>> entrySet = paramsMap.entrySet();
        entrySet.forEach(entry -> {
            String current = entry.getKey();
            try {
                ParameterName field = ParameterName.valueOf(current.toUpperCase());
                String regexp = field.getRegexp();
                if (field.isRequired()) {
                    validateRequired(current, entry.getValue(), regexp);
                } else {
                    validateNotRequired(current, entry.getValue(), regexp);
                }

            } catch (IllegalArgumentException e) {
                LOGGER.info("Parameter is not field " + current + " value " + entry.getValue());
            }
        });
        return errors.isEmpty() ? true : false;
    }

    /**
     * Gets the entry set errors.
     *
     * @return the entry set errors
     */
    public Set<Map.Entry<String, String>> getEntrySetErrors() {
        return errors.entrySet();
    }

    /**
     * Checks if is exist error.
     *
     * @param name the name
     * @return true, if is exist error
     */
    public boolean isExistError(String name) {
        return errors.containsKey(name);
    }

    /**
     * Validate required.
     *
     * @param name the name
     * @param value the value
     * @param regexp the regexp
     * @return true, if successful
     */
    private boolean validateRequired(String name, String value, String regexp) {
        if (value == null || value.isEmpty()) {
            errors.put(name, bundle.getString("message.empty.value"));
            return false;
        }
        value = value.trim();
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(value.trim());
        String matchString = null;
        if (matcher.find()) {
            matchString = matcher.group();
        }
        if (matchString == null || matchString != null && matchString.length() != value.length()) {
            errors.put(name, bundle.getString("message.format.incorrect"));
            return false;
        }

        return true;

    }

    /**
     * Validate not required.
     *
     * @param name the name
     * @param value the value
     * @param regexp the regexp
     * @return true, if successful
     */
    private boolean validateNotRequired(String name, String value, String regexp) {
        if (value == null || value.isEmpty()) {
            return true;
        }
        Pattern pattern = Pattern.compile(regexp);
        String matchString = null;
        Matcher matcher = pattern.matcher(value.trim());
        if (matcher.find()) {
            matchString = matcher.group();
        }
        if (matchString == null || matchString != null && matchString.length() != value.length()) {
            errors.put(name, bundle.getString("message.format.incorrect"));
            return false;
        }

        return true;

    }
}
