package Entities;

import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * Abstract class, which is used to create different type of accounts
 */
public abstract class Account {
    /**
     * There is shown a several fees, which is used during client's operation
     */
    private static final double transactionFee=0.05;
    private static final double accountOpeningFee = 20;
    private static final double accountClosingFee = 25;

    private String name;
    private double balance;

    private ArrayList<Transaction> transactions;
    private String currency;

    public Account(String name, int balance, String c) {
        this.name = name;
        this.balance = balance;
        this.transactions = new ArrayList<Transaction>();
        this.currency=c;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public double getTransactionFee() {
        return transactionFee;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public static double getAccountOpeningFee() {
        return accountOpeningFee;
    }

    public static double getAccountClosingFee() {
        return accountClosingFee;
    }

    public void deposit(double amount){
        this.setBalance(this.getBalance()+amount);
    }

    public void withdraw(double amount){
        if(this.getBalance()<amount){
            throw new InputMismatchException("Balance small than withdrawal amount");
        }else{
            this.setBalance(this.getBalance()-amount);
        }
    }

    @Override
    public String toString() {
        return this.getName();
    }

    @Override
    public boolean equals(Object obj) {
        Account account = (Account) obj;
        return this.getName().equals(account.getName());
    }
}
