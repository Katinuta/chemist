package by.teplouhova.chemist.controller;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Set;

/**
 * The Class SessionRequestContent.
 */
public class SessionRequestContent {

    private final static Logger LOGGER = LogManager.getLogger();

    private String contextPath;

    /** The request attributes. */
    private HashMap<String, Object> requestAttributes;

    /** The request parameters. */
    private HashMap<String, String[]> requestParameters;

    /** The session attributes. */
    private HashMap<String, Object> sessionAttributes;

    /** The request headers. */
    private HashMap<String, String> requestHeaders;


    /**
     * Instantiates a new session request content.
     */
    public SessionRequestContent() {
        requestAttributes = new HashMap<>();
        requestParameters = new HashMap<>();
        sessionAttributes = new HashMap<>();
        requestHeaders = new HashMap<>();
    }


    /**
     * Gets the session attribute.
     *
     * @param name the name
     * @return the session attribute
     */
    public Object getSessionAttribute(String name) {
        return sessionAttributes.get(name);
    }


    /**
     * Gets the parameter.
     *
     * @param name the name
     * @return the parameter
     */
    public String getParameter(String name) {
        if (!requestParameters.containsKey(name)) {
            LOGGER.log(Level.WARN, "Parameter of the request doesn't exist: " + name);
            return null;
        }
        return requestParameters.get(name)[0];
    }

    /**
     * Sets the session attribute.
     *
     * @param name the name
     * @param value the value
     */
    public void setSessionAttribute(String name, Object value) {
        sessionAttributes.put(name, value);
    }

    /**
     * Sets the request attributes.
     *
     * @param name the name
     * @param value the value
     */
    public void setRequestAttributes(String name, Object value) {
        requestAttributes.put(name, value);
    }

    /**
     * Checks if is contain parameter.
     *
     * @param name the name
     * @return true, if is contain parameter
     */
    public boolean isContainParameter(String name) {
        return requestParameters.containsKey(name);
    }

    /**
     * Checks if is contains attributes start with.
     *
     * @param name the name
     * @return true, if is contains attributes start with
     */
    public boolean isContainsAttributesStartWith(String name) {
        return requestAttributes.keySet().stream().anyMatch(key -> key.startsWith(name));
    }

    /**
     * Gets the request header.
     *
     * @param name the name
     * @return the request header
     */
    public String getRequestHeader(String name) {
        return requestHeaders.get(name);
    }

    /**
     * Extract values.
     *
     * @param request the request
     */
    public void extractValues(HttpServletRequest request) {
        contextPath=request.getContextPath();
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

    /**
     * Insert attributes.
     *
     * @param request the request
     */
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

    /**
     * Gets the parameter names.
     *
     * @return the parameter names
     */
    public Set<String> getParameterNames() {
        return requestParameters.keySet();
    }

    public String getContextPath() {
        return contextPath;
    }
}
