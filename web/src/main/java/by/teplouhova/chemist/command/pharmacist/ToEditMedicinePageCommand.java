package by.teplouhova.chemist.command.pharmacist;

import by.teplouhova.chemist.command.Command;
import by.teplouhova.chemist.command.CommandResult;
import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.entity.impl.Medicine;
import by.teplouhova.chemist.service.MedicineService;
import by.teplouhova.chemist.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.teplouhova.chemist.command.CommandResult.ResponseType.FORWARD;
import static by.teplouhova.chemist.command.PageConstant.PAGE_ERROR;
import static by.teplouhova.chemist.command.PageConstant.PAGE_PHARMACIST_MEDICINE_EDIT;


public class ToEditMedicinePageCommand implements Command {

    private static final Logger LOGGER= LogManager.getLogger();
    private static final String PARAM_MEDICINE_ID="medicine_id";
    private static final String ATTR_MEDICINE="medicine";
    private static final String ATTR_MESSAGE_ERROR="message";

    private MedicineService medicineService;

    public ToEditMedicinePageCommand(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @Override
    public CommandResult execute(SessionRequestContent content) {
        String page ;
        CommandResult.ResponseType responseType= FORWARD;;
        //todo validation
       long id=Long.valueOf(content.getParameter(PARAM_MEDICINE_ID));
        try {
            Medicine medicine=medicineService.getMedicineEdit(id);
            content.setRequestAttributes(ATTR_MEDICINE,medicine);
            page= PAGE_PHARMACIST_MEDICINE_EDIT;

        } catch (ServiceException e) {
            LOGGER.catching(e);
            page = PAGE_ERROR;
            content.setRequestAttributes(ATTR_MESSAGE_ERROR,e.getMessage());

        }

        return new CommandResult(responseType,page) ;
    }
}
