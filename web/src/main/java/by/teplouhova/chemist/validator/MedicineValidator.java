package by.teplouhova.chemist.validator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MedicineValidator {
    private static final Logger LOGGER= LogManager.getLogger();
    private Validator validator;

    public MedicineValidator() {
        validator = new Validator();
    }

    public boolean validateMedicine(String medicineId, String name, String price, String quantityPackages,
                                    String analogId, String unitDosage, String dosageSize, String quantityInPack,
                                    String unitInPack, String releaseFormId,
                                    String producerId) {
     if(analogId!=null&&analogId.isEmpty()){
        analogId=null;

     }
        if (!validator.validateRequired(medicineId, Validator.REGEXP_ID)) {

            return false;
        }
        if (!validator.validateRequired(name, Validator.REGEXP_NAME)) {
            return false;
        }
        if (!validator.validateRequired(price, Validator.REGEXP_BIGDECIMAL_PARAM)) {
            return false;
        }
        if (!validator.validateRequired(quantityPackages, Validator.REGEXP_QUANTITY)) {
            return false;
        }

        if (!validator.validateRequired(quantityInPack, Validator.REGEXP_QUANTITY)) {
            return false;
        }
        if (!validator.validateRequired(unitInPack, Validator.REGEXP_UNIT_IN_PACK)) {
            return false;
        }
        if (!validator.validateRequired(releaseFormId, Validator.REGEXP_ID)) {
            return false;
        }
        if (!validator.validateNotRequired(analogId, Validator.REGEXP_ID)) {
            return false;
        }
        if (!validator.validateNotRequired(dosageSize, Validator.REGEXP_BIGDECIMAL_PARAM) ||
                !validator.validateNotRequired(unitDosage, Validator.REGEXP_DOSAGE_UNIT)) {
            return false;
        } else {

            if (dosageSize!=null&&dosageSize.isEmpty()) {
                dosageSize = null;
            }
            if (unitDosage!=null&&unitDosage.isEmpty()) {
                unitDosage = null;
            }
            if (dosageSize == null && unitDosage != null||dosageSize != null && unitDosage == null) {
                return false;
            }
        }
        return validator.validateRequired(producerId, Validator.REGEXP_ID);
    }


    public boolean validateMedicine( String name, String price, String quantityPackages,
                                    String analogId, String unitDosage, String dosageSize, String quantityInPack,
                                    String unitInPack, String releaseFormId,
                                    String producerId) {
        if(analogId!=null&&analogId.isEmpty()){
            analogId=null;

        }
        if (!validator.validateRequired(name, Validator.REGEXP_NAME)) {
            return false;
        }
        if (!validator.validateRequired(price, Validator.REGEXP_BIGDECIMAL_PARAM)) {
            return false;
        }
        if (!validator.validateRequired(quantityPackages, Validator.REGEXP_QUANTITY)) {
            return false;
        }

        if (!validator.validateRequired(quantityInPack, Validator.REGEXP_QUANTITY)) {
            return false;
        }
        if (!validator.validateRequired(unitInPack, Validator.REGEXP_UNIT_IN_PACK)) {
            return false;
        }
        if (!validator.validateRequired(releaseFormId, Validator.REGEXP_ID)) {
            return false;
        }
        if (!validator.validateNotRequired(analogId, Validator.REGEXP_ID)) {
            return false;
        }
        if (!validator.validateNotRequired(dosageSize, Validator.REGEXP_BIGDECIMAL_PARAM) ||
                !validator.validateNotRequired(unitDosage, Validator.REGEXP_DOSAGE_UNIT)) {
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
        return validator.validateRequired(producerId, Validator.REGEXP_ID);
    }
}
