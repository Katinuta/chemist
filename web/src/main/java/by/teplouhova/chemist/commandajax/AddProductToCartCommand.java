package by.teplouhova.chemist.commandajax;

import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.entity.impl.Medicine;
import by.teplouhova.chemist.service.MedicineService;
import by.teplouhova.chemist.service.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * The Class AddProductToCartCommand.
 */
public class AddProductToCartCommand implements Command {
    private  static final Logger LOGGER = LogManager.getLogger();
    /** The Constant ATTR_CART. */
    private static final String ATTR_CART = "cart";

    /** The Constant ATTR_SIZE. */
    private static final  String ATTR_SIZE="size";

    /** The Constant ATTR_MEDICINE_ID. */
    private static final String ATTR_MEDICINE_ID="medicine_id";

    /** The medicine service. */
    private MedicineService medicineService;

    /**
     * Instantiates a new adds the product to cart command.
     *
     * @param medicineService the medicine service
     */

    public AddProductToCartCommand(MedicineService medicineService) {
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
        String id = content.getParameter(ATTR_MEDICINE_ID);
        HashMap<Medicine,Integer> cart= (HashMap<Medicine, Integer>) content.getSessionAttribute(ATTR_CART);
        Medicine medicine;
        JSONObject json=null;
        try {
            medicine = medicineService.getMedicine(Long.parseLong(id));
            cart.put(medicine,1);
            content.setSessionAttribute(ATTR_CART,cart);
            json = new JSONObject();
            json.put(ATTR_SIZE,String.valueOf(cart.size()));
        } catch (ServiceException e) {
            LOGGER.catching(e);
        }

        return json;
    }
}
