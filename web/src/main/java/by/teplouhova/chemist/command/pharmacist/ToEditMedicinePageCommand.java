package by.teplouhova.chemist.command.pharmacist;

import by.teplouhova.chemist.command.Command;
import by.teplouhova.chemist.command.CommandResult;
import by.teplouhova.chemist.controller.SessionRequestContent;
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
import static by.teplouhova.chemist.command.PageConstant.PAGE_PHARMACIST_MEDICINE_EDIT;

/**
 * The Class ToEditMedicinePageCommand.
 */
public class ToEditMedicinePageCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * The Constant PARAM_MEDICINE_ID.
     */
    private static final String PARAM_MEDICINE_ID = "medicine_id";

    /**
     * The Constant ATTR_MEDICINE.
     */
    private static final String ATTR_MEDICINE = "medicine";

    /**
     * The Constant ATTR_MESSAGE_ERROR.
     */
    private static final String ATTR_MESSAGE_ERROR = "message";

    /**
     * The Constant ATTR_MESSAGE_BUNDLE.
     */
    private static final String ATTR_MESSAGE_BUNDLE = "messageBundle";

    /**
     * The medicine service.
     */
    private MedicineService medicineService;

    /**
     * Instantiates a new to edit medicine page command.
     *
     * @param medicineService the medicine service
     */
    public ToEditMedicinePageCommand(MedicineService medicineService) {
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
            String medicineId = content.getParameter(PARAM_MEDICINE_ID);
            medicineParams.put(PARAM_MEDICINE_ID, medicineId);
            if (validator.isValid(medicineParams)) {
                long id = Long.valueOf(medicineParams.get(PARAM_MEDICINE_ID));
                Medicine medicine = medicineService.getMedicineEdit(id);
                content.setRequestAttributes(ATTR_MEDICINE, medicine);
                page = PAGE_PHARMACIST_MEDICINE_EDIT;
            } else {
                content.setRequestAttributes(ATTR_MESSAGE_ERROR, bundle.getString("message.medicine.parameter.wrong"));
                page = PAGE_ERROR;
            }

        } catch (ServiceException e) {
            page = PAGE_ERROR;
            content.setRequestAttributes(ATTR_MESSAGE_ERROR, e.getMessage());
            LOGGER.catching(e);

        }

        return new CommandResult(responseType, page);
    }
}
