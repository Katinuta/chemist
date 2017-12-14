package by.teplouhova.chemist.web.command;

public class ActionFactory {
    public static Command defineCommand(String commandName){

        CommandType type=CommandType.valueOf(commandName.toUpperCase());
        Command current=type.getCommand();
        return current;
    }
}
