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


public class ShowDoctorPrescriptionsCommand implements Command {
    private static final Logger LOGGER=  LogManager.getLogger();

    private static final String ATTR_PRESRIPTIONS="prescriptions";
    private static final String ATTR_USER="user";
    private static final String ATTR_MESSAGE="message";
    private static final String ATTR_MESSAGE_BUNDLE="messageBundle";



    private PrescriptionService prescriptionService;

    public ShowDoctorPrescriptionsCommand(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @Override
    public CommandResult execute(SessionRequestContent content) {
        String page=PAGE_DOCTOR_PRESCRIPTION;
        CommandResult.ResponseType responseType=FORWARD;
        User user= (User) content.getSessionAttribute(ATTR_USER);
        ResourceBundle bundle= (ResourceBundle) content.getSessionAttribute(ATTR_MESSAGE_BUNDLE);
        try {
            List<Prescription> prescriptions=prescriptionService.getDoctorPrescriptions(user.getUserId(),false);
            if(!prescriptions.isEmpty()){
                content.setRequestAttributes(ATTR_PRESRIPTIONS,prescriptions);
            }else{
                content.setRequestAttributes(ATTR_MESSAGE,bundle.getString("message.prescrip.emty"));
            }


        } catch (ServiceException e) {
            page= PAGE_ERROR;
            responseType=REDIRECT;
            LOGGER.log(Level.ERROR,e);
        }
        return new CommandResult(responseType,page);
    }
}
