package by.teplouhova.chemist.command.client;

import by.teplouhova.chemist.command.Command;
import by.teplouhova.chemist.command.CommandResult;
import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.entity.impl.Order;
import by.teplouhova.chemist.entity.impl.User;
import by.teplouhova.chemist.service.ClientService;
import by.teplouhova.chemist.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.teplouhova.chemist.command.CommandResult.ResponseType.FORWARD;
import static by.teplouhova.chemist.command.PageConstant.PAGE_CLIENT_PURCHASE;
import static by.teplouhova.chemist.command.PageConstant.PAGE_ERROR;

/**
 * The Class ShowAllOrdersCommand.
 */
public class ShowAllOrdersCommand implements Command {
    private static final Logger LOGGER= LogManager.getLogger();
    /** The Constant ATTR_USER. */
    private static final String ATTR_USER="user";

    /** The Constant ATTR_ORDERS. */
    private static final String ATTR_ORDERS="orders";

    /** The Constant ATTR_MESSAGE. */
    private static final String ATTR_MESSAGE="message";

    /** The client service. */
    private ClientService clientService;

    /**
     * Instantiates a new show all orders command.
     *
     * @param clientService the client service
     */
    public ShowAllOrdersCommand(ClientService clientService) {

        this.clientService = clientService;
    }

    /**
     * Execute.
     *
     * @param content the content
     * @return the command result
     */
    @Override
    public CommandResult execute(SessionRequestContent content) {
        String page=PAGE_CLIENT_PURCHASE;
        CommandResult.ResponseType responseType=FORWARD;;
        User user= (User) content.getSessionAttribute(ATTR_USER);
        try {
            List<Order> orders=clientService.getUserOrders(user.getUserId());
            content.setRequestAttributes(ATTR_ORDERS,orders);
        } catch (ServiceException e) {
            page=PAGE_ERROR;
            content.setRequestAttributes(ATTR_MESSAGE,e.getMessage());
            LOGGER.catching(e);
        }

        return new CommandResult(responseType,page);
    }
}
