package by.teplouhova.chemist.command;

import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.entity.impl.RoleType;
import by.teplouhova.chemist.entity.impl.User;
import by.teplouhova.chemist.service.ServiceException;
import by.teplouhova.chemist.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.ResourceBundle;

public class FindAllClientsCommand implements Command {

    private static final Logger LOGGER= LogManager.getLogger();
    private static final String PARAM_CURRENT_PAGE="currentPage";
    private static final String ATTR_MESSAGE_BUNDLE="messageBundle";
    private static final String ATTR_COUNT_PAGES="countpages";
    private static final String ATTR_CLIENTS="clients";

    private UserService service;

    public FindAllClientsCommand(UserService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(SessionRequestContent content) {
        String page =null;
        CommandResult.ResponseType responseType ;
        ResourceBundle bundle= (ResourceBundle) content.getSessionAttribute(ATTR_MESSAGE_BUNDLE);
        int currentPage= Integer.parseInt(content.getParameter(PARAM_CURRENT_PAGE));
        int[] countPages=new int[1];
        String role= RoleType.CLIENT.getName();
        try {
            List<User> users=service.getAllClients( role,currentPage,countPages);
          if(users!=null){
              content.setRequestAttributes(ATTR_CLIENTS,users);
              content.setRequestAttributes(ATTR_COUNT_PAGES,countPages[0]);
          }else{

          }
          page=PageConstant.PAGE_DOCTOR_MAIN;
           responseType= CommandResult.ResponseType.FORWARD;
LOGGER.debug("page",page);
        } catch (Exception e) {
            responseType= CommandResult.ResponseType.REDIRECT;
            page=PageConstant.PAGE_ERROR;
            LOGGER.catching(e);
        }


        return new CommandResult(responseType,page);
    }
}
