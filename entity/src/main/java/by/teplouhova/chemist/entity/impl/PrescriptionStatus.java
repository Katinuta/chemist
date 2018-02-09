package by.teplouhova.chemist.entity.impl;

/**
 * The Enum PrescriptionStatus.
 */
public enum PrescriptionStatus {

    /** The extend. */
    EXTEND("extend"),

    /** The active. */
    ACTIVE("active"),

    /** The inactive. */
    INACTIVE("inactive"),

    /** The used. */
    USED("used");

    /** The name status. */
    private String nameStatus;

    /**
     * Instantiates a new prescription status.
     *
     * @param nameStatus the name status
     */
    PrescriptionStatus(String nameStatus) {
        this.nameStatus = nameStatus;
    }

    /**
     * Gets the name status.
     *
     * @return the name status
     */
    public String getNameStatus() {
        return nameStatus;
    }
}
