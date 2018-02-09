package by.teplouhova.chemist.entity.impl;

public enum OrderStatusType {

    IN_PROCESSING("in processing"),
    PAID("paid"),
    READY_TO_RECEIVE("ready to receive"),
    RECEIVED("received");

    private String status;

    OrderStatusType(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
