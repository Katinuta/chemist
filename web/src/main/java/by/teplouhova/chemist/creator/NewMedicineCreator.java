package by.teplouhova.chemist.creator;

import by.teplouhova.chemist.validator.FieldName;

public class NewMedicineCreator extends EntityCreator {
    @Override
    protected void fillField(FieldName name, String value) {

    }
//    private static final Logger LOGGER = LogManager.getLogger();
//    private Medicine medicine;
//
//    public NewMedicineCreator() {
//        medicine = new Medicine();
//
//    }
//
//    @Override
//    protected void fillField(FieldName name, String value) {
//        switch (name) {
//            case MEDICINE_ID : {
//                medicine.setMedicineId(Long.parseLong(value));
//                break;
//            }
//            case MEDICINE_NAME: {
//                medicine.setName(value);
//                break;
//            }
//            case PRICE: {
//                medicine.setPrice(new BigDecimal(Double.parseDouble(value)));
//                break;
//            }
//            case ANALOG_MEDICINE_ID: {
//
//                medicine.setAnalog(new Medicine(Long.parseLong(value)));
//                break;
//            }
//            case DOSAGE_SIZE: {
//                medicine.getDosage().setSize(new BigDecimal(Double.parseDouble(value)));
//                break;
//            }
//            case DOSAGE_UNIT: {
//                medicine.getDosage().setUnit(value);
//                break;
//            }
//            case NEED_PRESCRIPTION: {
//                medicine.setNeedRecipe(Boolean.parseBoolean(value));
//                break;
//            }
//            case QUANTITY_PACKAGES: {
//                medicine.setQuantityPackages(Integer.parseInt(subStringToInteger(value)));
//                break;
//            }
//            case QUANTITY_IN_PACKAGE: {
//                medicine.setQuantityInPackage(Integer.parseInt(subStringToInteger(value)));
//                break;
//            }
//            case UNIT_IN_PACKAGE:{
//                medicine.setUnitInPackage(UnitInPackage.valueOf(value.toUpperCase()));
//                break;
//            }
//            case PRODUCER_ID:{
//                medicine.setProducer(new Producer(Long.parseLong(value)));
//                break;
//            }
//            case RELEASE_FORM_ID:{
//                medicine.setReleaseForm(new ReleaseForm(Long.parseLong(value)));
//                break;
//            }
//
//            default: {
//                LOGGER.debug("Parameter is not field  object User" + name );
//            }
//
//        }
//
//    }
//    private String subStringToInteger(String quantity) {
//        String substring = null;
//
//        if (quantity.contains(".")) {
//            int indexEnd = quantity.indexOf(".");
//            substring = quantity.substring(0, indexEnd);
//        }
//
//        return substring == null ? quantity : substring;
//    }
}
