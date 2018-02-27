package test.by.teplouhova.chemist.validator;

import by.teplouhova.chemist.validator.Validator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;

import static org.testng.Assert.assertFalse;
import static test.by.teplouhova.chemist.validator.ParameterName.*;

public class MedicineValidatorInvalidTest {


    @DataProvider(name = "nameInvalid")
    public Object[][] createDataName() {
        HashMap<String, String> medicineFirst = new HashMap<>();
        medicineFirst.put(PARAM_MEDICINE_NAME, null);
        HashMap<String, String> medicineSecond = new HashMap<>();
        medicineSecond.put(PARAM_MEDICINE_NAME, "");
        HashMap<String, String> medicineThird = new HashMap<>();
        medicineThird.put(PARAM_MEDICINE_NAME, "Спазмалгон plus");
        Object[][] data = new Object[][]{{medicineFirst}, {medicineSecond}, {medicineThird}};
        return data;
    }
    @Test(dataProvider = "nameInvalid")
    public void isNameInvalidTest(HashMap<String, String> params) {
        boolean actual = new Validator().isValid(params);
        assertFalse(actual);
    }

    @DataProvider(name = "idInvalid")
    public Object[][] createDataId() {
        HashMap<String, String> medicineFirst = new HashMap<>();
        medicineFirst.put(PARAM_MEDICINE_ID, "");
        HashMap<String, String> medicineSecond = new HashMap<>();
        medicineSecond.put(PARAM_MEDICINE_ID, null);
        HashMap<String, String> medicineThird = new HashMap<>();
        medicineThird.put(PARAM_MEDICINE_ID, "-35");
        Object[][] data = new Object[][]{{medicineFirst}, {medicineSecond}, {medicineThird}};
        return data;
    }

    @Test(dataProvider = "idInvalid")
    public void isIdInvalidTest(HashMap<String, String> params) {
        boolean actual = new Validator().isValid(params);
        assertFalse(actual);
    }

    @DataProvider(name = "priceInvalid")
    public Object[][] createDataPrice() {
        HashMap<String, String> medicineFirst = new HashMap<>();
        medicineFirst.put(PARAM_PRICE, null);
        HashMap<String, String> medicineSecond = new HashMap<>();
        medicineSecond.put(PARAM_PRICE, "g");
        HashMap<String, String> medicineThird = new HashMap<>();
        medicineThird.put(PARAM_PRICE, null);
        Object[][] data = new Object[][]{{medicineFirst}, {medicineSecond}, {medicineThird}};
        return data;
    }

    @Test(dataProvider = "priceInvalid")
    public void isPriceInvalidTest(HashMap<String, String> params) {
        boolean actual = new Validator().isValid(params);
        assertFalse(actual);
    }

    @DataProvider(name = "analogIdInvalid")
    public Object[][] createDataAnalogId() {
        HashMap<String, String> medicineFirst = new HashMap<>();
        medicineFirst.put(PARAM_ANALOG_MEDICINE_ID, "-15");
        Object[][] data = new Object[][]{{medicineFirst}};
        return data;
    }

    @Test(dataProvider = "analogIdInvalid")
    public void isAnalogIdInvalidTest(HashMap<String, String> params) {
        boolean actual = new Validator().isValid(params);
        assertFalse(actual);
    }

    @DataProvider(name = "producerIdInvalid")
    public Object[][] createDataProducerId() {
        HashMap<String, String> medicineFirst = new HashMap<>();
        medicineFirst.put(PARAM_PRODUCER_ID, "-15");
        HashMap<String, String> medicineSecond = new HashMap<>();
        medicineSecond.put(PARAM_PRODUCER_ID, "");
        HashMap<String, String> medicineThird = new HashMap<>();
        medicineThird.put(PARAM_PRODUCER_ID, null);
        Object[][] data = new Object[][]{{medicineFirst}, {medicineSecond}, {medicineThird}};
        return data;
    }

    @Test(dataProvider = "producerIdInvalid")
    public void isProducerIdInvalidTest(HashMap<String, String> params) {
        boolean actual = new Validator().isValid(params);
        assertFalse(actual);
    }

    @DataProvider(name = "releaseFormIdInvalid")
    public Object[][] createDataReleaseFormId() {
        HashMap<String, String> medicineSecond = new HashMap<>();
        medicineSecond.put(PARAM_RELEASE_FORM_ID, "");
        HashMap<String, String> medicineThird = new HashMap<>();
        medicineThird.put(PARAM_RELEASE_FORM_ID, null);
        HashMap<String, String> medicineFirst = new HashMap<>();
        medicineFirst.put(PARAM_RELEASE_FORM_ID, "-15");
        Object[][] data = new Object[][]{{medicineFirst}, {medicineSecond}, {medicineThird}};
        return data;
    }

    @Test(dataProvider = "releaseFormIdInvalid")
    public void isReleaseFormIdInvalidTest(HashMap<String, String> params) {
        boolean actual = new Validator().isValid(params);
        assertFalse(actual);
    }

