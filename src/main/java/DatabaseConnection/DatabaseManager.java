package DatabaseConnection;


import Entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    EntityManager em;

    public DatabaseManager() {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "MyEclipseLinkExample" );
        this.em = emfactory.createEntityManager();
    }

    public <E> void add(E entity){
        em.getTransaction().begin();
        em.persist(entity);
        em.flush();
        em.getTransaction().commit();
    }

    public <E> void remove(E entity){
        em.getTransaction().begin();
        em.merge(entity);
        em.flush();
        em.remove(entity);
        em.flush();
        em.getTransaction().commit();
    }

    public <E> void update(E entity){
        em.getTransaction().begin();
        em.merge(entity);
        em.flush();
        em.getTransaction().commit();
    }


    public Client findClient(Integer id){
        em.getTransaction().begin();
        Client result = em.find(Client.class,id);
        em.getTransaction().commit();
        return result;
    }
    public SavingAccount findSavingAccount(Integer id){
        em.getTransaction().begin();
        SavingAccount result = em.find(SavingAccount.class,id);
        em.getTransaction().commit();
        return result;
    }
    public CheckingAccount findCheckingAccount(Integer id){
        em.getTransaction().begin();
        CheckingAccount result = em.find(CheckingAccount.class,id);
        em.getTransaction().commit();
        return result;
    }
    public Loan findLoan(Integer id){
        em.getTransaction().begin();
        Loan result = em.find(Loan.class,id);
        em.getTransaction().commit();
        return result;
    }

    public Transaction findTransaction(Integer id){
        em.getTransaction().begin();
        Transaction result = em.find(Transaction.class,id);
        em.getTransaction().commit();
        return result;
    }


    public List<SavingAccount> getSavingAccounts(Client client){
        em.getTransaction().begin();
        List<Integer> resultList = (List<Integer>) em.createNativeQuery("SELECT id FROM account WHERE client_id="+client.getId()+" AND account_type = saving");
        ArrayList<SavingAccount> result = new ArrayList<>();
        for(int id: resultList) {
            result.add(findSavingAccount(id));
        }
        em.getTransaction().commit();
        return result;
    }
    public List<CheckingAccount> getCheckingAccounts(Client client){
        em.getTransaction().begin();
        List<Integer> resultList = (List<Integer>) em.createNativeQuery("SELECT id FROM account WHERE client_id="+client.getId()+" AND account_type = checking");
        ArrayList<CheckingAccount> result = new ArrayList<>();
        for(int id: resultList) {
            result.add(findCheckingAccount(id));
        }
        em.getTransaction().commit();
        return result;
    }
    public List<Loan> getLoans(Client client){
        em.getTransaction().begin();
        List<Integer> resultList = (List<Integer>) em.createNativeQuery("SELECT id FROM loan WHERE client_id="+client.getId());
        ArrayList<Loan> result = new ArrayList<>();
        for(int id: resultList) {
            result.add(findLoan(id));
        }
        em.getTransaction().commit();
        return result;
    }
    public List<Loan> getTransactions(Account account){
        em.getTransaction().begin();
        List<Integer> resultList = (List<Integer>) em.createNativeQuery("SELECT id FROM transactions WHERE sender_account_id="+account.getId());
        ArrayList<Loan> result = new ArrayList<>();
        for(int id: resultList) {
            result.add(findLoan(id));
        }
        em.getTransaction().commit();
        return result;
    }

    public List<SavingAccount> getAllSavingAccounts(){
        em.getTransaction().begin();
        List<Integer> resultList = (List<Integer>) em.createNativeQuery("SELECT id FROM account WHERE account_type = saving");
        ArrayList<SavingAccount> result = new ArrayList<>();
        for(int id: resultList) {
            result.add(findSavingAccount(id));
        }
        em.getTransaction().commit();
        return result;
    }
    public List<CheckingAccount> getAllCheckingAccounts(){
        em.getTransaction().begin();
        List<Integer> resultList = (List<Integer>) em.createNativeQuery("SELECT id FROM account WHERE account_type = checking");
        ArrayList<CheckingAccount> result = new ArrayList<>();
        for(int id: resultList) {
            result.add(findCheckingAccount(id));
        }
        em.getTransaction().commit();
        return result;
    }
    public List<Loan> getAllLoans(){
        em.getTransaction().begin();
        List<Integer> resultList = (List<Integer>) em.createNativeQuery("SELECT id FROM loan");
        ArrayList<Loan> result = new ArrayList<>();
        for(int id: resultList) {
            result.add(findLoan(id));
        }
        em.getTransaction().commit();
        return result;
    }
    public List<Loan> getAllTransactions(){
        em.getTransaction().begin();
        List<Integer> resultList = (List<Integer>) em.createNativeQuery("SELECT id FROM transactions");
        ArrayList<Loan> result = new ArrayList<>();
        for(int id: resultList) {
            result.add(findLoan(id));
        }
        em.getTransaction().commit();
        return result;
    }


    /*public void addClient(Client client){
        em.getTransaction().begin();
        em.persist(client);
        em.flush();
        em.getTransaction().commit();
    }

    public void addCheckhingAccount(CheckingAccount acc){
        em.getTransaction().begin();
        em.persist(acc);
        em.flush();
        em.getTransaction().commit();
    }

    public void addSavingAccount(SavingAccount acc){
        em.getTransaction().begin();
        em.persist(acc);
        em.flush();
        em.getTransaction().commit();
    }*/


}
