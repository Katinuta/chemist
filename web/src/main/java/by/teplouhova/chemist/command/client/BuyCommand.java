package by.teplouhova.chemist.command.client;

import by.teplouhova.chemist.command.Command;
import by.teplouhova.chemist.command.CommandResult;
import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.creator.OrderCreator;
import by.teplouhova.chemist.entity.impl.Medicine;
import by.teplouhova.chemist.entity.impl.Order;
import by.teplouhova.chemist.entity.impl.User;
import by.teplouhova.chemist.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Set;

import static by.teplouhova.chemist.command.CommandResult.ResponseType.FORWARD;
import static by.teplouhova.chemist.command.CommandResult.ResponseType.REDIRECT;
import static by.teplouhova.chemist.command.PageConstant.*;

public class BuyCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String ATTR_CART = "cart";
    private static final String ATTR_MESSAGE_COUNT = "messagecount_";
    private static final String ATTR_MESSAGE_PRESCRIP = "messageprescrip_";
    private static final String ATTR_USER = "user";
    private static final String ATTR_ERROR = "error";
    private static final String ATTR_MESSAGE_BUNDLE="messageBundle";
    private static final String ATTR_MESSAGE="message";

    private ClientService clientService;

    private OrderService orderService;
    private MedicineService medicineService;
    private PrescriptionService prescriptionService;

    public BuyCommand(ClientService clientService, OrderService orderService, MedicineService medicineService, PrescriptionService prescriptionService) {
        this.clientService = clientService;
        this.orderService = orderService;
        this.medicineService = medicineService;
        this.prescriptionService = prescriptionService;
    }

    @Override
    public CommandResult execute(SessionRequestContent content) {
        HashMap<Medicine, Integer> cart = (HashMap<Medicine, Integer>) content.getSessionAttribute(ATTR_CART);
        User user = (User) content.getSessionAttribute(ATTR_USER);
        ResourceBundle bundle= (ResourceBundle) content.getSessionAttribute(ATTR_MESSAGE_BUNDLE);
        String page = PAGE_CLIENT_CART;
        CommandResult.ResponseType responseType = FORWARD;
        if(!cart.isEmpty()){
            Set<Medicine> medicines = cart.keySet();
            try {
                for (Medicine medicine : medicines) {
                    int countCart = cart.get(medicine);
                    if(countCart==0){
                        cart.remove(medicine);
                    }
                    if (!medicineService.isCountAvailable(medicine.getMedicineId(),countCart)) {
                        content.setRequestAttributes(ATTR_MESSAGE_COUNT + medicine.getMedicineId(),
                                bundle.getString("message.error.amount"));
                    }
                    if (medicine.getIsNeedRecipe()) {
                        if (!prescriptionService.checkPrescripExistForMedicine(medicine, countCart, user.getUserId())) {
                            content.setRequestAttributes(ATTR_MESSAGE_PRESCRIP + medicine.getMedicineId(),
                                    bundle.getString("message.error.prescrip"));
                        }
                    }
                }
                if (!content.isContainsAttributesStartWith(ATTR_MESSAGE_PRESCRIP) &&
                        !content.isContainsAttributesStartWith(ATTR_MESSAGE_COUNT)&&!cart.isEmpty()) {
                     Order order = new OrderCreator().create(user, cart);
                       if(clientService.isHaveEnoughMoney(user.getUserId(),order.getTotal())){
                         orderService.create(order);
                        content.setSessionAttribute(ATTR_CART,new HashMap<Medicine,Integer>());
                        page = PAGE_CLIENT_SUCCESS_ORDER;
                        responseType =REDIRECT;
                    } else {
                        content.setRequestAttributes(ATTR_ERROR,bundle.getString("message.money.notenough") );
                    }
                }else{
                    content.setSessionAttribute(ATTR_CART,cart);
                }
            } catch (ServiceException e) {
                //todo message error
                page = PAGE_ERROR;
                content.setRequestAttributes(ATTR_MESSAGE,e.getMessage());
                LOGGER.catching(e);
            }
        }else{
            content.setRequestAttributes(ATTR_ERROR,bundle.getString("message.cart.empty"));
        }

        return new CommandResult(responseType, page);
    }
}
