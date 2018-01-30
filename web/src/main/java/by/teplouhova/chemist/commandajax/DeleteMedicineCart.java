package by.teplouhova.chemist.commandajax;

import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.entity.impl.Medicine;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.util.HashMap;

public class DeleteMedicineCart implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String PARAM_ID ="id";
    private static final String ATRR_CART="cart";

    @Override
    public JSONObject execute(SessionRequestContent content) {
        Long id=Long.parseLong(content.getParameter(PARAM_ID));
        HashMap<Medicine,Integer> cart= (HashMap<Medicine, Integer>) content.getSessionAttribute(ATRR_CART);
        JSONObject json=new JSONObject();
        Medicine medicine=cart.keySet().stream().filter(key->key.getMedicineId()==id).findFirst().get();
        cart.remove(medicine);
        content.setSessionAttribute(ATRR_CART,cart);
        int cartSize=0;
        if(!cart.isEmpty()){
           cartSize=cart.values().stream().reduce((s1,s2)->s1+s2).get();
        }

        json.put("size",cartSize);
        return json;
    }
}
