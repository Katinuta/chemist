package by.teplouhova.chemist.commandajax;

import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.entity.impl.Medicine;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class GetCartProductsIdCommand implements Command {

    private static final String ATTR_CART="cart";

    @Override
    public JSONObject execute(SessionRequestContent content) {
        HashMap<Medicine,Integer> cart= (HashMap<Medicine, Integer>) content.getSessionAttribute(ATTR_CART);
        JSONObject object;
        if(!cart.isEmpty()){
            ArrayList<Long> ids=new ArrayList<>();
            cart.keySet().stream().forEach(key-> ids.add(key.getMedicineId()));
            object=new JSONObject();
            object.put("ids",ids);
        }else{
            object=new JSONObject();
        }
        return object;
    }
}
