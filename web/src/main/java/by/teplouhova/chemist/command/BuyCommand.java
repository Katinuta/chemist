package by.teplouhova.chemist.command;

import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.creator.OrderCreator;
import by.teplouhova.chemist.entity.impl.Medicine;
import by.teplouhova.chemist.entity.impl.Order;
import by.teplouhova.chemist.entity.impl.User;
import by.teplouhova.chemist.service.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Set;

public class BuyCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String ATTR_CART = "cart";
    private static final String ATTR_MESSAGE_COUNT = "messagecount_";
    private static final String ATTR_MESSAGE_PRESCRIP = "messageprescrip_";
    private static final String ATTR_USER = "user";
    private static final String ATTR_ERROR = "error";

    private UserService userService;
    private OrderService orderService;
    private MedicineService medicineService;
    private PrescriptionService prescriptionService;

    public BuyCommand(UserService userService, OrderService orderService, MedicineService medicineService, PrescriptionService prescriptionService) {
        this.userService = userService;
        this.orderService = orderService;
        this.medicineService = medicineService;
        this.prescriptionService = prescriptionService;
    }

    @Override
    public CommandResult execute(SessionRequestContent content) {
        HashMap<Medicine, Integer> cart = (HashMap<Medicine, Integer>) content.getSessionAttribute(ATTR_CART);
        User user = (User) content.getSessionAttribute(ATTR_USER);
        String page =PageConstant.PAGE_CLIENT_CART;
        CommandResult.ResponseType responseType = CommandResult.ResponseType.FORWARD;
        if(!cart.isEmpty()){
            Set<Medicine> medicines = cart.keySet();
            try {
                for (Medicine medicine : medicines) {
                    int actualBalance = medicineService.getMedicineBalance(medicine.getMedicineId());
                    int countCart = cart.get(medicine);
                    if(countCart==0){
                        cart.remove(medicine);
                    }
                    if (actualBalance < countCart) {
                        content.setRequestAttributes(ATTR_MESSAGE_COUNT + medicine.getMedicineId(), "Not enough count");
                    }
                    if (medicine.getIsNeedRecipe()) {
                        if (!prescriptionService.checkPrescripExistForMedicine(medicine, countCart, user.getUserId())) {
                            content.setRequestAttributes(ATTR_MESSAGE_PRESCRIP + medicine.getMedicineId(), "have not prescription");
                        }
                    }
                }
                if (!content.isContainsAttributesStartWith(ATTR_MESSAGE_PRESCRIP) ||
                        !content.isContainsAttributesStartWith(ATTR_MESSAGE_COUNT)) {
                    BigDecimal balance = userService.getBalanceAccount(user.getUserId());
                    user.setAccount(balance);
                    Order order = new OrderCreator().create(user, cart);

                    if (user.getAccount() != null && user.getAccount().compareTo(order.getTotal()) != -1) {
                         orderService.create(order);
                        content.removeSessionAttribute(ATTR_CART);
                        page = PageConstant.PAGE_CLIENT_SUCCESS_ORDER;
                        responseType = CommandResult.ResponseType.REDIRECT;
                    } else {
                        content.setRequestAttributes(ATTR_ERROR, "You have not got enough money");
                    }
                }
            } catch (ServiceException e) {
                page = "/jsp/error/error.jsp";
                responseType = CommandResult.ResponseType.REDIRECT;
                LOGGER.log(Level.ERROR, e);
            }
        }else{
            content.setRequestAttributes(ATTR_ERROR,"Your cart is empty");
        }

        return new CommandResult(responseType, page);
    }
}
