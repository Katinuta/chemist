package by.teplouhova.chemist.command;

import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.dao.MedicineDAO;
import by.teplouhova.chemist.entity.impl.Medicine;
import by.teplouhova.chemist.service.MedicineService;
import by.teplouhova.chemist.service.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

public class OpenCartCommand implements Command {
    private static  final Logger LOGGER= LogManager.getLogger();

    private static final String ATTR_CART = "cart";
    private MedicineService medicineService;

    public OpenCartCommand(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @Override
    public CommandResult execute(SessionRequestContent content) {
        String page = null;
        CommandResult.ResponseType responseType = null;
        HashMap<Medicine, Integer> cart = (HashMap<Medicine, Integer>) content.getSessionAttribute(ATTR_CART);
        LOGGER.log(Level.DEBUG,cart);
        try {
            for (Medicine medicine : cart.keySet()) {
                medicine=medicineService.getMedicine(medicine.getMedicineId());
                if(medicine.getQuantityPackages()==0){
                    cart.put(medicine,0);
                }
            }
            responseType = CommandResult.ResponseType.FORWARD;
            page = "/jsp/client/cart.jsp";
        } catch (ServiceException e) {
            page="/jsp/error/error.jsp";
            responseType = CommandResult.ResponseType.REDIRECT;
        }

        return new CommandResult(responseType, page);
    }
}
