package by.teplouhova.chemist.web.command;

import by.teplouhova.chemist.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {


    public LogoutCommand(UserService userService) {
    }

    @Override
    public CommandResult execute(HttpServletRequest request) {
        return null;
    }
}
