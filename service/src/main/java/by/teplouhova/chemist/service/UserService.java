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

/**
 * The Class UserService.
 */
public class UserService {

    /**
     * The Constant RECORDS_PER_PAGE.
     */
    private static final int RECORDS_PER_PAGE = 10;

    /**
     * The user DAO.
     */
    private UserDAO userDAO;

    /**
     * Instantiates a new user service.
     */
    public UserService() {
        userDAO = DAOFactory.getDAOFactory().getUserDAO();
    }

    /**
     * Check user.
     *
     * @param login    the login
     * @param password the password
     * @return true, if successful
     * @throws ServiceException the service exception
     */
    public boolean checkUser(String login, String password) throws ServiceException {
        User user;
        TransactionManager transaction = new TransactionManager();
        try {
            transaction.beginTransaction(userDAO);
            user = userDAO.findByLoginPassword(login, password);
        } catch (DAOException e) {
            throw new ServiceException("User is not found by login" + login, e);
        } finally {
            transaction.endTransaction();
        }
        return user != null ? true : false;
    }

    /**
     * Gets the user.
     *
     * @param login the login
     * @return the user
     * @throws ServiceException the service exception
     */
    public User getUser(String login) throws ServiceException {
        User user;
        TransactionManager transaction = new TransactionManager();
        try {
            transaction.beginTransaction(userDAO);
            user = userDAO.findByLogin(login);
        } catch (DAOException e) {
            throw new ServiceException("User is not found by login" + login, e);
        } finally {
            transaction.endTransaction();
        }
        return user;
    }

    /**
     * Creates the user.
     *
     * @param user the user
     * @return the user
     * @throws ServiceException the service exception
     */
    public User createUser(User user) throws ServiceException {

        TransactionManager transaction = new TransactionManager();
        transaction.beginTransaction(userDAO);
        try {
            userDAO.create(user);
        } catch (DAOException e) {
            throw new ServiceException("User is not added", e);
        } finally {
            transaction.endTransaction();
        }
        return user;
    }


    /**
     * Gets the user by role by page.
     *
     * @param role        the role
     * @param currentPage the current page
     * @param countPages  the count pages
     * @return the user by role by page
     * @throws ServiceException the service exception
     */
    public List<User> getUserByRoleByPage(String role, int currentPage, int[] countPages) throws ServiceException {
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(userDAO);
        List<User> clients;
        try {
            int begin = (currentPage - 1) * RECORDS_PER_PAGE;
            clients = userDAO.findByRole(role, begin, RECORDS_PER_PAGE);
            if (clients == null) {
                throw new ServiceException("Users are not found by role: " + role);
            }
            countPages[0] = (int) Math.ceil(userDAO.getCountByRole(role) / (double) RECORDS_PER_PAGE);
        } catch (DAOException e) {
            throw new ServiceException("Users are not found by role: " + role);
        } finally {
            manager.endTransaction();
        }
        return clients;
    }

    /**
     * Gets the by id.
     *
     * @param userId the user id
     * @return the by id
     * @throws ServiceException the service exception
     */
    public User getById(long userId) throws ServiceException {
        User user;
        TransactionManager transaction = new TransactionManager();
        try {
            transaction.beginTransaction(userDAO);
            user = userDAO.findById(userId);
            if (user == null) {
                throw new ServiceException("User is not found by id " + userId);
            }
            user.setAccount(new BigDecimal(0));
            user.setPassword("");
        } catch (DAOException e) {
            throw new ServiceException("User is not found by id " + userId, e);
        } finally {
            transaction.endTransaction();
        }
        return user;
    }

    /**
     * Update.
     *
     * @param user             the user
     * @param isPasswordUpdate the is password update
     * @throws ServiceException the service exception
     */
    public void update(User user, boolean isPasswordUpdate) throws ServiceException {
        TransactionManager transaction = new TransactionManager();
        try {
            transaction.beginTransaction(userDAO);
            User oldUser = userDAO.findById(user.getUserId());
            if (oldUser == null) {
                throw new ServiceException("User is not found by id " + user.getUserId());
            }
            if (isPasswordUpdate) {
                oldUser.setPassword(user.getPassword());
                userDAO.updatePassword(oldUser);
            } else {
                userDAO.update(user);
            }
        } catch (DAOException e) {
            throw new ServiceException("User data is not updated", e);
        } finally {
            transaction.endTransaction();
        }
    }
}
