package by.teplouhova.chemist.commandajax;

import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.entity.impl.Medicine;
import org.json.JSONObject;

import java.util.HashMap;

public class DeleteProductCartCommand implements Command {

    private static final String PARAM_MEDICINE_ID ="medicine_id";
    private static final String ATRR_CART="cart";
    private static final String ATRR_SIZE="size";

    @Override
    public JSONObject execute(SessionRequestContent content) {
        Long id=Long.parseLong(content.getParameter(PARAM_MEDICINE_ID));
        HashMap<Medicine,Integer> cart= (HashMap<Medicine, Integer>) content.getSessionAttribute(ATRR_CART);
        JSONObject json=new JSONObject();
        Medicine medicine=cart.keySet().stream().filter(key->key.getMedicineId()==id).findFirst().get();
        cart.remove(medicine);
        content.setSessionAttribute(ATRR_CART,cart);
        int cartSize=0;
        if(!cart.isEmpty()){
           cartSize=cart.values().stream().reduce((s1,s2)->s1+s2).get();
        }

        json.put(ATRR_SIZE,cartSize);
        return json;
    }
}
