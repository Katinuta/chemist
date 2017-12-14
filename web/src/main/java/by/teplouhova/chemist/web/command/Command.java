package by.teplouhova.chemist.web.command;

import by.teplouhova.chemist.web.SessionRequestContent;


public interface Command {
   CommandResult execute(SessionRequestContent content);
}