    @DataProvider(name = "unitInPackageInvalid")
    public Object[][] createDataUnitInPackagemId() {
        HashMap<String, String> medicineSecond = new HashMap<>();
        medicineSecond.put(PARAM_UNIT_IN_PACKAGE, "");
        HashMap<String, String> medicineFirst = new HashMap<>();
        medicineFirst.put(PARAM_UNIT_IN_PACKAGE, "mg");
        HashMap<String, String> medicineThird = new HashMap<>();
        medicineThird.put(PARAM_UNIT_IN_PACKAGE, null);
        Object[][] data = new Object[][]{{medicineFirst}, {medicineSecond}, {medicineThird}};
        return data;
    }

    @Test(dataProvider = "unitInPackageInvalid")
    public void isUnitInPackageInvalidTest(HashMap<String, String> params) {
        boolean actual = new Validator().isValid(params);
        assertFalse(actual);
    }

    @DataProvider(name = "quantityInPackageInvalid")
    public Object[][] createDataQuantityInPackageId() {
        HashMap<String, String> medicineSecond = new HashMap<>();
        medicineSecond.put(PARAM_QUANTITY_IN_PACKAGE, "0.00");
        HashMap<String, String> medicineFirst = new HashMap<>();
        medicineFirst.put(PARAM_QUANTITY_IN_PACKAGE, "");
        HashMap<String, String> medicineThird = new HashMap<>();
        medicineThird.put(PARAM_QUANTITY_IN_PACKAGE, null);
        Object[][] data = new Object[][]{{medicineFirst}, {medicineSecond}, {medicineThird}};
        return data;
    }

    @Test(dataProvider = "quantityInPackageInvalid")
    public void isQuantityInPackageInvalidTest(HashMap<String, String> params) {
        boolean actual = new Validator().isValid(params);
        assertFalse(actual);
    }


    @DataProvider(name = "quantityPackagesInvalid")
    public Object[][] createDataQuantityInPackages() {
        HashMap<String, String> medicineSecond = new HashMap<>();
        medicineSecond.put(PARAM_QUANTITY_PACKAGES, "0.01");
        HashMap<String, String> medicineThird = new HashMap<>();
        medicineThird.put(PARAM_QUANTITY_PACKAGES, null);
        HashMap<String, String> medicineFirst = new HashMap<>();
        medicineFirst.put(PARAM_QUANTITY_PACKAGES, "-1");
        Object[][] data = new Object[][]{{medicineFirst}, {medicineSecond}, {medicineThird}};
        return data;
    }

    @Test(dataProvider = "quantityPackagesInvalid")
    public void isQuantityPackagesInvalidTest(HashMap<String, String> params) {
        boolean actual = new Validator().isValid(params);
        assertFalse(actual);
    }


    @DataProvider(name = "dosageSizeInvalid")
    public Object[][] createDataDosageSize() {
        HashMap<String, String> medicineSecond = new HashMap<>();
        HashMap<String, String> medicineFirst = new HashMap<>();
        HashMap<String, String> medicineThird = new HashMap<>();
        medicineSecond.put(PARAM_DOSAGE_SIZE, "0.0001");
        medicineThird.put(PARAM_DOSAGE_SIZE, "-1");
        medicineFirst.put(PARAM_DOSAGE_SIZE, "dosage");
        Object[][] data = new Object[][]{{medicineFirst}, {medicineSecond}, {medicineThird}};
        return data;
    }

    @Test(dataProvider = "dosageSizeInvalid")
    public void isDosageSizeInvalidTest(HashMap<String, String> params) {
        boolean actual =new Validator().isValid(params);
        assertFalse(actual);
    }

    @DataProvider(name = "dosageUnitInvalid")
    public Object[][] createDataDosageUnit() {
        HashMap<String, String> medicineFirst = new HashMap<>();
        HashMap<String, String> medicineSecond = new HashMap<>();
        medicineFirst.put(PARAM_UNIT_DOSAGE, "dosage");
        medicineSecond.put(PARAM_UNIT_DOSAGE, "1");
        HashMap<String, String> medicineThird = new HashMap<>();
        medicineThird.put(PARAM_UNIT_DOSAGE, "-1");
        Object[][] data = new Object[][]{{medicineFirst}, {medicineSecond}, {medicineThird}};
        return data;
    }

    @Test(dataProvider = "dosageUnitInvalid")
    public void isDosageUnitInvalidTest(HashMap<String, String> params) {
        boolean actual = new Validator().isValid(params);
        assertFalse(actual);
    }

    @DataProvider(name = "needPrescriptionInvalid")
    public Object[][] createDataNeedPrescription() {
        HashMap<String, String> medicineFirst = new HashMap<>();
        HashMap<String, String> medicineSecond = new HashMap<>();
        medicineFirst.put(PARAM_NEED_PRESCRIPTION, "on");
        HashMap<String, String> medicineThird = new HashMap<>();
        medicineSecond.put(PARAM_NEED_PRESCRIPTION, "");
        medicineThird.put(PARAM_NEED_PRESCRIPTION, null);
        Object[][] data = new Object[][]{{medicineFirst}, {medicineSecond}, {medicineThird}};
        return data;
    }

    @Test(dataProvider = "needPrescriptionInvalid")
    public void isNeedPrescriptionInvalidTest(HashMap<String, String> params) {
        boolean actual = new Validator().isValid(params);
        assertFalse(actual);
    }


}
