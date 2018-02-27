package by.teplouhova.chemist.entity.impl;

import by.teplouhova.chemist.entity.Entity;

import java.math.BigDecimal;

/**
 * The Class User.
 */
public class User extends Entity {

    /** The user id. */
    private long userId;

    /** The name. */
    private String name;

    /** The surname. */
    private String surname;

    /** The login. */
    private String login;

    /** The password. */
    private String password;

    /** The account. */
    private BigDecimal account;

    /** The role. */
    private RoleType role;

    /** The phone. */
    private String phone;

    /**
     * Instantiates a new user.
     */
    public User() {
        role=RoleType.CLIENT;
    }

    /**
     * Instantiates a new user.
     *
     * @param name the name
     * @param surname the surname
     * @param login the login
     * @param password the password
     * @param account the account
     * @param role the role
     * @param phone the phone
     */
    public User(String name, String surname, String login, String password, BigDecimal account, RoleType role, String phone) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.account = account;
        this.role = role;
        this.phone = phone;
    }

    /**
     * Instantiates a new user.
     *
     * @param userId the user id
     * @param name the name
     * @param surname the surname
     * @param login the login
     * @param password the password
     * @param account the account
     * @param role the role
     * @param phone the phone
     */
    public User(long userId, String name, String surname, String login, String password, BigDecimal account, RoleType role, String phone) {
        this.userId = userId;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.account = account;
        this.role = role;
        this.phone = phone;
    }

    /**
     * Instantiates a new user.
     *
     * @param userId the user id
     */
    public User(long userId) {
        this.userId = userId;
    }

    /**
     * Gets the user id.
     *
     * @return the user id
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Sets the user id.
     *
     * @param userId the new user id
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the surname.
     *
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the surname.
     *
     * @param surname the new surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Gets the login.
     *
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets the login.
     *
     * @param login the new login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the account.
     *
     * @return the account
     */
    public BigDecimal getAccount() {
        return account;
    }

    /**
     * Sets the account.
     *
     * @param account the new account
     */
    public void setAccount(BigDecimal account) {
        this.account = account;
    }

    /**
     * Gets the role.
     *
     * @return the role
     */
    public RoleType getRole() {
        return role;
    }

    /**
     * Sets the role.
     *
     * @param role the new role
     */
    public void setRole(RoleType role) {
        this.role = role;
    }

    /**
     * Gets the phone.
     *
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone.
     *
     * @param phone the new phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != user.userId) return false;
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
        int result = (int) (userId ^ (userId >>> 32));
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
                "userId=" + userId +
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
