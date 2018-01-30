package by.teplouhova.chemist.command;

import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.service.UserService;

public class RegisterCommand implements Command{


    private UserService userService;

    public RegisterCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(SessionRequestContent content) {

        return new CommandResult(CommandResult.ResponseType.FORWARD,PageConstant.PAGE_COMMON_SIGN_UP);
    }
}
