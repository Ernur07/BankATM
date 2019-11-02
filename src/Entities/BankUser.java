package Entities;

/**
 * Abstract class, which is used to create different type of bank users
 */
public abstract class BankUser {
    private String firstname;
    private String surname;
    private String login;

    public BankUser(String firstname, String surname, String login) {
        this.firstname = firstname;
        this.surname = surname;
        this.login = login;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
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

    @Override
    public String toString() {
        return this.getLogin();
    }
}
