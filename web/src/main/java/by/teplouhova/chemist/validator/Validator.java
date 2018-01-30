package by.teplouhova.chemist.validator;

import by.teplouhova.chemist.manager.MessageManager;

import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {


    public static final String REGEXP_NAME_USER = "(^[A-Za-z]{2,}\\s*$)|(^[А-Яа-яЁё]{2,}\\s*$)";
    public static final String REGEXP_SURNAME_USER = "(^[A-Za-z]{2,}\\-?([A-Za-z]{2,})?$)|(^[А-Яа-яЁё]{2,}\\-?([А-Яа-яЁё]{2,})?$)";
    public static final String REGEXP_LOGIN = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}$";
    public static final String REGEXP_PASSWORD = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}";
    public static final String REGEXP_PHONE = "^375(44|29|33|25)\\d{7}";
    public static final String REGEXP_BIGDECIMAL_PARAM = "\\d*\\.?\\d{0,2}";
    public static final String REGEXP_ID = "[1-9]+\\d*";
    public static final String REGEXP_DATE = "([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))";
    public static final String REGEXP_QUANTITY = "[1-9]+\\d*.?0{0,2}";
//review

    public static final String REGEXP_NAME =
            "(([A-Za-z]{2,}+\\-?\\s?[A-Za-z]*)|([А-Яа-яЁё]{2,}+\\-?\\s?[А-Яа-яЁё]*))";



    public static final String REGEXP_DOSAGE_UNIT = "(мг|%)";
    public static final String REGEXP_UNIT_IN_PACK = "(шт|г|мл)";
    public static final String REGEXP_BOOLEAN_PARAM = "(true|false)";


    private ResourceBundle bundle;

    public Validator() {
        bundle = MessageManager.EN.getBundle();
    }


    public Validator(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    public boolean validateRequired(String name, String value, String regexp, HashMap<String, String> errors) {
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

        // return matchString != null ? matchString.length() == value.length() : false;
        return true;

    }

    public boolean validateRequired(String value, String regexp) {
        if (value == null || value.isEmpty()) {

            return false;
        }
        value = value.trim();
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(value.trim());
        String matchString = null;
        if (matcher.find()) {
            matchString = matcher.group();
        }
//        if(matchString==null||matchString!=null&&matchString.length() != value.length() ){
//            return false;
//        }

        return matchString != null ? matchString.length() == value.length() : false;

    }

    public boolean validateNotRequired(String name, String value, String regexp, HashMap<String, String> errors) {
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

        // return matchString != null ? matchString.length() == value.length() : false;
        return true;

    }

//    boolean validateMoneyParam(String moneyParam){
//        if(moneyParam==null||moneyParam.isEmpty()){
//            return  false;
//        }
//        Pattern pattern=Pattern.compile(REGEXP_MONEY_PARAM);
//        String mathString=null;
//        Matcher matcher=pattern.matcher(mathString.trim());
//        if(matcher.find()){
//            moneyParam=matcher.group();
//        }
//        if(matcher==null){
//            return false;
//        }else{
//            return  moneyParam.length()==money.length();
//        }
//    }
}
