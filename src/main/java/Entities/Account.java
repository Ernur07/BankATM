package Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

/**
 * Abstract class, which is used to create different type of accounts
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "account_type")
public abstract class Account {
    /**
     * There is shown a several fees, which is used during client's operation
     */
    private static final double transactionFee=0.05;
    private static final double accountOpeningFee = 20;
    private static final double accountClosingFee = 25;

    @Id
    @SequenceGenerator(name = "account_id_gen", sequenceName = "account_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_id_gen")
    int id;

    private String name;
    private double balance;
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "sender_account_id")
    private List<Transaction> transactions;
    private String currency;

    @ManyToOne(fetch = FetchType.EAGER, cascade ={CascadeType.MERGE,CascadeType.DETACH,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "client_id")
    private Client owner;

    public Account(String name, int balance, String c,Client owner) {
        this.name = name;
        this.balance = balance;
        this.transactions = new ArrayList<Transaction>();
        this.currency=c;
        this.owner=owner;
    }
    public Account(){}

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

    public List<Transaction> getTransactions() {
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

    public void setOwner(Client client) {
        this.owner=client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Client getOwner() {
        return owner;
    }
}
