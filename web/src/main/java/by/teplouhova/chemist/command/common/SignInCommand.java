package by.teplouhova.chemist.command.common;

import by.teplouhova.chemist.command.Command;
import by.teplouhova.chemist.command.CommandResult;
import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.entity.impl.Medicine;
import by.teplouhova.chemist.entity.impl.RoleType;
import by.teplouhova.chemist.entity.impl.User;
import by.teplouhova.chemist.service.ServiceException;
import by.teplouhova.chemist.service.UserService;
import by.teplouhova.chemist.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.ResourceBundle;

import static by.teplouhova.chemist.command.CommandResult.ResponseType.FORWARD;
import static by.teplouhova.chemist.command.CommandResult.ResponseType.REDIRECT;
import static by.teplouhova.chemist.command.PageConstant.*;


/**
 * The Class SignInCommand.
 */
public class SignInCommand implements Command {

    private final static Logger LOGGER = LogManager.getLogger();
    /**
     * The Constant PARAM_LOGIN.
     */
    private static final String PARAM_LOGIN = "login";

    /**
     * The Constant PARAM_PASSWORD.
     */
    private static final String PARAM_PASSWORD = "password";

    /**
     * The Constant ATTR_USER.
     */
    private static final String ATTR_USER = "user";

    /**
     * The Constant ATTR_CART.
     */
    private static final String ATTR_CART = "cart";

    /**
     * The Constant ATTR_MESSAGE_BUNDLE.
     */
    private static final String ATTR_MESSAGE_BUNDLE = "messageBundle";

    /**
     * The Constant ATTR_MESSAGE_ERROR.
     */
    private static final String ATTR_MESSAGE_ERROR = "message";


    /**
     * The user service.
     */
    private UserService userService;

    /**
     * Instantiates a new sign in command.
     *
     * @param userService the user service
     */
    public SignInCommand(UserService userService) {
        this.userService = userService;
    }

    /**
     * Execute.
     *
     * @param content the content
     * @return the command result
     */
    @Override
    public CommandResult execute(SessionRequestContent content) {
        String page = PAGE_COMMON_LOGIN;
        String contextPath = content.getContextPath();
        CommandResult.ResponseType responseType = FORWARD;
        HashMap<String, String> userParams = new HashMap<>();
        userParams.put(PARAM_LOGIN, content.getParameter(PARAM_LOGIN));
        userParams.put(PARAM_PASSWORD, content.getParameter(PARAM_PASSWORD));
        try {
            ResourceBundle bundle = (ResourceBundle) content.getSessionAttribute(ATTR_MESSAGE_BUNDLE);
            Validator validator = new Validator(bundle);
            if (validator.isValid(userParams)) {
                String login = userParams.get(PARAM_LOGIN);
                String password = userParams.get(PARAM_PASSWORD);
                if (userService.checkUser(login, password)) {
                    User user = userService.getUser(login);
                    if (user.getRole().equals(RoleType.CLIENT)) {
                        page = contextPath + PAGE_CLIENT_MAIN;
                        content.setSessionAttribute(ATTR_CART, new HashMap<Medicine, Integer>());
                    }
                    if (user.getRole().equals(RoleType.PHARMACIST)) {
                        page = contextPath + PAGE_PHARMACIST_MAIN;
                    }
                    if (user.getRole().equals(RoleType.DOCTOR)) {
                        page = contextPath + PAGE_DOCTOR_MAIN;
                    }
                    content.setSessionAttribute(ATTR_USER, user);
                    responseType = REDIRECT;
                } else {
                    content.setRequestAttributes(ATTR_MESSAGE_ERROR, bundle.getString("message.wrong.login"));
                    userParams.entrySet()
                            .forEach(entry -> content.setRequestAttributes(entry.getKey(), entry.getValue()));
                }
            } else {
                userParams.entrySet()
                        .forEach(entry -> content.setRequestAttributes(entry.getKey(), entry.getValue()));
                content.setRequestAttributes(ATTR_MESSAGE_ERROR, bundle.getString("message.wrong.login"));
            }

        } catch (ServiceException e) {
            page = PAGE_ERROR;
            content.setRequestAttributes(ATTR_MESSAGE_ERROR, e.getMessage());
            LOGGER.catching(e);
        }
        return new CommandResult(responseType, page);
    }
}
