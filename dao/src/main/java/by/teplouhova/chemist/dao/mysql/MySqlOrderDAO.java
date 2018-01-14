package by.teplouhova.chemist.dao.mysql;

import by.teplouhova.chemist.dao.OrderDAO;
import by.teplouhova.chemist.entity.impl.OrderStatusType;
import by.teplouhova.chemist.dao.exception.DAOException;
import by.teplouhova.chemist.entity.impl.Order;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MySqlOrderDAO extends OrderDAO {

    private final static String SQL_INSERT_ORDER =
            "INSERT INTO chemist.order (u_user_id, o_date_create, o_status,ip_issue_point_id) VALUES ( ?, ?, ?,?)";
    private static final String SQL_SELECT_ORDERS_BY_CLIENT_ID=
            "SELECT o_order_id,o_date_created,o_status,o_total_sum FROM chemist.order WHERE u_user_id =?";

    @Override
    public Order findById(long id) {
        return null;
    }

    @Override
    public void create(Order entity) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_INSERT_ORDER);
            statement.setLong(1, entity.getUser().getUserId());
            statement.setDate(2, Date.valueOf(LocalDate.now()));
            statement.setString(3, OrderStatusType.valueOf("in processing").getStatus());
            statement.setLong(4, entity.getIssuePointId());
            statement.execute();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(statement);
        }

    }

    @Override
    public void update(Order entity) {

    }


    @Override
    public List<Order> findOrdersByClientId(long id) throws DAOException {
        PreparedStatement statement=null;
        List<Order> orders=new ArrayList<>();
        try {
            statement=connection.prepareStatement(SQL_SELECT_ORDERS_BY_CLIENT_ID);
            statement.setLong(1,id);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                Order order=new Order();
                order.setOrderId(result.getLong("o_order_id"));
                order.setDateCreating(result.getDate("o_date_created").toLocalDate());
                order.setTotal(result.getBigDecimal("o_total_sum"));
                order.setStatus(OrderStatusType.valueOf(result.getString("o_status").replaceAll(" ","_").toUpperCase()));
                orders.add(order);
            }
        } catch (SQLException e) {
           throw new DAOException(e);
        }finally {
            close(statement);
        }
        return !orders.isEmpty()?orders:null;
    }
}
