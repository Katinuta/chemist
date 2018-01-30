package by.teplouhova.chemist.command;


import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.entity.impl.Medicine;
import by.teplouhova.chemist.service.MedicineService;
import by.teplouhova.chemist.service.ServiceException;
import by.teplouhova.chemist.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.teplouhova.chemist.validator.Validator.*;

public class DeleteMedicineCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String PARAM_ID ="medicineId";
    private static final String ATTR_MESSAGE ="message";

    private MedicineService medicineService;

    public DeleteMedicineCommand(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @Override
    public CommandResult execute(SessionRequestContent content) {
        String page;
        CommandResult.ResponseType responseType;
        Long id=null;
        try {
            String medicineId=content.getParameter(content.getParameter(PARAM_ID));
            if(new Validator().validateRequired(medicineId,REGEXP_ID)){
                id=Long.parseLong(medicineId);
                medicineService.delete(new Medicine(id));
                responseType=CommandResult.ResponseType.REDIRECT;
                page=PageConstant.PAGE_PHARMACIST_MAIN;
            }else{
                //todo message
               page=PageConstant.PAGE_ERROR;
               responseType= CommandResult.ResponseType.FORWARD;
            }


        } catch (ServiceException e) {
            //TODO
            responseType= CommandResult.ResponseType.FORWARD;
            content.setRequestAttributes(ATTR_MESSAGE,"Medicine is not deleted"+ id );
            page=PageConstant.PAGE_ERROR;
            LOGGER.catching(e);
        }

        return new CommandResult(responseType,page);
    }
}
