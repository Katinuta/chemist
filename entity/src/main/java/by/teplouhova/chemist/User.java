package by.teplouhova.chemist;

import java.math.BigDecimal;

public class User extends Entity {
    private long usedId;
    private String name;
    private String surname;
    private String login;
    private String password;
    private BigDecimal account;
    private RoleEnum role;
    private String phone;

    public User() {
    }

    public User(String name, String surname, String login, String password, BigDecimal account, RoleEnum role, String phone) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.account = account;
        this.role = role;
        this.phone = phone;
    }

    public User(long usedId, String name, String surname, String login, String password, BigDecimal account, RoleEnum role, String phone) {
        this.usedId = usedId;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.account = account;
        this.role = role;
        this.phone = phone;
    }

    public long getUsedId() {
        return usedId;
    }

    public void setUsedId(long usedId) {
        this.usedId = usedId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BigDecimal getAccount() {
        return account;
    }

    public void setAccount(BigDecimal account) {
        this.account = account;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (usedId != user.usedId) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (surname != null ? !surname.equals(user.surname) : user.surname != null) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (account != null ? !account.equals(user.account) : user.account != null) return false;
        if (role != user.role) return false;
        return phone != null ? phone.equals(user.phone) : user.phone == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (usedId ^ (usedId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (account != null ? account.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "usedId=" + usedId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", account=" + account +
                ", role=" + role +
                ", phone='" + phone + '\'' +
                '}';
    }
}
