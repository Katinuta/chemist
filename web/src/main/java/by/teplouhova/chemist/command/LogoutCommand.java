package by.teplouhova.chemist.command;

import by.teplouhova.chemist.UserService;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {


    public LogoutCommand(UserService userService) {
    }

    @Override
    public String execute(HttpServletRequest request) {
        return null;
    }
}
