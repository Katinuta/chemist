package by.teplouhova.chemist.controller;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Set;

public class SessionRequestContent {

    private final static Logger LOGGER = LogManager.getLogger();

    private HashMap<String, Object> requestAttributes;
    private HashMap<String, String[]> requestParameters;
    private HashMap<String, Object> sessionAttributes;
    private HashMap<String, String> requestHeaders;


    public SessionRequestContent() {
        requestAttributes = new HashMap<>();
        requestParameters = new HashMap<>();
        sessionAttributes = new HashMap<>();
        requestHeaders = new HashMap<>();
    }


    public Object getSessionAttribute(String name) {
        return sessionAttributes.get(name);
    }


    public String getParameter(String name) {
        if (!requestParameters.containsKey(name)) {
            LOGGER.log(Level.WARN, "Parameter of the request doesn't exist: " + name);
            return null;
        }
        return requestParameters.get(name)[0];
    }

    public void setSessionAttribute(String name, Object value) {
        sessionAttributes.put(name, value);
    }

    public void setRequestAttributes(String name, Object value) {
        requestAttributes.put(name, value);
    }

    public boolean isContainParameter(String name) {
        return requestParameters.containsKey(name);
    }

    public boolean isContainsAttributesStartWith(String name) {
        return requestAttributes.keySet().stream().anyMatch(key -> key.startsWith(name));
    }

    public String getRequestHeader(String name) {
        return requestHeaders.get(name);
    }

    public void extractValues(HttpServletRequest request) {
        Enumeration<String> listRequestAttributeNames = request.getAttributeNames();
        while (listRequestAttributeNames.hasMoreElements()) {
            String name = listRequestAttributeNames.nextElement();
            Object value = request.getAttribute(name);
            requestAttributes.put(name, value);
        }

        Enumeration<String> listSessionAttributeNames = request.getSession().getAttributeNames();
        while (listSessionAttributeNames.hasMoreElements()) {
            String name = listSessionAttributeNames.nextElement();
            Object value = request.getSession().getAttribute(name);
            sessionAttributes.put(name, value);
        }

        Enumeration<String> listRequestParameterNames = request.getParameterNames();
        while (listRequestParameterNames.hasMoreElements()) {
            String name = listRequestParameterNames.nextElement();
            String[] value = request.getParameterValues(name);
            requestParameters.put(name, value);
        }
        Enumeration<String> listRequestHeaderNames = request.getHeaderNames();
        while (listRequestHeaderNames.hasMoreElements()) {
            String name = listRequestHeaderNames.nextElement();
            String value = request.getHeader(name);
            requestHeaders.put(name, value);
        }

    }

    public void insertAttributes(HttpServletRequest request) {

        if (!requestAttributes.containsKey("invalid")) {
            requestAttributes.entrySet().forEach(entry -> request.setAttribute(entry.getKey(), entry.getValue()));
            sessionAttributes.entrySet().forEach(entry -> request.getSession().setAttribute(entry.getKey(), entry.getValue()));
        } else {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
        }

    }

    public Set<String> getParameterNames() {
        return requestParameters.keySet();
    }


}
