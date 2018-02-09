package test.by.teplouhova.chemist.validator;

import by.teplouhova.chemist.validator.Validator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;

import static org.testng.Assert.assertFalse;
import static test.by.teplouhova.chemist.validator.ParameterName.*;
import static test.by.teplouhova.chemist.validator.ParameterName.PARAM_PHONE;

public class PrescriptionValidatorInvalidTest {



    @DataProvider(name = "clientIdInvalid")
    public Object[][] createDataAccount() {
        HashMap<String, String> medicineFirst = new HashMap<>();
        medicineFirst.put(PARAM_CLIENT_ID, "0.00");
        HashMap<String, String> medicineSecond = new HashMap<>();
        medicineSecond.put(PARAM_CLIENT_ID, "-1");
        HashMap<String, String> medicineThird = new HashMap<>();
        medicineThird.put(PARAM_CLIENT_ID, "");
        HashMap<String, String> medicineFourth = new HashMap<>();
        medicineFourth.put(PARAM_CLIENT_ID, null);
        Object[][] data = new Object[][]{{medicineFirst}, {medicineSecond}, {medicineThird},{medicineFourth}};
        return data;
    }

    @Test(dataProvider = "clientIdInvalid")
    public void isClientIdInvalidTest(HashMap<String, String> params) {
        boolean actual = new Validator().isValid(params);
        assertFalse(actual);
    }

    @DataProvider(name = "dateBeginInvalid")
    public Object[][] createDataDateBegin() {
        HashMap<String, String> medicineFirst = new HashMap<>();
        medicineFirst.put(PARAM_DATE_BEGIN, "10/02/2017");
        HashMap<String, String> medicineSecond = new HashMap<>();
        medicineSecond.put(PARAM_DATE_BEGIN, "");
        HashMap<String, String> medicineThird = new HashMap<>();
        medicineThird.put(PARAM_DATE_BEGIN, null);
        Object[][] data = new Object[][]{{medicineFirst}, {medicineSecond}, {medicineThird}};
        return data;
    }

    @Test(dataProvider = "dateBeginInvalid")
    public void isDateBeginInvalidTest(HashMap<String, String> params) {
        boolean actual = new Validator().isValid(params);
        assertFalse(actual);
    }

    @DataProvider(name = "dateEndInvalid")
    public Object[][] createDataSurname() {
        HashMap<String, String> medicineFirst = new HashMap<>();
        medicineFirst.put(PARAM_DATE_END, "41-13-2017");
        HashMap<String, String> medicineSecond = new HashMap<>();
        medicineSecond.put(PARAM_DATE_END, "");
        HashMap<String, String> medicineThird = new HashMap<>();
        medicineThird.put(PARAM_DATE_END, null);
        Object[][] data = new Object[][]{{medicineFirst}, {medicineSecond}, {medicineThird}};
        return data;
    }

    @Test(dataProvider = "dateEndInvalid")
    public void isDateEndInvalidTest(HashMap<String, String> params) {
        boolean actual = new Validator().isValid(params);
        assertFalse(actual);
    }

    @DataProvider(name = "quantityInvalid")
    public Object[][] createDataPhone() {
        HashMap<String, String> medicineSecond = new HashMap<>();
        medicineSecond.put(PARAM_QUANTITY, "");
        HashMap<String, String> medicineFirst = new HashMap<>();
        medicineFirst.put(PARAM_QUANTITY, "-33");
        HashMap<String, String> medicineThird = new HashMap<>();
        medicineThird.put(PARAM_QUANTITY, null);
        Object[][] data = new Object[][]{{medicineFirst}, {medicineSecond}, {medicineThird}};
        return data;
    }

    @Test(dataProvider = "quantityInvalid")
    public void isQuantityInvalidTest(HashMap<String, String> params) {
        boolean actual = new Validator().isValid(params);
        assertFalse(actual);
    }
}
