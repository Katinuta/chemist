package by.teplouhova.chemist.validator;

import by.teplouhova.chemist.entity.impl.Prescription;
import by.teplouhova.chemist.manager.MessageManager;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import static by.teplouhova.chemist.validator.FieldConstant.*;

public class PrescriptionValidator {

    private Validator validator;
    private HashMap<String,String> errors;
    private ResourceBundle bundle;

    public PrescriptionValidator() {
        validator = new Validator();
        errors=new HashMap<>();
        bundle = MessageManager.EN.getBundle();
    }
    public PrescriptionValidator(ResourceBundle bundle) {
        validator = new Validator(bundle);
        errors=new HashMap<>();
        this.bundle = bundle;
    }

    public boolean isPrescriptionValid( HashMap<String,String> prescripParams){
        Set<Map.Entry<String, String>> keySet = prescripParams.entrySet();
        keySet.forEach(entry -> validateField(entry.getKey(), entry.getValue()));
        return errors.isEmpty() ? true : false;

         }

    public Set<Map.Entry<String, String>> getValidationErrors(){
        return errors.entrySet();
    }

    private void validateField(String name, String value) {

        switch (name) {
            case PRESCRIPTION_FIELD_CLIENT_ID: {
                validator.validateRequired(name, value, Validator.REGEXP_ID, errors);
                break;
            }
            case PRESCRIPTION_FIELD_MEDICINE_ID: {
                validator.validateRequired(name, value, Validator.REGEXP_ID, errors);
                break;
            }
            case PRESCRIPTION_FIELD_DATE_BEGIN: {
                validator.validateRequired(name, value, Validator.REGEXP_DATE, errors);
                break;
            }
            case PRESCRIPTION_FIELD_DATE_END: {
                validator.validateRequired(name, value, Validator.REGEXP_DATE, errors);
                break;
            }
            case PRESCRIPTION_FIELD_QUANTITY: {
                validator.validateRequired(name, value, Validator.REGEXP_QUANTITY, errors);
                break;
            }
            case PRESCRIPTION_FIELD_ID:{
                validator.validateRequired(name, value, Validator.REGEXP_ID, errors);
                break;
            }

            default: {
                errors.put(FIELD_ERROR,
                        bundle.getString("message.wrong.field") + Prescription.class.getSimpleName() + name);
            }

        }
    }
}
