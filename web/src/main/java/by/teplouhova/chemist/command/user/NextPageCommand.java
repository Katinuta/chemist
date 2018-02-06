package by.teplouhova.chemist.command.user;

import by.teplouhova.chemist.command.Command;
import by.teplouhova.chemist.command.CommandResult;
import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.entity.impl.RoleType;
import by.teplouhova.chemist.entity.impl.User;

import static by.teplouhova.chemist.command.CommandResult.ResponseType.FORWARD;
import static by.teplouhova.chemist.command.PageConstant.PAGE_CLIENT_MAIN;
import static by.teplouhova.chemist.command.PageConstant.PAGE_PHARMACIST_MAIN;

public class NextPageCommand implements Command {
    private static final String ATTR_PAGE="current_page";
    private static final String ATTR_USER="user";

    @Override
    public CommandResult execute(SessionRequestContent content) {
    String  numberPage=content.getParameter(ATTR_PAGE);
    User user= (User) content.getSessionAttribute(ATTR_USER);
    content.setRequestAttributes(ATTR_PAGE,numberPage);
    String page=null;
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
        return new CommandResult(FORWARD,page);
    }
}
