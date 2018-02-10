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
import static by.teplouhova.chemist.command.CommandResult.ResponseType.REDIRECT;
import static by.teplouhova.chemist.command.PageConstant.*;

/**
 * The Class UpdateMedicineCommand.
 */
public class UpdateMedicineCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * The Constant PARAM_MEDICINE_ID.
     */
    private static final String PARAM_MEDICINE_ID = "medicine_id";

    /**
     * The Constant PARAM_DOSAGE_SIZE.
     */
    private static final String PARAM_DOSAGE_SIZE = "dosage_size";

    /**
     * The Constant PARAM_UNIT_DOSAGE.
     */
    private static final String PARAM_UNIT_DOSAGE = "dosage_unit";

    /**
     * The Constant PARAM_NEED_PRESCRIPTION.
     */
    private static final String PARAM_NEED_PRESCRIPTION = "need_prescription";

    /**
     * The Constant PARAM_MEDICINE_NAME.
     */
    private static final String PARAM_MEDICINE_NAME = "medicine_name";

    /**
     * The Constant PARAM_PRICE.
     */
    private static final String PARAM_PRICE = "price";

    /**
     * The Constant PARAM_ANALOG_MEDICINE_ID.
     */
    private static final String PARAM_ANALOG_MEDICINE_ID = "analog_medicine_id";

    /**
     * The Constant PARAM_PRODUCER_ID.
     */
    private static final String PARAM_PRODUCER_ID = "producer_id";

    /**
     * The Constant PARAM_RELEASE_FORM_ID.
     */
    private static final String PARAM_RELEASE_FORM_ID = "release_form_id";

    /**
     * The Constant PARAM_UNIT_IN_PACKAGE.
     */
    private static final String PARAM_UNIT_IN_PACKAGE = "unit_in_package";

    /**
     * The Constant PARAM_QUANTITY_IN_PACKAGE.
     */
    private static final String PARAM_QUANTITY_IN_PACKAGE = "quantity_in_package";

    /**
     * The Constant PARAM_QUANTITY_PACKAGES.
     */
    private static final String PARAM_QUANTITY_PACKAGES = "quantity_packages";

    /**
     * The Constant ATTR_ERROR.
     */
    private static final String ATTR_ERROR = "error_";

    /**
     * The Constant ATTR_MESSAGE.
     */
    private static final String ATTR_MESSAGE = "message";

    /**
     * The Constant ATTR_MEDICINE.
     */
    private static final String ATTR_MEDICINE = "medicine";

    /**
     * The Constant ATTR_DOSAGE.
     */
    private static final String ATTR_DOSAGE = "dosage";

    /**
     * The Constant ATTR_MESSAGE_BUNDLE.
     */
    private static final String ATTR_MESSAGE_BUNDLE = "messageBundle";

    /**
     * The medicine service.
     */
    private MedicineService medicineService;

    /**
     * Instantiates a new update medicine command.
     *
     * @param medicineService the medicine service
     */
    public UpdateMedicineCommand(MedicineService medicineService) {
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
        boolean isNeedPrescription = content.isContainParameter(PARAM_NEED_PRESCRIPTION);
        HashMap<String, String> medicineParams = new HashMap<>();
        medicineParams.put(PARAM_ANALOG_MEDICINE_ID, content.getParameter(PARAM_ANALOG_MEDICINE_ID));
        medicineParams.put(PARAM_PRODUCER_ID, content.getParameter(PARAM_PRODUCER_ID));
        medicineParams.put(PARAM_RELEASE_FORM_ID, content.getParameter(PARAM_RELEASE_FORM_ID));
        medicineParams.put(PARAM_UNIT_IN_PACKAGE, content.getParameter(PARAM_UNIT_IN_PACKAGE));
        medicineParams.put(PARAM_QUANTITY_PACKAGES, content.getParameter(PARAM_QUANTITY_PACKAGES));
        medicineParams.put(PARAM_QUANTITY_IN_PACKAGE, content.getParameter(PARAM_QUANTITY_IN_PACKAGE));
        medicineParams.put(PARAM_PRICE, content.getParameter(PARAM_PRICE));
        medicineParams.put(PARAM_MEDICINE_NAME, content.getParameter(PARAM_MEDICINE_NAME));
        medicineParams.put(PARAM_DOSAGE_SIZE, content.getParameter(PARAM_DOSAGE_SIZE));
        medicineParams.put(PARAM_UNIT_DOSAGE, content.getParameter(PARAM_UNIT_DOSAGE));
        medicineParams.put(PARAM_NEED_PRESCRIPTION, String.valueOf(isNeedPrescription));
        medicineParams.entrySet().stream()
                .filter(entry -> entry != null && entry.getValue().isEmpty())
                .forEach(entry -> entry.setValue(null));

        Validator validator = new Validator(bundle);
        boolean isValid = validator.isValid(medicineParams);
        String dosageSize = medicineParams.get(PARAM_DOSAGE_SIZE);
        String unitDosage = medicineParams.get(PARAM_UNIT_DOSAGE);

        try {
            if (isValid && !(dosageSize == null && unitDosage != null || dosageSize != null && unitDosage == null)) {
                Medicine medicine = new MedicineCreator().createMedicine(medicineParams);
                medicineService.update(medicine);
                page = PAGE_SUCCESS_MEDICINE_EDIT;
                responseType = CommandResult.ResponseType.REDIRECT;
            } else {
                if (!validator.isExistError(ATTR_ERROR + PARAM_MEDICINE_ID)) {
                    if (isValid && (dosageSize == null && unitDosage != null) || (dosageSize != null && unitDosage == null)) {
                        content.setRequestAttributes(ATTR_ERROR + ATTR_DOSAGE, bundle.getString("message.dosage.wrong"));
                    }
                    validator.getEntrySetErrors()
                            .forEach(entry -> content.setRequestAttributes(ATTR_ERROR + entry.getKey(), entry.getValue()));
                    String medicineId = medicineParams.get(PARAM_MEDICINE_ID);

                    Medicine medicine = medicineService.getMedicineEdit(Long.parseLong(medicineId));
                    content.setRequestAttributes(ATTR_MEDICINE, medicine);
                    page = PAGE_PHARMACIST_MEDICINE_EDIT;
                } else {
                    page = PAGE_ERROR;
                    content.setRequestAttributes(ATTR_MESSAGE, bundle.getString("message.medicine.parameter.wrong"));
                }

            }
        } catch (ServiceException e) {
            page = PAGE_ERROR;
            content.setRequestAttributes(ATTR_MESSAGE, e.getMessage());
            LOGGER.catching(e);
        }

        return new CommandResult(responseType, page);
    }
}
