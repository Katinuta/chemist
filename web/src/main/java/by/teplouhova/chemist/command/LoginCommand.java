package by.teplouhova.chemist.command;

import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.entity.impl.Medicine;
import by.teplouhova.chemist.entity.impl.RoleType;
import by.teplouhova.chemist.service.UserService;
import by.teplouhova.chemist.service.ServiceException;
import by.teplouhova.chemist.entity.impl.User;
import by.teplouhova.chemist.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.ResourceBundle;

public class LoginCommand implements Command {

    private final static Logger LOGGER = LogManager.getLogger();

    private static final String PARAM_LOGIN = "login";
    private static final String PARAM_PASSWORD = "password";
    private static final String ATTR_USER="user";
    private static final String ATTR_CART="cart";
    private static final String ATTR_MESSAGE_BUNDLE="messageBundle";
    private static final String ATTR_ERROR="error";
    private static final String ATTR_ERROR_FORMAT="error_";


    private UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(SessionRequestContent content) {
        String page = PageConstant.PAGE_COMMON_LOGIN;
        CommandResult.ResponseType responseType=CommandResult.ResponseType.FORWARD;
        HashMap<String,String> userParams=new HashMap<>();
        try {
            userParams.put(PARAM_PASSWORD,content.getParameter(PARAM_PASSWORD));
            userParams.put(PARAM_LOGIN,content.getParameter(PARAM_LOGIN));
            ResourceBundle bundle= (ResourceBundle) content.getSessionAttribute(ATTR_MESSAGE_BUNDLE);
            UserValidator validator=new UserValidator(bundle);
            if(validator.isUserValid(userParams)){
                String login = userParams.get(PARAM_LOGIN);
                String password = userParams.get(PARAM_PASSWORD);
                if (userService.checkUser(login, password)) {
                    User user = userService.getUser(login);
                    if(user.getRole().equals(RoleType.CLIENT)){
                        page =PageConstant.PAGE_CLIENT_MAIN;
                        content.setSessionAttribute(ATTR_CART,new HashMap<Medicine,Integer>());
                    }
                    if(user.getRole().equals(RoleType.PHARMACIST)){
                        page =PageConstant.PAGE_PHARMACIST_MAIN;
                    }
                    if(user.getRole().equals(RoleType.DOCTOR)){
                        page =PageConstant.PAGE_DOCTOR_MAIN;
                    }
                    content.setSessionAttribute(ATTR_USER, user);
                    responseType= CommandResult.ResponseType.REDIRECT;
                }else{
                    content.setRequestAttributes(ATTR_ERROR,bundle.getString("message.wrong.login") );
                    userParams.entrySet()
                            .forEach(entry -> content.setRequestAttributes(entry.getKey(),entry.getValue()));
                }
            }else{
                userParams.entrySet()
                        .forEach(entry -> content.setRequestAttributes(entry.getKey(),entry.getValue()));
                content.setRequestAttributes(ATTR_ERROR,bundle.getString("message.wrong.login") );
//                validator.gerSetErrors()
//                        .forEach(entry -> content.setRequestAttributes(ATTR_ERROR_FORMAT+entry.getKey(),entry.getValue()));
            }

        } catch (ServiceException e) {
            page = PageConstant.PAGE_ERROR;
            responseType= CommandResult.ResponseType.REDIRECT;
            LOGGER.catching(e);
        }
        return new CommandResult(responseType, page);
    }
}
