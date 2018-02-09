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

public class CreatePrescriptionCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String ATTR_USER = "user";
    private static final String PARAM_CLIENT_ID = "client_id";
    private static final String PARAM_DATE_BEGIN = "date_begin";
    private static final String PARAM_DATE_END = "date_end";
    private static final String PARAM_MEDICINE_ID = "medicine_id";
    private static final String PARAM_QUANTITY="quantity";
    private static final String ATTR_CLIENT = "client";
    private static final String ATTR_MESSAGE_BUNDLE = "messageBundle";
    private static final String ATTR_MESSAGE_ERROR = "message";
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
        prescripParams.put(PARAM_DATE_BEGIN,content.getParameter(PARAM_DATE_BEGIN));
        prescripParams.put(PARAM_DATE_END,content.getParameter(PARAM_DATE_END));
        prescripParams.put(PARAM_MEDICINE_ID,content.getParameter(PARAM_MEDICINE_ID));
        prescripParams.put(PARAM_QUANTITY,content.getParameter(PARAM_QUANTITY));
       Validator validator = new Validator(bundle);
        try{
            boolean isValid = validator.isValid(prescripParams);
            String dateBegin=prescripParams.get(PARAM_DATE_BEGIN);
            String dateEnd=prescripParams.get(PARAM_DATE_END);
            if (isValid&& !LocalDate.parse(dateEnd).isBefore(LocalDate.parse(dateBegin))) {
                Prescription prescription=new PrescriptionCreator().create(prescripParams);
                PrescriptionDetail detail=new PrescriptionDetailCreator().create(prescripParams);
                prescription.setDoctor(doctor);
                prescription.setDetails(detail);
                prescriptionService.createPrescription(prescription);
                page= PAGE_SUCCESS_PRESCRIPTION_CREATE;
                responseType= REDIRECT;
            } else {
                content.getParameterNames().forEach(name->content.setRequestAttributes(name,content.getParameter(name)));
                validator.getEntrySetErrors()
                        .forEach(entry->content.setRequestAttributes(ATTR_ERROR+entry.getKey(),entry.getValue()));
                if(isValid&&LocalDate.parse(dateEnd).isBefore(LocalDate.parse(dateBegin))){
                    content.setRequestAttributes(ATTR_ERROR+PARAM_DATE_END,bundle.getString("message.date.wrong"));

                }
                if(!validator.isExistError(ATTR_ERROR+PARAM_CLIENT_ID)){
                    User client=userService.getById(Long.parseLong(content.getParameter(PARAM_CLIENT_ID)));
                    content.setRequestAttributes(ATTR_CLIENT,client);
                }else{

                }
                page = PAGE_DOCTOR_PRESCRIPTION_NEW;
                responseType =FORWARD;
            }

        } catch (Exception e) {
            page=PAGE_ERROR;
            responseType =FORWARD;
            content.setRequestAttributes(ATTR_MESSAGE_ERROR,e.getMessage());
            LOGGER.catching(e);
        }

        return new CommandResult(responseType, page);
    }
}
