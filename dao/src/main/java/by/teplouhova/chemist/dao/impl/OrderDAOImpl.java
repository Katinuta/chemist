package by.teplouhova.chemist.dao.impl;

import by.teplouhova.chemist.dao.OrderDAO;
import by.teplouhova.chemist.dao.DAOException;
import by.teplouhova.chemist.entity.impl.Order;
import by.teplouhova.chemist.entity.impl.OrderStatusType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class OrderDAOImpl.
 */
public class OrderDAOImpl extends OrderDAO {


    /** The Constant SQL_INSERT_ORDER. */
    private final static String SQL_INSERT_ORDER =
            "INSERT INTO chemist.order (u_user_id,o_date_created,o_total_sum,o_status) VALUES ( ?,CURDATE(),?,?)";

    /** The Constant SQL_SELECT_ORDERS_BY_CLIENT_ID. */
    private static final String SQL_SELECT_ORDERS_BY_CLIENT_ID =
            "SELECT o_order_id,o_date_created,o_status,o_total_sum FROM chemist.order WHERE u_user_id =?";

    /** The Constant SQL_SELECT_BY_ID. */
    private static final String SQL_SELECT_BY_ID =
            "SELECT o_order_id,o_date_created,o_status,o_total_sum FROM chemist.order WHERE o_order_id =?";

    /**
     * Find order by id.
     *
     * @param id the id
     * @return the order
     * @throws DAOException the DAO exception
     */
    @Override
    public Order findById(long id) throws DAOException {
        PreparedStatement statement = null;
        Order order=null;
        try {
            statement=connection.prepareStatement(SQL_SELECT_BY_ID);
            statement.setLong(1, id);
            ResultSet result=statement.executeQuery();
            if(result.next()) {
                order = new Order();
                order.setOrderId(result.getLong("o_order_id"));
                order.setDateCreating(result.getDate("o_date_created").toLocalDate());
                order.setTotal(result.getBigDecimal("o_total_sum"));
                order.setStatus(OrderStatusType.valueOf(result.getString("o_status").replaceAll(" ", "_").toUpperCase()));
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        }finally {
            close(statement);
        }
        return order;
    }

    /**
     * Creates the order.
     *
     * @param entity the order
     * @throws DAOException the DAO exception
     */
    @Override
    public void create(Order entity) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_INSERT_ORDER,Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, entity.getUser().getUserId());
            statement.setBigDecimal(2, entity.getTotal());
            statement.setString(3,entity.getStatus().name().toLowerCase());
            statement.executeUpdate();
            ResultSet result = statement.getGeneratedKeys();
            if (result.next()) {
                entity.setOrderId(result.getLong(1));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(statement);
        }

    }

    /**
     * Update order.
     *
     * @param entity the entity
     */
    @Override
    public void update(Order entity) {
        throw new UnsupportedOperationException();
    }


    /**
     * Find orders by client id.
     *
     * @param id the id
     * @return the list
     * @throws DAOException the DAO exception
     */
    @Override
    public List<Order> findOrdersByClientId(long id) throws DAOException {
        PreparedStatement statement = null;
        List<Order> orders = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_SELECT_ORDERS_BY_CLIENT_ID);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Order order = new Order();
                order.setOrderId(result.getLong("o_order_id"));
                order.setDateCreating(result.getDate("o_date_created").toLocalDate());
                order.setTotal(result.getBigDecimal("o_total_sum"));
                order.setStatus(OrderStatusType.valueOf(result.getString("o_status").replaceAll(" ", "_").toUpperCase()));
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(statement);
        }
        return !orders.isEmpty() ? orders : null;
    }
}

