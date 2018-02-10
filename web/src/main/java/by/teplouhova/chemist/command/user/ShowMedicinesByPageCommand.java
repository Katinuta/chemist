package by.teplouhova.chemist.command.user;

import by.teplouhova.chemist.command.Command;
import by.teplouhova.chemist.command.CommandResult;
import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.entity.impl.Medicine;
import by.teplouhova.chemist.entity.impl.RoleType;
import by.teplouhova.chemist.entity.impl.User;
import by.teplouhova.chemist.service.MedicineService;
import by.teplouhova.chemist.service.ServiceException;
import by.teplouhova.chemist.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import static by.teplouhova.chemist.command.CommandResult.ResponseType.FORWARD;
import static by.teplouhova.chemist.command.CommandResult.ResponseType.REDIRECT;
import static by.teplouhova.chemist.command.PageConstant.*;

/**
 * The Class ShowMedicinesByPageCommand.
 */
public class ShowMedicinesByPageCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();

    /** The Constant ATTR_MEDICINES. */
    private static final String ATTR_MEDICINES = "medicines";

    /** The Constant PARAM_PAGE. */
    private static final String PARAM_PAGE = "current_page";

    /** The Constant ATTR_COUNT_PAGES. */
    private static final String ATTR_COUNT_PAGES = "countpages";

    /** The Constant ATTR_USER. */
    private static final String ATTR_USER = "user";

    /** The Constant ATTR_MESSAGE_BUNDLE. */
    private static final String ATTR_MESSAGE_BUNDLE = "messageBundle";

    /** The Constant ATTR_MESSAGE_ERROR. */
    private static final String ATTR_MESSAGE_ERROR = "message";

    /** The Constant ATTR_CURRENT_PAGE. */
    private static final String ATTR_CURRENT_PAGE = "page";

    /** The service. */
    private MedicineService service;

    /**
     * Instantiates a new show medicines by page command.
     *
     * @param service the service
     */
    public ShowMedicinesByPageCommand(MedicineService service) {
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
        try {
            HashMap<String, String> params = new HashMap<>();
            params.put(PARAM_PAGE, content.getParameter(PARAM_PAGE));
            if (new Validator().isValid(params)) {
                Integer numberPage = Integer.parseInt(content.getParameter(PARAM_PAGE));
                User user = (User) content.getSessionAttribute(ATTR_USER);
                ArrayList<Medicine> medicines = null;
                int[] countPages = new int[1];
                if (user.getRole().equals(RoleType.CLIENT)) {
                    medicines = service.getMedicinesPerPage(numberPage, countPages, true);
                }
                if (user.getRole().equals(RoleType.PHARMACIST)) {
                    medicines = service.getMedicinesPerPage(numberPage, countPages, false);
                }
                if (medicines != null) {
                    content.setRequestAttributes(ATTR_MEDICINES, medicines);
                    content.setRequestAttributes(ATTR_COUNT_PAGES, countPages[0]);
                    content.setRequestAttributes(ATTR_CURRENT_PAGE, numberPage);
                } else {
                    content.setRequestAttributes(ATTR_MESSAGE_ERROR, bundle.getString("message.medicines.notfound"));
                }
                if (user.getRole() == RoleType.CLIENT) {
                    page = PAGE_CLIENT_MEDICINE;
                }
                if (user.getRole() == RoleType.PHARMACIST) {
                    page = PAGE_PHARMACIST_MEDICINE;
                }
            } else {
                page = PAGE_ERROR;
                content.setRequestAttributes(ATTR_MESSAGE_ERROR, bundle.getString("message.parameter.page.wrong"));
            }


        } catch (ServiceException e) {
            page = PAGE_ERROR;
            content.setRequestAttributes(ATTR_MESSAGE_ERROR, e.getMessage());
            LOGGER.catching(e);
        }

        return new CommandResult(responseType, page);
    }
}
