package by.teplouhova.chemist.command;

import by.teplouhova.chemist.controller.SessionRequestContent;

public class SignInCommand implements Command {
    @Override
    public CommandResult execute(SessionRequestContent content) {
        return new CommandResult(CommandResult.ResponseType.FORWARD,PageConstant.PAGE_COMMON_LOGIN);
    }
}
