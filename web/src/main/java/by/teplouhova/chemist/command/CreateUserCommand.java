package by.teplouhova.chemist.command;

import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.creator.UserCreator;
import by.teplouhova.chemist.entity.impl.User;
import by.teplouhova.chemist.service.ServiceException;
import by.teplouhova.chemist.service.UserService;
import by.teplouhova.chemist.validator.UserValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.ResourceBundle;

public class CreateUserCommand implements Command {

    private final static Logger LOGGER = LogManager.getLogger();

    private final static String PARAM_NAME = "name";
    private final static String PARAM_SURNAME = "surname";
    private final static String PARAM_ACCOUNT = "account";
    private final static String PARAM_lOGIN = "login";
    private final static String PARAM_PASSWORD = "password";
    private final static String PARAM_PHONE = "phone";
    private final static String ATTR_MESSAGE_BUNDLE = "messageBundle";
    private final static String ATTR_ERROR = "error_";

    private UserService userService;

    public CreateUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(SessionRequestContent content) {
        String page = PageConstant.PAGE_COMMON_SIGN_UP;
        CommandResult.ResponseType responseType = CommandResult.ResponseType.FORWARD;

        try {
            HashMap<String, String> userParams = new HashMap<>();
            userParams.put(PARAM_NAME, content.getParameter(PARAM_NAME));
            userParams.put(PARAM_SURNAME, content.getParameter(PARAM_SURNAME));
            userParams.put(PARAM_ACCOUNT, content.getParameter(PARAM_ACCOUNT));

            userParams.put(PARAM_lOGIN, content.getParameter(PARAM_lOGIN));
            userParams.put(PARAM_PASSWORD, content.getParameter(PARAM_PASSWORD));
            userParams.put(PARAM_PHONE, content.getParameter(PARAM_PHONE));
            ResourceBundle bundle = (ResourceBundle) content.getSessionAttribute(ATTR_MESSAGE_BUNDLE);
            UserValidator validator = new UserValidator(bundle);
            if (validator.isUserValid(userParams)) {
                User user = new UserCreator().create(userParams);
                if (userService.getUser(user.getLogin()) == null) {
                    userService.createUser(user);
                    page = PageConstant.PAGE_COMMON_SUCCESS_CREATE_USER;
                    responseType = CommandResult.ResponseType.REDIRECT;
                } else {
                    content.setRequestAttributes(ATTR_ERROR + PARAM_lOGIN, bundle.getString("message.login.double"));
                    userParams.entrySet()
                            .forEach(entry -> content.setRequestAttributes(entry.getKey(), entry.getValue()));
                }

            } else {
                userParams.entrySet()
                        .forEach(entry -> content.setRequestAttributes(entry.getKey(), entry.getValue()));
                validator.gerSetErrors()
                        .forEach(entry -> content.setRequestAttributes(ATTR_ERROR + entry.getKey(), entry.getValue()));
            }


        } catch (ServiceException e) {

            page = PageConstant.PAGE_ERROR;
            responseType = CommandResult.ResponseType.REDIRECT;
            LOGGER.log(Level.ERROR,e.getMessage());
        }

        return new CommandResult(responseType, page);
    }
}
