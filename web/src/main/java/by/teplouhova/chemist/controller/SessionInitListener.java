package by.teplouhova.chemist.controller;

import by.teplouhova.chemist.manager.MessageManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

@WebListener
public class SessionInitListener implements HttpSessionListener {


    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        ResourceBundle bundle = MessageManager.EN.getBundle();
        httpSessionEvent.getSession().setAttribute("messageBundle", bundle);
        httpSessionEvent.getSession().setAttribute("locale", "en-EN");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

    }
}
