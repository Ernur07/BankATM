package Entities;

import java.util.ArrayList;

public class StockMarket {

    private ArrayList<Shares> shares;

    public StockMarket(ArrayList<Shares> shares) {
        this.shares = shares;
    }

    public ArrayList<Shares> getShares() {
        return shares;
    }

    public void setShares(ArrayList<Shares> shares) {
        this.shares = shares;
    }
}
