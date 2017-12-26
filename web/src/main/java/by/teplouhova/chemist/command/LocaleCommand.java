package by.teplouhova.chemist.command;

import by.teplouhova.chemist.service.UserService;
import by.teplouhova.chemist.controller.SessionRequestContent;

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
