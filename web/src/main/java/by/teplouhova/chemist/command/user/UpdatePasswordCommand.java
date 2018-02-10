package by.teplouhova.chemist.command.user;

import by.teplouhova.chemist.command.Command;
import by.teplouhova.chemist.command.CommandResult;
import by.teplouhova.chemist.command.PageConstant;
import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.entity.impl.User;
import by.teplouhova.chemist.service.ServiceException;
import by.teplouhova.chemist.service.UserService;
import by.teplouhova.chemist.validator.RegexpConstant;
import by.teplouhova.chemist.validator.Validator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.ResourceBundle;

import static by.teplouhova.chemist.command.CommandResult.ResponseType.FORWARD;
import static by.teplouhova.chemist.command.CommandResult.ResponseType.REDIRECT;
import static by.teplouhova.chemist.command.PageConstant.PAGE_COMMON_EDIT_PASSWORD;
import static by.teplouhova.chemist.command.PageConstant.PAGE_UPDATE_PASSWORD_SUCCESS;

public class UpdatePasswordCommand implements Command {

    private static final Logger LOGGER= LogManager.getLogger();

    /** The Constant PARAM_PASSWORD. */
    private static final String PARAM_PASSWORD="password";

    /** The Constant PARAM_NEW_PASSWORD. */
    private static final String PARAM_NEW_PASSWORD="new_password";

    /** The Constant ATTR_ERROR. */
    private static final String ATTR_ERROR="error_";

    /** The Constant ATTR_USER. */
    private static final String ATTR_USER="user";

    /** The Constant ATTR_MESSAGE_BUNDLE. */
    private static final String ATTR_MESSAGE_BUNDLE="messageBundle";

    /** The Constant ATTR_MESSAGE_ERROR. */
    private static final String ATTR_MESSAGE_ERROR="message";

    /** The user service. */
    private UserService userService;

    /**
     * Instantiates a new update password command.
     *
     * @param userService the user service
     */
    public UpdatePasswordCommand(UserService userService) {
        this.userService = userService;
    }

    /**
     * Execute.
     *
     * @param content the content
     * @return the command result
     */
    @Override
    public CommandResult execute(SessionRequestContent content) {
        String page =PAGE_COMMON_EDIT_PASSWORD;
        CommandResult.ResponseType responseType = FORWARD;
        User user = (User) content.getSessionAttribute(ATTR_USER);
        ResourceBundle bundle= (ResourceBundle) content.getSessionAttribute(ATTR_MESSAGE_BUNDLE);
        String password = content.getParameter(PARAM_PASSWORD);
        String newPassword = content.getParameter(PARAM_NEW_PASSWORD);
        HashMap<String,String> params=new HashMap<>();
        params.put(PARAM_NEW_PASSWORD,newPassword);
        Validator validator=new Validator(bundle);
        try {
            if (validator.isValid(params)) {
                if (userService.checkUser(user.getLogin(), password)) {
                        user.setPassword(newPassword);
                        userService.update(user,true);
                        page=PAGE_UPDATE_PASSWORD_SUCCESS;
                        responseType= REDIRECT;
                }else{
                    content.setRequestAttributes(ATTR_ERROR,bundle.getString("message.password.wrong"));
                }
            } else {
                content.getParameterNames()
                        .forEach(nameParam->content.setRequestAttributes(nameParam,content.getParameter(nameParam)));
                validator.getEntrySetErrors()
                        .forEach(entry->content.setRequestAttributes(ATTR_ERROR+entry.getKey(),entry.getValue()));
            }

        } catch (ServiceException e) {
            page=PageConstant.PAGE_ERROR;
            content.setRequestAttributes(ATTR_MESSAGE_ERROR,e.getMessage());
            LOGGER.catching(e);

        }
        return new CommandResult(responseType, page);
    }
    }
