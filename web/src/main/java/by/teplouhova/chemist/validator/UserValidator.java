package by.teplouhova.chemist.validator;

import by.teplouhova.chemist.entity.impl.User;
import by.teplouhova.chemist.manager.MessageManager;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import static by.teplouhova.chemist.validator.FieldConstant.*;

public class UserValidator {
//    private static final String USER_FIELD_NAME="name";
//    private static  final String USER_FIELD_SURNAME="surname";
//    private static final String USER_FIELD_LOGIN="login";
//    private static final String USER_FIELD_PASSWORD="password";
//    private static final String USER_FIELD_PHONE="phone";
//    private static final String USER_FIELD_ACCOUNT="account";
//    private static final String FIELD_ERROR="error";

    private Validator validator;
    private HashMap<String, String> errors;
    private ResourceBundle bundle;

    public UserValidator() {
        validator = new Validator();
        errors = new HashMap<>();
        bundle = MessageManager.EN.getBundle();
    }

    public UserValidator(ResourceBundle bundle) {
        validator = new Validator(bundle);
        errors = new HashMap<>();
        this.bundle = bundle;
    }

    public boolean isUserValid(HashMap<String, String> userParams) {
        Set<Map.Entry<String, String>> keySet = userParams.entrySet();
        keySet.forEach(entry -> validateField(entry.getKey(), entry.getValue()));
        return errors.isEmpty() ? true : false;
    }

    public Set<Map.Entry<String, String>> gerSetErrors() {
        return errors.entrySet();
    }

    private void validateField(String name, String value) {
//        String regex=null;
        switch (name) {
            case USER_FIELD_NAME: {
                validator.validateRequired(name, value, Validator.REGEXP_NAME_USER, errors);
                break;
            }
            case USER_FIELD_SURNAME: {
                validator.validateRequired(name, value, Validator.REGEXP_SURNAME_USER, errors);
                break;
            }
            case USER_FIELD_LOGIN: {
                validator.validateRequired(name, value, Validator.REGEXP_LOGIN, errors);
                break;
            }
            case USER_FIELD_PASSWORD: {

                validator.validateRequired(name, value, Validator.REGEXP_PASSWORD, errors);
                break;
            }
            case USER_FIELD_PHONE: {

                validator.validateRequired(name, value, Validator.REGEXP_PHONE, errors);
                break;
            }
            case USER_FIELD_ACCOUNT: {
                validator.validateRequired(name, value, Validator.REGEXP_BIGDECIMAL_PARAM, errors);
                break;
            }
            default: {
                errors.put(FIELD_ERROR, bundle.getString("message.wrong.field") + User.class.getSimpleName() + name);
            }

        }

    }

}
