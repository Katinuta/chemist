package test.by.teplouhova.chemist.validator;

import by.teplouhova.chemist.validator.RegexpConstant;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

public class ValidatorNotRequiredTest {

    private RegexpConstant validator;

    @BeforeClass
    public void before(){
        validator=new RegexpConstant();
    }

    @DataProvider(name="stringsValid")
    public Object[][] createDadaValid(){
        return new Object [][]{
                {"100.01", RegexpConstant.REGEXP_BIGDECIMAL_PARAM,true},
                {"100", RegexpConstant.REGEXP_ID,true},
                {"мг", RegexpConstant.REGEXP_DOSAGE_UNIT,true},
                {"%", RegexpConstant.REGEXP_DOSAGE_UNIT,true},
                {"", RegexpConstant.REGEXP_BIGDECIMAL_PARAM,true},
                {null, RegexpConstant.REGEXP_BIGDECIMAL_PARAM,true},
                {null, RegexpConstant.REGEXP_DOSAGE_UNIT,true},
                {"", RegexpConstant.REGEXP_DOSAGE_UNIT,true},
                {"", RegexpConstant.REGEXP_ID,true},
                {null, RegexpConstant.REGEXP_ID,true},
                {"", RegexpConstant.REGEXP_BIGDECIMAL_PARAM,true},
                {null, RegexpConstant.REGEXP_BIGDECIMAL_PARAM,true},
        };
    }
    @DataProvider(name="stringsInValid")
    public Object[][] createDadaInvalid(){
        return new Object [][]{
                {"-100.01", RegexpConstant.REGEXP_BIGDECIMAL_PARAM,false},
                {"A00.01", RegexpConstant.REGEXP_BIGDECIMAL_PARAM,false},
                {"-100", RegexpConstant.REGEXP_ID,false},
                {"Asd", RegexpConstant.REGEXP_ID,false},
                {"-", RegexpConstant.REGEXP_DOSAGE_UNIT,false},
                {"мл", RegexpConstant.REGEXP_DOSAGE_UNIT,false},

        };
    }

  //  @Test(dataProvider = "stringsValid")
    public void validateStringTestTrue(String string,String regexp,boolean expected){
     //   boolean actual=new RegexpConstant().validateNotRequired(string,regexp);
      //  Assert.assertEquals(actual,expected);
    }


   // @Test(dataProvider = "stringsInValid")
    public void validateStringTestFalse(String string,String regexp,boolean expected){
      //  boolean actual=new RegexpConstant().validateNotRequired(string,regexp);
      //  Assert.assertTrue(actual==expected);
    }
    @AfterClass
    public void after(){
        validator=null;
    }
}
