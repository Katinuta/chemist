package by.teplouhova.chemist.web.command;

import javax.servlet.http.HttpServletRequest;

public interface Command {
   CommandResult execute(HttpServletRequest request);
}