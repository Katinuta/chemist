package test.by.teplouhova.chemist.validator;

import by.teplouhova.chemist.validator.Validator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;

import static org.testng.Assert.assertFalse;
import static test.by.teplouhova.chemist.validator.ParameterName.*;

public class UserValidatorInvalidTest {
    @DataProvider(name = "nameInvalid")
    public Object[][] createDataName() {
        HashMap<String, String> medicineFirst = new HashMap<>();
        medicineFirst.put(PARAM_USER_NAME, null);
        HashMap<String, String> medicineSecond = new HashMap<>();
        medicineSecond.put(PARAM_USER_NAME, "");
        HashMap<String, String> medicineThird = new HashMap<>();
        medicineThird.put(PARAM_USER_NAME, "Антон антон");
        Object[][] data = new Object[][]{{medicineFirst}, {medicineSecond}, {medicineThird}};
        return data;
    }

    @Test(dataProvider = "nameInvalid")
    public void isNameInvalidTest(HashMap<String, String> params) {
        boolean actual = new Validator().isValid(params);
        assertFalse(actual);
    }


    @DataProvider(name = "loginInvalid")
    public Object[][] createDataLogin() {
        HashMap<String, String> medicineFirst = new HashMap<>();
        medicineFirst.put(PARAM_lOGIN, null);
        HashMap<String, String> medicineSecond = new HashMap<>();
        medicineSecond.put(PARAM_lOGIN, "");
        HashMap<String, String> medicineThird = new HashMap<>();
        medicineThird.put(PARAM_lOGIN, "name");
        Object[][] data = new Object[][]{{medicineFirst}, {medicineSecond}, {medicineThird}};
        return data;
    }

    @Test(dataProvider = "loginInvalid")
    public void isLoginInvalidTest(HashMap<String, String> params) {
        boolean actual = new Validator().isValid(params);
        assertFalse(actual);
    }

    @DataProvider(name = "accountInvalid")
    public Object[][] createDataAccount() {
        HashMap<String, String> medicineFirst = new HashMap<>();
        medicineFirst.put(PARAM_ACCOUNT, "0.00");
        HashMap<String, String> medicineSecond = new HashMap<>();
        medicineSecond.put(PARAM_ACCOUNT, "0");
        HashMap<String, String> medicineThird = new HashMap<>();
        medicineThird.put(PARAM_ACCOUNT, "-10.00");
        Object[][] data = new Object[][]{{medicineFirst}, {medicineSecond}, {medicineThird}};
        return data;
    }

    @Test(dataProvider = "accountInvalid")
    public void isAccountInvalidTest(HashMap<String, String> params) {
        boolean actual = new Validator().isValid(params);
        assertFalse(actual);
    }

    @DataProvider(name = "passwordInvalid")
    public Object[][] createDataPassword() {
        HashMap<String, String> medicineFirst = new HashMap<>();
        medicineFirst.put(PARAM_PASSWORD, "aaaaaaaa1");
        HashMap<String, String> medicineSecond = new HashMap<>();
        medicineSecond.put(PARAM_PASSWORD, "");
        HashMap<String, String> medicineThird = new HashMap<>();
        medicineThird.put(PARAM_PASSWORD, null);
        Object[][] data = new Object[][]{{medicineFirst}, {medicineSecond}, {medicineThird}};
        return data;
    }

    @Test(dataProvider = "passwordInvalid")
    public void isPasswordInvalidTest(HashMap<String, String> params) {
        boolean actual = new Validator().isValid(params);
        assertFalse(actual);
    }

    @DataProvider(name = "surnameInvalid")
    public Object[][] createDataSurname() {
        HashMap<String, String> medicineSecond = new HashMap<>();
        medicineSecond.put(PARAM_SURNAME, "");
        HashMap<String, String> medicineThird = new HashMap<>();
        medicineThird.put(PARAM_SURNAME, null);
        HashMap<String, String> medicineFirst = new HashMap<>();
        medicineFirst.put(PARAM_SURNAME, "Гутьko");
        Object[][] data = new Object[][]{{medicineFirst}, {medicineSecond}, {medicineThird}};
        return data;
    }

    @Test(dataProvider = "surnameInvalid")
    public void isSurnameInvalidTest(HashMap<String, String> params) {
        boolean actual = new Validator().isValid(params);
        assertFalse(actual);
    }

    @DataProvider(name = "phoneInvalid")
    public Object[][] createDataPhone() {
        HashMap<String, String> medicineSecond = new HashMap<>();
        medicineSecond.put(PARAM_PHONE, "");
        HashMap<String, String> medicineFirst = new HashMap<>();
        medicineFirst.put(PARAM_PHONE, "222222222");
        HashMap<String, String> medicineThird = new HashMap<>();
        medicineThird.put(PARAM_PHONE, null);
        Object[][] data = new Object[][]{{medicineFirst}, {medicineSecond}, {medicineThird}};
        return data;
    }

    @Test(dataProvider = "phoneInvalid")
    public void isPhoneInvalidTest(HashMap<String, String> params) {
        boolean actual = new Validator().isValid(params);
        assertFalse(actual);
    }

}
