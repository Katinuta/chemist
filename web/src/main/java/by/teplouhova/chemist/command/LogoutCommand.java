package by.teplouhova.chemist.command;

import by.teplouhova.chemist.service.UserService;
import by.teplouhova.chemist.controller.SessionRequestContent;

public class LogoutCommand implements Command {

    private static final String ATTR_USER = "user";


    public LogoutCommand() {
    }

    @Override
    public CommandResult execute(SessionRequestContent content) {
        String page = PageConstant.PAGE_INDEX;
        CommandResult.ResponseType responseType= CommandResult.ResponseType.REDIRECT;
//        content.removeSessionAttribute(ATTR_USER);
        content.setRequestAttributes("invalid","true");
        return new CommandResult(responseType,page);
    }
}
