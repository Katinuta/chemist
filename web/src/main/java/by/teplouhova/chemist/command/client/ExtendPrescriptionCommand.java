
package by.teplouhova.chemist.command.client;

import by.teplouhova.chemist.command.Command;
import by.teplouhova.chemist.command.CommandResult;
import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.creator.PrescriptionCreator;
import by.teplouhova.chemist.entity.impl.Prescription;
import by.teplouhova.chemist.entity.impl.PrescriptionStatus;
import by.teplouhova.chemist.service.PrescriptionService;
import by.teplouhova.chemist.service.ServiceException;
import by.teplouhova.chemist.validator.RegexpConstant;
import by.teplouhova.chemist.validator.Validator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.ResourceBundle;

import static by.teplouhova.chemist.command.CommandResult.ResponseType.FORWARD;
import static by.teplouhova.chemist.command.CommandResult.ResponseType.REDIRECT;
import static by.teplouhova.chemist.command.PageConstant.PAGE_ERROR;


/**
 * The Class ExtendPrescriptionCommand.
 */
public class ExtendPrescriptionCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();
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
     * The Constant HEADER_REFERER.
     */
    private static final String HEADER_REFERER = "referer";

    /**
     * The prescription service.
     */
    private PrescriptionService prescriptionService;


    /**
     * Instantiates a new extend prescription command.
     *
     * @param prescriptionService the prescription service
     */
    public ExtendPrescriptionCommand(PrescriptionService prescriptionService) {
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
        String page;
        CommandResult.ResponseType responseType = FORWARD;
        String prescriptionId = content.getParameter(PARAM_PRESCRIPTION_ID);
        ResourceBundle bundle = (ResourceBundle) content.getSessionAttribute(ATTR_MESSAGE_BUNDLE);
        Validator validator = new Validator(bundle);
        HashMap<String, String> params = new HashMap<>();
        params.put(PARAM_PRESCRIPTION_ID, prescriptionId);
        try {
            if (validator.isValid(params)) {
                Prescription prescription = new PrescriptionCreator().create(params);
                prescription.setStatus(PrescriptionStatus.EXTEND);
                prescriptionService.update(prescription);
                page = content.getRequestHeader(HEADER_REFERER);
                responseType = REDIRECT;
            } else {
                content.setRequestAttributes(ATTR_MESSAGE, bundle.getString("message.parameter.wrong"));
                page = PAGE_ERROR;
            }


        } catch (ServiceException e) {
            page = PAGE_ERROR;
            content.setRequestAttributes(ATTR_MESSAGE, bundle.getString("message.presrip.extend.error"));
            LOGGER.catching(e);
        }


        return new CommandResult(responseType, page);
    }
}
