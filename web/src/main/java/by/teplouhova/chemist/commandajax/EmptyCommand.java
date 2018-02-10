package by.teplouhova.chemist.commandajax;

import by.teplouhova.chemist.controller.SessionRequestContent;
import org.json.JSONObject;

/**
 * The Class EmptyCommand.
 */
public class EmptyCommand implements Command {

    /**
     * Execute.
     *
     * @param content the content
     * @return the JSON object
     */
    @Override
    public JSONObject execute(SessionRequestContent content) {
        return new JSONObject();
    }
}
