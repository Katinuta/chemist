package by.teplouhova.chemist.command.common;

import by.teplouhova.chemist.command.Command;
import by.teplouhova.chemist.command.CommandResult;
import by.teplouhova.chemist.controller.SessionRequestContent;

import java.util.ResourceBundle;

import static by.teplouhova.chemist.command.CommandResult.ResponseType.REDIRECT;
import static by.teplouhova.chemist.command.PageConstant.PAGE_ERROR;



public class EmptyCommand implements Command {

    private final static String ATTR_MESSAGE_BUNDLE = "messageBundle";
    private final static String ATTR_MESSAGE_ERROR = "message";
    private final static String ATTR_COMMAND_NAME="command";

    @Override
    public CommandResult execute(SessionRequestContent content) {

        ResourceBundle bundle = (ResourceBundle) content.getSessionAttribute(ATTR_MESSAGE_BUNDLE);
        content.setRequestAttributes(ATTR_MESSAGE_ERROR,
                bundle.getString("message.not.support.operation") +content.getParameter(ATTR_COMMAND_NAME));
        return new CommandResult(REDIRECT, PAGE_ERROR);
    }
}
