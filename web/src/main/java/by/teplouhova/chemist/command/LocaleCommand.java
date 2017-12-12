package by.teplouhova.chemist.command;

import by.teplouhova.chemist.UserService;

import javax.servlet.http.HttpServletRequest;

public class LocaleCommand implements Command {

    private UserService userService;

    public LocaleCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        return null;
    }
}
