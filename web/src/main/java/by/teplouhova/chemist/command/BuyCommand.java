package by.teplouhova.chemist.command;

import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.entity.impl.Medicine;
import by.teplouhova.chemist.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

public class BuyCommand implements Command {

    private static final Logger LOGGER= LogManager.getLogger();
    private static final String ATTR_CART="cart";

    private UserService userService;

    public BuyCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(SessionRequestContent content) {
        HashMap<Medicine,Integer> cart= (HashMap<Medicine, Integer>) content.getSessionAttribute(ATTR_CART);

        return null;
    }
}
