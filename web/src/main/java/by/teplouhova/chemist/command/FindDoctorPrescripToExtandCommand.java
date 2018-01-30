package by.teplouhova.chemist.command;

import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.entity.impl.Prescription;
import by.teplouhova.chemist.entity.impl.User;
import by.teplouhova.chemist.service.PrescriptionService;
import by.teplouhova.chemist.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.ResourceBundle;

public class FindDoctorPrescripToExtandCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String ATTR_USER = "user";
    private static final String ATTR_PRESCRIPTIONS = "prescriptions";
    private static final String ATTR_MESSAGE_BUNDLE = "messageBundle";
    private static final String ATTR_MESSAGE = "message";
    private static  final String ATTR_EXTENDING="extending";

    private PrescriptionService prescriptionService;

    public FindDoctorPrescripToExtandCommand(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @Override
    public CommandResult execute(SessionRequestContent content) {
        String page;
        CommandResult.ResponseType responseType;
        User doctor = (User) content.getSessionAttribute(ATTR_USER);
        ResourceBundle bundle = (ResourceBundle) content.getSessionAttribute(ATTR_MESSAGE_BUNDLE);
        List<Prescription> prescriptions;
        try {
            prescriptions = prescriptionService.getDoctorPrescriptions(doctor.getUserId(), true);
            content.setRequestAttributes(ATTR_EXTENDING,true);
            if (prescriptions != null) {
                content.setRequestAttributes(ATTR_PRESCRIPTIONS, prescriptions);
            } else {
                content.setRequestAttributes(ATTR_MESSAGE, bundle.getString("message.prescrip.extend.empty"));
            }
            page = PageConstant.PAGE_DOCTOR_MAIN;
            responseType = CommandResult.ResponseType.FORWARD;
        } catch (ServiceException e) {
            page = PageConstant.PAGE_ERROR;
            responseType = CommandResult.ResponseType.REDIRECT;
            LOGGER.catching(e);
        }

        return new CommandResult(responseType, page);
    }
}
