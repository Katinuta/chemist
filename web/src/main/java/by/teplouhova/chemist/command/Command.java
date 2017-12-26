package by.teplouhova.chemist.command;

import by.teplouhova.chemist.controller.SessionRequestContent;


public interface Command {
   CommandResult execute(SessionRequestContent content);
}
