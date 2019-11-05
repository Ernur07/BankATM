package Entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @SequenceGenerator(name = "transaction_id_gen", sequenceName = "transaction_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_id_gen")
    int id;

    @ManyToOne(fetch = FetchType.EAGER, cascade ={CascadeType.MERGE,CascadeType.DETACH,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "receiver_account_id")
    private Account receiverAccount;

    @ManyToOne(fetch = FetchType.EAGER, cascade ={CascadeType.MERGE,CascadeType.DETACH,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "sender_account_id")
    private Account senderAccount;

    private double amount;
    private Date date;

    public Transaction(){}
    public Transaction(Client receiver, Account receiverAccount, double amount) {
        this.receiverAccount = receiverAccount;
        this.amount = amount;
        date = new Date();
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
