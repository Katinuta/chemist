package by.teplouhova.chemist.command.common;

import by.teplouhova.chemist.command.Command;
import by.teplouhova.chemist.command.CommandResult;
import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.entity.impl.Medicine;
import by.teplouhova.chemist.entity.impl.RoleType;
import by.teplouhova.chemist.entity.impl.User;
import by.teplouhova.chemist.service.ServiceException;
import by.teplouhova.chemist.service.UserService;
import by.teplouhova.chemist.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.ResourceBundle;

import static by.teplouhova.chemist.command.CommandResult.ResponseType.FORWARD;
import static by.teplouhova.chemist.command.CommandResult.ResponseType.REDIRECT;
import static by.teplouhova.chemist.command.PageConstant.*;


public class SignInCommand implements Command {

    private final static Logger LOGGER = LogManager.getLogger();

    private static final String PARAM_LOGIN = "login";
    private static final String PARAM_PASSWORD = "password";
    private static final String ATTR_USER="user";
    private static final String ATTR_CART="cart";
    private static final String ATTR_MESSAGE_BUNDLE="messageBundle";
    private static final String ATTR_MESSAGE_ERROR="message";


    private UserService userService;

    public SignInCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(SessionRequestContent content) {
        String page = PAGE_COMMON_LOGIN;
        CommandResult.ResponseType responseType=FORWARD;
        HashMap<String,String> userParams=new HashMap<>();
        try {
            content.getParameterNames()
                    .forEach(key->userParams.put(key,content.getParameter(key)));
            ResourceBundle bundle= (ResourceBundle) content.getSessionAttribute(ATTR_MESSAGE_BUNDLE);
            Validator validator=new Validator(bundle);
            if(validator.isValid(userParams)){
                String login = userParams.get(PARAM_LOGIN);
                String password = userParams.get(PARAM_PASSWORD);
                if (userService.checkUser(login, password)) {
                    User user = userService.getUser(login);
                    if(user.getRole().equals(RoleType.CLIENT)){
                        page =PAGE_CLIENT_MAIN;
                        content.setSessionAttribute(ATTR_CART,new HashMap<Medicine,Integer>());
                    }
                    if(user.getRole().equals(RoleType.PHARMACIST)){
                        page =PAGE_PHARMACIST_MAIN;
                    }
                    if(user.getRole().equals(RoleType.DOCTOR)){
                        page =PAGE_DOCTOR_MAIN;
                    }
                    content.setSessionAttribute(ATTR_USER, user);
                    responseType=REDIRECT;
                }else{
                    content.setRequestAttributes(ATTR_MESSAGE_ERROR,bundle.getString("message.wrong.login") );
                    userParams.entrySet()
                            .forEach(entry -> content.setRequestAttributes(entry.getKey(),entry.getValue()));
                }
            }else{
                userParams.entrySet()
                        .forEach(entry -> content.setRequestAttributes(entry.getKey(),entry.getValue()));
                content.setRequestAttributes(ATTR_MESSAGE_ERROR,bundle.getString("message.wrong.login") );
            }

        } catch (ServiceException e) {
            //todo
            page = PAGE_ERROR;
            responseType= REDIRECT;
            LOGGER.catching(e);
        }
        return new CommandResult(responseType, page);
    }
}
