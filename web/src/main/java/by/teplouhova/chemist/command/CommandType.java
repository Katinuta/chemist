package by.teplouhova.chemist.command;

import by.teplouhova.chemist.UserService;

public enum CommandType {
    LOGIN(new LoginCommand( new UserService())),
    LOGOUT(new LogoutCommand(new UserService())),
    LOCALE(new LocaleCommand(new UserService()));

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand(){
        return this.command;
    }
}
