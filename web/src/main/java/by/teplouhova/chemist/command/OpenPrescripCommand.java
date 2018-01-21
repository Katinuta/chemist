package by.teplouhova.chemist.command;

import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.entity.impl.Prescription;
import by.teplouhova.chemist.entity.impl.User;
import by.teplouhova.chemist.service.ClientService;
import by.teplouhova.chemist.service.PrescriptionService;
import by.teplouhova.chemist.service.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.List;

public class OpenPrescripCommand implements Command{
    private static final Logger LOGGER= LogManager.getLogger();
    private static final String PARAM_PRESCRIPTION_ID="prescriptionId";
    private static final String ATTR_PRESCRIPTION="prescription";
    private static final String ATTR_USER="user";
    private static final String ATTR_PRESCRIPTIONS="prescriptions";
    private PrescriptionService service;
    private ClientService clientService;

    public OpenPrescripCommand(PrescriptionService service, ClientService clientService) {
        this.service = service;
        this.clientService = clientService;
    }

    @Override
    public CommandResult execute(SessionRequestContent content) {
        String page;
        CommandResult.ResponseType responseType= CommandResult.ResponseType.FORWARD;
        long id=Long.parseLong(content.getParameter(PARAM_PRESCRIPTION_ID));
        User user= (User) content.getSessionAttribute(ATTR_USER);
        List<Prescription> prescriptions;
         try {
             Prescription prescription=service.getPriscription(id);

             if (prescription != null) {

                 content.setRequestAttributes(ATTR_PRESCRIPTION, prescription);

                 page="/jsp/client/prescriptionDetail.jsp";

             }else{
                 page="/jsp/client/main.jsp";
                 prescriptions=clientService.getClientPrescriptions(user.getUserId());
                 content.setRequestAttributes(ATTR_PRESCRIPTIONS, prescriptions);
                 content.setRequestAttributes("error", "Prescription details is not found");
             }

        } catch (ServiceException e) {
             page="/jsp/client/main.jsp";
             content.setRequestAttributes("error", "Prescription details is not found");
            LOGGER.log(Level.ERROR,e);
        }

        return new CommandResult(responseType,page);
    }
}
