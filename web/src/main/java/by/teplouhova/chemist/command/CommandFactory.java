package by.teplouhova.chemist.command;

public class CommandFactory {
    public static Command defineCommand(String commandName){

        CommandType type=CommandType.valueOf(commandName.toUpperCase());
        Command current=type.getCommand();
        return current;
    }
}
