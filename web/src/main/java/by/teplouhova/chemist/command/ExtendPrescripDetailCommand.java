
package by.teplouhova.chemist.command;

import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.entity.impl.Prescription;
import by.teplouhova.chemist.entity.impl.PrescriptionDetail;
import by.teplouhova.chemist.entity.impl.PrescriptionStatus;
import by.teplouhova.chemist.entity.impl.User;
import by.teplouhova.chemist.service.ClientService;
import by.teplouhova.chemist.service.PrescripDetailService;
import by.teplouhova.chemist.service.PrescriptionService;
import by.teplouhova.chemist.service.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;

public class ExtendPrescripDetailCommand implements Command {


    private static final Logger LOGGER = LogManager.getLogger();
    private static final String PARAM_PRESCRIP_DETAIL_ID = "prescripDetailId";
    private static final String ATTR_USER = "user";
    private static final String ATTR_PRESCRIPTIONS = "prescriptions";
    private static final String ATTR_PRESCRIPTION = "prescription";

    private ClientService clientService;
    private PrescripDetailService detailService;
    private PrescriptionService prescriptionService;


    public ExtendPrescripDetailCommand(ClientService clientService, PrescripDetailService detailService, PrescriptionService prescriptionService) {
        this.clientService = clientService;
        this.detailService = detailService;
        this.prescriptionService = prescriptionService;
    }

    @Override
    public CommandResult execute(SessionRequestContent content) {
//        String page = "/jsp/client/main.jsp";
        String page =null;
        CommandResult.ResponseType responseType = CommandResult.ResponseType.FORWARD;
        long id = Long.parseLong(content.getParameter(PARAM_PRESCRIP_DETAIL_ID));
        User user = (User) content.getSessionAttribute(ATTR_USER);
        HashSet<Prescription> prescriptions;
        Prescription prescription = null;
        PrescriptionDetail detail;
        try {
            detail=detailService.getPrescripDetail(id);

            if(detail!=null){
                prescription=prescriptionService.getPriscription(detail.getPrescription().getPrescriptionId());
                detail.setStatus(PrescriptionStatus.EXTAND);
                if(prescription!=null){
                    prescription.setStatus(PrescriptionStatus.EXTAND);
//                    prescription.setClient(user);
                    detail.setPrescription(prescription);

                    detailService.updatePrescripDetail(detail);

                }else{
                    content.setRequestAttributes("error","Request to extend is not sent. Prescription is not found");
                }
                content.setRequestAttributes("error","Request to extend is not sent.Detail is is not found");

            }
            prescription=prescriptionService.getPriscription(detail.getPrescription().getPrescriptionId());
            content.setRequestAttributes(ATTR_PRESCRIPTION,prescription);
            page="/jsp/client/prescriptionDetail.jsp";
            responseType= CommandResult.ResponseType.FORWARD;

        } catch (ServiceException e) {

            page="/jsp/error/error.jsp";
            responseType= CommandResult.ResponseType.REDIRECT;
            LOGGER.log(Level.ERROR,e);
        }

        return new CommandResult(responseType, page);
    }
}
