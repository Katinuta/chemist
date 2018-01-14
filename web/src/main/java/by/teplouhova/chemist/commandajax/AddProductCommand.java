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

public class AddProductCommand implements Command {
    private  static final Logger LOGGER = LogManager.getLogger();

    private MedicineService medicineService;

    public AddProductCommand(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @Override
    public JSONObject execute(SessionRequestContent content) {
        String id = content.getParameter("medicineForAdd");
        HashMap<Medicine,Integer> cart= (HashMap<Medicine, Integer>) content.getSessionAttribute("cart");
        Medicine medicine;
        JSONObject json=null;
        try {
            medicine = medicineService.getMedicine(Long.parseLong(id));
            cart.put(medicine,1);
            content.setSessionAttribute("cart",cart);
            json = new JSONObject();
            json.put("size",String.valueOf(cart.size()));
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Product is not found "+e);
        }

        return json;
    }
}
