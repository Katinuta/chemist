package by.teplouhova.chemist.command;

import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.entity.impl.Prescription;
import by.teplouhova.chemist.entity.impl.User;
import by.teplouhova.chemist.service.PrescriptionService;
import by.teplouhova.chemist.service.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class DoctorPrescriptionCommand implements Command {
    private static final Logger LOGGER=  LogManager.getLogger();

    private String ATTR_PRESRIPTIONS="prescriptions";
    private String ATTR_USER="user";
    private String ATTR_ERROR="error";

    private PrescriptionService prescriptionService;

    public DoctorPrescriptionCommand(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @Override
    public CommandResult execute(SessionRequestContent content) {
        String page="/jsp/doctor/prescription.jsp";
        CommandResult.ResponseType responseType=CommandResult.ResponseType.FORWARD;
        User user= (User) content.getSessionAttribute(ATTR_USER);
        try {
            List<Prescription> prescriptions=prescriptionService.getDoctorPrescriptions(user.getUserId());
            if(!prescriptions.isEmpty()){
                content.setRequestAttributes(ATTR_PRESRIPTIONS,prescriptions);
            }else{
                content.setRequestAttributes(ATTR_ERROR,"Prescriptions is not found");
            }


        } catch (ServiceException e) {
            content.setRequestAttributes(ATTR_ERROR,"Error finding rescriptions");
            LOGGER.log(Level.ERROR,e);
        }
        return new CommandResult(responseType,page);
    }
}
