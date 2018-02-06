package by.teplouhova.chemist.creator;

import by.teplouhova.chemist.entity.impl.Medicine;
import by.teplouhova.chemist.entity.impl.Order;
import by.teplouhova.chemist.entity.impl.OrderDetail;
import by.teplouhova.chemist.entity.impl.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderCreator {
    private static final Logger LOGGER = LogManager.getLogger();

    public Order create(User user, HashMap<Medicine, Integer> cart) {
        Order order = new Order();
        order.setUser(user);
        List<OrderDetail> details = new ArrayList<>();
        for (Medicine medicine : cart.keySet()) {
            OrderDetail detail = new OrderDetail();
            int quantity = cart.get(medicine);
            detail.setQuantity(quantity);
            detail.setMedicine(medicine);
            detail.setPrice(medicine.getPrice());
            detail.setAmount(medicine.getPrice().multiply(BigDecimal.valueOf(quantity)));
            details.add(detail);
        }

        order.setDetails(details);
        BigDecimal total = new BigDecimal(0);
        for (OrderDetail detail:details ) {
            total=total.add(detail.getAmount());
        }
        order.setTotal(total);

        return order;
    }
}
