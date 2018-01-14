package by.teplouhova.chemist.dao.oracle;

import by.teplouhova.chemist.dao.UserDAO;
import by.teplouhova.chemist.dao.exception.DAOException;
import by.teplouhova.chemist.entity.impl.User;

public class OracleUserDAO extends UserDAO {
    @Override
    public User findById(long id) {
        return null;
    }

    @Override
    public void create(User entity) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(User entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public User findByLoginPassword(String login, String password) throws DAOException {
        return null;
    }

    @Override
    public User findByLogin(String login) throws DAOException {
        return null;
    }
}
