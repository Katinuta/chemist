package by.teplouhova.chemist.entity.impl;

/**
 * The Enum RoleType.
 */
public enum RoleType {

    /** The client. */
    CLIENT("client"),

    /** The doctor. */
    DOCTOR("doctor"),

    /** The pharmacist. */
    PHARMACIST("pharmacist");

    /** The name. */
    private String name;

    /**
     * Instantiates a new role type.
     *
     * @param name the name
     */
    RoleType(String name) {
        this.name = name;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }
}
