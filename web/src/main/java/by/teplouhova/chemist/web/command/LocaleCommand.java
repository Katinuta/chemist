package by.teplouhova.chemist.web.command;

import by.teplouhova.chemist.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class LocaleCommand implements Command {

    private UserService userService;

    public LocaleCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) {
        return null;
    }
}
