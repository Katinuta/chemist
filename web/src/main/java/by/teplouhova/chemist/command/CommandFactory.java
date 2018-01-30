package by.teplouhova.chemist.command;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class CommandFactory {
    private static final Logger LOGGER = LogManager.getLogger();

    public static Command defineCommand(String commandName) {
        Command current;
        commandName = commandName != null ? commandName : CommandType.EMPTY.name();
        try{
            CommandType type = CommandType.valueOf(commandName.toUpperCase());
          current = type.getCommand();
        }catch (IllegalArgumentException e){
            current=CommandType.EMPTY.getCommand();
            LOGGER.log(Level.ERROR,"Command name is wrong ",e);
        }

        return current;
    }
}
