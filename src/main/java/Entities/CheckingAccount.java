package Entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.ArrayList;

/**
 *  Represents checking account in my bank application
 */
@Entity
@DiscriminatorValue("checking")
public class CheckingAccount extends Account {

    public CheckingAccount(){}

    public CheckingAccount(String name, int balance, Currency currency, Client client) {
        super(name, balance,currency,client);
    }


}
