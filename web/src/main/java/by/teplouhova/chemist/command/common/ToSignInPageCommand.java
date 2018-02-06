package by.teplouhova.chemist.command.common;

import by.teplouhova.chemist.command.Command;
import by.teplouhova.chemist.command.CommandResult;
import by.teplouhova.chemist.controller.SessionRequestContent;

import static by.teplouhova.chemist.command.CommandResult.ResponseType.FORWARD;
import static by.teplouhova.chemist.command.PageConstant.PAGE_COMMON_LOGIN;

public class ToSignInPageCommand implements Command {
    @Override
    public CommandResult execute(SessionRequestContent content) {
        return new CommandResult(FORWARD, PAGE_COMMON_LOGIN);
    }
}
