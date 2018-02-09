package by.teplouhova.chemist.command.user;

import by.teplouhova.chemist.command.Command;
import by.teplouhova.chemist.command.CommandResult;
import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.entity.impl.RoleType;
import by.teplouhova.chemist.entity.impl.User;
import by.teplouhova.chemist.validator.Validator;

import java.util.HashMap;
import java.util.ResourceBundle;

import static by.teplouhova.chemist.command.CommandResult.ResponseType.FORWARD;
import static by.teplouhova.chemist.command.PageConstant.PAGE_CLIENT_MAIN;
import static by.teplouhova.chemist.command.PageConstant.PAGE_PHARMACIST_MAIN;
import static by.teplouhova.chemist.command.PageConstant.PAGE_ERROR;

public class NextPageCommand implements Command {
    private static final String PARAM_PAGE ="current_page";
    private static final String ATTR_USER="user";
    private static final String ATTR_MESSAGE_BUNDLE="messageBundle";
    private static final String ATTR_MESSAGE_ERROR="message";

    @Override
    public CommandResult execute(SessionRequestContent content) {
        String page = null;
        CommandResult.ResponseType responseType=FORWARD;
        ResourceBundle bundle= (ResourceBundle) content.getSessionAttribute(ATTR_MESSAGE_BUNDLE);
        HashMap<String,String> params=new HashMap<>();
        params.put(PARAM_PAGE,content.getParameter(PARAM_PAGE));
        if(new Validator().isValid(params)){
            String  numberPage=content.getParameter(PARAM_PAGE);
            User user= (User) content.getSessionAttribute(ATTR_USER);
            content.setRequestAttributes(PARAM_PAGE,numberPage);
            RoleType role=user.getRole();
            switch (role){
                case CLIENT:{
                    page = PAGE_CLIENT_MAIN;
                    break;
                }
                case PHARMACIST:{
                    page = PAGE_PHARMACIST_MAIN;
                    break;
                }
            }
        }else{
            page=PAGE_ERROR;
            content.setRequestAttributes(ATTR_MESSAGE_ERROR,bundle.getString("message.parameter.page.wrong"));
        }

        return new CommandResult(responseType,page);
    }
}
