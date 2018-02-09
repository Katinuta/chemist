package by.teplouhova.chemist.creator;

import by.teplouhova.chemist.entity.impl.User;
import by.teplouhova.chemist.validator.ParameterName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UserCreator {
    private static final Logger LOGGER = LogManager.getLogger();
    private User user;

    public UserCreator() {
        user = new User();
    }

    public User create(HashMap<String, String> userParams) {
        Set<Map.Entry<String, String>> keySet = userParams.entrySet();
        keySet.stream().filter(entry -> entry.getValue()!=null).forEach(entry -> {
            String current=entry.getKey();
            try{
                ParameterName field= ParameterName.valueOf(current.toUpperCase());
                fillField(field,entry.getValue());
            }catch (IllegalArgumentException e){
                LOGGER.info("Parameter is not field  : " +current );
            }
        });
        return user;
    }

    private void fillField(ParameterName name, String value) {

        switch (name) {
            case USER_NAME: {
                user.setName(value);
                break;
            }
            case SURNAME: {
                user.setSurname(value);
                break;
            }
            case LOGIN: {
                user.setLogin(value);
                break;
            }
            case PASSWORD: {
                user.setPassword(value);
                break;
            }
            case PHONE: {
                user.setPhone(value);
                break;
            }
            case ACCOUNT: {
                user.setAccount(new BigDecimal(value));
                break;
            }
            default:{
                LOGGER.info("Parameter is not field  " + name +" for class " + user.getClass().getSimpleName() );
            }

        }
    }
}
