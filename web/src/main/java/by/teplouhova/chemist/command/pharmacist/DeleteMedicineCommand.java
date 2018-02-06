package by.teplouhova.chemist.command.pharmacist;


import by.teplouhova.chemist.command.Command;
import by.teplouhova.chemist.command.CommandResult;
import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.creator.MedicineCreator;
import by.teplouhova.chemist.entity.impl.Medicine;
import by.teplouhova.chemist.service.MedicineService;
import by.teplouhova.chemist.service.ServiceException;
import by.teplouhova.chemist.validator.RegexpConstant;
import by.teplouhova.chemist.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.ResourceBundle;

import static by.teplouhova.chemist.command.CommandResult.ResponseType.FORWARD;
import static by.teplouhova.chemist.command.PageConstant.PAGE_ERROR;
import static by.teplouhova.chemist.validator.RegexpConstant.REGEXP_ID;

public class DeleteMedicineCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String PARAM_ID ="medicine_id";
    private static final String ATTR_MESSAGE ="message";
    private static final String HEADER_REFERER = "referer";
    private static final String ATTR_MESSAGE_BUNDLE="messageBundle";

    private MedicineService medicineService;

    public DeleteMedicineCommand(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @Override
    public CommandResult execute(SessionRequestContent content) {
        String page;
        CommandResult.ResponseType responseType;
        ResourceBundle bundle= (ResourceBundle) content.getSessionAttribute(ATTR_MESSAGE_BUNDLE);
        HashMap<String, String> medicineParams = new HashMap<>();
        Validator validator=new Validator(bundle);
        try {
            //todo validation
            String medicineId=content.getParameter(PARAM_ID);
            medicineParams.put(PARAM_ID,medicineId);
            if(validator.isValid(medicineParams)){
               Medicine medicine=new MedicineCreator().createMedicine(medicineParams);
                medicineService.delete(medicine);
                responseType=CommandResult.ResponseType.REDIRECT;
                page=content.getRequestHeader(HEADER_REFERER);
            }else{
               content.setRequestAttributes(ATTR_MESSAGE,bundle.getString("message.medicine.parameter.wrong"));
               page= PAGE_ERROR;
               responseType= FORWARD;
            }
        } catch (ServiceException e) {
            //TODO
            responseType= FORWARD;
            content.setRequestAttributes(ATTR_MESSAGE,e.getMessage());
            page=PAGE_ERROR;
            LOGGER.catching(e);
        }

        return new CommandResult(responseType,page);
    }
}
