package by.teplouhova.chemist.command;


import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.entity.impl.Medicine;
import by.teplouhova.chemist.service.MedicineService;
import by.teplouhova.chemist.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteMedicineCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String PARAM_ID ="id";
    private static final String ATTR_MESSAGE ="message";

    private MedicineService medicineService;

    public DeleteMedicineCommand(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @Override
    public CommandResult execute(SessionRequestContent content) {
        String page=null;
        CommandResult.ResponseType responseType=null;
        Long id=Long.parseLong(content.getParameter(PARAM_ID));
        try {
            medicineService.delete(new Medicine(id));
            responseType=CommandResult.ResponseType.REDIRECT;
            page="/jsp/pharmacist/main.jsp";

        } catch (ServiceException e) {
            responseType= CommandResult.ResponseType.FORWARD;
            content.setRequestAttributes(ATTR_MESSAGE,"Medicine is not deleted"+ id );
            page="/jsp/pharmacist/main.jsp";
        }

        return new CommandResult(responseType,page);
    }
}
