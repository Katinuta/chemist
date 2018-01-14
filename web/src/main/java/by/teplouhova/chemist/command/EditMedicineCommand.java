package by.teplouhova.chemist.command;

import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.entity.impl.Medicine;
import by.teplouhova.chemist.service.MedicineService;
import by.teplouhova.chemist.service.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EditMedicineCommand implements Command {

    private static final Logger LOGGER= LogManager.getLogger();
    private static final String PARAM_MEDICINE_ID="medicine_id";
    private static final String ATTR_MEDICINE="medicine";

    private MedicineService medicineService;

    public EditMedicineCommand(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @Override
    public CommandResult execute(SessionRequestContent content) {
        String page ;
        CommandResult.ResponseType responseType;
       long id=Long.valueOf(content.getParameter(PARAM_MEDICINE_ID));
        try {
            Medicine medicine=medicineService.getMedicineEdit(id);
            content.setRequestAttributes(ATTR_MEDICINE,medicine);
            page="/jsp/pharmacist/medicineedit.jsp";
            responseType= CommandResult.ResponseType.FORWARD;
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR,"Error edit medicine "+e);
            page = "/jsp/error/error.jsp";
            responseType= CommandResult.ResponseType.REDIRECT;
        }

        return new CommandResult(responseType,page) ;
    }
}
