package by.teplouhova.chemist.command.common;

import by.teplouhova.chemist.command.Command;
import by.teplouhova.chemist.command.CommandResult;
import by.teplouhova.chemist.controller.SessionRequestContent;

import static by.teplouhova.chemist.command.CommandResult.ResponseType.REDIRECT;
import static by.teplouhova.chemist.command.PageConstant.PAGE_ERROR;



public class EmptyCommand implements Command {
    @Override
    public CommandResult execute(SessionRequestContent content) {
        return new CommandResult(REDIRECT, PAGE_ERROR);
    }
}
