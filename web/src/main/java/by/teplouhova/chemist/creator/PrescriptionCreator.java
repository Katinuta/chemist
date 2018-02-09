package by.teplouhova.chemist.creator;

import by.teplouhova.chemist.entity.impl.Prescription;
import by.teplouhova.chemist.entity.impl.User;
import by.teplouhova.chemist.validator.ParameterName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PrescriptionCreator {
    private static final Logger LOGGER = LogManager.getLogger();
    private Prescription prescription;

    public PrescriptionCreator() {
        prescription=new Prescription();
    }

    public Prescription create(HashMap<String,String> prescripParams){
        Set<Map.Entry<String, String>> keySet = prescripParams.entrySet();
        keySet.stream().filter(entry -> entry.getValue()!=null).forEach(entry -> {
            String current=entry.getKey();
            try{
                ParameterName field= ParameterName.valueOf(current.toUpperCase());
                fillField(field,entry.getValue());
            }catch (IllegalArgumentException e){
                LOGGER.debug("Parameter is not field  : " +current );
            }
        });
        return prescription;
    }



    private void fillField(ParameterName name, String value) {

        switch (name) {
            case PRESCRIPTION_ID:{
                prescription.setPrescriptionId(Long.parseLong(value));
                break;
            }

            case CLIENT_ID: {
                prescription.setClient(new User(Long.parseLong(value)));
                break;
            }

            case DATE_BEGIN: {
                prescription.setDateBegin(LocalDate.parse(value));
                break;
            }
            case DATE_END: {
                prescription.setDateEnd(LocalDate.parse(value));
                break;
            }
            default:{
                LOGGER.info("Parameter is not field  " + name +" for class " + prescription.getClass().getSimpleName());
            }

        }
    }
}
