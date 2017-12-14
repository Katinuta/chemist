package by.teplouhova.chemist.web.command;

import by.teplouhova.chemist.service.UserService;
import by.teplouhova.chemist.service.exception.ServiceException;
import by.teplouhova.chemist.entity.impl.User;
import by.teplouhova.chemist.web.SessionRequestContent;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

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
                page = "/jsp/common/main.jsp";
                User user = userService.getUser(login);
                content.setSessionAttribute("user", user);
                responseType= CommandResult.ResponseType.REDIRECT;
            }else{
                //content.
                page="/index.jsp";
            }

        } catch (ServiceException e) {
            page = "/jsp/error/error.jsp";
        }
        return new CommandResult(CommandResult.ResponseType.FORWARD, page);
    }
}
