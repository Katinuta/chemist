
package by.teplouhova.chemist.command.client;

import by.teplouhova.chemist.command.Command;
import by.teplouhova.chemist.command.CommandResult;
import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.creator.PrescriptionDetailCreator;
import by.teplouhova.chemist.entity.impl.PrescriptionDetail;
import by.teplouhova.chemist.entity.impl.PrescriptionStatus;
import by.teplouhova.chemist.service.PrescripDetailService;
import by.teplouhova.chemist.service.ServiceException;
import by.teplouhova.chemist.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.ResourceBundle;

import static by.teplouhova.chemist.command.CommandResult.ResponseType.FORWARD;
import static by.teplouhova.chemist.command.CommandResult.ResponseType.REDIRECT;
import static by.teplouhova.chemist.command.PageConstant.PAGE_ERROR;


public class ExtendPrescripDetailCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String PARAM_PRESCRIP_DETAIL_ID = "prescrip_detail_id";
    private static final String ATTR_MESSAGE_BUNDLE = "messageBundle";
    private static final String ATTR_MESSAGE = "message";
    private static final String HEADER_REFERER = "referer";

    private PrescripDetailService detailService;

    public ExtendPrescripDetailCommand(PrescripDetailService detailService) {
        this.detailService = detailService;
    }

    @Override
    public CommandResult execute(SessionRequestContent content) {
        String page;
        CommandResult.ResponseType responseType = FORWARD;

        ResourceBundle bundle = (ResourceBundle) content.getSessionAttribute(ATTR_MESSAGE_BUNDLE);
        try {
            HashMap<String, String> params = new HashMap<>();
            params.put(PARAM_PRESCRIP_DETAIL_ID, content.getParameter(PARAM_PRESCRIP_DETAIL_ID));
            Validator validator = new Validator(bundle);
            if (validator.isValid(params)) {
                PrescriptionDetail detail = new PrescriptionDetailCreator().create(params);
                detail.setStatus(PrescriptionStatus.EXTEND);
                detailService.updatePrescripDetail(detail);
                page = content.getRequestHeader(HEADER_REFERER);
                responseType = REDIRECT;
            } else {
                content.setRequestAttributes(ATTR_MESSAGE, bundle.getString("message.parameter.wrong"));
                page = PAGE_ERROR;
            }

        } catch (ServiceException e) {
            page = PAGE_ERROR;
            content.setRequestAttributes(ATTR_MESSAGE, e.getMessage());
            LOGGER.catching(e);
        }

        return new CommandResult(responseType, page);
    }
}
