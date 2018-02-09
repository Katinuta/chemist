package by.teplouhova.chemist.dao;

import by.teplouhova.chemist.entity.impl.User;

import java.math.BigDecimal;
import java.util.List;

/**
 * The Class UserDAO.
 */
public abstract class UserDAO extends AbstractDAO<User> {

    /**
     * Find by login password.
     *
     * @param login the login
     * @param password the password
     * @return the user
     * @throws DAOException the DAO exception
     */
    public abstract User findByLoginPassword(String login, String password) throws DAOException;

    /**
     * Find by login.
     *
     * @param login the login
     * @return the user
     * @throws DAOException the DAO exception
     */
    public abstract User findByLogin(String login) throws DAOException;

    /**
     * Find balance by user id.
     *
     * @param userId the user id
     * @return the big decimal
     * @throws DAOException the DAO exception
     */
    public abstract BigDecimal findBalanceByUserId(long userId) throws DAOException;

    /**
     * Find by role.
     *
     * @param role the role
     * @param begin the begin
     * @param end the end
     * @return the list
     * @throws DAOException the DAO exception
     */
    public abstract List<User> findByRole(String role,int begin, int end) throws DAOException;

    /**
     * Gets the count by role.
     *
     * @param role the role
     * @return the count by role
     * @throws DAOException the DAO exception
     */
    public abstract int getCountByRole(String role) throws DAOException;

    /**
     * Update password.
     *
     * @param entity the entity
     * @throws DAOException the DAO exception
     */
    public abstract void updatePassword(User entity) throws DAOException;
}
