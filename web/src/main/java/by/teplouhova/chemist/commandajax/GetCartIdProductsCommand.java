package by.teplouhova.chemist.commandajax;

import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.entity.impl.Medicine;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GetCartIdProductsCommand implements Command {

    private static final String ATTR_CART="cart";

    @Override
    public JSONObject execute(SessionRequestContent content) {
        HashMap<Medicine,Integer> cart= (HashMap<Medicine, Integer>) content.getSessionAttribute(ATTR_CART);
        JSONObject object;
        if(!cart.isEmpty()){
            ArrayList<Long> ids=new ArrayList<>();
            cart.keySet().stream().forEach(key-> ids.add(key.getMedicineId()));
            object=new JSONObject();
//            JSONArray list = new JSONArray();
//            list.put(ids);

            object.put("ids",ids);
        }else{
            object=new JSONObject();

        }

        return object;
    }
}
