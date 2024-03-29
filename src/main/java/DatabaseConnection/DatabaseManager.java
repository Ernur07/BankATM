package DatabaseConnection;


import Entities.*;
import Enums.CurrencyExchangeRate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
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
        E temp = em.merge(entity);
        em.flush();
        em.remove(temp);
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
    public SecurityAccount findSecurityAccount(Integer id){
        em.getTransaction().begin();
        SecurityAccount result = em.find(SecurityAccount.class,id);
        em.getTransaction().commit();
        return result;
    }
    public Shares findShares(Integer id){
        em.getTransaction().begin();
        Shares result = em.find(Shares.class,id);
        em.getTransaction().commit();
        return result;
    }
    public PrivateShares findPrivateShares(Integer id){
        em.getTransaction().begin();
        PrivateShares result = em.find(PrivateShares.class,id);
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
    public CurrencyExchange findCurrencyExchange(Integer id){
        em.getTransaction().begin();
        CurrencyExchange result = em.find(CurrencyExchange.class,id);
        em.getTransaction().commit();
        return result;
    }
    public Currency findCurrency(Integer id){
        em.getTransaction().begin();
        Currency result = em.find(Currency.class,id);
        em.getTransaction().commit();
        return result;
    }


    public List<SavingAccount> getSavingAccounts(Client client){
        em.getTransaction().begin();
        /*Query q = em.createNativeQuery("SELECT id FROM account WHERE client_id = ? and account_type= ?");
        q.setParameter(1,client.getId()).setParameter(2,"saving");
        List<Integer> resultList = q.getResultList();
        ArrayList<SavingAccount> result = new ArrayList<>();
        for(Integer id: resultList) {
            result.add(findSavingAccount(id));
        }*/
        Query q = em.createQuery("SELECT s FROM SavingAccount s WHERE s.owner = :client");
        q.setParameter("client",client);
        ArrayList<SavingAccount> result = new ArrayList<>(q.getResultList()) ;
        em.getTransaction().commit();
        return result;
    }
    public List<CheckingAccount> getCheckingAccounts(Client client){
        em.getTransaction().begin();
        /*Query q = em.createNativeQuery("SELECT id FROM account WHERE client_id= ? AND account_type = ?");
        q.setParameter(1,client.getId()).setParameter(2,"checking");
        List<Integer> resultList = q.getResultList();
        ArrayList<CheckingAccount> result = new ArrayList<>();
        for(int id: resultList) {
            result.add(findCheckingAccount(id));
        }*/
        Query q = em.createQuery("SELECT s FROM CheckingAccount s WHERE s.owner = :client");
        q.setParameter("client",client);
        ArrayList<CheckingAccount> result = new ArrayList<>(q.getResultList()) ;
        em.getTransaction().commit();
        return result;
    }
    public List<SecurityAccount> getSecurityAccount(Client client){
        em.getTransaction().begin();
        /*Query q = em.createNativeQuery("SELECT id FROM account WHERE client_id= ? AND account_type = ?");
        q.setParameter(1,client.getId()).setParameter(2,"security");
        List<Integer> resultList = q.getResultList();
        ArrayList<SecurityAccount> result = new ArrayList<>();
        for(int id: resultList) {
            result.add(findSecurityAccount(id));
        }*/
        Query q = em.createQuery("SELECT s FROM SecurityAccount s WHERE s.owner = :client");
        q.setParameter("client",client);
        ArrayList<SecurityAccount> result = new ArrayList<>(q.getResultList()) ;
        em.getTransaction().commit();
        return result;
    }
    public List<Loan> getLoans(Client client){
        em.getTransaction().begin();
        /*Query q = em.createNativeQuery("SELECT id FROM loan WHERE client_id= ?");
        q.setParameter(1,client.getId());
        List<Integer> resultList = q.getResultList();
        ArrayList<Loan> result = new ArrayList<>();
        for(int id: resultList) {
            result.add(findLoan(id));
        }*/
        /*Query q = em.createQuery("SELECT l FROM Loan l WHERE l.owner= :id");
        q.setParameter("id",client);
        ArrayList<Loan> result = new ArrayList<Loan> (q.getResultList());
        for (Loan l:result) {
            System.out.println(l.getTotalPayment());
        }*/
        Query q = em.createQuery("SELECT s FROM Loan s WHERE s.owner = :client");
        q.setParameter("client",client);
        ArrayList<Loan> result = new ArrayList<>(q.getResultList()) ;
        em.getTransaction().commit();
        return result;
    }
    public List<Transaction> getTransactions(Account account){
        em.getTransaction().begin();
        /*List<Integer> resultList = (List<Integer>) em.createNativeQuery("SELECT id FROM transactions WHERE sender_account_id="+account.getId());
        ArrayList<Loan> result = new ArrayList<>();
        for(int id: resultList) {
            result.add(findLoan(id));
        }*/
        Query q = em.createQuery("SELECT t FROM Transaction t WHERE t.senderAccount.id= :id");
        q.setParameter("id",account.getId());
        ArrayList<Transaction> result = (ArrayList<Transaction>) q.getResultList();
        em.getTransaction().commit();
        return result;
    }
    public List<PrivateShares> getPrivateShares(Account account){
        em.getTransaction().begin();
        /*List<Integer> resultList = (List<Integer>) em.createNativeQuery("SELECT id FROM privateshares WHERE security_account_id="+account.getId());
        ArrayList<Loan> result = new ArrayList<>();
        for(int id: resultList) {
            result.add(findLoan(id));
        }*/
        Query q = em.createQuery("SELECT s FROM PrivateShares s WHERE s.securityAccount.id= :id");
        q.setParameter("id",account.getId());
        ArrayList<PrivateShares> result = new ArrayList<>(q.getResultList());
        em.getTransaction().commit();
        return result;
    }

    public List<SavingAccount> getAllSavingAccounts(){
        em.getTransaction().begin();
        /*List<Integer> resultList = (List<Integer>) em.createNativeQuery("SELECT id FROM account WHERE account_type = saving");
        ArrayList<SavingAccount> result = new ArrayList<>();
        for(int id: resultList) {
            result.add(findSavingAccount(id));
        }*/
        Query q = em.createQuery("SELECT s FROM SavingAccount s");
        ArrayList<SavingAccount> result = (ArrayList<SavingAccount>) q.getResultList();
        em.getTransaction().commit();
        return result;
    }
    public List<CheckingAccount> getAllCheckingAccounts(){
        em.getTransaction().begin();
        /*List<Integer> resultList = (List<Integer>) em.createNativeQuery("SELECT id FROM account WHERE account_type = checking");
        ArrayList<CheckingAccount> result = new ArrayList<>();
        for(int id: resultList) {
            result.add(findCheckingAccount(id));
        }*/
        Query q = em.createQuery("SELECT s FROM CheckingAccount s");
        ArrayList<CheckingAccount> result = (ArrayList<CheckingAccount>) q.getResultList();
        em.getTransaction().commit();
        return result;
    }
    public List<Loan> getAllLoans(){
        em.getTransaction().begin();
        /*List<Integer> resultList = (List<Integer>) em.createNativeQuery("SELECT id FROM loan");
        ArrayList<Loan> result = new ArrayList<>();
        for(int id: resultList) {
            result.add(findLoan(id));
        }*/
        Query q = em.createQuery("SELECT s FROM Loan s");
        ArrayList<Loan> result = (ArrayList<Loan>) q.getResultList();
        em.getTransaction().commit();
        return result;
    }
    public List<Transaction> getAllTransactions(){
        em.getTransaction().begin();
        /*List<Integer> resultList = (List<Integer>) em.createNativeQuery("SELECT id FROM transactions");
        ArrayList<Loan> result = new ArrayList<>();
        for(int id: resultList) {
            result.add(findLoan(id));
        }*/
        Query q = em.createQuery("SELECT s FROM Transaction s");
        ArrayList<Transaction> result = (ArrayList<Transaction>) q.getResultList();
        em.getTransaction().commit();
        return result;
    }

    public List<Shares> getAllShares(){
        em.getTransaction().begin();
        /*List<Integer> resultList = (List<Integer>) em.createNativeQuery("SELECT id FROM shares");
        ArrayList<Shares> result = new ArrayList<>();
        for(int id: resultList) {
            result.add(findShares(id));
        }*/
        Query q = em.createQuery("SELECT s FROM Shares s");
        ArrayList<Shares> temp = new ArrayList<Shares>(q.getResultList());
        for(int i =0;i<temp.size();i++){
            if(temp.get(i) instanceof PrivateShares){
                temp.remove(i);
                i--;
            }
        }
        em.getTransaction().commit();
        return temp;
    }
    public List<PrivateShares> getAllPrivateShares(){
        em.getTransaction().begin();
        /*List<Integer> resultList = (List<Integer>) em.createNativeQuery("SELECT id FROM privateshares");
        ArrayList<PrivateShares> result = new ArrayList<>();
        for(int id: resultList) {
            result.add(findPrivateShares(id));
        }*/
        Query q = em.createQuery("SELECT s FROM PrivateShares s");
        ArrayList<PrivateShares> result = (ArrayList<PrivateShares>) q.getResultList();
        em.getTransaction().commit();
        return result;
    }

    public List<SecurityAccount> getAllSecurityAccount(){
        em.getTransaction().begin();
        /*List<Integer> resultList = (List<Integer>) em.createNativeQuery("SELECT id FROM account WHERE account_type = security");
        ArrayList<SecurityAccount> result = new ArrayList<>();
        for(int id: resultList) {
            result.add(findSecurityAccount(id));
        }*/
        Query q = em.createQuery("SELECT s FROM SecurityAccount s");
        ArrayList<SecurityAccount> result = (ArrayList<SecurityAccount>) q.getResultList();
        em.getTransaction().commit();
        return result;
    }
    public List<Client> getAllClient(){
        em.getTransaction().begin();
        Query q = em.createQuery("SELECT s FROM Client s");
        ArrayList<Client> result = new ArrayList<Client>(q.getResultList());
        em.getTransaction().commit();
        System.out.println(result);
        return result;
    }
    public List<CurrencyExchange> getAllCurrencyExchange(){
        em.getTransaction().begin();
        /*List<Integer> resultList = (List<Integer>) em.createNativeQuery("SELECT id FROM account WHERE account_type = saving");
        ArrayList<SavingAccount> result = new ArrayList<>();
        for(int id: resultList) {
            result.add(findSavingAccount(id));
        }*/
        Query q = em.createQuery("SELECT s FROM CurrencyExchange s");
        ArrayList<CurrencyExchange> result = new ArrayList<>(q.getResultList());
        em.getTransaction().commit();
        return result;
    }
    public List<Currency> getAllCurrency(){
        em.getTransaction().begin();
        /*List<Integer> resultList = (List<Integer>) em.createNativeQuery("SELECT id FROM account WHERE account_type = saving");
        ArrayList<SavingAccount> result = new ArrayList<>();
        for(int id: resultList) {
            result.add(findSavingAccount(id));
        }*/
        Query q = em.createQuery("SELECT s FROM Currency s");
        ArrayList<Currency> result = new ArrayList<>(q.getResultList());
        em.getTransaction().commit();
        return result;
    }
    public boolean isRegistered(String login, String password){
        em.getTransaction().begin();
        Client result= null;
        try{
            Query q = em.createQuery("SELECT s FROM Client s WHERE s.login = :login AND s.password = :password");
            q.setParameter("login",login).setParameter("password",password);
            result = (Client) q.getSingleResult();
            System.out.println(result);

        }catch(Exception e){
        }

        em.getTransaction().commit();
        if(result == null){
            return true;
        }else{
            return false;
        }
    }

    public boolean loginCheck(String login){
        em.getTransaction().begin();
        Client result = null;
        try {
            Query q = em.createQuery("SELECT s FROM Client s WHERE s.login = :login");
            q.setParameter("login",login);
            result = (Client) q.getSingleResult();
            System.out.println(result);
        }catch (Exception e){}
        em.getTransaction().commit();
        if(result == null){
            return false;
        }else{
            return true;
        }
    }
    public Client findClientByLogin(String login){
        em.getTransaction().begin();
        Query q = em.createQuery("SELECT s FROM Client s WHERE s.login = :login");
        q.setParameter("login",login);
        Client result = (Client) q.getSingleResult();
        em.getTransaction().commit();
        return result;
    }

    public CurrencyExchange findExchangeByCurrencies(Currency c1,Currency c2){
        em.getTransaction().begin();
        Query q = em.createQuery("SELECT s FROM CurrencyExchange s WHERE s.currency1 = :c1 AND s.currency2= :c2");
        q.setParameter("c1",c1).setParameter("c2",c2);
        CurrencyExchange result = (CurrencyExchange) q.getSingleResult();
        em.getTransaction().commit();
        return result;
    }

    public double getUsersBalance(Client c){
        ArrayList<SavingAccount> accounts = (ArrayList<SavingAccount>) this.getSavingAccounts(c);
        double sum =0;
        for(SavingAccount acc : accounts){
            sum+=acc.getBalance();
        }
        return sum;
    }

    public double getSecurityAccountBalance(SecurityAccount c){
        ArrayList<PrivateShares> shares = (ArrayList<PrivateShares>) this.getPrivateShares(c);
        double sum =0;
        for(PrivateShares acc : shares){
            sum+=acc.getSharePrice();
        }
        System.out.println(sum);
        return sum;
    }

    public void updatePrivateShares(Shares share){
        em.getTransaction().begin();
        Query q = em.createQuery("SELECT s FROM PrivateShares s WHERE s.tickr = :tic");
        q.setParameter("tic",share.getTickr());
        ArrayList<PrivateShares> temp = new ArrayList<>(q.getResultList());
        em.getTransaction().commit();
        for(PrivateShares t : temp){
            t.setSharePrice(share.getSharePrice());
            this.update(t);
        }
    }

    public boolean currencyNameCheck(String s){
        em.getTransaction().begin();
        Query q = em.createQuery("SELECT s FROM Currency s WHERE s.name = :name");
        q.setParameter("name",s);
        if(q.getResultList().isEmpty()){
            em.getTransaction().commit();
            return true;
        }else{
            em.getTransaction().commit();
            return false;
        }

    }
    public boolean shareNameCheck(String s){
        em.getTransaction().begin();
        Query q = em.createQuery("SELECT s FROM Shares s WHERE s.tickr = :name");
        q.setParameter("name",s);
        if(q.getResultList().isEmpty()){
            em.getTransaction().commit();
            return true;
        }else{
            em.getTransaction().commit();
            return false;
        }

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
