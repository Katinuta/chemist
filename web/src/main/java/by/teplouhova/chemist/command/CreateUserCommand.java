package by.teplouhova.chemist.command;

import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.entity.impl.User;
import by.teplouhova.chemist.service.ServiceException;
import by.teplouhova.chemist.service.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

public class CreateUserCommand implements Command {

    private final static Logger LOGGER=LogManager.getLogger();

    private final static String PARAM_NAME = "name";
    private final static String PARAM_SURNAME = "surname";
    private final static String PARAM_ACCOUNT = "account";
    private final static String PARAM_lOGIN = "login";
    private final static String PARAM_PASSWORD = "password";
    private final static String PARAM_PHONE = "phone";
    private final static String PARAM_EMAIL = "email";

    private UserService userService;

    public CreateUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(SessionRequestContent content) {
        String page = null;
        CommandResult.ResponseType responseType = null;
        try {
            String name = content.getParameter(PARAM_NAME);
            String surname = content.getParameter(PARAM_SURNAME);
            String account = content.getParameter(PARAM_ACCOUNT);
            String login = content.getParameter(PARAM_lOGIN);
            String password = content.getParameter(PARAM_PASSWORD);
            String phone = content.getParameter(PARAM_PHONE);
            User user=userService.createUser(name, surname, new BigDecimal(account), login, password, phone);
            content.setSessionAttribute("user",user);
            page="/jsp/client/main.jsp";
            responseType= CommandResult.ResponseType.REDIRECT;


        } catch (ServiceException e) {
            content.setRequestAttributes("error","User didn't created");
            page="/jsp/common/registration.jsp";
            responseType= CommandResult.ResponseType.FORWARD;

        }

        return new CommandResult(responseType,page);
    }
}
