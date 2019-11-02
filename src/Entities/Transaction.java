package Entities;

import java.util.Date;

public class Transaction {
    private Client receiver;
    private Account receiverAccount;
    private double amount;
    private Date date;

    public Transaction(Client receiver, Account receiverAccount, double amount) {
        this.receiver = receiver;
        this.receiverAccount = receiverAccount;
        this.amount = amount;
        date = new Date();
    }

    public Client getReceiver() {
        return receiver;
    }

    public void setReceiver(Client receiver) {
        this.receiver = receiver;
    }

    public Account getReceiverAccount() {
        return receiverAccount;
    }

    public void setReceiverAccount(Account receiverAccount) {
        this.receiverAccount = receiverAccount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
