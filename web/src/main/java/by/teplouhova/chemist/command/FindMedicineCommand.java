package by.teplouhova.chemist.command;

import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.entity.impl.Medicine;
import by.teplouhova.chemist.entity.impl.RoleType;
import by.teplouhova.chemist.entity.impl.User;
import by.teplouhova.chemist.service.MedicineService;
import by.teplouhova.chemist.service.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Set;

public class FindMedicineCommand implements Command {

    private static final Logger LOGGER= LogManager.getLogger();

    private static final String PARAM_SERACH="search";
    private static final String ATTR_MEDICINES="medicines";
    private static final String ATTR_FLAG_FIND="flagFind";
    private static final String ATTR_USER="user";

    private MedicineService service;

    public FindMedicineCommand(MedicineService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(SessionRequestContent content) {
        String page=null ;
        CommandResult.ResponseType responseType;
        String name=content.getParameter(PARAM_SERACH);
        try {
            ArrayList<Medicine> medicines=service.getMedicinesByName(name.toUpperCase());
            LOGGER.log(Level.DEBUG,medicines);
            if(medicines!=null){
                content.setRequestAttributes(ATTR_MEDICINES,medicines);
                content.setRequestAttributes(ATTR_FLAG_FIND,true);
            }else{
                content.setRequestAttributes("error","Medicine was not found");
            }
            User user= (User) content.getSessionAttribute(ATTR_USER);
            if(user.getRole().equals(RoleType.CLIENT)){
                page = "/jsp/client/main.jsp";
            }
            if(user.getRole().equals(RoleType.PHARMACIST)){
                page = "/jsp/pharmacist/main.jsp";
            }
            responseType= CommandResult.ResponseType.FORWARD;
            LOGGER.log(Level.DEBUG,page);

        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR,"find medicine"+e);
            page = "/jsp/error/error.jsp";
            responseType= CommandResult.ResponseType.REDIRECT;
        }

        return new CommandResult(responseType,page);
    }
}
