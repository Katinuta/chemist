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

public class UpdateMedicineCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String PARAM_MEDICINE_ID = "medicine_id";
    private static final String PARAM_DOSAGE_SIZE = "dosage_size";
    private static final String PARAM_UNIT_DOSAGE = "dosage_unit";
    private static final String PARAM_NEED_PRESCRIPTION = "need_prescription";
    private static final String ATTR_ERROR = "error_";
    private static final String ATTR_MESSAGE="message";
    private static final String ATTR_MEDICINE = "medicine";
    private static final String ATTR_DOSAGE = "dosage";
    private static final String ATTR_MESSAGE_BUNDLE = "messageBundle";

    private MedicineService medicineService;

    public UpdateMedicineCommand(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @Override
    public CommandResult execute(SessionRequestContent content) {

        String page ;
        CommandResult.ResponseType responseType = FORWARD;
        ResourceBundle bundle = (ResourceBundle) content.getSessionAttribute(ATTR_MESSAGE_BUNDLE);
        boolean isNeedPrescription = content.isContainParameter(PARAM_NEED_PRESCRIPTION) ? true : false;
        HashMap<String, String> medicineParams = new HashMap<>();
        content.getParameterNames()
                .forEach(key->medicineParams.put(key,content.getParameter(key)));
        medicineParams.put(PARAM_NEED_PRESCRIPTION, String.valueOf(isNeedPrescription));
        Validator validator=new Validator(bundle);
        boolean isValid=validator.isValid(medicineParams);
        medicineParams.entrySet().stream()
                .filter(entry -> entry != null && entry.getValue().isEmpty())
                .forEach(entry -> entry.setValue(null));

        String dosageSize = medicineParams.get(PARAM_DOSAGE_SIZE);
        String unitDosage = medicineParams.get(PARAM_UNIT_DOSAGE);

        try {
            if (isValid && !(dosageSize == null && unitDosage != null || dosageSize != null && unitDosage == null)) {
                Medicine medicine = new MedicineCreator().createMedicine(medicineParams);
                medicineService.update(medicine);
                page = PAGE_SUCCESS_MEDICINE_EDIT;
                responseType = CommandResult.ResponseType.REDIRECT;
            } else {
                if(!validator.isExistError(ATTR_ERROR+PARAM_MEDICINE_ID)){
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
                    content.setRequestAttributes(ATTR_MESSAGE,bundle.getString("message.medicine.parameter.wrong"));
                }

            }
        } catch (ServiceException e) {
            page = PAGE_ERROR;
            responseType = REDIRECT;
            LOGGER.catching(e);
        }

        return new CommandResult(responseType, page);
    }
}
