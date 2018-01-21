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

    private static final Logger LOGGER= LogManager.getLogger();
    private static final String ATTR_CART="cart";
    private static  final String ATTR_MESSAGE_COUNT ="messagecount_";
    private static final  String ATTR_MESSAGE_PRESCRIP="messageprescrip_";
    private static final String ATTR_USER="user";

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
        HashMap<Medicine,Integer> cart= (HashMap<Medicine, Integer>) content.getSessionAttribute(ATTR_CART);
        User user= (User) content.getSessionAttribute(ATTR_USER);
        String page ="/jsp/client/cart.jsp";
        CommandResult.ResponseType responseType = CommandResult.ResponseType.FORWARD;
        Set<Medicine> medicines=cart.keySet();
        try {
        for (Medicine medicine : medicines  ) {
                int actualBalance=medicineService.getMedicineBalance(medicine.getMedicineId());
                int countCart=cart.get(medicine);
                if(actualBalance<countCart){
                    content.setRequestAttributes(ATTR_MESSAGE_COUNT +medicine.getMedicineId(),"Not enough count");
                }
                if(medicine.getIsNeedRecipe()){
                   if(!prescriptionService.checkPrescripExistForMedicine(medicine,countCart,user.getUserId())){
                       content.setRequestAttributes(ATTR_MESSAGE_PRESCRIP +medicine.getMedicineId(),"have not prescription");
                   }
                }
        }
        if(!content.isContainsAttributesStartWith(ATTR_MESSAGE_PRESCRIP)||
                !content.isContainsAttributesStartWith(ATTR_MESSAGE_COUNT)){
            BigDecimal balance=userService.getBalanceAccount();
            Order order=new OrderCreator().createOrder(user,cart);

//            if(user.getAccount().compareTo(order.getTotal())==1){
                orderService.create(order);
                content.setSessionAttribute("order",order);
                page="/jsp/client/order.jsp";
                responseType= CommandResult.ResponseType.REDIRECT;
//            }else{

//            }


        }
        } catch (ServiceException e) {
            page="/jsp/error/error.jsp";
            responseType= CommandResult.ResponseType.REDIRECT;
            LOGGER.log(Level.ERROR,e);
        }
        return new CommandResult(responseType, page);
    }
}
