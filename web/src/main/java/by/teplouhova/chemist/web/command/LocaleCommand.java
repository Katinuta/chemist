package by.teplouhova.chemist.web.command;

import by.teplouhova.chemist.service.UserService;
import by.teplouhova.chemist.web.SessionRequestContent;

import javax.servlet.http.HttpServletRequest;

public class LocaleCommand implements Command {

    private UserService userService;

    public LocaleCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(SessionRequestContent content) {
        return null;
    }
}
