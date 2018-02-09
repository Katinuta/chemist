package by.teplouhova.chemist.entity.impl;

/**
 * The Enum OrderStatusType.
 */
public enum OrderStatusType {

    /** The in processing. */
    IN_PROCESSING("in processing"),

    /** The paid. */
    PAID("paid"),

    /** The ready to receive. */
    READY_TO_RECEIVE("ready to receive"),

    /** The received. */
    RECEIVED("received");

    /** The status. */
    private String status;

    /**
     * Instantiates a new order status type.
     *
     * @param status the status
     */
    OrderStatusType(String status) {
        this.status = status;
    }

    /**
     * Gets the status.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }
}
