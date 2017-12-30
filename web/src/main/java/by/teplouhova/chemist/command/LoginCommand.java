package by.teplouhova.chemist.command;

import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.entity.impl.Medicine;
import by.teplouhova.chemist.service.UserService;
import by.teplouhova.chemist.service.ServiceException;
import by.teplouhova.chemist.entity.impl.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;

public class LoginCommand implements Command {

    private final static Logger LOGGER = LogManager.getLogger();

    private static String PARAM_LOGIN = "login";
    private static String PARAM_PASSWORD = "password";

    private UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(SessionRequestContent content) {
        String page = null;
        CommandResult.ResponseType responseType=null;
        try {
            String login = content.getParameter(PARAM_LOGIN);
            String password = content.getParameter(PARAM_PASSWORD);

            if (userService.checkUser(login, password)) {
                page = "/jsp/client/main.jsp";
                User user = userService.getUser(login);
                content.setSessionAttribute("user", user);
                content.setSessionAttribute("basket",new HashMap<Medicine,Integer>());
                responseType= CommandResult.ResponseType.REDIRECT;
            }else{
                //content.
                page="/index.jsp";
            }

        } catch (ServiceException e) {
            page = "/jsp/error/error.jsp";
        }
        return new CommandResult(responseType, page);
    }
}
