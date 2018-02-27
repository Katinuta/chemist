package by.teplouhova.chemist.command.common;

import by.teplouhova.chemist.command.Command;
import by.teplouhova.chemist.command.CommandResult;
import by.teplouhova.chemist.controller.SessionRequestContent;

import java.util.ResourceBundle;

import static by.teplouhova.chemist.command.CommandResult.ResponseType.FORWARD;
import static by.teplouhova.chemist.command.PageConstant.PAGE_ERROR;

/**
 * The Class EmptyCommand.
 */
public class EmptyCommand implements Command {

    /**
     * The Constant ATTR_MESSAGE_BUNDLE.
     */
    private final static String ATTR_MESSAGE_BUNDLE = "messageBundle";

    /**
     * The Constant ATTR_MESSAGE_ERROR.
     */
    private final static String ATTR_MESSAGE_ERROR = "message";

    /**
     * The Constant ATTR_COMMAND_NAME.
     */
    private final static String ATTR_COMMAND_NAME = "command";

    /**
     * Execute.
     *
     * @param content the content
     * @return the command result
     */
    @Override
    public CommandResult execute(SessionRequestContent content) {

        ResourceBundle bundle = (ResourceBundle) content.getSessionAttribute(ATTR_MESSAGE_BUNDLE);
        content.setRequestAttributes(ATTR_MESSAGE_ERROR,
                bundle.getString("message.not.support.operation") + content.getParameter(ATTR_COMMAND_NAME));
        return new CommandResult(FORWARD, PAGE_ERROR);
    }
}
