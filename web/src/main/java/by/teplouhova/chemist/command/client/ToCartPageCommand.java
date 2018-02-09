package by.teplouhova.chemist.command.client;

import by.teplouhova.chemist.command.Command;
import by.teplouhova.chemist.command.CommandResult;
import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.entity.impl.Medicine;
import by.teplouhova.chemist.service.MedicineService;
import by.teplouhova.chemist.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.ResourceBundle;

import static by.teplouhova.chemist.command.CommandResult.ResponseType.FORWARD;
import static by.teplouhova.chemist.command.PageConstant.PAGE_CLIENT_CART;
import static by.teplouhova.chemist.command.PageConstant.PAGE_ERROR;

public class ToCartPageCommand implements Command {
    private static  final Logger LOGGER= LogManager.getLogger();

    private static final String ATTR_CART = "cart";
    private static final String ATTR_MESSAGE_ERROR = "message";
    private static  final String ATTR_MESSAGE_BUNDLE="messageBundle";
    private MedicineService medicineService;

    public ToCartPageCommand(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @Override
    public CommandResult execute(SessionRequestContent content) {
        String page ;
        CommandResult.ResponseType responseType = FORWARD; ;
        HashMap<Medicine, Integer> cart = (HashMap<Medicine, Integer>) content.getSessionAttribute(ATTR_CART);
        ResourceBundle bundle= (ResourceBundle) content.getSessionAttribute(ATTR_MESSAGE_BUNDLE);
        try {
            for (Medicine medicine : cart.keySet()) {
                Medicine medicineNew=medicineService.getMedicine(medicine.getMedicineId());
                if(medicineNew.getQuantityPackages()==0){
                    cart.put(medicine,0);
                }
                if(!medicineNew.getPrice().equals(medicine.getPrice())){
                    int quantity=cart.remove(medicine);
                    cart.put(medicineNew,quantity);

                }
            }

            page = PAGE_CLIENT_CART;
        } catch (ServiceException e) {
            page=PAGE_ERROR;
            content.setRequestAttributes(ATTR_MESSAGE_ERROR,bundle.getString("message.cart.error"));
            LOGGER.catching(e);
        }

        return new CommandResult(responseType, page);
    }
}
