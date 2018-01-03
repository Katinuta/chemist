package by.teplouhova.chemist.command;

import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.entity.impl.Prescription;
import by.teplouhova.chemist.entity.impl.User;
import by.teplouhova.chemist.service.ClientService;
import by.teplouhova.chemist.service.ServiceException;

import java.util.Set;


public class ClientPrescriptionCommand implements Command {


    private static final String ATTR_PRESCRIPTIONS="prescriptions";
    private static final String ATTR_FLAG_FIND="flagFind";
    private ClientService service;

    public ClientPrescriptionCommand(ClientService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(SessionRequestContent content) {
        String page;
        CommandResult.ResponseType responseType;
        User user= (User) content.getSessionAttribute("user");
        try {
            Set<Prescription> prescriptions= service.getClientPrescriptions(user.getUsedId());
            if(prescriptions!=null){
                content.setRequestAttributes(ATTR_PRESCRIPTIONS,prescriptions);

            }else{
                content.setRequestAttributes("error","Prescriptions was not found");
            }
            content.setRequestAttributes(ATTR_FLAG_FIND,true);
            page="/jsp/client/main.jsp";
            responseType= CommandResult.ResponseType.FORWARD;
        } catch (ServiceException e) {
            page = "/jsp/error/error.jsp";
            responseType= CommandResult.ResponseType.REDIRECT;
        }

        return new CommandResult(responseType,page);
    }
}
