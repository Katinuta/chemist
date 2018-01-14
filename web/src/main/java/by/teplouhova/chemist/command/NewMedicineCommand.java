package by.teplouhova.chemist.command;

import by.teplouhova.chemist.controller.SessionRequestContent;

public class NewMedicineCommand implements Command {



    @Override
    public CommandResult execute(SessionRequestContent content) {

        return new CommandResult(CommandResult.ResponseType.FORWARD,"/jsp/pharmacist/medicinenew.jsp");
    }
}
