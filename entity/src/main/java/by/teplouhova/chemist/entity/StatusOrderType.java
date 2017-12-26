package by.teplouhova.chemist.entity;

public enum StatusOrderType {

    IN_PROCESSING("in processing"),
    APPROVED("approved"),
    PAID("paid"),
    READY_TO_RECEIVE("ready to receive"),
    RECEIVED("received"),
    CANCELED("canceled");

    //'in processing', 'approved', 'paid', 'ready to receive', 'received', 'canceled'
    private String status;

    StatusOrderType(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
