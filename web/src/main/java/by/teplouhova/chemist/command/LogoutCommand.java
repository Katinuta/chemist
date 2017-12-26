package by.teplouhova.chemist.command;

import by.teplouhova.chemist.service.UserService;
import by.teplouhova.chemist.controller.SessionRequestContent;

public class LogoutCommand implements Command {


    public LogoutCommand(UserService userService) {
    }

    @Override
    public CommandResult execute(SessionRequestContent content) {
        return null;
    }
}
