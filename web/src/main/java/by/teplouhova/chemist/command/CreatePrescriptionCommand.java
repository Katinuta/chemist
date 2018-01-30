package by.teplouhova.chemist.command;

import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.creator.PrescriptionCreator;
import by.teplouhova.chemist.entity.impl.Prescription;
import by.teplouhova.chemist.entity.impl.User;
import by.teplouhova.chemist.service.PrescriptionService;
import by.teplouhova.chemist.service.UserService;
import by.teplouhova.chemist.validator.PrescriptionValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.ResourceBundle;

public class CreatePrescriptionCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String ATTR_USER = "user";
    private static final String PARAM_CLIENT_ID = "clientId";
    private static final String PARAM_MEDICINE_ID = "medicineId";
    private static final String PARAM_QUANTITY = "quantity";
    private static final String PARAM_DATE_BEGIN = "dateBegin";
    private static final String PARAM_DATE_END = "dateEnd";
    private static final String ATTR_CLIENT = "client";
    private static final String ATTR_MESSAGE_BUNDLE = "messageBundle";
    private static final String ATTR_ERROR="error_";

    private PrescriptionService prescriptionService;
    private UserService userService;



    public CreatePrescriptionCommand(PrescriptionService prescriptionService, UserService userService) {
        this.prescriptionService = prescriptionService;
        this.userService = userService;
    }

    @Override
    public CommandResult execute(SessionRequestContent content) {
        String page ;
        CommandResult.ResponseType responseType ;
        User doctor = (User) content.getSessionAttribute(ATTR_USER);
        ResourceBundle bundle = (ResourceBundle) content.getSessionAttribute(ATTR_MESSAGE_BUNDLE);
        HashMap<String,String> prescripParams=new HashMap<>();
        prescripParams.put(PARAM_CLIENT_ID,content.getParameter(PARAM_CLIENT_ID));
        prescripParams.put(PARAM_MEDICINE_ID,content.getParameter(PARAM_MEDICINE_ID));
        prescripParams.put(PARAM_QUANTITY,content.getParameter(PARAM_QUANTITY));
        prescripParams.put(PARAM_DATE_BEGIN,content.getParameter(PARAM_DATE_BEGIN));
        prescripParams.put(PARAM_DATE_END,content.getParameter(PARAM_DATE_END));
        PrescriptionValidator validator = new PrescriptionValidator(bundle);
        try{
            boolean isValid = validator.isPrescriptionValid(prescripParams);
            String dateBegin=content.getParameter(PARAM_DATE_BEGIN);
            String dateEnd=content.getParameter(PARAM_DATE_END);
            if (isValid&& !LocalDate.parse(dateEnd).isBefore(LocalDate.parse(dateBegin))) {
                Prescription prescription=new PrescriptionCreator().create(prescripParams);
                prescription.setDoctor(doctor);
                prescriptionService.createPrescription(prescription);
                page=PageConstant.PAGE_SUCCESS_PRESCRIPTION_CREATE;
                responseType= CommandResult.ResponseType.REDIRECT;
            } else {
                User client=userService.getById(Long.parseLong(content.getParameter(PARAM_CLIENT_ID)));
                content.setRequestAttributes(ATTR_CLIENT,client);
                content.getParameterNames().forEach(name->content.setRequestAttributes(name,content.getParameter(name)));
                validator.getValidationErrors()
                        .forEach(entry->content.setRequestAttributes(ATTR_ERROR+entry.getKey(),entry.getValue()));
                if(isValid&&LocalDate.parse(dateEnd).isBefore(LocalDate.parse(dateBegin))){
                    content.setRequestAttributes(ATTR_ERROR+PARAM_DATE_END,bundle.getString("message.date.wrong"));

                }
                page = PageConstant.PAGE_DOCTOR_PRESCRIPTION_NEW;
                responseType = CommandResult.ResponseType.FORWARD;
            }

        } catch (Exception e) {
            page=PageConstant.PAGE_ERROR;
            //todo message
            responseType= CommandResult.ResponseType.REDIRECT;
            LOGGER.catching(e);
        }

        return new CommandResult(responseType, page);
    }
}
