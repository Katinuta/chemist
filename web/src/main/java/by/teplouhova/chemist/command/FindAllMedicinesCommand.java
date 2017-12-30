package by.teplouhova.chemist.command;

import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.dao.MedicineDAO;
import by.teplouhova.chemist.dao.TransactionManager;
import by.teplouhova.chemist.dao.exception.DAOException;
import by.teplouhova.chemist.dao.factory.DAOFactory;
import by.teplouhova.chemist.dao.mysql.MySqlMedicineDAO;
import by.teplouhova.chemist.entity.impl.Medicine;
import by.teplouhova.chemist.service.MedicineService;
import by.teplouhova.chemist.service.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class FindAllMedicinesCommand implements Command {
    private static final Logger LOGGER= LogManager.getLogger();
    private static final String ATTR_MEDICINES="medicines";
    private MedicineService service;

    public FindAllMedicinesCommand(MedicineService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(SessionRequestContent content) {
        String page ;
        CommandResult.ResponseType responseType ;
        try {
            Set<Medicine> medicines=service.getMedicines();
            if(medicines!=null){

                content.setRequestAttributes(ATTR_MEDICINES,medicines);
            }else{
                content.setRequestAttributes("error","Medicines was not found");
            }
            page="/jsp/client/medicines.jsp";
            responseType= CommandResult.ResponseType.FORWARD;
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR,"Error in findall command" +e);
            page = "/jsp/error/error.jsp";
            responseType= CommandResult.ResponseType.REDIRECT;
        }

        return new CommandResult(responseType,page);
    }
}
