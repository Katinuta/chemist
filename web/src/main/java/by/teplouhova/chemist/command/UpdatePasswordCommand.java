package by.teplouhova.chemist.command;

import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.entity.impl.User;
import by.teplouhova.chemist.service.ServiceException;
import by.teplouhova.chemist.service.UserService;
import by.teplouhova.chemist.validator.Validator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ResourceBundle;

public class UpdatePasswordCommand implements Command {
    private static final Logger LOGGER= LogManager.getLogger();
    private static final String PARAM_PASSWORD="password";
    private static final String PARAM_NEW_PASSWORD="newPassword";
    private static final String ATTR_ERROR_PASS="error_password";
    private static final String ATTR_ERROR_PASS_NEW="error_newPassword";
    private static final String ATTR_USER="user";
    private static final String ATTR_MESSAGE_BUNDLE="messageBundle";

    private UserService userService;

    public UpdatePasswordCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(SessionRequestContent content) {
        String page = PageConstant.PAGE_COMMON_EDIT_PASSWORD;
        CommandResult.ResponseType responseType = CommandResult.ResponseType.FORWARD;
        User user = (User) content.getSessionAttribute(ATTR_USER);
        ResourceBundle bundle= (ResourceBundle) content.getSessionAttribute(ATTR_MESSAGE_BUNDLE);
        String password = content.getParameter(PARAM_PASSWORD);
        String newPassword = content.getParameter(PARAM_NEW_PASSWORD);
        try {
            if (password != null&&!password.isEmpty() ) {
                if (userService.checkUser(user.getLogin(), password)) {
                    if(new Validator().validateRequired(newPassword,Validator.REGEXP_PASSWORD)){
                        user.setPassword(newPassword);
                        userService.update(user);
                        page=PageConstant.PAGE_UPDATE_PASSWORD_SUCCESS;
                        responseType= CommandResult.ResponseType.REDIRECT;
                    }else{
                        content.setRequestAttributes(ATTR_ERROR_PASS_NEW,bundle.getString("message.new.password.wrong"));
                    }
                }else{
                    content.setRequestAttributes(ATTR_ERROR_PASS,bundle.getString("message.password.wrong"));
                }
            } else {
                    content.setRequestAttributes(ATTR_ERROR_PASS,bundle.getString("message.password.null"));
            }


        } catch (ServiceException e) {
            page=PageConstant.PAGE_ERROR;
            responseType= CommandResult.ResponseType.REDIRECT;
            LOGGER.log(Level.ERROR,e);

        }
        return new CommandResult(responseType, page);
    }
    }
