package by.teplouhova.chemist.command.common;

import by.teplouhova.chemist.command.Command;
import by.teplouhova.chemist.command.CommandResult;
import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.service.UserService;

import static by.teplouhova.chemist.command.CommandResult.ResponseType.FORWARD;
import static by.teplouhova.chemist.command.PageConstant.PAGE_COMMON_SIGN_UP;

public class ToSingUpPageCommand implements Command {


    private UserService userService;

    public ToSingUpPageCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(SessionRequestContent content) {

        return new CommandResult(FORWARD, PAGE_COMMON_SIGN_UP);
    }
}
