package Entities;

import java.util.ArrayList;

public class SecurityAccount extends Account {

    private static final double minimumBalance = 100000;
    private ArrayList<Shares> shares;

    public SecurityAccount(String name, int balance, String c) {
        super(name, balance, c);
    }

    public ArrayList<Shares> getShares() {
        return shares;
    }

    public void setShares(ArrayList<Shares> shares) {
        this.shares = shares;
    }
}
