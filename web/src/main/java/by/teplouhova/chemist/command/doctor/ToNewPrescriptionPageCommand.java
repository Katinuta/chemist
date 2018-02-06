package by.teplouhova.chemist.command.doctor;

import by.teplouhova.chemist.command.Command;
import by.teplouhova.chemist.command.CommandResult;
import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.entity.impl.User;
import by.teplouhova.chemist.service.ServiceException;
import by.teplouhova.chemist.service.UserService;
import by.teplouhova.chemist.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.ResourceBundle;

import static by.teplouhova.chemist.command.CommandResult.ResponseType.FORWARD;
import static by.teplouhova.chemist.command.PageConstant.PAGE_DOCTOR_PRESCRIPTION_NEW;
import static by.teplouhova.chemist.command.PageConstant.PAGE_ERROR;

public class ToNewPrescriptionPageCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String PARAM_CLIENT_ID = "client_id";
    private static final String ATTR_CLIENT = "client";
    private static final String ATTR_MESSAGE_ERROR = "message";
    private static final String ATTR_DATE_BEGIN = "date_begin";
    private static final String ATTR_MESSAGE_BUNDLE = "messageBundle";

    private UserService userService;

    public ToNewPrescriptionPageCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(SessionRequestContent content) {
        String page = PAGE_DOCTOR_PRESCRIPTION_NEW;
        CommandResult.ResponseType responseType = FORWARD;
        ResourceBundle bundle = (ResourceBundle) content.getSessionAttribute(ATTR_MESSAGE_BUNDLE);
        Validator validator = new Validator(bundle);
        HashMap<String, String> params = new HashMap<>();
        params.put(PARAM_CLIENT_ID, content.getParameter(PARAM_CLIENT_ID));
        try {
            if (validator.isValid(params)) {
                long clientId = Integer.parseInt(content.getParameter(PARAM_CLIENT_ID));
                User client = userService.getById(clientId);
                content.setRequestAttributes(ATTR_DATE_BEGIN, LocalDate.now());
                content.setRequestAttributes(ATTR_CLIENT, client);
            } else {
                page = PAGE_ERROR;
                content.setRequestAttributes(ATTR_MESSAGE_ERROR, bundle.getString("message.parameter.wrong"));
            }

        } catch (ServiceException e) {
            content.setRequestAttributes(ATTR_MESSAGE_ERROR, e.getMessage());
            page = PAGE_ERROR;
            LOGGER.catching(e);

        }

        return new CommandResult(responseType, page);
    }
}
