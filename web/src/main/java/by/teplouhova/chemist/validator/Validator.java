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

public class Validator {

    private static final Logger LOGGER = LogManager.getLogger();

    private ResourceBundle bundle;
    private HashMap<String, String> errors;

    public Validator() {
        bundle = MessageManager.EN.getBundle();
        errors = new HashMap<>();
    }


    public Validator(ResourceBundle bundle) {
        this.bundle = bundle;
        errors = new HashMap<>();
    }

    public boolean isValid(HashMap<String, String> paramsMap) {
//        if(paramsMap.isEmpty()){
//            errors.put("params", bundle.getString("message.format.incorrect"));
//
//        }
        Set<Map.Entry<String, String>> entrySet = paramsMap.entrySet();
//        paramsMap.entrySet().stream()
//                .filter(entry -> entry != null && entry.getValue().isEmpty())
//                .forEach(entry -> entry.setValue(null));
        entrySet.forEach(entry -> {
            String current = entry.getKey();
            try {
                FieldName field = FieldName.valueOf(current.toUpperCase());
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

    public Set<Map.Entry<String, String>> getEntrySetErrors() {
        return errors.entrySet();
    }

    public boolean isExistError(String name) {
        return errors.containsKey(name);
    }

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
