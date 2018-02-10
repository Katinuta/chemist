package by.teplouhova.chemist.command;

import by.teplouhova.chemist.controller.SessionRequestContent;

/**
 * The Interface Command.
 */
public interface Command {

   /**
    * Execute.
    *
    * @param content the content
    * @return the command result
    */
   CommandResult execute(SessionRequestContent content);
}
