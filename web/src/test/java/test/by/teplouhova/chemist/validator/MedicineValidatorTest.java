package test.by.teplouhova.chemist.validator;

import by.teplouhova.chemist.validator.MedicineValidator;
import by.teplouhova.chemist.validator.Validator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MedicineValidatorTest {

    private MedicineValidator validator;

    @BeforeClass
    public void before(){
        validator=new MedicineValidator();
    }
    @DataProvider(name="medicineValid")
    public Object[][] createDadaValid(){
        return new Object [][]{
                {"1","Амоклав","10.01","10","2","%","500.00","10","шт","6","8",true},
                {"1","Амоклав","10","10","2","%","500.00","10","шт","6","8",true},
                {"1","Амоклав","10.01","10",null,"%","500.00","10","шт","6","8",true},
                {"1","Амоклав","10.01","10","","%","500.00","10","шт","6","8",true},
                {"1","Амоклав","10.01","10","","","","10","шт","6","8",true},
                {"1","Амоклав","10.01","10","2",null,null,"10","шт","6","8",true},
                {"1","Амоклав","10.01","10","2","мг","500.00","10","шт","6","8",true}
        };
    }
    @DataProvider(name="medicineInValid")
    public Object[][] createDadaInValid(){
        return new Object [][]{
                {"-1","Амоклав","10.01","10","2","%","500.00","10","шт","6","8"},
                {"1","Амоклав","10.01f","10",null,"%","500.00","10","шт","6","8"},
                {"1","Амоклав","10.01","-10.0",null,"%","500.00","10","шт","6","8"},
                {"1","Амоклав","10.01","10","-2a","%","500.00","10","шт","6","8"},
                {"1","Амоклав","10.01","10","-2","%","500.00","10","шт","6","8"},
                {"1","Амоклав","10.01","10","2","г","500.00","10","шт","6","8"},
                {"1","Амоклав","10.01","10","2","г","-500","10","шт","6","8"},
                {"1","Амоклав","10.01","10","2","г","500","10","шт","6","8"},
                {"1","Амоклав","10.01","10","2","г","500.00","10","шт","6","8"},
                {"1","Амоклав","10.01","10","2","мг","-500.00","10","шт","6","8"},
                {"1","Амоклав","10.01","10","2","","500.00","10","шт","6","8"},
                {"1","Амоклав","10.01","10","2","%","","10","шт","6","8"},
                {"1","Амоклав","10.01","10","2",null,"500.00","10","шт","6","8"},
                {"1","Амоклав","10.01","10","2","%",null,"10","шт","6","8"},
                {"1","А мо 3клав","10.01","10","2","%","500.00","10","шт","6","8"},
                {"1","Амоклав","10.01","10","2","мл","500.00","10","%","6","8"},
                {"1","Амоклав","10.01","10","2","мл","500.00","10","шт","6","8"},
                {"1","Амоклав","10.01","10","2","%","500.00","10","шт","-6.0f","8"},
                {"1","Амоклав","10.01","10","2","%","500.00","10","шт","6","-8.0g"},
                {"1","Амоклав","10.01","10","2","%","500.00","-10","шт","6","8"},
        };
    }

    @Test(dataProvider = "medicineValid")
    public void validateStringTestTrue(String medicineId, String name, String price, String quantityPackages,
                                       String analogId, String unitDosage, String dosageSize, String quantityInPack,
                                       String unitInPack, String releaseFormId,
                                       String producerId,boolean expected){
        boolean actual=validator.validateMedicine(medicineId,name, price,  quantityPackages,
                 analogId,  unitDosage, dosageSize,quantityInPack,unitInPack,
                releaseFormId,  producerId);
        Assert.assertEquals(actual,expected);
    }
    @Test(dataProvider = "medicineInValid")
    public void validateStringTestFalse(String medicineId, String name, String price, String quantityPackages,
                                       String analogId, String unitDosage, String dosageSize, String quantityInPack,
                                       String unitInPack, String releaseFormId,
                                       String producerId){
        boolean actual=validator.validateMedicine(medicineId,name, price,  quantityPackages,
                analogId,  unitDosage, dosageSize,quantityInPack,unitInPack,
                releaseFormId,  producerId);
        Assert.assertFalse(actual);
    }
//    @DataProvider(name="medicineInValid")
//    public Object[][] createDadaInvalid(){
//        return new Object [][]{
//                {"", Validator.REGEXP_NAME,false},
//                {null, Validator.REGEXP_NAME,false},
//                {" 1ann",Validator.REGEXP_NAME,false},
//                {"An nn",Validator.REGEXP_NAME,false},
//                {"Ann!!",Validator.REGEXP_NAME,false},
//                {"-100.01",Validator.REGEXP_BIGDECIMAL_PARAM,false},
//                {"A00.01",Validator.REGEXP_BIGDECIMAL_PARAM,false},
//                {"-100",Validator.REGEXP_ID,false},
//                {"Asd",Validator.REGEXP_ID,false},
//                {"not",Validator.REGEXP_BOOLEAN_PARAM,false},
//                {"11",Validator.REGEXP_BOOLEAN_PARAM,false},
//                {"-10",Validator.REGEXP_QUANTITY,false},
//                {"10.0",Validator.REGEXP_QUANTITY,false},
//                {"zero",Validator.REGEXP_QUANTITY,false},
//                {"-",Validator.REGEXP_DOSAGE_UNIT,false},
//                {"мл",Validator.REGEXP_DOSAGE_UNIT,false},
//                {"мг",Validator.REGEXP_UNIT_IN_PACK,false},
//        };
//    }

}
