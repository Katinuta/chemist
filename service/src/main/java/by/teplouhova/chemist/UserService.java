package by.teplouhova.chemist;

import by.teplouhova.chemist.dao.exception.DAOException;
import by.teplouhova.chemist.dao.exception.ServiceException;
import by.teplouhova.chemist.dao.impl.User;
import by.teplouhova.chemist.dao.impl.UserDAO;

public class UserService {

    public boolean checkUser(String login, String password) throws ServiceException {
        User user;
        try {

            user = new UserDAO().findByLoginPassword(login, password);

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return user != null ? true : false;
    }
}
