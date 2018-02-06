package by.teplouhova.chemist.commandajax;

import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.entity.impl.Medicine;
import by.teplouhova.chemist.service.MedicineService;
import by.teplouhova.chemist.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.util.ArrayList;

public class ShowMedicinesByPrescripCommand implements Command {
    private  static final Logger LOGGER = LogManager.getLogger();

    private static final String ATTR_MEDICINES="medicines";

    private MedicineService medicineService;

    public ShowMedicinesByPrescripCommand(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @Override
    public JSONObject execute(SessionRequestContent content) {
        JSONObject object = new JSONObject();
        try {
            ArrayList<Medicine> medicines=medicineService.getMedicinesByPrescrip(true);
            object.put(ATTR_MEDICINES,medicines);
        } catch (ServiceException e) {
            LOGGER.catching(e);
        }
        return object;
    }
}
