package by.teplouhova.chemist.validator;


public class RegexpConstant {


    public static final String REGEXP_NAME_USER = "(^[A-Za-z]{2,}\\s*$)|(^[А-Яа-яЁё]{2,}\\s*$)";
    public static final String REGEXP_SURNAME_USER =
            "(^[A-Za-z]{2,}\\-?([A-Za-z]{2,})?\\s*$)|(^[А-Яа-яЁё]{2,}\\-?([А-Яа-яЁё]{2,})?\\s*$)";
    public static final String REGEXP_LOGIN = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}$";
    public static final String REGEXP_PASSWORD = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}";
    public static final String REGEXP_PHONE = "^375(44|29|33|25)\\d{7}";
    public static final String REGEXP_BIGDECIMAL_PARAM = "^[1-9]+\\d*\\.?\\d{0,2}";
    public static final String REGEXP_ID = "^[1-9]+\\d*$";
    public static final String REGEXP_DATE = "([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))";
    public static final String REGEXP_QUANTITY = "[1-9]+\\d*.?0{0,2}";
    public static final String REGEXP_MEDICINE_NAME =
            "(^[A-Za-z]{2,}\\-?\\s?[A-Za-z]*\\s*$)|(^[А-Яа-яЁё]{2,}\\-?\\s?[А-Яа-яЁё]*\\s*$)";
    public static final String REGEXP_DOSAGE_UNIT = "(mg|%)";
    public static final String REGEXP_UNIT_IN_PACK = "(pcs|g|ml)";
    public static final String REGEXP_BOOLEAN_PARAM = "(true|false)";




}
