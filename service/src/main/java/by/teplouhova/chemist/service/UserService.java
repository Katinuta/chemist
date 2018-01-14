package by.teplouhova.chemist.service;

import by.teplouhova.chemist.dao.OrderDAO;
import by.teplouhova.chemist.dao.TransactionManager;
import by.teplouhova.chemist.dao.UserDAO;
import by.teplouhova.chemist.dao.exception.DAOException;
import by.teplouhova.chemist.dao.factory.DAOFactory;
import by.teplouhova.chemist.entity.impl.RoleType;
import by.teplouhova.chemist.entity.impl.Order;
import by.teplouhova.chemist.entity.impl.User;
import by.teplouhova.chemist.dao.mysql.MySqlUserDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;

public class UserService {
    private final static Logger LOGGER = LogManager.getLogger();

    public boolean checkUser(String login, String password) throws ServiceException {
        User user;
        TransactionManager transaction;

        try {
            UserDAO userDAO = DAOFactory.getDAOFactory().getUserDAO();
            transaction = new TransactionManager();
            transaction.beginTransaction(userDAO);
            user = userDAO.findByLoginPassword(login, password);
            transaction.endTransaction();

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return user != null ? true : false;
    }

    public User getUser(String login) throws ServiceException {
        User user;

        try {
            MySqlUserDAO mySqlUserDAO = new MySqlUserDAO();
            TransactionManager transaction = new TransactionManager();
            transaction.beginTransaction(mySqlUserDAO);
            user = mySqlUserDAO.findByLogin(login);
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
            UserDAO userDAO = DAOFactory.getDAOFactory().getUserDAO();
            TransactionManager transaction = new TransactionManager();
            transaction.beginTransaction(userDAO);
            userDAO.create(new User(name, surname, login, password, account, RoleType.CLIENT, phone));
            user = userDAO.findByLogin(login);
            transaction.endTransaction();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return user;
    }

    public List<Order> getOrders(){

        return null;
    }

    public Order buyMedicine(){
        TransactionManager manager=new TransactionManager();
        OrderDAO orderDAO=DAOFactory.getDAOFactory().getOrderDAO();

        return null;
    }
}
