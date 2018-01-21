package by.teplouhova.chemist.dao;

import by.teplouhova.chemist.dao.AbstractDAO;
import by.teplouhova.chemist.dao.exception.DAOException;
import by.teplouhova.chemist.entity.impl.User;

import java.math.BigDecimal;

public abstract class UserDAO extends AbstractDAO<User> {
    public abstract User findByLoginPassword(String login, String password) throws DAOException;
    public abstract User findByLogin(String login) throws DAOException;
    public abstract BigDecimal findBalanceByUserId(long userId) throws DAOException;
}
