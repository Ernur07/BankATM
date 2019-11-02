package Entities;

import java.util.ArrayList;

/**
 * One of the main classes, which is used to save all required elements of Bank application
 */
public class Bank {

    private Manager manager;
    private ArrayList<Client> clients;

    public Bank(Manager manager, ArrayList<Client> clients) {
        this.manager = manager;
        this.clients = clients;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }

    public Client getPlayerByLogin(String login){
        Client result = null;
        for(Client c:this.getClients()){
            if(c.getLogin().equals(login)){
                result=c;
            }
        }
        return result;
    }

    public ArrayList<Transaction> getAllTransactions(){
        ArrayList<Transaction> result = new ArrayList<Transaction>();
        for(Client client:this.getClients()){
            for(CheckingAccount check:client.getCheckingAccounts()){
                result.addAll(check.getTransactions());
            }
            for(SavingAccount save:client.getSavingAccounts()){
                result.addAll(save.getTransactions());
            }
        }
        return result;
    }

    public ArrayList<Loan> getAllLoans(){
        ArrayList<Loan> result = new ArrayList<Loan>();
        for(Client client:this.getClients()){
            result.addAll(client.getLoans());
        }
        return result;
    }

}
