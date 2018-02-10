package by.teplouhova.chemist.command.client;

import by.teplouhova.chemist.command.Command;
import by.teplouhova.chemist.command.CommandResult;
import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.entity.impl.Prescription;
import by.teplouhova.chemist.entity.impl.User;
import by.teplouhova.chemist.service.ClientService;
import by.teplouhova.chemist.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.ResourceBundle;

import static by.teplouhova.chemist.command.CommandResult.ResponseType.FORWARD;
import static by.teplouhova.chemist.command.PageConstant.PAGE_CLIENT_MAIN;
import static by.teplouhova.chemist.command.PageConstant.PAGE_ERROR;

/**
 * The Class ShowClientPrescriptionsCommand.
 */
public class ShowClientPrescriptionsCommand implements Command {

    private final static Logger LOGGER = LogManager.getLogger();
    /** The Constant ATTR_PRESCRIPTIONS. */
    private static final String ATTR_PRESCRIPTIONS = "prescriptions";

    /** The Constant ATTR_USER. */
    private static final String ATTR_USER = "user";

    /** The Constant ATTR_MESSAGE_ERROR. */
    private static final String ATTR_MESSAGE_ERROR = "message";

    /** The Constant ATTR_MESSAGE_PRESCRIPTION. */
    private static final String ATTR_MESSAGE_PRESCRIPTION ="prescriptions_message";

    /** The Constant ATTR_MESSAGE_BUNDLE. */
    private static  final String ATTR_MESSAGE_BUNDLE="messageBundle";

    /** The service. */
    private ClientService service;

    /**
     * Instantiates a new show client prescriptions command.
     *
     * @param service the service
     */
    public ShowClientPrescriptionsCommand(ClientService service) {
        this.service = service;
    }

    /**
     * Execute.
     *
     * @param content the content
     * @return the command result
     */
    @Override
    public CommandResult execute(SessionRequestContent content) {
        String page;
        CommandResult.ResponseType responseType = FORWARD;
        User user = (User) content.getSessionAttribute(ATTR_USER);
        ResourceBundle bundle= (ResourceBundle) content.getSessionAttribute(ATTR_MESSAGE_BUNDLE);
        try {
            List<Prescription> prescriptions = service.getPrescriptions(user.getUserId());
            if(prescriptions==null){
                content.setRequestAttributes(ATTR_MESSAGE_PRESCRIPTION,bundle.getString("message.prescriptions.empty"));
            }
            content.setRequestAttributes(ATTR_PRESCRIPTIONS, prescriptions);
            page = PAGE_CLIENT_MAIN;
        } catch (ServiceException e) {
            content.setRequestAttributes(ATTR_MESSAGE_ERROR, e.getMessage());
            page = PAGE_ERROR;
            LOGGER.catching(e);
        }

        return new CommandResult(responseType, page);
    }
}
