package by.teplouhova.chemist.validator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

public class MedicineValidator {


    private Validator validator;
    private HashMap<String,String> errors;

    public MedicineValidator() {
        validator = new Validator();
        errors=new HashMap<>();
    }

    public boolean isMedicineValid(String medicineId, String name, String price, String quantityPackages,
                                   String analogId, String unitDosage, String dosageSize, String quantityInPack,
                                   String unitInPack, String releaseFormId,
                                   String producerId) {

     if(analogId!=null&&analogId.isEmpty()){
        analogId=null;

     }
        validator.validateRequired("medicineId",medicineId, Validator.REGEXP_ID,errors);

        validator.validateRequired("name",name, Validator.REGEXP_NAME,errors);
        validator.validateRequired("price",price, Validator.REGEXP_BIGDECIMAL_PARAM,errors);
        validator.validateRequired("quantityPackages",quantityPackages, Validator.REGEXP_QUANTITY,errors);

       validator.validateRequired("quantityInPack",quantityInPack, Validator.REGEXP_QUANTITY,errors);
        validator.validateRequired("unitInPack",unitInPack, Validator.REGEXP_UNIT_IN_PACK,errors);
        validator.validateRequired("releaseFormId",releaseFormId, Validator.REGEXP_ID,errors);
       validator.validateNotRequired("analogId",analogId, Validator.REGEXP_ID,errors);
        validator.validateRequired("producerId",producerId, Validator.REGEXP_ID,errors);
        if (!validator.validateNotRequired("dosageSize",dosageSize, Validator.REGEXP_BIGDECIMAL_PARAM,errors) ||
                !validator.validateNotRequired("unitDosage",unitDosage, Validator.REGEXP_DOSAGE_UNIT,errors)) {
            return false;
        } else {

            if (dosageSize!=null&&dosageSize.isEmpty()) {
                dosageSize = null;
            }
            if (unitDosage!=null&&unitDosage.isEmpty()) {
                unitDosage = null;
            }
            if (dosageSize == null && unitDosage != null||dosageSize != null && unitDosage == null) {
               errors.put("dosage","Dosage is described wrong");
            }
        }
        return errors.isEmpty()?true:false ;
    }


    public boolean isMedicineValid(String name, String price, String quantityPackages,
                                   String analogId, String unitDosage, String dosageSize, String quantityInPack,
                                   String unitInPack, String releaseFormId,
                                   String producerId) {
        if(analogId!=null&&analogId.isEmpty()){
            analogId=null;

        }
        if (!validator.validateRequired("name",name, Validator.REGEXP_NAME,errors)) {
            return false;
        }
        if (!validator.validateRequired("price",price, Validator.REGEXP_BIGDECIMAL_PARAM,errors)) {
            return false;
        }
        if (!validator.validateRequired("quantityPackages",quantityPackages, Validator.REGEXP_QUANTITY,errors)) {
            return false;
        }

        if (!validator.validateRequired("quantityInPack",quantityInPack, Validator.REGEXP_QUANTITY,errors)) {
            return false;
        }
        if (!validator.validateRequired("unitInPack",unitInPack, Validator.REGEXP_UNIT_IN_PACK,errors)) {
            return false;
        }
        if (!validator.validateRequired("releaseFormId",releaseFormId, Validator.REGEXP_ID,errors)) {
            return false;
        }
        if (!validator.validateNotRequired("analogId",analogId, Validator.REGEXP_ID,errors)) {
            return false;
        }
        if (!validator.validateNotRequired("dosageSize",dosageSize, Validator.REGEXP_BIGDECIMAL_PARAM,errors) ||
                !validator.validateNotRequired("unitDosage",unitDosage, Validator.REGEXP_DOSAGE_UNIT,errors)) {
            return false;
        } else {
            if (unitDosage!=null&&unitDosage.isEmpty()) {
                unitDosage = null;
            }

            if (dosageSize!=null&&dosageSize.isEmpty()) {
                dosageSize = null;
            }

            if (dosageSize == null && unitDosage != null||dosageSize != null && unitDosage == null) {
                return false;
            }
        }
        return validator.validateRequired("producerId",producerId, Validator.REGEXP_ID,errors);
    }
}
