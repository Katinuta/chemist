package by.teplouhova.chemist.command.user;

import by.teplouhova.chemist.command.Command;
import by.teplouhova.chemist.command.CommandResult;
import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.entity.impl.Prescription;
import by.teplouhova.chemist.entity.impl.RoleType;
import by.teplouhova.chemist.entity.impl.User;
import by.teplouhova.chemist.service.ClientService;
import by.teplouhova.chemist.service.PrescriptionService;
import by.teplouhova.chemist.service.ServiceException;
import by.teplouhova.chemist.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.ResourceBundle;

import static by.teplouhova.chemist.command.CommandResult.ResponseType.FORWARD;
import static by.teplouhova.chemist.command.PageConstant.*;

public class ShowPrescripInfoCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String PARAM_PRESCRIPTION_ID = "prescription_id";
    private static final String ATTR_PRESCRIPTION = "prescription";
    private static final String ATTR_USER = "user";
    private static final String ATTR_MESSAGE = "message";
    private static final String ATTR_MESSAGE_BUNDLE="messageBundle";

    private PrescriptionService service;


    public ShowPrescripInfoCommand(PrescriptionService service, ClientService clientService) {
        this.service = service;

    }

    @Override
    public CommandResult execute(SessionRequestContent content) {
        String page;
        CommandResult.ResponseType responseType = FORWARD;
        String prescriptionId = content.getParameter(PARAM_PRESCRIPTION_ID);
        User user = (User) content.getSessionAttribute(ATTR_USER);
        HashMap<String,String> params=new HashMap<>();
        content.getParameterNames().forEach(key->params.put(key,content.getParameter(key)));
        ResourceBundle bundle= (ResourceBundle) content.getSessionAttribute(ATTR_MESSAGE_BUNDLE);
        try {
            Validator validator=new Validator(bundle);
            if (validator.isValid(params)) {
                Prescription prescription = service.getPrescription(Long.parseLong(prescriptionId));
                content.setRequestAttributes(ATTR_PRESCRIPTION, prescription);
                if (user.getRole() == RoleType.CLIENT) {
                    page =PAGE_CLIENT_PRESCRIPTION_DETAIL;
                } else {
                    page = PAGE_DOCTOR_PRESCRIPTION_DETAIL;
                }

            } else {
                page = PAGE_ERROR;
                content.setRequestAttributes(ATTR_MESSAGE,bundle.getString("message.prescrip.parameter.wrong") +prescriptionId);
            }
        } catch (ServiceException e) {
            page = PAGE_ERROR;
            responseType =FORWARD;
            //todo message
            content.setRequestAttributes(ATTR_MESSAGE,bundle.getString("message.prescrip.not.found")+prescriptionId);
            LOGGER.catching( e);
        }

        return new CommandResult(responseType, page);
    }
}
