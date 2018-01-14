package by.teplouhova.chemist.command;

import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.creator.MedicineCreator;
import by.teplouhova.chemist.entity.impl.Medicine;
import by.teplouhova.chemist.service.MedicineService;
import by.teplouhova.chemist.service.ServiceException;
import by.teplouhova.chemist.validator.MedicineValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreateMedicineCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String PARAM_NAME = "name";
    private static final String PARAM_PRICE = "price";
    private static final String PARAM_QUANTITY_PACKAGES = "quantity_packages";
    private static final String PARAM_DOSAGE_SIZE = "dosage_size";
    private static final String PARAM_UNIT_DOSAGE = "unit_dosage";
    private static final String PARAM_QUANTITY_IN_PACK = "quantity_in_pack";
    private static final String PARAM_UNIT_IN_PACK = "unit_in_package";
    private static final String PARAM_NEED_PRESCRIPTION = "need_prescription";
    private static final String PARAM_RELEASE_FORM_ID = "release_form_id";
    private static final String PARAM_PRODUCER_ID = "producer_id";
    private static final String PARAM_ANALOG_ID = "analog_id";
    private static final String ATTR_ERROR = "error";
//    private static final String ATTR_MEDICINE = "medicine";

    private MedicineService medicineService;

    public CreateMedicineCommand(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @Override
    public CommandResult execute(SessionRequestContent content) {
        String page = null;
        CommandResult.ResponseType responseType = null;
        String name = content.getParameter(PARAM_NAME);
        String price = content.getParameter(PARAM_PRICE);
        String quantityPackages = content.getParameter(PARAM_QUANTITY_PACKAGES);
        String dosageSize = content.getParameter(PARAM_DOSAGE_SIZE);
        String unitDosage = content.getParameter(PARAM_UNIT_DOSAGE);
        String quantityInPack = content.getParameter(PARAM_QUANTITY_IN_PACK);
        String unitInPack = content.getParameter(PARAM_UNIT_IN_PACK);
        boolean isNeedPrescription = content.isContainParameter(PARAM_NEED_PRESCRIPTION) ? true : false;
        String releaseFormId = content.getParameter(PARAM_RELEASE_FORM_ID);
        String producerId = content.getParameter(PARAM_PRODUCER_ID);
        String analogId = content.getParameter(PARAM_ANALOG_ID);

        boolean isValid = new MedicineValidator().validateMedicine( name, price, quantityPackages,
                analogId, unitDosage, dosageSize, quantityInPack,
                unitInPack, releaseFormId,
                producerId);

        try {
            if (isValid) {
                Medicine medicine = new MedicineCreator().createMedicine( name, price, quantityPackages,
                        analogId, unitDosage, dosageSize, quantityInPack,
                        unitInPack, isNeedPrescription, releaseFormId,
                        producerId);
                medicineService.create(medicine);
                page = "/jsp/pharmacist/main.jsp";
                responseType = CommandResult.ResponseType.REDIRECT;
            } else {

                content.setRequestAttributes(ATTR_ERROR, "Invalid data");
                page = "/jsp/pharmacist/medicinenew.jsp";
                responseType = CommandResult.ResponseType.FORWARD;
            }
        } catch (ServiceException e) {
            page = "/jsp/error/error.jsp";
            responseType = CommandResult.ResponseType.REDIRECT;
            LOGGER.log(Level.ERROR, e);
        }

        return new CommandResult(responseType, page);
    }
}
