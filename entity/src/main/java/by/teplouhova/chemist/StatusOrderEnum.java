package by.teplouhova.chemist;

public enum StatusOrderEnum {

    IN_PROCESSING("in processing"),
    APPROVED("approved"),
    PAID("paid"),
    READY_TO_RECEIVE("ready to receive"),
    RECEIVED("received"),
    CANCELED("canceled");

    //'in processing', 'approved', 'paid', 'ready to receive', 'received', 'canceled'
    private String status;

    StatusOrderEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
