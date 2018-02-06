package by.teplouhova.chemist.commandajax;

import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.entity.impl.Medicine;
import by.teplouhova.chemist.service.MedicineService;
import by.teplouhova.chemist.service.ServiceException;
import org.json.JSONObject;

import java.util.HashMap;

public class ReduceProductAmountCartCommand implements Command {

    private static final String PARAM_MEDICINE_ID = "medicine_id";
    private static final String PARAM_AMOUNT="amount";
    private static final String ATRR_CART="cart";
    private static  final String ATTR_MESSAGE_BUNDLE="messageBundle";

    private MedicineService medicineService;
    public ReduceProductAmountCartCommand(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @Override
    public JSONObject execute(SessionRequestContent content) {
        Long id=Long.parseLong(content.getParameter(PARAM_MEDICINE_ID));
        Integer amount=Integer.parseInt(content.getParameter(PARAM_AMOUNT));
        HashMap<Medicine,Integer> cart= (HashMap<Medicine, Integer>) content.getSessionAttribute(ATRR_CART);
        JSONObject json=new JSONObject();
        Medicine medicine=cart.keySet().stream().filter(key->key.getMedicineId()==id).findFirst().get();
        try {
            int currentBalance=medicineService.getMedicineBalance(id);
            if(currentBalance>=amount&&currentBalance!=0){
                cart.put(medicine,amount);
            }else{

                if(currentBalance!=0){
                    json.put("message", "Not enough product");

                }else {
                    amount=0;
                    cart.put(medicine,amount);
                    json.put("message","In archive");
                }
            }


            content.setSessionAttribute(ATRR_CART,cart);
            int basketSize=cart.values().stream().reduce((s1,s2)->s1+s2).get();
            json.put("size",basketSize);
            json.put("amount",amount);
        } catch (ServiceException e) {
            //todo check necessary
            json.put("error","error reduce");
        }

        return json;
    }
}
