package by.teplouhova.chemist.command.user;

import by.teplouhova.chemist.command.Command;
import by.teplouhova.chemist.command.CommandResult;
import by.teplouhova.chemist.controller.SessionRequestContent;

import static by.teplouhova.chemist.command.CommandResult.ResponseType.REDIRECT;
import static by.teplouhova.chemist.command.PageConstant.PAGE_INDEX;


public class SignOutCommand implements Command {

    private static final String ATTR_INVALID="invalid";

    public SignOutCommand() {
    }

    @Override
    public CommandResult execute(SessionRequestContent content) {
        String page = PAGE_INDEX;
        CommandResult.ResponseType responseType= REDIRECT;
        content.setRequestAttributes(ATTR_INVALID,"true");
        return new CommandResult(responseType,page);
    }
}
