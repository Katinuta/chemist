package by.teplouhova.chemist.validator;

import static by.teplouhova.chemist.validator.RegexpConstant.*;
/**
 * The Enum ParameterName.
 */
public enum ParameterName {

    /** The user name. */
    USER_NAME("user_name", REGEXP_NAME_USER, true),

    /** The surname. */
    SURNAME("surname", REGEXP_SURNAME_USER, true),

    /** The login. */
    LOGIN("login", REGEXP_LOGIN, true),

    /** The password. */
    PASSWORD("password", REGEXP_PASSWORD, true),

    /** The new password. */
    NEW_PASSWORD("new_password", REGEXP_PASSWORD, true),

    /** The phone. */
    PHONE("phone", REGEXP_PHONE, true),

    /** The account. */
    ACCOUNT("account", REGEXP_BIGDECIMAL_PARAM, true),

    /** The date begin. */
    DATE_BEGIN("date_begin", REGEXP_DATE, true),

    /** The date end. */
    DATE_END("date_end", REGEXP_DATE, true),

    /** The client id. */
    CLIENT_ID("client_id", REGEXP_ID, true),

    /** The medicine id. */
    MEDICINE_ID("medicine_id", REGEXP_ID, true),

    /** The quantity. */
    QUANTITY("quantity", REGEXP_QUANTITY, true),

    /** The prescription id. */
    PRESCRIPTION_ID("prescription_id", REGEXP_ID, true),

    /** The prescrip detail id. */
    PRESCRIP_DETAIL_ID("prescrip_detail_id", REGEXP_ID, true),

    /** The medicine name. */
    MEDICINE_NAME("medicine_name", REGEXP_MEDICINE_NAME, true),

    /** The price. */
    PRICE("price", REGEXP_BIGDECIMAL_PARAM, true),

    /** The quantity packages. */
    QUANTITY_PACKAGES("quantity_packages", REGEXP_QUANTITY, true),

    /** The quantity in package. */
    QUANTITY_IN_PACKAGE("quantity_in_package", REGEXP_QUANTITY, true),

    /** The need prescription. */
    NEED_PRESCRIPTION("need_prescription", REGEXP_BOOLEAN_PARAM, true),

    /** The release form id. */
    RELEASE_FORM_ID("release_form_id", REGEXP_ID, true),

    /** The producer id. */
    PRODUCER_ID("producer_id", REGEXP_ID, true),

    /** The order id. */
    ORDER_ID("order_id", REGEXP_ID, true),

    /** The unit in package. */
    UNIT_IN_PACKAGE("unit_in_package", REGEXP_UNIT_IN_PACK, true),

    /** The dosage size. */
    DOSAGE_SIZE("dosage_size", REGEXP_BIGDECIMAL_PARAM, false),

    /** The dosage unit. */
    DOSAGE_UNIT("dosage_unit", REGEXP_DOSAGE_UNIT, false),

    /** The analog medicine id. */
    ANALOG_MEDICINE_ID("analog_medicine_id", REGEXP_ID, false),

    /** The current page. */
    CURRENT_PAGE("current_page",REGEXP_ID,true);


    /** The name. */
    private String name;

    /** The regexp. */
    private String regexp;

    /** The is required. */
    private boolean isRequired;

    /**
     * Instantiates a new parameter name.
     *
     * @param name the name
     * @param regexp the regexp
     * @param isRequired the is required
     */
    ParameterName(String name, String regexp, boolean isRequired) {
        this.name = name;
        this.regexp = regexp;
        this.isRequired = isRequired;
    }

    /**
     * Checks if is required.
     *
     * @return true, if is required
     */
    public boolean isRequired() {
        return isRequired;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the regexp.
     *
     * @return the regexp
     */
    public String getRegexp() {
        return regexp;
    }
}
