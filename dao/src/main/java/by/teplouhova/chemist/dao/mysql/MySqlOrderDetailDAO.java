package by.teplouhova.chemist.dao.mysql;

import by.teplouhova.chemist.dao.OrderDetailDAO;
import by.teplouhova.chemist.dao.exception.DAOException;
import by.teplouhova.chemist.entity.impl.OrderDetail;
import com.mysql.cj.api.jdbc.Statement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlOrderDetailDAO extends OrderDetailDAO {

    private static final String SQL_INSERT_ORDER_DETAIL=
            "INSERT INTO chemist.order_detail ( m_medicine_id, od_quantity, od_amount, o_order_id, od_price)" +
                    " VALUES (?, ?, ?, ?, ?)";

    @Override
    public OrderDetail findById(long id) throws DAOException {
        return null;
    }

    @Override
    public void create(OrderDetail entity) throws DAOException {
        PreparedStatement statement=null;
        try {
            statement=connection.prepareStatement(SQL_INSERT_ORDER_DETAIL, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1,entity.getMedicine().getMedicineId());
            statement.setInt(2,entity.getQuantity());
            statement.setBigDecimal(3,entity.getAmount());
            statement.setLong(4,entity.getOrder().getOrderId());
            statement.setBigDecimal(5,entity.getPrice());
            statement.execute();
            ResultSet result = statement.getGeneratedKeys();
            if (result.next()) {
                entity.setRecordId(result.getLong(1));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

    }

    @Override
    public void update(OrderDetail entity) throws DAOException {

    }
}
