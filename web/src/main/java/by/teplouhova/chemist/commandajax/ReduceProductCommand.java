package by.teplouhova.chemist.commandajax;

import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.entity.impl.Medicine;
import org.json.JSONObject;

import java.util.HashMap;

public class ReduceProductCommand implements Command {

    private static final String PARAM_ID ="id";
    private static final String PARAM_AMOUNT="amount";
    private static final String ATRR_CART="cart";

    @Override
    public JSONObject execute(SessionRequestContent content) {
        Long id=Long.parseLong(content.getParameter(PARAM_ID));
        Integer amount=Integer.parseInt(content.getParameter(PARAM_AMOUNT));
        HashMap<Medicine,Integer> cart= (HashMap<Medicine, Integer>) content.getSessionAttribute(ATRR_CART);
        JSONObject json=new JSONObject();
        Medicine medicine=cart.keySet().stream().filter(key->key.getMedicineId()==id).findFirst().get();
        cart.put(medicine,amount);
        content.setSessionAttribute(ATRR_CART,cart);
        int basketSize=cart.values().stream().reduce((s1,s2)->s1+s2).get();
        json.put("size",basketSize);
        return json;
    }
}
