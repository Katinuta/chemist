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
/**
 * The listener interface for receiving sessionInit events.
 * The class that is interested in processing a sessionInit
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addSessionInitListener<code> method. When
 * the sessionInit event occurs, that object's appropriate
 * method is invoked.
 *
 */
@WebListener
public class SessionInitListener implements HttpSessionListener {


    /**
     * Invoked when session is created.
     *
     * @param httpSessionEvent the http session event
     */
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        ResourceBundle bundle = MessageManager.EN.getBundle();
        httpSessionEvent.getSession().setAttribute("messageBundle", bundle);
        httpSessionEvent.getSession().setAttribute("locale", "en-EN");
    }

    /**
     * Session destroyed.
     *
     * @param httpSessionEvent the http session event
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

    }
}
