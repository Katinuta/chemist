package by.teplouhova.chemist.controller;

import by.teplouhova.chemist.manager.MessageManager;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.ResourceBundle;

@WebListener
public class SessionInitListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        ResourceBundle bundle= MessageManager.EN.getBundle();
        httpSessionEvent.getSession().setAttribute("messageBundle",bundle);
        httpSessionEvent.getSession().setAttribute("locale","en-EN");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        httpSessionEvent.getSession().removeAttribute("messageBundle");
        httpSessionEvent.getSession().removeAttribute("user");
        httpSessionEvent.getSession().removeAttribute("url");
        httpSessionEvent.getSession().removeAttribute("locale");

//        httpSessionEvent.getSession().
    }
}
