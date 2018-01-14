package by.teplouhova.chemist.entity.impl;

public enum RoleType {
    CLIENT("client"),
    DOCTOR("doctor"),
    PHARMACIST("pharmacist");

    private String name;

    RoleType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
