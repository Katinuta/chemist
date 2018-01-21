package by.teplouhova.chemist.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public static final String REGEXP_NAME =
            "(([A-Za-z]{2,}+\\-?\\s?[A-Za-z]*)|([А-Яа-яЁё]{2,}+\\-?\\s?[А-Яа-яЁё]*))";
    public static final String REGEXP_BIGDECIMAL_PARAM = "\\d*\\.?\\d{0,2}";
    public static final String REGEXP_ID = "[1-9]+\\d*";
    public static final String REGEXP_QUANTITY = "[1-9]+\\d*.?0{0,2}";
    public static final String REGEXP_DOSAGE_UNIT = "(мг|%)";
    public static final String REGEXP_UNIT_IN_PACK = "(шт|г|мл)";
    public static final String REGEXP_BOOLEAN_PARAM = "(true|false)";


    public boolean validateRequired(String name, String regexp) {
        if (name == null || name.isEmpty()) {
            return false;
        }
        name = name.trim();
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(name.trim());
        String matchString = null;
        if (matcher.find()) {
            matchString = matcher.group();
        }
        return matchString != null ? matchString.length() == name.length() : false;

    }

    public boolean validateNotRequired(String name, String regexp) {
        if (name == null || name.isEmpty()) {
            return true;
        }
        // name=name.trim();
        Pattern pattern = Pattern.compile(regexp);
        String matchString = null;
        Matcher matcher = pattern.matcher(name.trim());
        if (matcher.find()) {
            matchString = matcher.group();
        }

        return matchString != null ? matchString.length() == name.length() : false;

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
