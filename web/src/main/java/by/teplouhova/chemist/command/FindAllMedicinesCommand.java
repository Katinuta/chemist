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
import java.util.Set;

public class FindAllMedicinesCommand implements Command {
    private static final Logger LOGGER= LogManager.getLogger();
    private static final String ATTR_MEDICINES="medicines";
    private static final String PARAM_PAGE="currentpage";
    private static final String ATTR_COUNT_PAGES="countpages";
    private static final String ATTR_USER="user";

    private MedicineService service;

    public FindAllMedicinesCommand(MedicineService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(SessionRequestContent content) {
        String page =null;
        CommandResult.ResponseType responseType ;
        try {
            Integer numberPage=Integer.parseInt(content.getParameter(PARAM_PAGE));
            User user= (User) content.getSessionAttribute(ATTR_USER);
            int[] countPages=new int[1];
            ArrayList<Medicine> medicines=service.getMedicines(numberPage,countPages);
            if(medicines!=null){
                content.setRequestAttributes(ATTR_MEDICINES,medicines);
                content.setRequestAttributes(ATTR_COUNT_PAGES,countPages[0]);
            }else{
                content.setRequestAttributes("error","Medicines was not found");
            }

            if(user.getRole().equals(RoleType.CLIENT)){
                page = "/jsp/client/medicine.jsp";
            }
            if(user.getRole().equals(RoleType.PHARMACIST)){
                page = "/jsp/pharmacist/medicine.jsp";
            }
            if(user.getRole().equals(RoleType.PHARMACIST)){
                page = "/jsp/doctor/medicine.jsp";
            }

            responseType= CommandResult.ResponseType.FORWARD;
        } catch (Exception e) {
            LOGGER.log(Level.ERROR,"Error in findall command", e);
            page = "/jsp/error/error.jsp";
            responseType= CommandResult.ResponseType.REDIRECT;
        }

        return new CommandResult(responseType,page);
    }
}
