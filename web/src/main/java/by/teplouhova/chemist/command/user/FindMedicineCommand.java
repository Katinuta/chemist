package by.teplouhova.chemist.command.user;

import by.teplouhova.chemist.command.Command;
import by.teplouhova.chemist.command.CommandResult;
import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.entity.impl.Medicine;
import by.teplouhova.chemist.entity.impl.RoleType;
import by.teplouhova.chemist.entity.impl.User;
import by.teplouhova.chemist.service.MedicineService;
import by.teplouhova.chemist.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.ResourceBundle;

import static by.teplouhova.chemist.command.CommandResult.ResponseType.FORWARD;
import static by.teplouhova.chemist.command.CommandResult.ResponseType.REDIRECT;
import static by.teplouhova.chemist.command.PageConstant.*;

public class FindMedicineCommand implements Command {

    private static final Logger LOGGER= LogManager.getLogger();

    private static final String PARAM_SEARCH ="medicine_name";
    private static final String ATTR_MEDICINES="medicines";
    private static final String ATTR_FLAG_FIND="flagFind";
    private static final String ATTR_USER="user";
    private static final String ATTR_MESSAGE="message";
    private static final String ATTR_MESSAGE_BUNDLE="messageBundle";

    private MedicineService service;

    public FindMedicineCommand(MedicineService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(SessionRequestContent content) {
        String page=null ;
        CommandResult.ResponseType responseType;
        ResourceBundle bundle= (ResourceBundle) content.getSessionAttribute(ATTR_MESSAGE_BUNDLE);
        //todo validation
        String name=content.getParameter(PARAM_SEARCH);
        try {
            ArrayList<Medicine> medicines=service.getMedicinesByName(name.toUpperCase());
            content.setRequestAttributes(ATTR_FLAG_FIND,true);
            if(medicines!=null){
                content.setRequestAttributes(ATTR_MEDICINES,medicines);
            }else{
                content.setRequestAttributes(ATTR_MESSAGE,bundle.getString("message.medicines.notfound.byname")+name);
            }
            User user= (User) content.getSessionAttribute(ATTR_USER);
            if(user.getRole().equals(RoleType.CLIENT)){
                page = PAGE_CLIENT_MAIN ;
            }
            if(user.getRole().equals(RoleType.PHARMACIST)){
                page = PAGE_PHARMACIST_MAIN ;
            }
            responseType= FORWARD;

        } catch (ServiceException e) {
            //todo message
            LOGGER.catching(e);
            page = PAGE_ERROR;
            responseType= REDIRECT;
        }
        return new CommandResult(responseType,page);
    }
}
