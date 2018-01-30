package by.teplouhova.chemist.service;

import by.teplouhova.chemist.dao.TransactionManager;
import by.teplouhova.chemist.dao.UserDAO;
import by.teplouhova.chemist.dao.exception.DAOException;
import by.teplouhova.chemist.dao.factory.DAOFactory;
import by.teplouhova.chemist.entity.impl.Order;
import by.teplouhova.chemist.entity.impl.User;
import by.teplouhova.chemist.dao.mysql.MySqlUserDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;

public class UserService {
    private final static Logger LOGGER = LogManager.getLogger();
    private  static final int  RECORDS_PER_PAGE=10;

    private UserDAO userDAO;

    public UserService() {
        userDAO = DAOFactory.getDAOFactory().getUserDAO();
    }

    public boolean checkUser(String login, String password) throws ServiceException {
        User user;
        TransactionManager transaction = new TransactionManager();
        try {
            transaction.beginTransaction(userDAO);
            user = userDAO.findByLoginPassword(login, password);
            transaction.endTransaction();
        } catch (DAOException e) {
            throw new ServiceException("Exception in method checkUser ",e);
        }finally {
            transaction.endTransaction();
        }
        return user != null ? true : false;
    }

    public User getUser(String login) throws ServiceException {
        User user;
        TransactionManager transaction = new TransactionManager();
        try {
            UserDAO userDAO = DAOFactory.getDAOFactory().getUserDAO();
            transaction.beginTransaction(userDAO);
            user = userDAO.findByLogin(login);
            transaction.endTransaction();
        } catch (DAOException e) {
            throw new ServiceException("Exception in getUser method ",e);
        }finally {
            transaction.endTransaction();
        }
        return user;
    }

    public User createUser(User user) throws ServiceException {

            UserDAO userDAO = DAOFactory.getDAOFactory().getUserDAO();
            TransactionManager transaction = new TransactionManager();
            transaction.beginTransaction(userDAO);
        try {
            userDAO.create(user);
            transaction.endTransaction();
        } catch (DAOException e) {
            throw new ServiceException("Exception in createUser method ",e);
        }finally {
            transaction.endTransaction();
        }
        return user;
    }

    public List<Order> getOrders(){

        return null;
    }

    public BigDecimal getBalanceAccount(long userId) throws ServiceException {
        TransactionManager manager=new TransactionManager();
        UserDAO userDAO=DAOFactory.getDAOFactory().getUserDAO();
        manager.beginTransaction(userDAO);
        BigDecimal balance;
        try {
            balance=  userDAO.findBalanceByUserId(userId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }finally {
            manager.endTransaction();
        }
        return balance;
    }

    public List<User> getAllClients(String role,int currentPage,int[] countPages) throws ServiceException {
        TransactionManager manager=new TransactionManager();
        UserDAO userDAO=DAOFactory.getDAOFactory().getUserDAO();
        manager.beginTransaction(userDAO);
        List<User> clients;
        try {
            int begin=(currentPage-1)*RECORDS_PER_PAGE;
            clients=  userDAO.findByRole(role,begin,RECORDS_PER_PAGE);
            if(clients==null){
                throw new ServiceException("Clients are not found");
            }
            countPages[0]= (int) Math.ceil(userDAO.getCountByRole(role)/(double)RECORDS_PER_PAGE);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }finally {
            manager.endTransaction();
        }

        return  clients;
    }

    public User getById(long userId) throws ServiceException {
        User user;
        TransactionManager transaction = new TransactionManager();
        try {
            UserDAO userDAO = DAOFactory.getDAOFactory().getUserDAO();
            transaction.beginTransaction(userDAO);
            user = userDAO.findById(userId);
            if(user==null){
                throw new ServiceException("User is not found by id "+ userId);
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }finally {
            transaction.endTransaction();
        }
        return user;
    }


    public void update(User user) throws ServiceException {
        TransactionManager transaction = new TransactionManager();
        try {
            UserDAO userDAO = DAOFactory.getDAOFactory().getUserDAO();
            transaction.beginTransaction(userDAO);
            userDAO.update(user);
        } catch (DAOException e) {
            throw new ServiceException("Exception in method update",e);
        }finally {
            transaction.endTransaction();
        }
    }
}
