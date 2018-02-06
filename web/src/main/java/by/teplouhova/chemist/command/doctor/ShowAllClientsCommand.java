package by.teplouhova.chemist.command.doctor;

import by.teplouhova.chemist.command.Command;
import by.teplouhova.chemist.command.CommandResult;
import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.entity.impl.RoleType;
import by.teplouhova.chemist.entity.impl.User;
import by.teplouhova.chemist.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.ResourceBundle;

import static by.teplouhova.chemist.command.CommandResult.ResponseType.FORWARD;
import static by.teplouhova.chemist.command.CommandResult.ResponseType.REDIRECT;
import static by.teplouhova.chemist.command.PageConstant.PAGE_DOCTOR_MAIN;
import static by.teplouhova.chemist.command.PageConstant.PAGE_ERROR;

public class ShowAllClientsCommand implements Command {

    private static final Logger LOGGER= LogManager.getLogger();
    private static final String PARAM_CURRENT_PAGE="current_page";
    private static final String ATTR_MESSAGE_BUNDLE="messageBundle";
    private static final String ATTR_COUNT_PAGES="countpages";
    private static final String ATTR_CLIENTS="clients";

    private UserService service;

    public ShowAllClientsCommand(UserService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(SessionRequestContent content) {
        String page;
        CommandResult.ResponseType responseType ;
        ResourceBundle bundle= (ResourceBundle) content.getSessionAttribute(ATTR_MESSAGE_BUNDLE);
        int currentPage= Integer.parseInt(content.getParameter(PARAM_CURRENT_PAGE));
        int[] countPages=new int[1];
        String role= RoleType.CLIENT.getName();
        try {
            List<User> users=service.getUserByRoleByPage( role,currentPage,countPages);
          if(users!=null){
              content.setRequestAttributes(ATTR_CLIENTS,users);
              content.setRequestAttributes(ATTR_COUNT_PAGES,countPages[0]);
          }else{
//todo
          }
          page= PAGE_DOCTOR_MAIN;
           responseType=FORWARD;
        } catch (Exception e) {
            //todo message
            responseType= REDIRECT;
            page=PAGE_ERROR;
            LOGGER.catching(e);
        }


        return new CommandResult(responseType,page);
    }
}
