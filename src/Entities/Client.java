package Entities;

import java.util.ArrayList;

/**
 * This class simulates bank's clients
 */
public class Client extends BankUser{
    private ArrayList<CheckingAccount> checkingAccounts;
    private ArrayList<SavingAccount> savingAccounts;
    private ArrayList<Loan> loans;
    /**
     * This attributes saves account opening and closing fees for each client
     */
    private double otherFee = 0;

    public Client(String firstname, String surname, String login) {
        super(firstname, surname,login);
        this.checkingAccounts = new ArrayList<CheckingAccount>();
        this.savingAccounts = new ArrayList<SavingAccount>();
        this.loans = new ArrayList<Loan>();
    }

    public ArrayList<CheckingAccount> getCheckingAccounts() {
        return checkingAccounts;
    }

    public void setCheckingAccounts(ArrayList<CheckingAccount> checkingAccounts) {
        this.checkingAccounts = checkingAccounts;
    }

    public ArrayList<SavingAccount> getSavingAccounts() {
        return savingAccounts;
    }

    public void setSavingAccounts(ArrayList<SavingAccount> savingAccounts) {
        this.savingAccounts = savingAccounts;
    }

    public ArrayList<Loan> getLoans() {
        return loans;
    }

    public void setLoans(ArrayList<Loan> loans) {
        this.loans = loans;
    }

    public ArrayList<Account> getAllAccounts(){
        ArrayList<Account> result = new ArrayList<>();
        result.addAll(this.getSavingAccounts());
        result.addAll(this.getCheckingAccounts());
        return result;
    }

    public ArrayList<Transaction> getAllTransactions(){
        ArrayList<Transaction> result = new ArrayList<>();
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
