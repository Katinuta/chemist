package by.teplouhova.chemist.service;

import by.teplouhova.chemist.dao.ProducerDAO;
import by.teplouhova.chemist.dao.TransactionManager;
import by.teplouhova.chemist.dao.DAOException;
import by.teplouhova.chemist.dao.factory.DAOFactory;
import by.teplouhova.chemist.entity.impl.Producer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class ProducerService {

    public ArrayList<Producer> getProdusers() throws ServiceException {
        ArrayList<Producer> producers;
        TransactionManager manager=new TransactionManager();
        ProducerDAO producerDAO = DAOFactory.getDAOFactory().getProducerDAO();
        manager.beginTransaction(producerDAO);
        try {
            producers=producerDAO.findAll();
            if(producers==null){
                throw new ServiceException("Producers are not found");
            }
        } catch (DAOException e) {
            throw new ServiceException("Producers are not found",e);
        }finally {
            manager.endTransaction();
        }
        return producers;
    }
}
