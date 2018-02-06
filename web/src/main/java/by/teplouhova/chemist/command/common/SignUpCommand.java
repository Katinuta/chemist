package by.teplouhova.chemist.command.common;

import by.teplouhova.chemist.command.Command;
import by.teplouhova.chemist.command.CommandResult;
import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.creator.UserCreator;
import by.teplouhova.chemist.entity.impl.User;
import by.teplouhova.chemist.service.ServiceException;
import by.teplouhova.chemist.service.UserService;
import by.teplouhova.chemist.validator.Validator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.ResourceBundle;

import static by.teplouhova.chemist.command.CommandResult.ResponseType.FORWARD;
import static by.teplouhova.chemist.command.CommandResult.ResponseType.REDIRECT;
import static by.teplouhova.chemist.command.PageConstant.*;

public class SignUpCommand implements Command {

    private final static Logger LOGGER = LogManager.getLogger();

    private final static String PARAM_lOGIN = "login";
    private final static String ATTR_MESSAGE_BUNDLE = "messageBundle";
    private final static String ATTR_ERROR = "error_";

    private UserService userService;

    public SignUpCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(SessionRequestContent content) {
        String page = PAGE_COMMON_SIGN_UP;
        CommandResult.ResponseType responseType = FORWARD;

        try {
            HashMap<String, String> userParams = new HashMap<>();
            ResourceBundle bundle = (ResourceBundle) content.getSessionAttribute(ATTR_MESSAGE_BUNDLE);
            content.getParameterNames()
                    .forEach(key -> userParams.put(key, content.getParameter(key)));
            Validator validator = new Validator(bundle);
            if (validator.isValid(userParams)) {
                User user = new UserCreator().create(userParams);
                if (userService.getUser(user.getLogin()) == null) {
                    userService.createUser(user);
                    page = PAGE_COMMON_SUCCESS_CREATE_USER;
                    responseType = REDIRECT;
                } else {
                    content.setRequestAttributes(ATTR_ERROR + PARAM_lOGIN, bundle.getString("message.login.double"));
                    userParams.entrySet()
                            .forEach(entry -> content.setRequestAttributes(entry.getKey(), entry.getValue()));
                }
            } else {
                userParams.entrySet()
                        .forEach(entry -> content.setRequestAttributes(entry.getKey(), entry.getValue()));
                validator.getEntrySetErrors()
                        .forEach(entry -> content.setRequestAttributes(ATTR_ERROR + entry.getKey(), entry.getValue()));
            }

        } catch (ServiceException e) {
            //todo message
            page = PAGE_ERROR;
            responseType = REDIRECT;
            LOGGER.log(Level.ERROR, e.getMessage());
        }
LOGGER.debug(page);
        return new CommandResult(responseType, page);
    }
}
