package by.teplouhova.chemist.creator;

import by.teplouhova.chemist.entity.impl.PrescriptionDetail;
import by.teplouhova.chemist.validator.ParameterName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * The Class PrescriptionDetailCreator.
 */
public class PrescriptionDetailCreator {
    private static final Logger LOGGER = LogManager.getLogger();
    /** The detail. */
    private PrescriptionDetail detail;

    /**
     * Instantiates a new prescription detail creator.
     */
    public PrescriptionDetailCreator() {
        detail=new PrescriptionDetail();

    }

    /**
     * Creates the.
     *
     * @param prescripParams the prescrip params
     * @return the prescription detail
     */
    public PrescriptionDetail create(HashMap<String,String> prescripParams){
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
        return detail;
    }


    /**
     * Sub string to integer.
     *
     * @param quantity the quantity
     * @return the string
     */
    private String subStringToInteger(String quantity) {
        String substring = null;

        if (quantity.contains(".")) {
            int indexEnd = quantity.indexOf(".");
            substring = quantity.substring(0, indexEnd);
        }

        return substring == null ? quantity : substring;
    }

    /**
     * Fill field.
     *
     * @param name the name
     * @param value the value
     */
    private void fillField(ParameterName name, String value) {

        switch (name) {
            case PRESCRIPTION_ID:{
                detail.getPrescription().setPrescriptionId(Long.parseLong(value));
                break;
            }

            case MEDICINE_ID: {
                detail.getMedicine().setMedicineId(Long.parseLong(value));
                break;
            }
            case QUANTITY: {
                detail.setQuantityPack(Integer.parseInt(subStringToInteger(value)));
                break;
            }
            case PRESCRIP_DETAIL_ID:{
                detail.setDetailId(Long.parseLong(value));
                break;
            }

            default:{
                LOGGER.info("Parameter is not field  " + name +" for class " + detail.getClass().getSimpleName() );
            }

        }
    }

}
