package by.teplouhova.chemist.creator;

import by.teplouhova.chemist.entity.impl.OrderDetail;
import by.teplouhova.chemist.validator.FieldName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class OrderDetailCreator {
    private static final Logger LOGGER = LogManager.getLogger();
    private OrderDetail detail;

    public OrderDetailCreator() {
        detail=new OrderDetail();
    }

    public OrderDetail create(HashMap<String,String> params){
        Set<Map.Entry<String, String>> keySet = params.entrySet();
        keySet.stream().filter(entry -> entry.getValue()!=null).forEach(entry -> {
            String current=entry.getKey();
            try{
                FieldName field=FieldName.valueOf(current.toUpperCase());
                fillField(field,entry.getValue());
            }catch (IllegalArgumentException e){
                LOGGER.debug("Parameter is not field  : " +current );
            }
        });
        return detail;
    }
    private String subStringToInteger(String quantity) {
        String substring = null;

        if (quantity.contains(".")) {
            int indexEnd = quantity.indexOf(".");
            substring = quantity.substring(0, indexEnd);
        }

        return substring == null ? quantity : substring;
    }

    private void fillField(FieldName name, String value) {

        switch (name) {
//            case ORDER_ID:{
//                detail.getOrder().setOrderId(Long.parseLong(value));
//                break;
//            }

            case MEDICINE_ID: {
                detail.getMedicine().setMedicineId(Long.parseLong(value));
                break;
            }
//            case QUANTITY: {
//                detail.setQuantityPack(Integer.parseInt(subStringToInteger(value)));
//                break;
//            }
//            case PRICE:{
//
//            }
//            case AMOUNT:{
//
//            }

            default:{
                LOGGER.info("Parameter is not field  " + name +" for class " + detail.getClass().getSimpleName() );
            }

        }
    }
}
