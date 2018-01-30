package by.teplouhova.chemist.command;

import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.entity.impl.Prescription;
import by.teplouhova.chemist.entity.impl.User;
import by.teplouhova.chemist.service.ServiceException;
import by.teplouhova.chemist.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;


public class NewPrescriptionCommand implements Command {
    private static final Logger LOGGER= LogManager.getLogger();
    private static  final String PARAM_CLIENT_ID="clientId";
    private static  final String ATTR_CLIENT="client";
    private static final String ATTR_USER="user";
    private static final String ATTR_DATE_BEGIN="dateBegin";

    private UserService userService;

    public NewPrescriptionCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(SessionRequestContent content) {
       String page=PageConstant.PAGE_DOCTOR_PRESCRIPTION_NEW;
       CommandResult.ResponseType responseType= CommandResult.ResponseType.FORWARD;
       long clientId=Integer.parseInt(content.getParameter(PARAM_CLIENT_ID));
       User user= (User) content.getSessionAttribute(ATTR_USER);
        try {
            User client=userService.getById(clientId);
            content.setRequestAttributes(ATTR_DATE_BEGIN,LocalDate.now());
            content.setRequestAttributes(ATTR_CLIENT,client);
        } catch (ServiceException e) {
            page=PageConstant.PAGE_ERROR;
            responseType= CommandResult.ResponseType.REDIRECT;
        }


        return new CommandResult(responseType,page);
    }
}
