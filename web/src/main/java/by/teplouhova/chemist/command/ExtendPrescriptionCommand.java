
package by.teplouhova.chemist.command;

import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.entity.impl.Prescription;
import by.teplouhova.chemist.entity.impl.PrescriptionDetail;
import by.teplouhova.chemist.entity.impl.PrescriptionStatus;
import by.teplouhova.chemist.entity.impl.User;
import by.teplouhova.chemist.service.ClientService;
import by.teplouhova.chemist.service.PrescriptionService;
import by.teplouhova.chemist.service.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class ExtendPrescriptionCommand implements Command {


    private static final Logger LOGGER = LogManager.getLogger();
    private static final String PARAM_PRESCRIPTION_ID = "prescriptionId";
    private static final String ATTR_PRESCRIPTION = "prescription";
    private static final String ATTR_USER = "user";
    private static final String ATTR_PRESCRIPTIONS = "prescriptions";
    private PrescriptionService service;
    private ClientService clientService;

    public ExtendPrescriptionCommand(PrescriptionService service, ClientService clientService) {
        this.service = service;
        this.clientService = clientService;
    }

    @Override
    public CommandResult execute(SessionRequestContent content) {
        String page = "/jsp/client/main.jsp";
        CommandResult.ResponseType responseType = CommandResult.ResponseType.FORWARD;
        long id = Long.parseLong(content.getParameter(PARAM_PRESCRIPTION_ID));
        User user = (User) content.getSessionAttribute(ATTR_USER);
        List<Prescription> prescriptions;
        Prescription prescription = null;
        try {
            prescription = service.getPriscription(id);
            if (prescription != null) {
//                prescription.setClient(user);
                prescription.setStatus(PrescriptionStatus.EXTAND);
                if (!prescription.isEmptyDetails()) {
                    Iterator<PrescriptionDetail> iterator = prescription.getDetailsIterator();
                    while (iterator.hasNext()) {
                        PrescriptionDetail detail = iterator.next();
                        if (PrescriptionStatus.INACTIVE.equals(detail.getStatus())) {
                            detail.setStatus(PrescriptionStatus.EXTAND);
                        }
                    }
                    service.updatePriscription(prescription);

                } else {
                    //detail empty!!!  TODO
                }

            }
            prescriptions = clientService.getClientPrescriptions(user.getUserId());
            content.setRequestAttributes(ATTR_PRESCRIPTIONS, prescriptions);
        } catch (ServiceException e) {
            content.setRequestAttributes("error", "Prescription is not extended");

            //responseType = CommandResult.ResponseType.REDIRECT;
            LOGGER.log(Level.ERROR, e);
        }


        return new CommandResult(responseType, page);
    }
}
