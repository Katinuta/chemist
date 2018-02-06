package by.teplouhova.chemist.commandajax;

import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.entity.impl.Medicine;
import by.teplouhova.chemist.service.MedicineService;
import by.teplouhova.chemist.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.ResourceBundle;

public class IncreaseProductAmountCartCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    private MedicineService medicineService;
    private static final String PARAM_MEDICINE_ID = "medicine_id";
    private static final String PARAM_AMOUNT = "amount";
    private static final String ATRR_CART = "cart";
    private static  final String ATTR_MESSAGE_BUNDLE="messageBundle";
    private static final String ATTR_MESSAGE="message";
    private static final  String ATTR_SIZE="size";

    public IncreaseProductAmountCartCommand(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @Override
    public JSONObject execute(SessionRequestContent content) {
        //todo validation
        Long id = Long.parseLong(content.getParameter(PARAM_MEDICINE_ID));
        Integer amount = Integer.parseInt(content.getParameter(PARAM_AMOUNT));
        HashMap<Medicine, Integer> cart = (HashMap<Medicine, Integer>) content.getSessionAttribute(ATRR_CART);
        ResourceBundle bundle= (ResourceBundle) content.getSessionAttribute(ATTR_MESSAGE_BUNDLE);
        JSONObject json = new JSONObject();
        try {
            int currentBalance = medicineService.getMedicineBalance(id);
            Medicine medicine = cart.keySet().stream().filter(key -> key.getMedicineId() == id).findFirst().get();
            if (currentBalance >=amount &&currentBalance!=0) {
                cart.put(medicine, amount);
            } else {
                if (currentBalance != 0) {
                    json.put(ATTR_MESSAGE,bundle.getString("message.error.amount"));
                    amount=--amount;
                } else {
                    json.put(ATTR_MESSAGE,bundle.getString("label.archive"));
                    amount = 0;
                    cart.put(medicine, amount);
                }

            }
            content.setSessionAttribute(ATRR_CART, cart);
            int cartSize = cart.values().stream().reduce((s1, s2) -> s1 + s2).get();
            json.put(ATTR_SIZE, cartSize);
            json.put(PARAM_AMOUNT, amount);
        } catch (ServiceException e) {
            LOGGER.catching(e);
        }

        return json;
    }
}
