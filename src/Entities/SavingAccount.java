package Entities;

import java.util.ArrayList;

/**
 * This class represents Saving accounts in Bank application
 */
public class SavingAccount extends Account {
    private static final double minimumBalanceForInterest=100000;
    private double interestRate;

    public SavingAccount(String name, int balance, double interestRate, String currency) {
        super(name, balance,currency);
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
