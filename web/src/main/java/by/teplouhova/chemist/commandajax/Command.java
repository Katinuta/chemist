package by.teplouhova.chemist.commandajax;

import by.teplouhova.chemist.controller.SessionRequestContent;
import org.json.JSONArray;
import org.json.JSONObject;

public interface Command {
    JSONObject execute(SessionRequestContent content);
}