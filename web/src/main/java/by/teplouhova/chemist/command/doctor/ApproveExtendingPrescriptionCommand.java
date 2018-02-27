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

/**
 * The Class ApproveExtendingPrescriptionCommand.
 */
public class ApproveExtendingPrescriptionCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * The Constant PARAM_DATE_END.
     */
    private static final String PARAM_DATE_END = "date_end";

    /**
     * The Constant PARAM_PRESCRIPTION_ID.
     */
    private static final String PARAM_PRESCRIPTION_ID = "prescription_id";

    /**
     * The Constant ATTR_MESSAGE_BUNDLE.
     */
    private static final String ATTR_MESSAGE_BUNDLE = "messageBundle";

    /**
     * The Constant ATTR_MESSAGE.
     */
    private static final String ATTR_MESSAGE = "message";

    /**
     * The Constant ATTR_ERROR.
     */
    private static final String ATTR_ERROR = "error_";

    /**
     * The prescription service.
     */
    private PrescriptionService prescriptionService;

    /**
     * Instantiates a new approve extending prescription command.
     *
     * @param prescriptionService the prescription service
     */
    public ApproveExtendingPrescriptionCommand(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    /**
     * Execute.
     *
     * @param content the content
     * @return the command result
     */
    @Override
    public CommandResult execute(SessionRequestContent content) {
        String page = PageConstant.PAGE_DOCTOR_MAIN;
        CommandResult.ResponseType responseType = FORWARD;
        String contextPath = content.getContextPath();
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
                page = contextPath + PAGE_SUCCESS_PRESCRIPTION_APPROVE;
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
