package by.teplouhova.chemist.manager;

import java.util.Locale;
import java.util.ResourceBundle;

public enum MessageManager {

    EN(ResourceBundle.getBundle("MessagesBundle",new Locale("en","EN"))),
    RU(ResourceBundle.getBundle("MessagesBundle",new Locale("ru","RU")));
    private ResourceBundle bundle;

    MessageManager(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    public ResourceBundle getBundle() {
        return bundle;
    }
}
