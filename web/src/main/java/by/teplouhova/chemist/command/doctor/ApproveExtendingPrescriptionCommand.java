package by.teplouhova.chemist.command.doctor;

import by.teplouhova.chemist.command.Command;
import by.teplouhova.chemist.command.CommandResult;
import by.teplouhova.chemist.command.PageConstant;
import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.creator.PrescriptionCreator;
import by.teplouhova.chemist.entity.impl.Prescription;
import by.teplouhova.chemist.service.PrescriptionService;
import by.teplouhova.chemist.service.ServiceException;
import by.teplouhova.chemist.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.ResourceBundle;

import static by.teplouhova.chemist.command.CommandResult.ResponseType.FORWARD;
import static by.teplouhova.chemist.command.CommandResult.ResponseType.REDIRECT;
import static by.teplouhova.chemist.command.PageConstant.PAGE_ERROR;
import static by.teplouhova.chemist.command.PageConstant.PAGE_SUCCESS_PRESCRIPTION_APPROVE;

public class ApproveExtendingPrescriptionCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String PARAM_DATE_END = "date_end";
    private static final String PARAM_PRESCRIPTION_ID = "prescription_id";
    private static final String ATTR_MESSAGE_BUNDLE = "messageBundle";
    private static final String ATTR_MESSAGE = "message";
    private static final String ATTR_ERROR = "error_";

    private PrescriptionService prescriptionService;

    public ApproveExtendingPrescriptionCommand(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @Override
    public CommandResult execute(SessionRequestContent content) {
        String page = PageConstant.PAGE_DOCTOR_MAIN;
        CommandResult.ResponseType responseType = FORWARD;
        ResourceBundle bundle = (ResourceBundle) content.getSessionAttribute(ATTR_MESSAGE_BUNDLE);
        HashMap<String, String> prescripParams = new HashMap<>();
        Validator validator = new Validator(bundle);
        prescripParams.put(PARAM_DATE_END, content.getParameter(PARAM_DATE_END));
        prescripParams.put(PARAM_PRESCRIPTION_ID, content.getParameter(PARAM_PRESCRIPTION_ID));
        try {
            boolean isValid = validator.isValid(prescripParams);
            LocalDate now = LocalDate.now();
            String dateEnd = prescripParams.get(PARAM_DATE_END);
            if (isValid && !LocalDate.parse(dateEnd).isBefore(LocalDate.now()) || now.isEqual(LocalDate.parse(dateEnd))) {
                Prescription prescription = new PrescriptionCreator().create(prescripParams);
                prescriptionService.update(prescription);
                responseType = REDIRECT;
                page = PAGE_SUCCESS_PRESCRIPTION_APPROVE;
            } else {
                String prescriptionId = prescripParams.get(PARAM_PRESCRIPTION_ID);
                if (isValid && LocalDate.parse(dateEnd).isBefore(now)) {
                    content.setRequestAttributes(ATTR_ERROR + PARAM_DATE_END + prescriptionId, bundle.getString("message.date.wrong"));
                }
                content.getParameterNames().forEach(name -> content.setRequestAttributes(name, content.getParameter(name)));
                validator.getEntrySetErrors()
                        .forEach(entry -> content.setRequestAttributes(ATTR_ERROR + entry.getKey() + prescriptionId, entry.getValue()));
            }

        } catch (ServiceException e) {
            page = PAGE_ERROR;
            content.setRequestAttributes(ATTR_MESSAGE, "message.presrip.approve.error");
            LOGGER.catching(e);
        }

        return new CommandResult(responseType, page);
    }
}
