package by.teplouhova.chemist.controller;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;

public class SessionRequestContent {

    private HashMap<String, Object> requestAttributes;
    private HashMap<String, String[]> requestParameters;
    private HashMap<String, Object> sessionAttributes;

    public SessionRequestContent() {
        requestAttributes = new HashMap<>();
        requestParameters = new HashMap<>();
        sessionAttributes = new HashMap<>();
    }

    public Object getRequestAttribute(String name) {
        return requestAttributes.get(name);
    }

    public Object getSessionAttribute(String name) {
        return sessionAttributes.get(name);
    }

    public String[] getParameters(String name){
        return requestParameters.get(name);
    }
    public String getParameter(String name){
        return requestParameters.get(name)[0];
    }

    public void setSessionAttribute(String name, Object value) {
        sessionAttributes.put(name,value);
    }

    public void setRequestAttributes(String name, Object value) {
       requestAttributes.put(name, value);
    }
    public void removeSessionAttribute(String name){
        sessionAttributes.remove(name);
    }

    public boolean isContainParameter(String name){
        return requestParameters.containsKey(name);
    }
    public boolean isContainsAttributesStartWith(String name){
        return requestAttributes.keySet().stream().anyMatch(key-> key.startsWith(name));
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

    }

    public void insertAttributes(HttpServletRequest request) {
        requestAttributes.entrySet().forEach(entry -> request.setAttribute(entry.getKey(), entry.getValue()));
        sessionAttributes.entrySet().forEach(entry -> request.getSession().setAttribute(entry.getKey(), entry.getValue()));
    }
}
