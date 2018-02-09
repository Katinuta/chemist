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

    private  static final Logger LOGGER = LogManager.getLogger();

    private  static final String PARAM_lOGIN = "login";
    private  static final String PARAM_USER_NAME = "user_name";
    private  static final String PARAM_SURNAME = "surname";
    private  static final String PARAM_ACCOUNT="account";
    private  static final String PARAM_PHONE="phone";
    private  static final String PARAM_PASSWORD="password";
    private  static final String ATTR_MESSAGE_BUNDLE = "messageBundle";
    private  static final String ATTR_ERROR = "error_";
    private  static final String ATTR_MESSAGE_ERROR = "message";

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
            userParams.put(PARAM_USER_NAME,content.getParameter(PARAM_USER_NAME));
            userParams.put(PARAM_lOGIN,content.getParameter(PARAM_lOGIN));
            userParams.put(PARAM_SURNAME,content.getParameter(PARAM_SURNAME));
            userParams.put(PARAM_ACCOUNT,content.getParameter(PARAM_ACCOUNT));
            userParams.put(PARAM_PHONE,content.getParameter(PARAM_PHONE));
            userParams.put(PARAM_PASSWORD,content.getParameter(PARAM_PASSWORD));
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
            page = PAGE_ERROR;
            content.setRequestAttributes(ATTR_MESSAGE_ERROR,e.getMessage());
            LOGGER.catching( e);
        }

        return new CommandResult(responseType, page);
    }
}
