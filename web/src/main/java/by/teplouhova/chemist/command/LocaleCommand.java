package by.teplouhova.chemist.command;

import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.manager.MessageManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.jstl.fmt.LocalizationContext;
import java.util.ResourceBundle;

public class LocaleCommand implements Command {
    private static final Logger LOGGER= LogManager.getLogger();

    private static final String PARAM_NEW_LOCALE="newlocale";
    private static final String ATTR_PAGE="refererUrl";
    private static final String ATTR_LOCALE="locale";
    private static  final String ATTR_MESSAGE_BUNDLE="messageBundle";

    @Override
    public CommandResult execute(SessionRequestContent content) {
        String locale=content.getParameter(PARAM_NEW_LOCALE);
        String page= (String) content.getSessionAttribute(ATTR_PAGE);
        content.setSessionAttribute(ATTR_LOCALE,locale);
        ResourceBundle bundle=MessageManager.EN.getBundle();
        if("ru-RU".equals(locale)){
            bundle=MessageManager.RU.getBundle();
        }
        content.setSessionAttribute(ATTR_MESSAGE_BUNDLE, bundle);
        return new CommandResult(CommandResult.ResponseType.REDIRECT,page);
    }
}
