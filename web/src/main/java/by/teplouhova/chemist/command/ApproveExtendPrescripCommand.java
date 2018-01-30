package by.teplouhova.chemist.command;

import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.creator.PrescriptionCreator;
import by.teplouhova.chemist.entity.impl.Prescription;
import by.teplouhova.chemist.service.PrescriptionService;
import by.teplouhova.chemist.service.ServiceException;
import by.teplouhova.chemist.validator.PrescriptionValidator;
import org.apache.logging.log4j.LogManager;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ApproveExtendPrescripCommand implements Command {
    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();
    private static final String PARAM_DATE_BEGIN="dateBegin";
    private static final String PARAM_DATE_END="dateEnd";
    private static final String PARAM_PRESCRIPTION_ID="prescriptionId";
    private static final String ATTR_USER="user";
    private static final String ATTR_MESSAGE_BUNDLE = "messageBundle";
    private static final String ATTR_ERROR="error_";

    private PrescriptionService prescriptionService;

    public ApproveExtendPrescripCommand(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @Override
    public CommandResult execute(SessionRequestContent content) {
        String page=PageConstant.PAGE_DOCTOR_MAIN;
        CommandResult.ResponseType responseType= CommandResult.ResponseType.FORWARD;
        ResourceBundle bundle = (ResourceBundle) content.getSessionAttribute(ATTR_MESSAGE_BUNDLE);
        HashMap<String,String> prescripParams=new HashMap<>();
        prescripParams.put(PARAM_PRESCRIPTION_ID,content.getParameter(PARAM_PRESCRIPTION_ID));
        prescripParams.put(PARAM_DATE_BEGIN,content.getParameter(PARAM_DATE_BEGIN));
        prescripParams.put(PARAM_DATE_END,content.getParameter(PARAM_DATE_END));
        PrescriptionValidator validator = new PrescriptionValidator(bundle);
        try {
        boolean isValid=validator.isPrescriptionValid(prescripParams);
        String dateBegin=content.getParameter(PARAM_DATE_BEGIN);
        String dateEnd=content.getParameter(PARAM_DATE_END);
        if(isValid&& !LocalDate.parse(dateEnd).isBefore(LocalDate.parse(dateBegin))){
            Prescription prescription=new PrescriptionCreator().create(prescripParams);
            prescriptionService.update(prescription);
            responseType=CommandResult.ResponseType.REDIRECT;
            page=PageConstant.PAGE_SUCCESS_PRESCRIPTION_APPROVE;
        }else{
            if(isValid&&LocalDate.parse(dateEnd).isBefore(LocalDate.parse(dateBegin))){
                content.setRequestAttributes(ATTR_ERROR+PARAM_DATE_END,bundle.getString("message.date.wrong"));
            }
            content.getParameterNames().forEach(name->content.setRequestAttributes(name,content.getParameter(name)));
            validator.getValidationErrors()
                    .forEach(entry->content.setRequestAttributes(ATTR_ERROR+entry.getKey(),entry.getValue()));
        }

        } catch (ServiceException e) {
           page=PageConstant.PAGE_ERROR;
           //todo message?
           responseType=CommandResult.ResponseType.REDIRECT;
            LOGGER.catching(e);
        }

        return new CommandResult(responseType,page);
    }
}
