
package by.teplouhova.chemist.command;

import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.entity.impl.Prescription;
import by.teplouhova.chemist.entity.impl.PrescriptionStatus;
import by.teplouhova.chemist.service.ClientService;
import by.teplouhova.chemist.service.PrescriptionService;
import by.teplouhova.chemist.service.ServiceException;
import by.teplouhova.chemist.validator.Validator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ResourceBundle;

public class ExtendPrescriptionCommand implements Command {


    private static final Logger LOGGER = LogManager.getLogger();
    private static final String PARAM_PRESCRIPTION_ID = "prescriptionId";
    private static final String ATTR_MESSAGE_BUNDLE = "messageBundle";
    private static final String ATTR_MESSEGE="message";
    private PrescriptionService prescriptionService;


    public ExtendPrescriptionCommand(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;

    }

    @Override
    public CommandResult execute(SessionRequestContent content) {
        String page;
        CommandResult.ResponseType responseType = CommandResult.ResponseType.FORWARD;
        String prescriptionId = content.getParameter(PARAM_PRESCRIPTION_ID);
        ResourceBundle bundle= (ResourceBundle) content.getSessionAttribute(ATTR_MESSAGE_BUNDLE);
        try {
            if (new Validator().validateRequired(prescriptionId, Validator.REGEXP_ID)){
                Prescription prescription=new Prescription(Long.parseLong(prescriptionId));
                prescription.setStatus(PrescriptionStatus.EXTEND);
                prescriptionService.update(prescription);
                page=PageConstant.PAGE_CLIENT_SUCCESS_PRESCRIPTION;
                responseType= CommandResult.ResponseType.REDIRECT;
            }else{
               content.setRequestAttributes(ATTR_MESSEGE,bundle.getString("message.parameter.wrong"));
               page=PageConstant.PAGE_ERROR;
            }


        } catch (ServiceException e) {
            page=PageConstant.PAGE_ERROR;
            //todo
            content.setRequestAttributes(ATTR_MESSEGE, "Prescription is not extended");
//todo message
            //responseType = CommandResult.ResponseType.REDIRECT;
            LOGGER.log(Level.ERROR, e);
        }


        return new CommandResult(responseType, page);
    }
}
