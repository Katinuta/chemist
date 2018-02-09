package by.teplouhova.chemist.creator;

import by.teplouhova.chemist.entity.impl.Medicine;
import by.teplouhova.chemist.entity.impl.Producer;
import by.teplouhova.chemist.entity.impl.ReleaseForm;
import by.teplouhova.chemist.entity.impl.UnitInPackage;
import by.teplouhova.chemist.validator.ParameterName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class MedicineCreator {

    private static final Logger LOGGER = LogManager.getLogger();
    private Medicine medicine;

    public MedicineCreator(){
        medicine=new Medicine();
    }

    public Medicine createMedicine(HashMap<String,String> medicineParams) {
        medicine.setAnalog(new Medicine());
        Set<Map.Entry<String, String>> keySet = medicineParams.entrySet();
        keySet.stream().filter(entry -> entry.getValue()!=null).forEach(entry -> {
            String current=entry.getKey();
            try{
                ParameterName field= ParameterName.valueOf(current.toUpperCase());
                fillField(field,entry.getValue());
            }catch (IllegalArgumentException e){
                LOGGER.debug("Parameter is not field  : " +current );
            }
        });
        return medicine;

    }
    private void fillField(ParameterName name, String value) {

        switch (name) {
            case MEDICINE_ID : {
                medicine.setMedicineId(Long.parseLong(value));
                break;
            }
            case MEDICINE_NAME: {
                medicine.setName(value);
                break;
            }
            case PRICE: {
                medicine.setPrice(new BigDecimal(Double.parseDouble(value)));
                break;
            }
            case ANALOG_MEDICINE_ID: {

                medicine.setAnalog(new Medicine(Long.parseLong(value)));
                break;
            }
            case DOSAGE_SIZE: {
                medicine.getDosage().setSize(new BigDecimal(Double.parseDouble(value)));
                break;
            }
            case DOSAGE_UNIT: {
                medicine.getDosage().setUnit(value);
                break;
            }
            case NEED_PRESCRIPTION: {
                medicine.setNeedRecipe(Boolean.parseBoolean(value));
                break;
            }
            case QUANTITY_PACKAGES: {
                medicine.setQuantityPackages(Integer.parseInt(subStringToInteger(value)));
                break;
            }
            case QUANTITY_IN_PACKAGE: {
                medicine.setQuantityInPackage(Integer.parseInt(subStringToInteger(value)));
                break;
            }
            case UNIT_IN_PACKAGE:{
                medicine.setUnitInPackage(UnitInPackage.valueOf(value.toUpperCase()));
                break;
            }
            case PRODUCER_ID:{
                medicine.setProducer(new Producer(Long.parseLong(value)));
                break;
            }
            case RELEASE_FORM_ID:{
                medicine.setReleaseForm(new ReleaseForm(Long.parseLong(value)));
                break;
            }

            default: {
                LOGGER.info("Parameter is not field  object User" + name );
            }

        }
    }

    private String subStringToInteger(String quantity) {
        String substring = null;

        if (quantity.contains(".")) {
            int indexEnd = quantity.indexOf(".");
            substring = quantity.substring(0, indexEnd);
        }

        return substring == null ? quantity : substring;
    }
}
