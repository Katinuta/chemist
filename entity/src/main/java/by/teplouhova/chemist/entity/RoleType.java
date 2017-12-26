package by.teplouhova.chemist.entity;

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
