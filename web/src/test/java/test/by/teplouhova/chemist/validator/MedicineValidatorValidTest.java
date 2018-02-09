package test.by.teplouhova.chemist.validator;

import by.teplouhova.chemist.validator.Validator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;

import static org.testng.Assert.assertTrue;
import static test.by.teplouhova.chemist.validator.ParameterName.*;

public class MedicineValidatorValidTest {


    @DataProvider(name = "nameInvalid")
    public Object[][] createDataName() {
        HashMap<String, String> medicineFirst = new HashMap<>();
        medicineFirst.put(PARAM_MEDICINE_NAME, "LINEX");
        HashMap<String, String> medicineSecond = new HashMap<>();
        medicineSecond.put(PARAM_MEDICINE_NAME, "Линекс форте");
        HashMap<String, String> medicineThird = new HashMap<>();
        medicineThird.put(PARAM_MEDICINE_NAME, "Спазмалгон-плюс");
        Object[][] data = new Object[][]{{medicineFirst}, {medicineSecond}, {medicineThird}};
        return data;
    }

    @Test(dataProvider = "nameInvalid")
    public void isNameValidTest(HashMap<String, String> params) {
        boolean actual = new Validator().isValid(params);
        assertTrue(actual);
    }

    @DataProvider(name = "idValid")
    public Object[][] createDataId() {
        HashMap<String, String> medicineFirst = new HashMap<>();
        medicineFirst.put(PARAM_MEDICINE_ID, "100");
        HashMap<String, String> medicineSecond = new HashMap<>();
        medicineSecond.put(PARAM_MEDICINE_ID, "35");
        HashMap<String, String> medicineThird = new HashMap<>();
        medicineThird.put(PARAM_MEDICINE_ID, "1");
        Object[][] data = new Object[][]{{medicineFirst}, {medicineSecond}, {medicineThird}};
        return data;
    }

    @Test(dataProvider = "idValid")
    public void isIdValidTest(HashMap<String, String> params) {
        boolean actual =new Validator().isValid(params);
        assertTrue(actual);
    }

    @DataProvider(name = "priceValid")
    public Object[][] createDataPrice() {
        HashMap<String, String> medicineFirst = new HashMap<>();
        medicineFirst.put(PARAM_PRICE, "1.00");
        HashMap<String, String> medicineSecond = new HashMap<>();
        medicineSecond.put(PARAM_PRICE, "10.00");
        HashMap<String, String> medicineThird = new HashMap<>();
        medicineThird.put(PARAM_PRICE, "10");
        Object[][] data = new Object[][]{{medicineFirst}, {medicineSecond}, {medicineThird}};
        return data;
    }

    @Test(dataProvider = "priceValid")
    public void isPriceValidTest(HashMap<String, String> params) {
        boolean actual = new Validator().isValid(params);
        assertTrue(actual);
    }

    @DataProvider(name = "analogIdValid")
    public Object[][] createDataAnalogId() {
        HashMap<String, String> medicineFirst = new HashMap<>();
        medicineFirst.put(PARAM_ANALOG_MEDICINE_ID, "15");
        Object[][] data = new Object[][]{{medicineFirst}};
        return data;
    }

    @Test(dataProvider = "analogIdValid")
    public void isAnalogIdValidTest(HashMap<String, String> params) {
        boolean actual = new Validator().isValid(params);
        assertTrue(actual);
    }

    @DataProvider(name = "producerIdValid")
    public Object[][] createDataProducerId() {
        HashMap<String, String> medicineFirst = new HashMap<>();
        medicineFirst.put(PARAM_PRODUCER_ID, "15");
        HashMap<String, String> medicineSecond = new HashMap<>();
        medicineSecond.put(PARAM_PRODUCER_ID, "100");
        HashMap<String, String> medicineThird = new HashMap<>();
        medicineThird.put(PARAM_PRODUCER_ID, "1");
        Object[][] data = new Object[][]{{medicineFirst}, {medicineSecond}, {medicineThird}};
        return data;
    }

    @Test(dataProvider = "producerIdValid")
    public void isProducerIdValidTest(HashMap<String, String> params) {
        boolean actual = new Validator().isValid(params);
        assertTrue(actual);
    }

    @DataProvider(name = "releaseFormIdInvalid")
    public Object[][] createDataReleaseFormId() {
        HashMap<String, String> medicineSecond = new HashMap<>();
        medicineSecond.put(PARAM_RELEASE_FORM_ID, "1");
        HashMap<String, String> medicineThird = new HashMap<>();
        medicineThird.put(PARAM_RELEASE_FORM_ID, "100");
        HashMap<String, String> medicineFirst = new HashMap<>();
        medicineFirst.put(PARAM_RELEASE_FORM_ID, "10");
        Object[][] data = new Object[][]{{medicineFirst}, {medicineSecond}, {medicineThird}};
        return data;
    }

    @Test(dataProvider = "releaseFormIdInvalid")
    public void isReleaseFormIdValidTest(HashMap<String, String> params) {
        boolean actual = new Validator().isValid(params);
        assertTrue(actual);
    }

