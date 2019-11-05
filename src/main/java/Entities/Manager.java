package Entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * This class simulates manager of the bank
 */

@Entity
@DiscriminatorValue("manager")
public class Manager extends BankUser{
    public Manager(){}
    public Manager(String firstname, String surname, String login) {
        super(firstname, surname,login);
    }
}
