package by.teplouhova.chemist.service;

import by.teplouhova.chemist.dao.ReleaseFormDAO;
import by.teplouhova.chemist.dao.TransactionManager;
import by.teplouhova.chemist.dao.DAOException;
import by.teplouhova.chemist.dao.factory.DAOFactory;
import by.teplouhova.chemist.entity.impl.ReleaseForm;

import java.util.ArrayList;

public class ReleaseFormService {

    public ArrayList<ReleaseForm> getReleaseForms() throws ServiceException {
        TransactionManager manager=new TransactionManager();
        ReleaseFormDAO dao= DAOFactory.getDAOFactory().getReleaseFormDAO();
        manager.beginTransaction(dao);
        ArrayList<ReleaseForm> releaseForms;
        try {
            releaseForms=dao.findAll();

        } catch (DAOException e) {
            throw new ServiceException("Releases form are bot found",e);
        }finally {
            manager.endTransaction();
        }
        return releaseForms;
    }
}
