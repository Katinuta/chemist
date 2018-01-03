package by.teplouhova.chemist.command;

import by.teplouhova.chemist.controller.SessionRequestContent;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LocaleCommand implements Command {
    private static final Logger LOGGER= LogManager.getLogger();

    private static final String PARAM_NEWLOCALE="newlocale";
    private static final String PARAM_PAGE="page";
    private static final String ATTR_LOCALE="locale";

    @Override
    public CommandResult execute(SessionRequestContent content) {
        String locale=content.getParameter(PARAM_NEWLOCALE);
        String page=content.getParameter(PARAM_PAGE);
        content.setSessionAttribute(ATTR_LOCALE,locale);
        return new CommandResult(CommandResult.ResponseType.FORWARD,page);
    }
}
