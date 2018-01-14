package by.teplouhova.chemist.commandajax;

import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.entity.impl.Medicine;
import by.teplouhova.chemist.service.MedicineService;
import by.teplouhova.chemist.service.ServiceException;
import by.teplouhova.chemist.service.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.util.HashMap;

public class IncreaseProductCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    private MedicineService medicineService;
    private static final String PARAM_ID ="id";
    private static final String PARAM_AMOUNT="amount";
    private static final String ATRR_CART="cart";

    public IncreaseProductCommand(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @Override
    public JSONObject execute(SessionRequestContent content) {
        Long id=Long.parseLong(content.getParameter(PARAM_ID));
        Integer amount=Integer.parseInt(content.getParameter(PARAM_AMOUNT));
        HashMap<Medicine,Integer> cart= (HashMap<Medicine, Integer>) content.getSessionAttribute(ATRR_CART);
        JSONObject json=new JSONObject();
        try {
            int currentBalance=medicineService.getMedicineBalance(id);
            if(currentBalance>amount){
                Medicine medicine=cart.keySet().stream().filter(key->key.getMedicineId()==id).findFirst().get();
                cart.put(medicine,amount);
                content.setSessionAttribute(ATRR_CART,cart);

            }else{
                json.put("message","Not enough product");
            }
            int cartSize=cart.values().stream().reduce((s1,s2)->s1+s2).get();
            json.put("size",cartSize);
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR,""+e);
        }

        return json;
    }
}
