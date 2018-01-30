package by.teplouhova.chemist.command;

import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.entity.impl.Order;
import by.teplouhova.chemist.service.OrderService;
import by.teplouhova.chemist.service.ServiceException;

public class OrderDetailCommand implements Command {

    private static final String ATTR_ORDER_ID="orderId";
    private static final String ATTR_ORDER="order";
    private OrderService orderService;

    public OrderDetailCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public CommandResult execute(SessionRequestContent content) {
        String page;
        CommandResult.ResponseType responseType;
        long orderId=Long.parseLong(content.getParameter(ATTR_ORDER_ID));
        try {
            Order order=orderService.getById(orderId);
            content.setRequestAttributes(ATTR_ORDER,order);
            page=PageConstant.PAGE_CLIENT_ORDER;
            responseType= CommandResult.ResponseType.FORWARD;

        } catch (ServiceException e) {
            page="/jsp/error/error.jsp";
            responseType= CommandResult.ResponseType.REDIRECT;
        }


        return new CommandResult(responseType,page);
    }
}
