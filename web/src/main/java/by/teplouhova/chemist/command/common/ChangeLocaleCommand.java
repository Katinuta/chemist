package by.teplouhova.chemist.command.common;

import by.teplouhova.chemist.command.Command;
import by.teplouhova.chemist.command.CommandResult;
import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.manager.MessageManager;

import java.util.ResourceBundle;

import static by.teplouhova.chemist.command.CommandResult.ResponseType.REDIRECT;


public class ChangeLocaleCommand implements Command {
    private static final String PARAM_NEW_LOCALE="new_locale";
    private static final String HEADER_REFERER= "referer";
    private static final String ATTR_LOCALE="locale";
    private static  final String ATTR_MESSAGE_BUNDLE="messageBundle";

    @Override
    public CommandResult execute(SessionRequestContent content) {
        String locale=content.getParameter(PARAM_NEW_LOCALE);
        String page= content.getRequestHeader(HEADER_REFERER);
        content.setSessionAttribute(ATTR_LOCALE,locale);
        ResourceBundle bundle=MessageManager.EN.getBundle();
        if("ru-RU".equals(locale)){
            bundle=MessageManager.RU.getBundle();
        }

        content.setSessionAttribute(ATTR_MESSAGE_BUNDLE, bundle);
        return new CommandResult(REDIRECT,page);
    }
}
