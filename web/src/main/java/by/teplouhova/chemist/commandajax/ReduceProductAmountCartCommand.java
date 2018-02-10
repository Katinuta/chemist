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

/**
 * The Class ReduceProductAmountCartCommand.
 */
public class ReduceProductAmountCartCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();
    /** The Constant PARAM_MEDICINE_ID. */
    private static final String PARAM_MEDICINE_ID = "medicine_id";

    /** The Constant PARAM_AMOUNT. */
    private static final String PARAM_AMOUNT = "amount";

    /** The Constant ATRR_CART. */
    private static final String ATRR_CART = "cart";

    /** The Constant ATTR_MESSAGE_BUNDLE. */
    private static final String ATTR_MESSAGE_BUNDLE = "messageBundle";

    /** The Constant ATTR_MESSAGE. */
    private static final String ATTR_MESSAGE = "message";

    /** The Constant ATTR_SIZE. */
    private static final String ATTR_SIZE = "size";

    /** The Constant ATTR_AMOUNT. */
    private static final String ATTR_AMOUNT = "amount";

    /** The medicine service. */
    private MedicineService medicineService;

    /**
     * Instantiates a new reduce product amount cart command.
     *
     * @param medicineService the medicine service
     */
    public ReduceProductAmountCartCommand(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    /**
     * Execute.
     *
     * @param content the content
     * @return the JSON object
     */
    @Override
    public JSONObject execute(SessionRequestContent content) {
        Long id = Long.parseLong(content.getParameter(PARAM_MEDICINE_ID));
        Integer amount = Integer.parseInt(content.getParameter(PARAM_AMOUNT));
        ResourceBundle bundle = (ResourceBundle) content.getSessionAttribute(ATTR_MESSAGE_BUNDLE);
        HashMap<Medicine, Integer> cart = (HashMap<Medicine, Integer>) content.getSessionAttribute(ATRR_CART);
        JSONObject json = new JSONObject();
        Medicine medicine = cart.keySet().stream().filter(key -> key.getMedicineId() == id).findFirst().get();
        try {
            int currentBalance = medicineService.getMedicineBalance(id);
            if (currentBalance >= amount && currentBalance != 0) {
                cart.put(medicine, amount);
            } else {
                if (currentBalance != 0) {
                    json.put(ATTR_MESSAGE, bundle.getString("message.error.amount"));
                } else {
                    amount = 0;
                    cart.put(medicine, amount);
                    json.put(ATTR_MESSAGE, bundle.getString("label.archive"));
                }
            }
            content.setSessionAttribute(ATRR_CART, cart);
            int basketSize = cart.values().stream().reduce((s1, s2) -> s1 + s2).get();
            json.put(ATTR_SIZE, basketSize);
            json.put(ATTR_AMOUNT, amount);
        } catch (ServiceException e) {
            LOGGER.catching(e);
        }

        return json;
    }
}
