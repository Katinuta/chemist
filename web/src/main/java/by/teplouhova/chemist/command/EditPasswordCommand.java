package by.teplouhova.chemist.command;

import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EditPasswordCommand implements Command {

    private static final Logger LOGGER= LogManager.getLogger();
    private  UserService userService;

    public EditPasswordCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(SessionRequestContent content) {
        String page=PageConstant.PAGE_COMMON_EDIT_PASSWORD;
        CommandResult.ResponseType responseType= CommandResult.ResponseType.FORWARD;

        return new CommandResult(responseType,page);
    }
}
