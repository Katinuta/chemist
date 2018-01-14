package by.teplouhova.chemist.validator;

public class UserValidator {
    private  Validator validator;

    public UserValidator() {
        validator=new Validator();
    }

    public boolean validateUser(String name, String surname){
        if(!validator.validateRequired(name,Validator.REGEXP_NAME)){
            return false;
        }
//        if(!validator.validateRequired(surname,Validator.REGEXP_MONEY_PARAM)){
//            return false;
//        }
     return validator.validateRequired(surname,Validator.REGEXP_NAME);
    }



}
