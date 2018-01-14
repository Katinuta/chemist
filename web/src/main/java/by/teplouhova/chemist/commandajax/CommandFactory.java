package by.teplouhova.chemist.commandajax;

import by.teplouhova.chemist.commandajax.Command;
import by.teplouhova.chemist.commandajax.CommandType;

public class CommandFactory {

        public static Command defineCommand(String commandName){
            CommandType type=CommandType.valueOf(commandName.toUpperCase());
            Command current=type.getCommand();
            return current;
        }

}
