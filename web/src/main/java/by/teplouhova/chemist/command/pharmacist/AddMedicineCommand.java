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

public class AddMedicineCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String PARAM_DOSAGE_SIZE = "dosage_size";
    private static final String PARAM_UNIT_DOSAGE = "dosage_unit";
    private static final String PARAM_NEED_PRESCRIPTION = "need_prescription";
    private static final String ATTR_MESSAGE_BUNDLE="messageBundle";
    private static final String ATTR_ERROR = "error_";
    private static final String ATTR_DOSAGE = "dosage";
    private static final String ATTR_MESSAGE_ERROR="message";

    private MedicineService medicineService;

    public AddMedicineCommand(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @Override

    public CommandResult execute(SessionRequestContent content) {
        String page ;
        CommandResult.ResponseType responseType = FORWARD;
        ResourceBundle bundle= (ResourceBundle) content.getSessionAttribute(ATTR_MESSAGE_BUNDLE);
        boolean isNeedPrescription = content.isContainParameter(PARAM_NEED_PRESCRIPTION);
        HashMap<String, String> medicineParams = new HashMap<>();
        //or pull from content by one???
        content.getParameterNames()
                .forEach(key->medicineParams.put(key,content.getParameter(key)));
        medicineParams.put(PARAM_NEED_PRESCRIPTION, String.valueOf(isNeedPrescription));
        medicineParams.entrySet().stream()
                .filter(entry -> entry!=null&&entry.getValue().isEmpty())
                .forEach(entry -> entry.setValue(null));
        Validator validator=new Validator(bundle);
        boolean isValid = validator.isValid(medicineParams);
        String dosageSize = medicineParams.get(PARAM_DOSAGE_SIZE);
        String dosageUnit = medicineParams.get(PARAM_UNIT_DOSAGE);

        try {
            if (isValid && !(dosageSize == null && dosageUnit != null || dosageSize != null && dosageUnit == null)) {
                Medicine medicine = new MedicineCreator().createMedicine(medicineParams);
                medicineService.create(medicine);
                page = PAGE_SUCCESS_MEDICINE_NEW;
                responseType = REDIRECT;
            } else {
                if (isValid && (dosageSize == null && dosageUnit != null) || (dosageSize != null && dosageUnit == null)) {
                    content.setRequestAttributes(ATTR_ERROR+ATTR_DOSAGE, bundle.getString("message.dosage.wrong"));
                }
                content.getParameterNames()
                        .forEach(nameParam->content.setRequestAttributes(nameParam,content.getParameter(nameParam)));
                validator.getEntrySetErrors()
                        .forEach(entry->content.setRequestAttributes(ATTR_ERROR+entry.getKey(),entry.getValue()));
                page = PAGE_PHARMACIST_MEDICINE_NEW;
            }
        } catch (ServiceException e) {
            page = PAGE_ERROR;
            content.setRequestAttributes(ATTR_MESSAGE_ERROR,e.getMessage());
            LOGGER.catching(e);
            //Forward?
        }

        return new CommandResult(responseType, page);
    }
}
