package by.teplouhova.chemist.service;

import by.teplouhova.chemist.dao.ProducerDAO;
import by.teplouhova.chemist.dao.TransactionManager;
import by.teplouhova.chemist.dao.exception.DAOException;
import by.teplouhova.chemist.dao.factory.DAOFactory;
import by.teplouhova.chemist.entity.impl.Producer;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashSet;

public class ProducerService {
    private static final Logger LOGGER = LogManager.getLogger();

    public ArrayList<Producer> getProdusers() throws ServiceException {
        ArrayList<Producer> producers;
        TransactionManager manager=new TransactionManager();
        ProducerDAO producerDAO = DAOFactory.getDAOFactory().getProducerDAO();
        manager.beginTransaction(producerDAO);
        try {
            producers=producerDAO.findAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }finally {
            manager.endTransaction();
        }
        return producers;
    }
}
