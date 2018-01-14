package test.by.teplouhova.chemist.validator;

import by.teplouhova.chemist.validator.Validator;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ValidatorNotRequiredTest {

    private Validator validator;

    @BeforeClass
    public void before(){
        validator=new Validator();
    }

    @DataProvider(name="stringsValid")
    public Object[][] createDadaValid(){
        return new Object [][]{
                {"100.01",Validator.REGEXP_BIGDECIMAL_PARAM,true},
                {"100",Validator.REGEXP_ID,true},
                {"мг",Validator.REGEXP_DOSAGE_UNIT,true},
                {"%",Validator.REGEXP_DOSAGE_UNIT,true},
                {"",Validator.REGEXP_BIGDECIMAL_PARAM,true},
                {null,Validator.REGEXP_BIGDECIMAL_PARAM,true},
                {null,Validator.REGEXP_DOSAGE_UNIT,true},
                {"",Validator.REGEXP_DOSAGE_UNIT,true},
                {"",Validator.REGEXP_ID,true},
                {null,Validator.REGEXP_ID,true},
                {"",Validator.REGEXP_BIGDECIMAL_PARAM,true},
                {null,Validator.REGEXP_BIGDECIMAL_PARAM,true},
        };
    }
    @DataProvider(name="stringsInValid")
    public Object[][] createDadaInvalid(){
        return new Object [][]{
                {"-100.01",Validator.REGEXP_BIGDECIMAL_PARAM,false},
                {"A00.01",Validator.REGEXP_BIGDECIMAL_PARAM,false},
                {"-100",Validator.REGEXP_ID,false},
                {"Asd",Validator.REGEXP_ID,false},
                {"-",Validator.REGEXP_DOSAGE_UNIT,false},
                {"мл",Validator.REGEXP_DOSAGE_UNIT,false},

        };
    }

    @Test(dataProvider = "stringsValid")
    public void validateStringTestTrue(String string,String regexp,boolean expected){
        boolean actual=new Validator().validateNotRequired(string,regexp);
        Assert.assertEquals(actual,expected);
    }


    @Test(dataProvider = "stringsInValid")
    public void validateStringTestFalse(String string,String regexp,boolean expected){
        boolean actual=new Validator().validateNotRequired(string,regexp);
        Assert.assertTrue(actual==expected);
    }
    @AfterClass
    public void after(){
        validator=null;
    }
}
