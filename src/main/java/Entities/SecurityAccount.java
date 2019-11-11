package Entities;

import DatabaseConnection.DatabaseManager;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("security")
public class SecurityAccount extends Account {

    public static final double minimumBalance = 100000;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "security_account_id")
    private List<PrivateShares> shares;

    public SecurityAccount(String name, int balance, Currency c, Client owner) {
        super(name, balance, c,owner);
        shares = new ArrayList<PrivateShares>();
    }

    public SecurityAccount() {}

    public List<PrivateShares> getShares() {
        return shares;
    }

    public void setShares(List<PrivateShares> shares) {

        this.shares = shares;
        updateBalance();
    }

    public void addShare(PrivateShares share) {

        this.shares.add(share);
        updateBalance();
    }

    public void updateBalance() {
        DatabaseManager db = new DatabaseManager();
        super.setBalance(db.getSecurityAccountBalance(this));
    }

    @Override
    public double getBalance() {
        updateBalance();
        return super.getBalance();
    }
}
