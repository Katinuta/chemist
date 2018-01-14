package by.teplouhova.chemist.command;

import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.entity.impl.Prescription;
import by.teplouhova.chemist.entity.impl.PrescriptionDetail;
import by.teplouhova.chemist.entity.impl.PrescriptionStatus;
import by.teplouhova.chemist.entity.impl.User;
import by.teplouhova.chemist.service.PrescriptionService;
import by.teplouhova.chemist.service.ServiceException;

import java.time.LocalDate;
import java.util.List;

public class ApproveExtendPrescripCommand implements Command {

    private static final String PARAM_DATE_BEGIN="dateBegin";
    private static final String PARAM_DATE_END="dateEnd";
    private static final String PARAM_PRESCRIPTION_ID="prescriptionId";
    private static final String ATTR_USER="user";

    private PrescriptionService prescriptionService;

    public ApproveExtendPrescripCommand(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @Override
    public CommandResult execute(SessionRequestContent content) {
        String page="/jsp/doctor/main.jsp";
        CommandResult.ResponseType responseType;
        LocalDate dateBegin=LocalDate.parse(content.getParameter(PARAM_DATE_BEGIN));
        LocalDate dateEnd=LocalDate.parse(content.getParameter(PARAM_DATE_END));
        long prescriptionId=Long.parseLong(content.getParameter(PARAM_PRESCRIPTION_ID));
        try {
            if(dateEnd.isAfter(dateBegin)){
                Prescription prescription=prescriptionService.getPriscription(prescriptionId);
                prescription.setDateEnd(dateEnd);
                prescription.setStatus(PrescriptionStatus.ACTIVE);
                List<PrescriptionDetail> details=prescription.getDetails();
                details.stream().forEach(detail -> detail.setStatus(PrescriptionStatus.ACTIVE));
                prescriptionService.updatePriscription(prescription);
                responseType=CommandResult.ResponseType.REDIRECT;
            }else{
                responseType= CommandResult.ResponseType.FORWARD;
                content.setRequestAttributes("message","Prescriptions is not  updated");
            }

        } catch (ServiceException e) {
           page="/jsp/error/error.jsp";
           responseType=CommandResult.ResponseType.REDIRECT;

        }

        return new CommandResult(responseType,page);
    }
}
