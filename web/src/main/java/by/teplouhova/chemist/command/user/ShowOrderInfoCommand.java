package by.teplouhova.chemist.command.user;

import by.teplouhova.chemist.command.Command;
import by.teplouhova.chemist.command.CommandResult;
import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.entity.impl.Order;
import by.teplouhova.chemist.service.OrderService;
import by.teplouhova.chemist.service.ServiceException;
import by.teplouhova.chemist.validator.Validator;

import java.util.HashMap;
import java.util.ResourceBundle;

import static by.teplouhova.chemist.command.CommandResult.ResponseType.FORWARD;
import static by.teplouhova.chemist.command.CommandResult.ResponseType.REDIRECT;
import static by.teplouhova.chemist.command.PageConstant.PAGE_CLIENT_ORDER;
import static by.teplouhova.chemist.command.PageConstant.PAGE_ERROR;

public class ShowOrderInfoCommand implements Command {

    private static final String ATTR_ORDER_ID="order_id";
    private static final String ATTR_ORDER="order";
    private static final String ATTR_MESSAGE_BUNDLE="messageBundle";
    private static final String ATTR_MESSAGE_ERROR="message";
    private OrderService orderService;

    public ShowOrderInfoCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public CommandResult execute(SessionRequestContent content) {
        String page;
        CommandResult.ResponseType responseType;
        HashMap<String,String> params=new HashMap<>();
        content.getParameterNames().forEach(key->params.put(key,content.getParameter(key)));
        ResourceBundle bundle= (ResourceBundle) content.getSessionAttribute(ATTR_MESSAGE_BUNDLE);
//        long orderId=Long.parseLong();

        try {
            if(new Validator(bundle).isValid(params)){
                Order order=orderService.getById(Long.parseLong(params.get(ATTR_ORDER_ID)));
                content.setRequestAttributes(ATTR_ORDER,order);
                page= PAGE_CLIENT_ORDER;
                responseType= FORWARD;
            }else{
                page=PAGE_ERROR;
                responseType=FORWARD;
                content.setRequestAttributes(ATTR_MESSAGE_ERROR,bundle.getString("message.order.parameter.wrong"));

            }

        } catch (ServiceException e) {
            //todo message
            page=PAGE_ERROR;
            responseType=REDIRECT;
        }
        return new CommandResult(responseType,page);
    }
}
