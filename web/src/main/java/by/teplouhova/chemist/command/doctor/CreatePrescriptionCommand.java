package by.teplouhova.chemist.command.doctor;

import by.teplouhova.chemist.command.Command;
import by.teplouhova.chemist.command.CommandResult;
import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.creator.PrescriptionCreator;
import by.teplouhova.chemist.creator.PrescriptionDetailCreator;
import by.teplouhova.chemist.entity.impl.Prescription;
import by.teplouhova.chemist.entity.impl.PrescriptionDetail;
import by.teplouhova.chemist.entity.impl.User;
import by.teplouhova.chemist.service.PrescriptionService;
import by.teplouhova.chemist.service.ServiceException;
import by.teplouhova.chemist.service.UserService;
import by.teplouhova.chemist.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.ResourceBundle;

import static by.teplouhova.chemist.command.CommandResult.ResponseType.FORWARD;
import static by.teplouhova.chemist.command.CommandResult.ResponseType.REDIRECT;
import static by.teplouhova.chemist.command.PageConstant.*;

/**
 * The Class CreatePrescriptionCommand.
 */
public class CreatePrescriptionCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * The Constant ATTR_USER.
     */
    private static final String ATTR_USER = "user";

    /**
     * The Constant PARAM_CLIENT_ID.
     */
    private static final String PARAM_CLIENT_ID = "client_id";

    /**
     * The Constant PARAM_DATE_BEGIN.
     */
    private static final String PARAM_DATE_BEGIN = "date_begin";

    /**
     * The Constant PARAM_DATE_END.
     */
    private static final String PARAM_DATE_END = "date_end";

    /**
     * The Constant PARAM_MEDICINE_ID.
     */
    private static final String PARAM_MEDICINE_ID = "medicine_id";

    /**
     * The Constant PARAM_QUANTITY.
     */
    private static final String PARAM_QUANTITY = "quantity";

    /**
     * The Constant ATTR_CLIENT.
     */
    private static final String ATTR_CLIENT = "client";

    /**
     * The Constant ATTR_MESSAGE_BUNDLE.
     */
    private static final String ATTR_MESSAGE_BUNDLE = "messageBundle";

    /**
     * The Constant ATTR_MESSAGE_ERROR.
     */
    private static final String ATTR_MESSAGE_ERROR = "message";

    /**
     * The Constant ATTR_ERROR.
     */
    private static final String ATTR_ERROR = "error_";

    /**
     * The prescription service.
     */
    private PrescriptionService prescriptionService;

    /**
     * The user service.
     */
    private UserService userService;


    /**
     * Instantiates a new creates the prescription command.
     *
     * @param prescriptionService the prescription service
     * @param userService         the user service
     */
    public CreatePrescriptionCommand(PrescriptionService prescriptionService, UserService userService) {
        this.prescriptionService = prescriptionService;
        this.userService = userService;
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
        CommandResult.ResponseType responseType;
        String contextPath=content.getContextPath();
        User doctor = (User) content.getSessionAttribute(ATTR_USER);
        ResourceBundle bundle = (ResourceBundle) content.getSessionAttribute(ATTR_MESSAGE_BUNDLE);
        HashMap<String, String> prescripParams = new HashMap<>();
        prescripParams.put(PARAM_CLIENT_ID, content.getParameter(PARAM_CLIENT_ID));
        prescripParams.put(PARAM_DATE_BEGIN, content.getParameter(PARAM_DATE_BEGIN));
        prescripParams.put(PARAM_DATE_END, content.getParameter(PARAM_DATE_END));
        prescripParams.put(PARAM_MEDICINE_ID, content.getParameter(PARAM_MEDICINE_ID));
        prescripParams.put(PARAM_QUANTITY, content.getParameter(PARAM_QUANTITY));
        Validator validator = new Validator(bundle);
        try {
            boolean isValid = validator.isValid(prescripParams);
            String dateBegin = prescripParams.get(PARAM_DATE_BEGIN);
            String dateEnd = prescripParams.get(PARAM_DATE_END);
            if (isValid && !LocalDate.parse(dateEnd).isBefore(LocalDate.parse(dateBegin))) {
                Prescription prescription = new PrescriptionCreator().create(prescripParams);
                PrescriptionDetail detail = new PrescriptionDetailCreator().create(prescripParams);
                prescription.setDoctor(doctor);
                prescription.setDetails(detail);
                prescriptionService.createPrescription(prescription);
                page =contextPath+ PAGE_SUCCESS_PRESCRIPTION_CREATE;
                responseType = REDIRECT;
            } else {
                content.getParameterNames().forEach(name -> content.setRequestAttributes(name, content.getParameter(name)));
                validator.getEntrySetErrors()
                        .forEach(entry -> content.setRequestAttributes(ATTR_ERROR + entry.getKey(), entry.getValue()));
                if (isValid && LocalDate.parse(dateEnd).isBefore(LocalDate.parse(dateBegin))) {
                    content.setRequestAttributes(ATTR_ERROR + PARAM_DATE_END, bundle.getString("message.date.wrong"));

                }
                if (!validator.isExistError(ATTR_ERROR + PARAM_CLIENT_ID)) {
                    User client = userService.getById(Long.parseLong(content.getParameter(PARAM_CLIENT_ID)));
                    content.setRequestAttributes(ATTR_CLIENT, client);
                } else {

                }
                page = PAGE_DOCTOR_PRESCRIPTION_NEW;
                responseType = FORWARD;
            }

        } catch (ServiceException e) {
            page = PAGE_ERROR;
            responseType = FORWARD;
            content.setRequestAttributes(ATTR_MESSAGE_ERROR, e.getMessage());
            LOGGER.catching(e);
        }

        return new CommandResult(responseType, page);
    }
}
