package by.teplouhova.chemist.validator;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {

    private static final String REGEXP_NAME="[A-Za-zА-Яа-яЁё]{2,}+";

    public boolean validateUser(String name,String surname){
        if(!validateName(name)){
            return false;
        }
        if(!validateName(surname)){
            return false;
        }
     return validateName(name);
    }

    private boolean validateName(String name){
        if(name==null||name.isEmpty()){
            return  false;
        }
        Pattern pattern =Pattern.compile(REGEXP_NAME);
        String matchString=null;
        Matcher matcher = pattern.matcher(name.trim());
        if(matcher.find()){
            matchString=matcher.group();
        }
        if(matcher==null){
            return false;
        }else{
            return  matchString.length()==name.length();
        }


    }

}
