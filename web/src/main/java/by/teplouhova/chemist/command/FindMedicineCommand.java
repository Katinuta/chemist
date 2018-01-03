package by.teplouhova.chemist.command;

import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.entity.impl.Medicine;
import by.teplouhova.chemist.service.MedicineService;
import by.teplouhova.chemist.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Set;

public class FindMedicineCommand implements Command {

    private static final Logger LOGGER= LogManager.getLogger();

    private static final String PARAM_NAME="name";
    private static final String ATTR_MEDICINES="medicines";
    private static final String ATTR_FLAG_FIND="flagFind";

    private MedicineService service;

    public FindMedicineCommand(MedicineService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(SessionRequestContent content) {
        String page ;
        CommandResult.ResponseType responseType;
        String name=content.getParameter(PARAM_NAME);
        try {
            ArrayList<Medicine> medicines=service.getMedicinesByName(name.toUpperCase());
            if(medicines!=null){
                content.setRequestAttributes(ATTR_MEDICINES,medicines);
                content.setRequestAttributes(ATTR_FLAG_FIND,true);
            }else{
                content.setRequestAttributes("error","Medicine was not found");
            }
            page="/jsp/client/main.jsp";
            responseType= CommandResult.ResponseType.FORWARD;

        } catch (ServiceException e) {
            //log?
            page = "/jsp/error/error.jsp";
            responseType= CommandResult.ResponseType.REDIRECT;
        }

        return new CommandResult(responseType,page);
    }
}
