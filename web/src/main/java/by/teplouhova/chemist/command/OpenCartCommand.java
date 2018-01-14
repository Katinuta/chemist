package by.teplouhova.chemist.command;

import by.teplouhova.chemist.controller.SessionRequestContent;

public class OpenCartCommand implements Command {
    @Override
    public CommandResult execute(SessionRequestContent content) {
        String page="/jsp/client/cart.jsp";
        CommandResult responseType=null;

        return new CommandResult(CommandResult.ResponseType.FORWARD,page);
    }
}
