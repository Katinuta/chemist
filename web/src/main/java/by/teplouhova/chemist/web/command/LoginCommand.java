package by.teplouhova.chemist.web.command;

import by.teplouhova.chemist.service.UserService;
import by.teplouhova.chemist.service.exception.ServiceException;
import by.teplouhova.chemist.entity.impl.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {

    private final static Logger LOGGER= LogManager.getLogger();

    private static String PARAM_LOGIN= "login";
    private static String PARAM_PASSWORD= "password";

    private UserService userService;

    public LoginCommand(UserService userService) {
        this.userService=userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String page=null;
        try {
            String login=request.getParameter(PARAM_LOGIN);
            String password=request.getParameter(PARAM_PASSWORD);
           // LOGGER.log(Level.INFO,login+password);
boolean flag=userService.checkUser(login,password);
     //      LOGGER.log(Level.INFO,flag);
            if(flag){

                page="/jsp/common/main.jsp";


                User user=userService.getUser(login);
                LOGGER.log(Level.INFO,page);
                request.setAttribute("user",user);
            }

        } catch (ServiceException e) {
            page="/jsp/error/error.jsp";
            LOGGER.log(Level.INFO,page);
        }
        LOGGER.log(Level.INFO,page);
        return new CommandResult(CommandResult.ResponseType.FORWARD,page);
    }
}
