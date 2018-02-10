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
import static by.teplouhova.chemist.command.PageConstant.PAGE_CLIENT_ORDER;
import static by.teplouhova.chemist.command.PageConstant.PAGE_ERROR;

/**
 * The Class ShowOrderInfoCommand.
 */
public class ShowOrderInfoCommand implements Command {

    private static final String PARAM_ORDER_ID = "order_id";
    /**
     * The Constant ATTR_ORDER.
     */
    private static final String ATTR_ORDER = "order";

    /**
     * The Constant ATTR_MESSAGE_BUNDLE.
     */
    private static final String ATTR_MESSAGE_BUNDLE = "messageBundle";

    /**
     * The Constant ATTR_MESSAGE_ERROR.
     */
    private static final String ATTR_MESSAGE_ERROR = "message";

    /**
     * The order service.
     */
    private OrderService orderService;

    /**
     * Instantiates a new show order info command.
     *
     * @param orderService the order service
     */
    public ShowOrderInfoCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Execute.
     *
     * @param content the content
     * @return the command result
     */
    @Override
    public CommandResult execute(SessionRequestContent content) {
        String page;
        CommandResult.ResponseType responseType = FORWARD;
        HashMap<String, String> params = new HashMap<>();
        params.put(PARAM_ORDER_ID, content.getParameter(PARAM_ORDER_ID));
        ResourceBundle bundle = (ResourceBundle) content.getSessionAttribute(ATTR_MESSAGE_BUNDLE);
        try {
            if (new Validator(bundle).isValid(params)) {
                Order order = orderService.getById(Long.parseLong(params.get(PARAM_ORDER_ID)));
                content.setRequestAttributes(ATTR_ORDER, order);
                page = PAGE_CLIENT_ORDER;
            } else {
                page = PAGE_ERROR;
                content.setRequestAttributes(ATTR_MESSAGE_ERROR, bundle.getString("message.order.parameter.wrong"));
            }

        } catch (ServiceException e) {
            content.setRequestAttributes(ATTR_MESSAGE_ERROR, e.getMessage());
            page = PAGE_ERROR;

        }
        return new CommandResult(responseType, page);
    }
}
