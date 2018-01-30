package by.teplouhova.chemist.command;

import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.entity.impl.RoleType;
import by.teplouhova.chemist.entity.impl.User;

public class NextPageCommand implements Command {
    private static final String ATTR_PAGE="currentpage";
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
            page = PageConstant.PAGE_CLIENT_MAIN;
            break;
        }
        case PHARMACIST:{
            page = PageConstant.PAGE_PHARMACIST_MAIN;
            break;
        }
    }

        return new CommandResult(CommandResult.ResponseType.FORWARD,page);
    }
}
