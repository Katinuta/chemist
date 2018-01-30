package by.teplouhova.chemist.command;

import by.teplouhova.chemist.controller.SessionRequestContent;

public class EmptyCommand implements Command {
    @Override
    public CommandResult execute(SessionRequestContent content) {
        return new CommandResult(CommandResult.ResponseType.REDIRECT,PageConstant.PAGE_ERROR);
    }
}
