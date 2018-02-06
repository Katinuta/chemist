package by.teplouhova.chemist.commandajax;

import by.teplouhova.chemist.controller.SessionRequestContent;
import org.json.JSONObject;

public class EmptyCommand implements Command {
    @Override
    public JSONObject execute(SessionRequestContent content) {
        return new JSONObject();
    }
}
