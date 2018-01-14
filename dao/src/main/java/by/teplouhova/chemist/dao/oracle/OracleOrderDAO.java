package by.teplouhova.chemist.dao.oracle;

import by.teplouhova.chemist.dao.OrderDAO;
import by.teplouhova.chemist.dao.exception.DAOException;
import by.teplouhova.chemist.entity.impl.Order;

import java.util.List;

public class OracleOrderDAO  extends OrderDAO{


    @Override
    public Order findById(long id) throws DAOException {
        return null;
    }

    @Override
    public void create(Order entity) throws DAOException {

    }

    @Override
    public void update(Order entity) throws DAOException {

    }

    @Override
    public List<Order> findOrdersByClientId(long id) throws DAOException {
        return null;
    }
}
