package by.teplouhova.chemist.entity.impl;

import by.teplouhova.chemist.entity.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 * The Class Order.
 */
public class Order extends Entity {

    /** The order id. */
    private long orderId;

    /** The date creating. */
    private LocalDate dateCreating;

    /** The status. */
    private OrderStatusType status;

    /** The user. */
    private User user;

    /** The issue point id. */
    private long issuePointId;

    /** The total. */
    private BigDecimal total;

    /** The details. */
    private List<OrderDetail> details;

    /**
     * Instantiates a new order.
     */
    public Order() {
        details=new ArrayList<>();
        total=new BigDecimal(0);
    }

    /**
     * Gets the order id.
     *
     * @return the order id
     */
    public long getOrderId() {
        return orderId;
    }

    /**
     * Sets the order id.
     *
     * @param orderId the new order id
     */
    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    /**
     * Gets the date creating.
     *
     * @return the date creating
     */
    public LocalDate getDateCreating() {
        return dateCreating;
    }

    /**
     * Sets the date creating.
     *
     * @param dateCreating the new date creating
     */
    public void setDateCreating(LocalDate dateCreating) {
        this.dateCreating = dateCreating;
    }

    /**
     * Gets the status.
     *
     * @return the status
     */
    public OrderStatusType getStatus() {
        return status;
    }

    /**
     * Sets the status.
     *
     * @param status the new status
     */
    public void setStatus(OrderStatusType status) {
        this.status = status;
    }

    /**
     * Gets the user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user id.
     *
     * @param userId the new user id
     */
    public void setUserId(long userId) {
        this.user= user;
    }

    /**
     * Gets the issue point id.
     *
     * @return the issue point id
     */
    public long getIssuePointId() {
        return issuePointId;
    }

    /**
     * Sets the issue point id.
     *
     * @param issuePointId the new issue point id
     */
    public void setIssuePointId(long issuePointId) {
        this.issuePointId = issuePointId;
    }

    /**
     * Sets the user.
     *
     * @param user the new user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets the total.
     *
     * @return the total
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * Sets the total.
     *
     * @param total the new total
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    /**
     * Gets the details.
     *
     * @return the details
     */
    public List<OrderDetail> getDetails() {
        return details;
    }

    /**
     * Sets the details.
     *
     * @param details the new details
     */
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
                ", details=" + details +
                '}';
    }
}
