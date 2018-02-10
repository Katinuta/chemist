package by.teplouhova.chemist.command.doctor;

import by.teplouhova.chemist.command.Command;
import by.teplouhova.chemist.command.CommandResult;
import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.entity.impl.RoleType;
import by.teplouhova.chemist.entity.impl.User;
import by.teplouhova.chemist.service.ServiceException;
import by.teplouhova.chemist.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.ResourceBundle;

import static by.teplouhova.chemist.command.CommandResult.ResponseType.FORWARD;
import static by.teplouhova.chemist.command.CommandResult.ResponseType.REDIRECT;
import static by.teplouhova.chemist.command.PageConstant.PAGE_DOCTOR_MAIN;
import static by.teplouhova.chemist.command.PageConstant.PAGE_ERROR;

/**
 * The Class ShowAllClientsCommand.
 */
public class ShowAllClientsCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * The Constant PARAM_CURRENT_PAGE.
     */
    private static final String PARAM_CURRENT_PAGE = "current_page";

    /**
     * The Constant ATTR_COUNT_PAGES.
     */
    private static final String ATTR_COUNT_PAGES = "countpages";

    /**
     * The Constant ATTR_CLIENTS.
     */
    private static final String ATTR_CLIENTS = "clients";

    /**
     * The Constant ATTR_MESSAGE_ERROR.
     */
    private static final String ATTR_MESSAGE_ERROR = "message";

    /**
     * The service.
     */
    private UserService service;

    /**
     * Instantiates a new show all clients command.
     *
     * @param service the service
     */
    public ShowAllClientsCommand(UserService service) {
        this.service = service;
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
        int currentPage = Integer.parseInt(content.getParameter(PARAM_CURRENT_PAGE));
        int[] countPages = new int[1];
        String role = RoleType.CLIENT.getName();
        try {
            List<User> users = service.getUserByRoleByPage(role, currentPage, countPages);
            content.setRequestAttributes(ATTR_CLIENTS, users);
            content.setRequestAttributes(ATTR_COUNT_PAGES, countPages[0]);
            page = PAGE_DOCTOR_MAIN;

        } catch (ServiceException e) {
            content.setRequestAttributes(ATTR_MESSAGE_ERROR, e.getMessage());
            page = PAGE_ERROR;
            LOGGER.catching(e);
        }


        return new CommandResult(responseType, page);
    }
}
