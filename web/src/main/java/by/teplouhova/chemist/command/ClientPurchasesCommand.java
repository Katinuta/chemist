package by.teplouhova.chemist.command;

import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.dao.OrderDAO;
import by.teplouhova.chemist.entity.impl.Order;
import by.teplouhova.chemist.entity.impl.User;
import by.teplouhova.chemist.service.OrderService;
import by.teplouhova.chemist.service.ServiceException;
import by.teplouhova.chemist.service.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ClientPurchasesCommand implements Command {
    private static final Logger LOGGER= LogManager.getLogger();

    private static final String ATTR_USER="user";
    private static final String ATTR_ORDERS="orders";
private OrderService orderService;

    public ClientPurchasesCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public CommandResult execute(SessionRequestContent content) {
        String page;
        CommandResult.ResponseType responseType=CommandResult.ResponseType.FORWARD;;
        User user= (User) content.getSessionAttribute(ATTR_USER);
        try {
            List<Order> orders=orderService.getUserOrders(user.getUserId());
            content.setRequestAttributes(ATTR_ORDERS,orders);
            page="/jsp/client/purchase.jsp";
//            responseType= CommandResult.ResponseType.FORWARD;
            LOGGER.log(Level.DEBUG,orders);
        } catch (ServiceException e) {
            page="/jsp/error/error.jsp";
            content.setRequestAttributes("error","Purchases is not found");
            LOGGER.log(Level.ERROR,e);
        }


        return new CommandResult(responseType,page);
    }
}
