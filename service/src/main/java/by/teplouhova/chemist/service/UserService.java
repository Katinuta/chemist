package by.teplouhova.chemist.service;

import by.teplouhova.chemist.dao.TransactionManager;
import by.teplouhova.chemist.dao.UserDAO;
import by.teplouhova.chemist.dao.DAOException;
import by.teplouhova.chemist.dao.factory.DAOFactory;
import by.teplouhova.chemist.entity.impl.User;
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
            throw new ServiceException("User is not found by login" + login,e);
        }finally {
            transaction.endTransaction();
        }
        return user != null ? true : false;
    }

    public User getUser(String login) throws ServiceException {
        User user;
        TransactionManager transaction = new TransactionManager();
        try {
            transaction.beginTransaction(userDAO);
            user = userDAO.findByLogin(login);
            transaction.endTransaction();
        } catch (DAOException e) {
            throw new ServiceException("User is not found by login" + login,e);
        }finally {
            transaction.endTransaction();
        }
        return user;
    }

    public User createUser(User user) throws ServiceException {

            TransactionManager transaction = new TransactionManager();
            transaction.beginTransaction(userDAO);
        try {
            userDAO.create(user);
            transaction.endTransaction();
        } catch (DAOException e) {
            throw new ServiceException("User is not added",e);
        }finally {
            transaction.endTransaction();
        }
        return user;
    }



    public List<User> getUserByRoleByPage(String role, int currentPage, int[] countPages) throws ServiceException {
        TransactionManager manager=new TransactionManager();
        manager.beginTransaction(userDAO);
        List<User> clients;
        try {
            int begin=(currentPage-1)*RECORDS_PER_PAGE;
            clients=  userDAO.findByRole(role,begin,RECORDS_PER_PAGE);
            if(clients==null){
                throw new ServiceException("Users are not found by role: "+role);
            }
            countPages[0]= (int) Math.ceil(userDAO.getCountByRole(role)/(double)RECORDS_PER_PAGE);
        } catch (DAOException e) {
            throw new ServiceException("Users are not found by role: "+role);
        }finally {
            manager.endTransaction();
        }
        return  clients;
    }

    public User getById(long userId) throws ServiceException {
        User user;
        TransactionManager transaction = new TransactionManager();
        try {
            transaction.beginTransaction(userDAO);
            user = userDAO.findById(userId);
            if(user==null){
                throw new ServiceException("User is not found by id "+ userId);
            }
            user.setAccount(new BigDecimal(0));
            user.setPassword("");
        } catch (DAOException e) {
            throw new ServiceException("User is not found by id "+ userId,e);
        }finally {
            transaction.endTransaction();
        }
        return user;
    }


    public void update(User user,boolean isPasswordUpdate) throws ServiceException {
        TransactionManager transaction = new TransactionManager();
        try {
            transaction.beginTransaction(userDAO);
            User oldUser=userDAO.findById(user.getUserId());
            if(oldUser==null){
                throw new ServiceException("User is not found by id "+user.getUserId());
            }
            if(isPasswordUpdate){
                oldUser.setPassword(user.getPassword());
                userDAO.updatePassword(oldUser);
            }else{
                userDAO.update(user);
            }
        } catch (DAOException e) {
            throw new ServiceException("User data is not updated",e);
        }finally {
            transaction.endTransaction();
        }
    }
}
