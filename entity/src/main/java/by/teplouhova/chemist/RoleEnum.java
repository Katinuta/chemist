package by.teplouhova.chemist;

public enum  RoleEnum {
    CLIENT("client"),
    DOCTOR("doctor"),
    PHARMACIST("pharmacist");

    private String name;

    RoleEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
