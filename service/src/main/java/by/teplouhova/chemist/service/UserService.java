package by.teplouhova.chemist.service;

import by.teplouhova.chemist.dao.TransactionManager;
import by.teplouhova.chemist.dao.exception.DAOException;
import by.teplouhova.chemist.entity.RoleType;
import by.teplouhova.chemist.entity.impl.User;
import by.teplouhova.chemist.dao.impl.UserDAOImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

public class UserService {
    private final static Logger LOGGER = LogManager.getLogger();

    public boolean checkUser(String login, String password) throws ServiceException {
        User user;
        TransactionManager transaction;

        try {
            UserDAOImpl userDAOImpl = new UserDAOImpl();
            transaction = new TransactionManager();
            transaction.beginTransaction(userDAOImpl);
            user = userDAOImpl.findByLoginPassword(login, password);
            transaction.endTransaction();

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return user != null ? true : false;
    }

    public User getUser(String login) throws ServiceException {
        User user;

        try {
            UserDAOImpl userDAOImpl = new UserDAOImpl();
            TransactionManager transaction = new TransactionManager();
            transaction.beginTransaction(userDAOImpl);
            user = userDAOImpl.findByLogin(login);
            transaction.endTransaction();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return user;
    }

    public User createUser(String name, String surname, BigDecimal account,
                           String login, String password, String phone) throws ServiceException {

        User user = null;
        try {
//            User user = new User(name, surname, login, password, account, RoleType.CLIENT, phone);
            UserDAOImpl userDAOImpl = new UserDAOImpl();
            TransactionManager transaction = new TransactionManager();
            transaction.beginTransaction(userDAOImpl);
            userDAOImpl.create(new User(name, surname, login, password, account, RoleType.CLIENT, phone));
            user = userDAOImpl.findByLogin(login);
            transaction.endTransaction();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return user;
    }
}
