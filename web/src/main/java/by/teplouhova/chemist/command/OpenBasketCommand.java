package by.teplouhova.chemist.command;

import by.teplouhova.chemist.controller.SessionRequestContent;

public class OpenBasketCommand implements Command {
    @Override
    public CommandResult execute(SessionRequestContent content) {
        String page=null;
        CommandResult responseType=null;

        return new CommandResult(CommandResult.ResponseType.FORWARD,page);
    }
}
