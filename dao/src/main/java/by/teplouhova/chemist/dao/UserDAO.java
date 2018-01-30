package by.teplouhova.chemist.dao;

import by.teplouhova.chemist.dao.AbstractDAO;
import by.teplouhova.chemist.dao.exception.DAOException;
import by.teplouhova.chemist.entity.impl.RoleType;
import by.teplouhova.chemist.entity.impl.User;

import java.math.BigDecimal;
import java.util.List;

public abstract class UserDAO extends AbstractDAO<User> {
    public abstract User findByLoginPassword(String login, String password) throws DAOException;
    public abstract User findByLogin(String login) throws DAOException;
    public abstract BigDecimal findBalanceByUserId(long userId) throws DAOException;
    public abstract List<User> findByRole(String role,int begin, int end) throws DAOException;
    public abstract int getCountByRole(String role) throws DAOException;
}
