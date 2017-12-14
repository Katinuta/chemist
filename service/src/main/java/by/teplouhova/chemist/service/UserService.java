package by.teplouhova.chemist.service;

import by.teplouhova.chemist.dao.TransactionManager;
import by.teplouhova.chemist.dao.exception.DAOException;
import by.teplouhova.chemist.service.exception.ServiceException;
import by.teplouhova.chemist.entity.impl.User;
import by.teplouhova.chemist.dao.impl.UserDAO;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserService {
    private final static Logger LOGGER= LogManager.getLogger();
    public boolean checkUser(String login, String password) throws ServiceException {
        User user;
        TransactionManager transaction;

        try {
            UserDAO userDAO=new UserDAO();
           transaction= new TransactionManager();
           transaction.beginTransaction(userDAO );
            user = userDAO.findByLoginPassword(login, password);
            transaction.endTransaction();

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return user != null ? true : false;
    }
    public User getUser(String login) throws ServiceException {
        User user;
        TransactionManager transaction;
        try {
            UserDAO userDAO=new UserDAO();
            transaction= new TransactionManager();
            transaction.beginTransaction(userDAO );
            LOGGER.log(Level.INFO,"begin");
            user = userDAO.findByLogin(login);
            LOGGER.log(Level.INFO,"end");
            transaction.endTransaction();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return user ;
    }
}
