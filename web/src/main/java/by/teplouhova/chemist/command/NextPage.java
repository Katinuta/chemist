package by.teplouhova.chemist.command;

import by.teplouhova.chemist.controller.SessionRequestContent;

public class NextPage implements Command {
    private static final String ATTR_PAGE="currentpage";

    @Override
    public CommandResult execute(SessionRequestContent content) {
    String  numberPage=content.getParameter(ATTR_PAGE);
    content.setRequestAttributes(ATTR_PAGE,numberPage);
    String page="/jsp/client/main.jsp";
        return new CommandResult(CommandResult.ResponseType.FORWARD,page);
    }
}
