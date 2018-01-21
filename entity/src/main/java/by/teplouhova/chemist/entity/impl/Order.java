package by.teplouhova.chemist.entity.impl;

import by.teplouhova.chemist.entity.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order extends Entity {
    private long orderId;
    private LocalDate dateCreating;
    private OrderStatusType status;
    private User user;
    private long issuePointId;
    private BigDecimal total;
    private List<OrderDetail> details;

    public Order() {
        details=new ArrayList<>();
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public LocalDate getDateCreating() {
        return dateCreating;
    }

    public void setDateCreating(LocalDate dateCreating) {
        this.dateCreating = dateCreating;
    }

    public OrderStatusType getStatus() {
        return status;
    }

    public void setStatus(OrderStatusType status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUserId(long userId) {
        this.user= user;
    }

    public long getIssuePointId() {
        return issuePointId;
    }

    public void setIssuePointId(long issuePointId) {
        this.issuePointId = issuePointId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<OrderDetail> getDetails() {
        return details;
    }

    public void setDetails(List<OrderDetail> details) {
        this.details = details;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (orderId != order.orderId) return false;
        if (issuePointId != order.issuePointId) return false;
        if (dateCreating != null ? !dateCreating.equals(order.dateCreating) : order.dateCreating != null) return false;
        if (status != order.status) return false;
        if (user != null ? !user.equals(order.user) : order.user != null) return false;
        return total != null ? total.equals(order.total) : order.total == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (orderId ^ (orderId >>> 32));
        result = 31 * result + (dateCreating != null ? dateCreating.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (int) (issuePointId ^ (issuePointId >>> 32));
        result = 31 * result + (total != null ? total.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", dateCreating=" + dateCreating +
                ", status=" + status +
                ", user=" + user +
                ", issuePointId=" + issuePointId +
                ", total=" + total +
                '}';
    }
}
