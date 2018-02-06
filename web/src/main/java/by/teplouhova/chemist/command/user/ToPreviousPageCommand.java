package by.teplouhova.chemist.command.user;

import by.teplouhova.chemist.command.Command;
import by.teplouhova.chemist.command.CommandResult;
import by.teplouhova.chemist.controller.SessionRequestContent;
//todo delete
public class ToPreviousPageCommand implements Command{
    private static final String ATTR_REFERER_URL="refererUrl";

    @Override
    public CommandResult execute(SessionRequestContent content) {
        String previousPage= (String) content.getSessionAttribute(ATTR_REFERER_URL);
        return new CommandResult(CommandResult.ResponseType.REDIRECT,previousPage);
    }
}
