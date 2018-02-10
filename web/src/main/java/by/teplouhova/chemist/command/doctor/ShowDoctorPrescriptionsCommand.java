package by.teplouhova.chemist.command.doctor;

import by.teplouhova.chemist.command.Command;
import by.teplouhova.chemist.command.CommandResult;
import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.entity.impl.Prescription;
import by.teplouhova.chemist.entity.impl.User;
import by.teplouhova.chemist.service.PrescriptionService;
import by.teplouhova.chemist.service.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.ResourceBundle;

import static by.teplouhova.chemist.command.CommandResult.ResponseType.FORWARD;
import static by.teplouhova.chemist.command.CommandResult.ResponseType.REDIRECT;
import static by.teplouhova.chemist.command.PageConstant.PAGE_DOCTOR_PRESCRIPTION;
import static by.teplouhova.chemist.command.PageConstant.PAGE_ERROR;


/**
 * The Class ShowDoctorPrescriptionsCommand.
 */
public class ShowDoctorPrescriptionsCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * The Constant ATTR_PRESRIPTIONS.
     */
    private static final String ATTR_PRESRIPTIONS = "prescriptions";

    /**
     * The Constant ATTR_USER.
     */
    private static final String ATTR_USER = "user";

    /**
     * The Constant ATTR_MESSAGE.
     */
    private static final String ATTR_MESSAGE = "message";

    /**
     * The Constant ATTR_MESSAGE_BUNDLE.
     */
    private static final String ATTR_MESSAGE_BUNDLE = "messageBundle";


    /**
     * The prescription service.
     */
    private PrescriptionService prescriptionService;

    /**
     * Instantiates a new show doctor prescriptions command.
     *
     * @param prescriptionService the prescription service
     */
    public ShowDoctorPrescriptionsCommand(PrescriptionService prescriptionService) {
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
        String page = PAGE_DOCTOR_PRESCRIPTION;
        CommandResult.ResponseType responseType = FORWARD;
        User user = (User) content.getSessionAttribute(ATTR_USER);
        ResourceBundle bundle = (ResourceBundle) content.getSessionAttribute(ATTR_MESSAGE_BUNDLE);
        try {
            List<Prescription> prescriptions = prescriptionService.getDoctorPrescriptions(user.getUserId(), false);
            if (!prescriptions.isEmpty()) {
                content.setRequestAttributes(ATTR_PRESRIPTIONS, prescriptions);
            } else {
                content.setRequestAttributes(ATTR_MESSAGE, bundle.getString("message.prescrip.emty"));
            }

        } catch (ServiceException e) {
            page = PAGE_ERROR;
            content.setRequestAttributes(ATTR_MESSAGE, e.getMessage());
            LOGGER.catching(e);
        }
        return new CommandResult(responseType, page);
    }
}
