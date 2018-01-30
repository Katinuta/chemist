package by.teplouhova.chemist.command;

import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.entity.impl.Prescription;
import by.teplouhova.chemist.entity.impl.RoleType;
import by.teplouhova.chemist.entity.impl.User;
import by.teplouhova.chemist.service.ClientService;
import by.teplouhova.chemist.service.PrescriptionService;
import by.teplouhova.chemist.service.ServiceException;
import by.teplouhova.chemist.validator.Validator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class OpenPrescripCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String PARAM_PRESCRIPTION_ID = "prescriptionId";
    private static final String ATTR_PRESCRIPTION = "prescription";
    private static final String ATTR_USER = "user";
    private static final String ATTR_MESSAGE = "message";
    private PrescriptionService service;
    private ClientService clientService;

    public OpenPrescripCommand(PrescriptionService service, ClientService clientService) {
        this.service = service;
        this.clientService = clientService;
    }

    @Override
    public CommandResult execute(SessionRequestContent content) {
        String page;
        CommandResult.ResponseType responseType = CommandResult.ResponseType.FORWARD;
        String prescriptionId = content.getParameter(PARAM_PRESCRIPTION_ID);
        User user = (User) content.getSessionAttribute(ATTR_USER);
        try {
            if (new Validator().validateRequired(prescriptionId, Validator.REGEXP_ID)) {
                Prescription prescription = service.getPrescription(Long.parseLong(prescriptionId));
                content.setRequestAttributes(ATTR_PRESCRIPTION, prescription);
                if (user.getRole() == RoleType.CLIENT) {
                    page = PageConstant.PAGE_CLIENT_PRESCRIPTION_DETAIL;
                } else {
                    page = PageConstant.PAGE_DOCTOR_PRESCRIPTION_DETAIL;
                }

            } else {
                page = PageConstant.PAGE_ERROR;
                content.setRequestAttributes(ATTR_MESSAGE,"Prescription is not found");
            }
        } catch (ServiceException e) {
            page = PageConstant.PAGE_ERROR;
            responseType = CommandResult.ResponseType.FORWARD;
            content.setRequestAttributes(ATTR_MESSAGE,"Prescription is not found catch");
            LOGGER.log(Level.ERROR, e);
        }

        return new CommandResult(responseType, page);
    }
}
