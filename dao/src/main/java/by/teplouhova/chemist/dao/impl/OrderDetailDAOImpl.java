package by.teplouhova.chemist.dao.impl;

import by.teplouhova.chemist.dao.OrderDetailDAO;
import by.teplouhova.chemist.dao.DAOException;
import by.teplouhova.chemist.entity.impl.Medicine;
import by.teplouhova.chemist.entity.impl.Order;
import by.teplouhova.chemist.entity.impl.OrderDetail;
import com.mysql.cj.api.jdbc.Statement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAOImpl extends OrderDetailDAO {

    private static final String SQL_INSERT_ORDER_DETAIL=
            "INSERT INTO chemist.order_detail ( m_medicine_id, od_quantity, od_amount, o_order_id, od_price) " +
                    " VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_ALL_BY_ORDER_ID=
            "SELECT od_record_id, m_medicine_id,od_quantity,od_price ,od_amount,o_order_id , CONCAT(medicine.m_name,' ',release_form.rf_name,' ',  " +
                    "            IFNULL( IF(dosage.d_dosage_size > 1,  " +
                    "            FLOOR(dosage.d_dosage_size), dosage.d_dosage_size),''), " +
                    "            IFNULL(dosage.d_dosage_unit,'' ),' ', " +
                    "            IF(m_unit_in_package ='шт', " +
                    "            CONCAT('N',  medicine.m_quantity_in_pack), " +
                    "            CONCAT(medicine.m_quantity_in_pack, medicine.m_unit_in_package,' N1')))   " +
                    "            AS fullname  " +
                    "FROM chemist.order_detail " +
                    "JOIN chemist.medicine USING(m_medicine_id) " +
                    "JOIN chemist.release_form USING(rf_release_form_id) " +
                    "LEFT JOIN chemist.dosage USING(d_dosage_id) " +
                    "   WHERE o_order_id=? " +
                    "ORDER BY fullname ";

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

    public List<OrderDetail> findAllByOrderId(long orderId) throws DAOException {
        List<OrderDetail> details=new ArrayList<>();
        PreparedStatement statement=null;
        try {
            statement=connection.prepareStatement(SQL_SELECT_ALL_BY_ORDER_ID);
            statement.setLong(1,orderId);
            ResultSet result=statement.executeQuery();
            while(result.next()){
                OrderDetail orderDetail=new OrderDetail();
                orderDetail.setRecordId(result.getLong("od_record_id"));
                Medicine medicine=new Medicine();
                medicine.setName(result.getString("fullname"));
                medicine.setMedicineId(result.getLong("m_medicine_id"));
                orderDetail.setMedicine(medicine);
                orderDetail.setQuantity(result.getInt("od_quantity"));
                orderDetail.setAmount(result.getBigDecimal("od_amount"));
                Order order=new Order();
                order.setOrderId(result.getLong("o_order_id"));
                orderDetail.setOrder(order);
                orderDetail.setPrice(result.getBigDecimal("od_price"));
                details.add(orderDetail);
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return  !details.isEmpty()?details:null;
    }
}
