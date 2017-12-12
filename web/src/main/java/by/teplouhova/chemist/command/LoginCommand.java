package by.teplouhova.chemist.command;

import by.teplouhova.chemist.UserService;
import by.teplouhova.chemist.dao.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {

    private static String PARAM_LOGIN= "input.login";
    private static String PARAM_PASSWORD= "input.password";

    private UserService userService;

    public LoginCommand(UserService userService) {
        this.userService=userService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        try {
            String login=request.getParameter(PARAM_LOGIN);
            String password=request.getParameter(PARAM_PASSWORD);
            userService.checkUser(login,password);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return null;
    }
}
