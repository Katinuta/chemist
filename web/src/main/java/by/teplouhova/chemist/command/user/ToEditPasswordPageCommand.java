package by.teplouhova.chemist.command.user;

import by.teplouhova.chemist.command.Command;
import by.teplouhova.chemist.command.CommandResult;
import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.teplouhova.chemist.command.CommandResult.ResponseType.FORWARD;
import static by.teplouhova.chemist.command.PageConstant.PAGE_COMMON_EDIT_PASSWORD;


public class ToEditPasswordPageCommand implements Command {


    @Override
    public CommandResult execute(SessionRequestContent content) {
        String page= PAGE_COMMON_EDIT_PASSWORD;
        CommandResult.ResponseType responseType= FORWARD;

        return new CommandResult(responseType,page);
    }
}
