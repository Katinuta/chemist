package by.teplouhova.chemist.entity.impl;

public enum PrescriptionStatus {
    EXTEND("extend"),
    ACTIVE("active"),
    INACTIVE("inactive"),
    USED("used");

    private String nameStatus;

    PrescriptionStatus(String nameStatus) {
        this.nameStatus = nameStatus;
    }

    public String getNameStatus() {
        return nameStatus;
    }
}
