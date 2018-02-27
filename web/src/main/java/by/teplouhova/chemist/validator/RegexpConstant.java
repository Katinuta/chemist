package by.teplouhova.chemist.validator;

/**
 * The Class RegexpConstant.
 */
public class RegexpConstant {

    /**
     * The Constant REGEXP_NAME_USER.
     */
    public static final String REGEXP_NAME_USER = "(^[A-Za-z]{2,}\\s*$)|(^[А-Яа-яЁё]{2,}\\s*$)";

    /**
     * The Constant REGEXP_SURNAME_USER.
     */
    public static final String REGEXP_SURNAME_USER =
            "(^[A-Za-z]{2,}\\s*$)|(^[А-Яа-яЁё]{2,}\\s*$)";
    /**
     * The Constant REGEXP_LOGIN.
     */
    public static final String REGEXP_LOGIN = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}$";

    /**
     * The Constant REGEXP_PASSWORD.
     */
    public static final String REGEXP_PASSWORD = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}";

    /**
     * The Constant REGEXP_PHONE.
     */
    public static final String REGEXP_PHONE = "^375(44|29|33|25)\\d{7}";

    /**
     * The Constant REGEXP_BIGDECIMAL_PARAM.
     */
    public static final String REGEXP_BIGDECIMAL_PARAM = "^[1-9]+\\d*\\.?\\d{0,2}";

    /**
     * The Constant REGEXP_ID.
     */
    public static final String REGEXP_ID = "^[1-9]+\\d*$";

    /**
     * The Constant REGEXP_DATE.
     */
    public static final String REGEXP_DATE = "([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))";

    /**
     * The Constant REGEXP_QUANTITY.
     */
    public static final String REGEXP_QUANTITY = "[1-9]+\\d*.?0{0,2}";

    /**
     * The Constant REGEXP_MEDICINE_NAME.
     */
    public static final String REGEXP_MEDICINE_NAME =
            "(^[A-Za-z]{2,}\\s?[A-Za-z]*\\s*$)|(^[А-Яа-яЁё]{2,}\\s?[А-Яа-яЁё]*\\s*$)";

    /**
     * The Constant REGEXP_DOSAGE_UNIT.
     */
    public static final String REGEXP_DOSAGE_UNIT = "(mg|%)";

    /**
     * The Constant REGEXP_UNIT_IN_PACK.
     */
    public static final String REGEXP_UNIT_IN_PACK = "(pcs|g|ml)";

    /**
     * The Constant REGEXP_BOOLEAN_PARAM.
     */
    public static final String REGEXP_BOOLEAN_PARAM = "(true|false)";

}
