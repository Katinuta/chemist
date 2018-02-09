package by.teplouhova.chemist.entity.impl;

import by.teplouhova.chemist.entity.Entity;

import java.math.BigDecimal;

/**
 * The Class OrderDetail.
 */
public class OrderDetail extends Entity {

    /** The record id. */
    private long recordId;

    /** The medicine. */
    private Medicine medicine;

    /** The quantity. */
    private int quantity;

    /** The price. */
    private BigDecimal price;

    /** The amount. */
    private BigDecimal amount;

    /** The order. */
    private Order order;

    /**
     * Instantiates a new order detail.
     */
    public OrderDetail() {
        order=new Order();
        medicine=new Medicine();
    }

    /**
     * Gets the record id.
     *
     * @return the record id
     */
    public long getRecordId() {
        return recordId;
    }

    /**
     * Sets the record id.
     *
     * @param recordId the new record id
     */
    public void setRecordId(long recordId) {
        this.recordId = recordId;
    }

    /**
     * Gets the medicine.
     *
     * @return the medicine
     */
    public Medicine getMedicine() {
        return medicine;
    }

    /**
     * Sets the medicine.
     *
     * @param medicine the new medicine
     */
    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    /**
     * Gets the quantity.
     *
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity.
     *
     * @param quantity the new quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets the price.
     *
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets the price.
     *
     * @param price the new price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Gets the amount.
     *
     * @return the amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the amount.
     *
     * @param amount the new amount
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * Gets the order.
     *
     * @return the order
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Sets the order.
     *
     * @param order the new order
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * Equals.
     *
     * @param o the o
     * @return true, if successful
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderDetail that = (OrderDetail) o;

        if (quantity != that.quantity) return false;
        if (medicine != null ? !medicine.equals(that.medicine) : that.medicine != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        return order != null ? order.equals(that.order) : that.order == null;
    }

    /**
     * Hash code.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        int result = medicine != null ? medicine.hashCode() : 0;
        result = 31 * result + quantity;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (order != null ? order.hashCode() : 0);
        return result;
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "OrderDetail{" +
                "recordId=" + recordId +
                ", medicine=" + medicine +
                ", quantity=" + quantity +
                ", price=" + price +
                ", amount=" + amount +
                ", order=" + order +
                '}';
    }
}
