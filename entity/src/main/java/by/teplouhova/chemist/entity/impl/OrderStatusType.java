package by.teplouhova.chemist.entity.impl;

public enum OrderStatusType {

    IN_PROCESSING("in processing"),
    APPROVED("approved"),
    PAID("paid"),
    READY_TO_RECEIVE("ready to receive"),
    RECEIVED("received"),
    CANCELED("canceled");

    //'in processing', 'approved', 'paid', 'ready to receive', 'received', 'canceled'
    private String status;

    OrderStatusType(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
