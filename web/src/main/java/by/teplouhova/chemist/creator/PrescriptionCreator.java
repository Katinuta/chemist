package by.teplouhova.chemist.creator;

import by.teplouhova.chemist.entity.impl.*;
import by.teplouhova.chemist.validator.Validator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static by.teplouhova.chemist.validator.FieldConstant.*;
import static by.teplouhova.chemist.validator.FieldConstant.USER_FIELD_ACCOUNT;
import static by.teplouhova.chemist.validator.FieldConstant.USER_FIELD_PHONE;

public class PrescriptionCreator {

    private Prescription prescription;

    public PrescriptionCreator() {
        prescription=new Prescription();

        prescription.setDetails(new PrescriptionDetail());
    }

    public Prescription create(HashMap<String,String> prescripParams){
        Set<Map.Entry<String, String>> keySet = prescripParams.entrySet();
        keySet.forEach(entry -> fillField(entry.getKey(), entry.getValue()));
        return prescription;
    }


    private String subStringToInteger(String quantity) {
        String substring = null;

        if (quantity.contains(".")) {
            int indexEnd = quantity.indexOf(".");
            substring = quantity.substring(0, indexEnd);
        }

        return substring == null ? quantity : substring;
    }
    private void fillField(String name, String value) {

        switch (name) {
            case PRESCRIPTION_FIELD_ID:{
                prescription.setPrescriptionId(Long.parseLong(value));
                break;
            }

            case PRESCRIPTION_FIELD_CLIENT_ID: {
                prescription.setClient(new User(Long.parseLong(value)));
                break;
            }
            case PRESCRIPTION_FIELD_MEDICINE_ID: {
               prescription.getDetails().get(1).getMedicine().setMedicineId(Long.parseLong(value));
                break;
            }
            case PRESCRIPTION_FIELD_QUANTITY: {
                prescription.getDetails().get(1).getMedicine()
                        .setQuantityPackages(Integer.parseInt(subStringToInteger(value)));
                break;
            }
            case PRESCRIPTION_FIELD_DATE_BEGIN: {
                prescription.setDateBegin(LocalDate.parse(value));
                break;
            }
            case PRESCRIPTION_FIELD_DATE_END: {
                prescription.setDateEnd(LocalDate.parse(value));
                break;
            }
            default:{
                //todo
            }

        }
    }
}
