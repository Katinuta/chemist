package test.by.teplouhova.chemist.validator;

import by.teplouhova.chemist.validator.Validator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static test.by.teplouhova.chemist.validator.ParameterName.*;

public class UserValidatorValidTest {
    @DataProvider(name = "nameValid")
    public Object[][] createDataName() {
        HashMap<String, String> medicineFirst = new HashMap<>();
        medicineFirst.put(PARAM_USER_NAME, "ALEX");
        HashMap<String, String> medicineSecond = new HashMap<>();
        medicineSecond.put(PARAM_USER_NAME, "Alex");
        HashMap<String, String> medicineThird = new HashMap<>();
        medicineThird.put(PARAM_USER_NAME, "Антон");
        Object[][] data = new Object[][]{{medicineFirst}, {medicineSecond}, {medicineThird}};
        return data;
    }

    @Test(dataProvider = "nameValid")
    public void isNameInvalidTest(HashMap<String, String> params) {
        boolean actual = new Validator().isValid(params);
        assertTrue(actual);
    }


    @DataProvider(name = "loginValid")
    public Object[][] createDataLogin() {
        HashMap<String, String> medicineFirst = new HashMap<>();
        medicineFirst.put(PARAM_lOGIN, "alex@tut.by");
        HashMap<String, String> medicineSecond = new HashMap<>();
        medicineSecond.put(PARAM_lOGIN, "alex111@tut.by");
        Object[][] data = new Object[][]{{medicineFirst}, {medicineSecond}};
        return data;
    }

    @Test(dataProvider = "loginValid")
    public void isLoginValidTest(HashMap<String, String> params) {
        boolean actual = new Validator().isValid(params);
        assertTrue(actual);
    }

    @DataProvider(name = "accountValid")
    public Object[][] createDataAccount() {
        HashMap<String, String> medicineFirst = new HashMap<>();
        medicineFirst.put(PARAM_ACCOUNT, "10.00");
        HashMap<String, String> medicineSecond = new HashMap<>();
        medicineSecond.put(PARAM_ACCOUNT, "5");
        HashMap<String, String> medicineThird = new HashMap<>();
        medicineThird.put(PARAM_ACCOUNT, "110.06");
        Object[][] data = new Object[][]{{medicineFirst}, {medicineSecond}, {medicineThird}};
        return data;
    }

    @Test(dataProvider = "accountValid")
    public void isAccountValidTest(HashMap<String, String> params) {
        boolean actual = new Validator().isValid(params);
        assertTrue(actual);
    }

    @DataProvider(name = "passwordInvalid")
    public Object[][] createDataPassword() {
        HashMap<String, String> medicineFirst = new HashMap<>();
        medicineFirst.put(PARAM_PASSWORD, "Aaaaaaaa1");
        HashMap<String, String> medicineSecond = new HashMap<>();
        medicineSecond.put(PARAM_PASSWORD, "Levlev1");
        HashMap<String, String> medicineThird = new HashMap<>();
        medicineThird.put(PARAM_PASSWORD, "ANNAanna111");
        Object[][] data = new Object[][]{{medicineFirst}, {medicineSecond}, {medicineThird}};
        return data;
    }

    @Test(dataProvider = "passwordInvalid")
    public void isPasswordValidTest(HashMap<String, String> params) {
        boolean actual = new Validator().isValid(params);
        assertTrue(actual);
    }

    @DataProvider(name = "surnameValid")
    public Object[][] createDataSurname() {
        HashMap<String, String> medicineSecond = new HashMap<>();
        medicineSecond.put(PARAM_SURNAME, "Levko");
        HashMap<String, String> medicineThird = new HashMap<>();
        medicineThird.put(PARAM_SURNAME, "Popova");
        HashMap<String, String> medicineFirst = new HashMap<>();
        medicineFirst.put(PARAM_SURNAME, "Гутько");
        Object[][] data = new Object[][]{{medicineFirst}, {medicineSecond}, {medicineThird}};
        return data;
    }

    @Test(dataProvider = "surnameValid")
    public void isSurnameValidTest(HashMap<String, String> params) {
        boolean actual = new Validator().isValid(params);
        assertTrue(actual);
    }

    @DataProvider(name = "phoneValid")
    public Object[][] createDataPhone() {
        HashMap<String, String> medicineSecond = new HashMap<>();
        medicineSecond.put(PARAM_PHONE, "375332565241");
        HashMap<String, String> medicineFirst = new HashMap<>();
        medicineFirst.put(PARAM_PHONE, "375442222222");
        HashMap<String, String> medicineThird = new HashMap<>();
        medicineThird.put(PARAM_PHONE, "375296666666");
        Object[][] data = new Object[][]{{medicineFirst}, {medicineSecond}, {medicineThird}};
        return data;
    }

    @Test(dataProvider = "phoneValid")
    public void isPhoneInvalidTest(HashMap<String, String> params) {
        boolean actual = new Validator().isValid(params);
        assertTrue(actual);
    }
}
