package by.teplouhova.chemist.command.pharmacist;

import by.teplouhova.chemist.command.Command;
import by.teplouhova.chemist.command.CommandResult;
import by.teplouhova.chemist.controller.SessionRequestContent;

import static by.teplouhova.chemist.command.CommandResult.ResponseType.FORWARD;
import static by.teplouhova.chemist.command.PageConstant.PAGE_PHARMACIST_MEDICINE_NEW;

public class ToNewMedicinePageCommand implements Command {

    @Override
    public CommandResult execute(SessionRequestContent content) {
        return new CommandResult(FORWARD,PAGE_PHARMACIST_MEDICINE_NEW);
    }
}