    @DataProvider(name = "unitInPackageValid")
    public Object[][] createDataUnitInPackagemId() {
        HashMap<String, String> medicineSecond = new HashMap<>();
        medicineSecond.put(PARAM_UNIT_IN_PACKAGE, "pcs");
        HashMap<String, String> medicineFirst = new HashMap<>();
        medicineFirst.put(PARAM_UNIT_IN_PACKAGE, "ml");
        HashMap<String, String> medicineThird = new HashMap<>();
        medicineThird.put(PARAM_UNIT_IN_PACKAGE, "g");
        Object[][] data = new Object[][]{{medicineFirst}, {medicineSecond}, {medicineThird}};
        return data;
    }

    @Test(dataProvider = "unitInPackageValid")
    public void isUnitInPackageValidTest(HashMap<String, String> params) {
        boolean actual = new Validator().isValid(params);
        assertTrue(actual);
    }

    @DataProvider(name = "quantityInPackageValid")
    public Object[][] createDataQuantityInPackageId() {
        HashMap<String, String> medicineSecond = new HashMap<>();
        medicineSecond.put(PARAM_QUANTITY_IN_PACKAGE, "10.00");
        HashMap<String, String> medicineFirst = new HashMap<>();
        medicineFirst.put(PARAM_QUANTITY_IN_PACKAGE, "1");
        HashMap<String, String> medicineThird = new HashMap<>();
        medicineThird.put(PARAM_QUANTITY_IN_PACKAGE,"1.00");
        Object[][] data = new Object[][]{{medicineFirst}, {medicineSecond}, {medicineThird}};
        return data;
    }

    @Test(dataProvider = "quantityInPackageValid")
    public void isQuantityInPackageValidTest(HashMap<String, String> params) {
        boolean actual = new Validator().isValid(params);
        assertTrue(actual);
    }


    @DataProvider(name = "quantityPackagesValid")
    public Object[][] createDataQuantityInPackages() {
        HashMap<String, String> medicineSecond = new HashMap<>();
        medicineSecond.put(PARAM_QUANTITY_PACKAGES, "1.00");
        HashMap<String, String> medicineThird = new HashMap<>();
        medicineThird.put(PARAM_QUANTITY_PACKAGES, "10.0");
        HashMap<String, String> medicineFirst = new HashMap<>();
        medicineFirst.put(PARAM_QUANTITY_PACKAGES, "1");
        Object[][] data = new Object[][]{{medicineFirst}, {medicineSecond}, {medicineThird}};
        return data;
    }

    @Test(dataProvider = "quantityPackagesValid")
    public void isQuantityPackagesValidTest(HashMap<String, String> params) {
        boolean actual = new Validator().isValid(params);
        assertTrue(actual);
    }


    @DataProvider(name = "dosageSizeValid")
    public Object[][] createDataDosageSize() {
        HashMap<String, String> medicineSecond = new HashMap<>();
        HashMap<String, String> medicineFirst = new HashMap<>();
        HashMap<String, String> medicineThird = new HashMap<>();
        medicineSecond.put(PARAM_DOSAGE_SIZE, "500.01");
        medicineThird.put(PARAM_DOSAGE_SIZE, null);
        medicineFirst.put(PARAM_DOSAGE_SIZE, "");
        Object[][] data = new Object[][]{{medicineFirst}, {medicineSecond}, {medicineThird}};
        return data;
    }

    @Test(dataProvider = "dosageSizeValid")
    public void isDosageSizeValidTest(HashMap<String, String> params) {
        boolean actual = new Validator().isValid(params);
        assertTrue(actual);
    }

    @DataProvider(name = "dosageUnitValid")
    public Object[][] createDataDosageUnit() {
        HashMap<String, String> medicineFirst = new HashMap<>();
        HashMap<String, String> medicineSecond = new HashMap<>();
        medicineFirst.put(PARAM_UNIT_DOSAGE, "%");
        medicineSecond.put(PARAM_UNIT_DOSAGE, "mg");
        HashMap<String, String> medicineThird = new HashMap<>();
        medicineThird.put(PARAM_UNIT_DOSAGE, "");
        HashMap<String, String> medicineFourth = new HashMap<>();
        medicineThird.put(PARAM_UNIT_DOSAGE, null);
        Object[][] data = new Object[][]{{medicineFirst}, {medicineSecond}, {medicineThird},{medicineFourth}};
        return data;
    }

    @Test(dataProvider = "dosageUnitValid")
    public void isDosageUnitValidTest(HashMap<String, String> params) {
        boolean actual = new Validator().isValid(params);
        assertTrue(actual);
    }

    @DataProvider(name = "needPrescriptionValid")
    public Object[][] createDataNeedPrescription() {
        HashMap<String, String> medicineFirst = new HashMap<>();
        HashMap<String, String> medicineSecond = new HashMap<>();
        medicineFirst.put(PARAM_NEED_PRESCRIPTION, "true");
        medicineSecond.put(PARAM_NEED_PRESCRIPTION, "false");
        Object[][] data = new Object[][]{{medicineFirst}, {medicineSecond}};
        return data;
    }

    @Test(dataProvider = "needPrescriptionValid")
    public void isNeedPrescriptionValidTest(HashMap<String, String> params) {
        boolean actual = new Validator().isValid(params);
        assertTrue(actual);
    }

}
