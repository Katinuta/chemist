package by.teplouhova.chemist.dao.impl;

import by.teplouhova.chemist.StatusOrderEnum;
import by.teplouhova.chemist.dao.exception.DAOException;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class OrderDAO extends AbstractDAO<Order> {

    private final static String SQL_INSERT_ORDER =
            "INSERT INTO chemist.order (u_user_id, o_date_create, o_status,ip_issue_point_id) VALUES ( ?, ?, ?,?)";

    @Override
    public Order findById(long id) {
        return null;
    }

    @Override
    public void create(Order entity) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_INSERT_ORDER);
            statement.setLong(1, entity.getUser().getUsedId());
            statement.setDate(2, Date.valueOf(LocalDate.now()));
            statement.setString(3, StatusOrderEnum.valueOf("in processing").getStatus());
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
}
