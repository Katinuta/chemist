package by.teplouhova.chemist.command;

import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.dao.MedicineDAO;
import by.teplouhova.chemist.dao.TransactionManager;
import by.teplouhova.chemist.dao.exception.DAOException;
import by.teplouhova.chemist.dao.factory.DAOFactory;
import by.teplouhova.chemist.dao.mysql.MySqlMedicineDAO;
import by.teplouhova.chemist.entity.impl.Medicine;
import by.teplouhova.chemist.entity.impl.RoleType;
import by.teplouhova.chemist.entity.impl.User;
import by.teplouhova.chemist.service.MedicineService;
import by.teplouhova.chemist.service.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Set;

public class FindAllMedicinesCommand implements Command {
    private static final Logger LOGGER= LogManager.getLogger();
    private static final String ATTR_MEDICINES="medicines";
    private static final String PARAM_PAGE="currentpage";
    private static final String ATTR_COUNT_PAGES="countpages";
    private static final String ATTR_USER="user";
    private static final String ATTR_MESSAGE_BUNDLE="messageBundle";
    private static final String ATTR_ERROR="error";
    private static final String ATTR_CURRENT_PAGE="page";

    private MedicineService service;

    public FindAllMedicinesCommand(MedicineService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(SessionRequestContent content) {
        String page =null;
        CommandResult.ResponseType responseType ;
        ResourceBundle bundle= (ResourceBundle) content.getSessionAttribute(ATTR_MESSAGE_BUNDLE);
        try {
            Integer numberPage=Integer.parseInt(content.getParameter(PARAM_PAGE));
            User user= (User) content.getSessionAttribute(ATTR_USER);
            ArrayList<Medicine> medicines=null;
            int[] countPages=new int[1];
            if(user.getRole().equals(RoleType.CLIENT)){
                medicines=service.getMedicines(numberPage,countPages,true);
            }
            if(user.getRole().equals(RoleType.PHARMACIST)){
                medicines=service.getMedicines(numberPage,countPages,false);
            }
            if(medicines!=null){
                content.setRequestAttributes(ATTR_MEDICINES,medicines);
                content.setRequestAttributes(ATTR_COUNT_PAGES,countPages[0]);
                content.setRequestAttributes(ATTR_CURRENT_PAGE,numberPage);
            }else{
                content.setRequestAttributes(ATTR_ERROR,bundle.getString("message.medicines.notfound"));
            }
            if(user.getRole()==RoleType.CLIENT){
                page = PageConstant.PAGE_CLIENT_MEDICINE;
            }
            if(user.getRole()==RoleType.PHARMACIST){
                page = PageConstant.PAGE_PHARMACIST_MEDICINE;
            }
            LOGGER.log(Level.DEBUG,countPages[0]);
            responseType= CommandResult.ResponseType.FORWARD;
        } catch (Exception e) {
            page = PageConstant.PAGE_ERROR;
            responseType= CommandResult.ResponseType.REDIRECT;
            LOGGER.log(Level.ERROR,"Error in findall command", e);
        }

        return new CommandResult(responseType,page);
    }
}
