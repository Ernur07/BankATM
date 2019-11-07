package Entities;

import javax.persistence.*;

/**
 * Abstract class, which is used to create different type of bank users
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type")
public abstract class BankUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstname;
    private String surname;
    private String login;
    private String password;

    public BankUser(){}
    public BankUser(String firstname, String surname, String login,String password) {
        this.firstname = firstname;
        this.surname = surname;
        this.login = login;
        this.password = password;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return this.getLogin()+" "+this.getFirstname()+" "+this.id+" ";
    }
}
