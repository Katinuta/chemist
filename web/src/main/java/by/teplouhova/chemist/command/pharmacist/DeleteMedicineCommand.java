package by.teplouhova.chemist.command.pharmacist;


import by.teplouhova.chemist.command.Command;
import by.teplouhova.chemist.command.CommandResult;
import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.creator.MedicineCreator;
import by.teplouhova.chemist.entity.impl.Medicine;
import by.teplouhova.chemist.service.MedicineService;
import by.teplouhova.chemist.service.ServiceException;
import by.teplouhova.chemist.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.ResourceBundle;

import static by.teplouhova.chemist.command.CommandResult.ResponseType.FORWARD;
import static by.teplouhova.chemist.command.PageConstant.PAGE_ERROR;

/**
 * The Class DeleteMedicineCommand.
 */
public class DeleteMedicineCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * The Constant PARAM_ID.
     */
    private static final String PARAM_ID = "medicine_id";

    /**
     * The Constant ATTR_MESSAGE.
     */
    private static final String ATTR_MESSAGE = "message";

    /**
     * The Constant HEADER_REFERER.
     */
    private static final String HEADER_REFERER = "referer";

    /**
     * The Constant ATTR_MESSAGE_BUNDLE.
     */
    private static final String ATTR_MESSAGE_BUNDLE = "messageBundle";

    /**
     * The medicine service.
     */
    private MedicineService medicineService;

    /**
     * Instantiates a new delete medicine command.
     *
     * @param medicineService the medicine service
     */
    public DeleteMedicineCommand(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    /**
     * Execute.
     *
     * @param content the content
     * @return the command result
     */
    @Override
    public CommandResult execute(SessionRequestContent content) {
        String page;
        CommandResult.ResponseType responseType = FORWARD;
        ResourceBundle bundle = (ResourceBundle) content.getSessionAttribute(ATTR_MESSAGE_BUNDLE);
        HashMap<String, String> medicineParams = new HashMap<>();
        Validator validator = new Validator(bundle);
        try {
            String medicineId = content.getParameter(PARAM_ID);
            medicineParams.put(PARAM_ID, medicineId);
            if (validator.isValid(medicineParams)) {
                Medicine medicine = new MedicineCreator().createMedicine(medicineParams);
                medicineService.delete(medicine);
                responseType = CommandResult.ResponseType.REDIRECT;
                page = content.getRequestHeader(HEADER_REFERER);
            } else {
                content.setRequestAttributes(ATTR_MESSAGE, bundle.getString("message.medicine.parameter.wrong"));
                page = PAGE_ERROR;
            }
        } catch (ServiceException e) {
            content.setRequestAttributes(ATTR_MESSAGE, e.getMessage());
            page = PAGE_ERROR;
            LOGGER.catching(e);
        }

        return new CommandResult(responseType, page);
    }
}
