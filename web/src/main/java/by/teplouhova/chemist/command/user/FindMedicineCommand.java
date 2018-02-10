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

/**
 * The Class FindMedicineCommand.
 */
public class FindMedicineCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * The Constant PARAM_SEARCH.
     */
    private static final String PARAM_SEARCH = "medicine_name";

    /**
     * The Constant ATTR_MEDICINES.
     */
    private static final String ATTR_MEDICINES = "medicines";

    /**
     * The Constant ATTR_FLAG_FIND.
     */
    private static final String ATTR_FLAG_FIND = "flagFind";

    /**
     * The Constant ATTR_USER.
     */
    private static final String ATTR_USER = "user";

    /**
     * The Constant ATTR_MESSAGE.
     */
    private static final String ATTR_MESSAGE = "message";

    /**
     * The Constant ATTR_MESSAGE_BUNDLE.
     */
    private static final String ATTR_MESSAGE_BUNDLE = "messageBundle";

    /**
     * The service.
     */
    private MedicineService service;

    /**
     * Instantiates a new find medicine command.
     *
     * @param service the service
     */
    public FindMedicineCommand(MedicineService service) {
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
        String page = null;
        CommandResult.ResponseType responseType = FORWARD;
        ResourceBundle bundle = (ResourceBundle) content.getSessionAttribute(ATTR_MESSAGE_BUNDLE);
        String name = content.getParameter(PARAM_SEARCH);

        try {
            ArrayList<Medicine> medicines = service.getMedicinesByName(name.toUpperCase());
            content.setRequestAttributes(ATTR_FLAG_FIND, true);
            if (medicines != null) {
                content.setRequestAttributes(ATTR_MEDICINES, medicines);
            } else {
                content.setRequestAttributes(ATTR_MESSAGE, bundle.getString("message.medicines.notfound.byname") + name);
            }
            User user = (User) content.getSessionAttribute(ATTR_USER);
            if (user.getRole().equals(RoleType.CLIENT)) {
                page = PAGE_CLIENT_MAIN;

            }
            if (user.getRole().equals(RoleType.PHARMACIST)) {
                page = PAGE_PHARMACIST_MAIN;
            }

        } catch (ServiceException e) {
            content.setRequestAttributes(ATTR_MESSAGE, e.getMessage());
            LOGGER.catching(e);
            page = PAGE_ERROR;

        }
        return new CommandResult(responseType, page);
    }
}
