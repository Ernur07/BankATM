package Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class simulates bank's clients
 */
@Entity
@DiscriminatorValue("client")
public class Client extends BankUser{
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private List<CheckingAccount> checkingAccounts;
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private List<SavingAccount> savingAccounts;
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private List<Loan> loans;
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private List<SecurityAccount> securityAccounts;
    /**
     * This attributes saves account opening and closing fees for each client
     */
    private double otherFee = 0;

    public Client(){}
    public Client(String firstname, String surname, String login) {
        super(firstname, surname,login);
        this.checkingAccounts = new ArrayList<CheckingAccount>();
        this.savingAccounts = new ArrayList<SavingAccount>();
        this.loans = new ArrayList<Loan>();
        this.securityAccounts = new ArrayList<SecurityAccount>();
    }

    public List<CheckingAccount> getCheckingAccounts() {
        return checkingAccounts;
    }

    public void setCheckingAccounts(ArrayList<CheckingAccount> checkingAccounts) {
        this.checkingAccounts = checkingAccounts;
    }

    public List<SavingAccount> getSavingAccounts() {
        return savingAccounts;
    }

    public void setSavingAccounts(ArrayList<SavingAccount> savingAccounts) {
        this.savingAccounts = savingAccounts;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    public ArrayList<Account> getAllAccounts(){
        ArrayList<Account> result = new ArrayList<Account>();
        result.addAll(this.getSavingAccounts());
        result.addAll(this.getCheckingAccounts());
        return result;
    }

    public ArrayList<Transaction> getAllTransactions(){
        ArrayList<Transaction> result = new ArrayList<Transaction>();
        ArrayList<Account> accounts = this.getAllAccounts();
        for(Account account:accounts){
            result.addAll(account.getTransactions());
        }
        return result;
    }

    public double getOtherFee() {
        return otherFee;
    }

    public void setOtherFee(double otherFee) {
        this.otherFee = otherFee;
    }


    @Override
    public boolean equals(Object obj) {
        Client client = (Client) obj;
        return this.getLogin().equals(client.getLogin());
    }
}
