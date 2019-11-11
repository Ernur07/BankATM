package Entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.ArrayList;

/**
 * This class represents Saving accounts in Bank application
 */
@Entity
@DiscriminatorValue("saving")
public class SavingAccount extends Account {
    private static final double minimumBalanceForInterest=100000;
    private double interestRate;

    public SavingAccount(){}

    public SavingAccount(String name, int balance, double interestRate, Currency currency, Client client) {
        super(name, balance,currency,client);
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public void addInterest(){
        this.setInterestRate(this.getBalance()+(this.getInterestRate()*this.getBalance()));
    }
}
