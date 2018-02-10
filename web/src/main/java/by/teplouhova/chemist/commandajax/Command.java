package by.teplouhova.chemist.commandajax;

import by.teplouhova.chemist.controller.SessionRequestContent;
import org.json.JSONObject;

/**
 * The Interface Command.
 */
public interface Command {

    /**
     * Execute.
     *
     * @param content the content
     * @return the JSON object
     */
    JSONObject execute(SessionRequestContent content);
}