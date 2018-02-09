package by.teplouhova.chemist.commandajax;

import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.entity.impl.Medicine;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class GetCartProductsIdCommand implements Command {

    private static final String ATTR_CART="cart";
    private static final String ATTR_IDS="ids";

    @Override
    public JSONObject execute(SessionRequestContent content) {
        HashMap<Medicine,Integer> cart= (HashMap<Medicine, Integer>) content.getSessionAttribute(ATTR_CART);
        JSONObject object;
        if(!cart.isEmpty()){
            ArrayList<Long> ids=new ArrayList<>();
            cart.keySet().stream().forEach(key-> ids.add(key.getMedicineId()));
            object=new JSONObject();
            object.put(ATTR_IDS,ids);
        }else{
            object=new JSONObject();
        }
        return object;
    }
}
