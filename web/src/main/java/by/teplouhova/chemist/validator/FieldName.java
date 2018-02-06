package by.teplouhova.chemist.validator;

import static by.teplouhova.chemist.validator.RegexpConstant.*;

public enum FieldName {
    USER_NAME("user_name", REGEXP_NAME_USER, true),
    SURNAME("surname", REGEXP_SURNAME_USER, true),
    LOGIN("login", REGEXP_LOGIN, true),
    PASSWORD("password", REGEXP_PASSWORD, true),
    NEW_PASSWORD("new_password", REGEXP_PASSWORD, true),
    PHONE("phone", REGEXP_PHONE, true),
    ACCOUNT("account", REGEXP_BIGDECIMAL_PARAM, true),
    DATE_BEGIN("date_begin", REGEXP_DATE, true),
    DATE_END("date_end", REGEXP_DATE, true),
    CLIENT_ID("client_id", REGEXP_ID, true),
    MEDICINE_ID("medicine_id", REGEXP_ID, true),
    QUANTITY("quantity", REGEXP_QUANTITY, true),
    PRESCRIPTION_ID("prescription_id", REGEXP_ID, true),
    PRESCRIP_DETAIL_ID("prescrip_detail_id", REGEXP_ID, true),
    MEDICINE_NAME("medicine_name", REGEXP_MEDICINE_NAME, true),
    PRICE("price", REGEXP_BIGDECIMAL_PARAM, true),
    QUANTITY_PACKAGES("quantity_packages", REGEXP_QUANTITY, true),
    QUANTITY_IN_PACKAGE("quantity_in_package", REGEXP_QUANTITY, true),
    NEED_PRESCRIPTION("need_prescription", REGEXP_BOOLEAN_PARAM, true),
    RELEASE_FORM_ID("release_form_id", REGEXP_ID, true),
    PRODUCER_ID("producer_id", REGEXP_ID, true),
    ORDER_ID("order_id", REGEXP_ID, true),
    UNIT_IN_PACKAGE("unit_in_package", REGEXP_UNIT_IN_PACK, true),
    DOSAGE_SIZE("dosage_size", REGEXP_BIGDECIMAL_PARAM, false),
    DOSAGE_UNIT("dosage_unit", REGEXP_DOSAGE_UNIT, false),
    ANALOG_MEDICINE_ID("analog_medicine_id", REGEXP_ID, false);


    private String name;
    private String regexp;
    private boolean isRequired;

    FieldName(String name, String regexp, boolean isRequired) {
        this.name = name;
        this.regexp = regexp;
        this.isRequired = isRequired;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public String getName() {
        return name;
    }

    public String getRegexp() {
        return regexp;
    }
}
