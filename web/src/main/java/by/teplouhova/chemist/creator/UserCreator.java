package by.teplouhova.chemist.creator;

import by.teplouhova.chemist.entity.impl.RoleType;
import by.teplouhova.chemist.entity.impl.User;
import by.teplouhova.chemist.validator.Validator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static by.teplouhova.chemist.validator.FieldConstant.*;

public class UserCreator {

    private User user;

    public UserCreator() {
        user = new User();
    }

    public User create(HashMap<String, String> userParams) {
        user.setRole(RoleType.CLIENT);
        Set<Map.Entry<String, String>> keySet = userParams.entrySet();
        keySet.forEach(entry -> fillField(entry.getKey(), entry.getValue()));
        return user;
    }

    private void fillField(String name, String value) {

        switch (name) {
            case USER_FIELD_NAME: {
                user.setName(value);
                break;
            }
            case USER_FIELD_SURNAME: {
                user.setSurname(value);
                break;
            }
            case USER_FIELD_LOGIN: {
                user.setLogin(value);
                break;
            }
            case USER_FIELD_PASSWORD: {
                user.setPassword(value);
                break;
            }
            case USER_FIELD_PHONE: {
                user.setPhone(value);
                break;
            }
            case USER_FIELD_ACCOUNT: {
                user.setAccount(new BigDecimal(value));
                break;
            }
            default:{
                //todo
            }

        }
    }
}
