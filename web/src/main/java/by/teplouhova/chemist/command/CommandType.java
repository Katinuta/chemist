package by.teplouhova.chemist.command;

import by.teplouhova.chemist.service.UserService;

public enum CommandType {
    LOGIN(new LoginCommand( new UserService())),
    LOGOUT(new LogoutCommand(new UserService())),
    LOCALE(new LocaleCommand(new UserService())),
    REGISTER(new RegisterCommand(new UserService())),
    CREATEUSER(new CreateUserCommand(new UserService()));

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand(){
        return this.command;
    }
}
