package Entities;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Shares {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String companyName;
    private String tickr;
    private double sharePrice;
    private double amountOfShares;

    public Shares(String companyName, String tickr, double sharePrice, double amountofShares) {
        this.companyName = companyName;
        this.tickr = tickr;
        this.sharePrice = sharePrice;
        this.amountOfShares = amountofShares;
    }

    public Shares() {}

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTickr() {
        return this.tickr;
    }

    public void setTickr(String tickr) {
        this.tickr = tickr;
    }

    public double getSharePrice() {
        return this.sharePrice;
    }

    public void setSharePrice(double sharePrice) {
        this.sharePrice = sharePrice;
    }

    public double getAmountofShares() {
        return this.amountOfShares;
    }

    public void setAmountofShares(double amountofShares) {
        this.amountOfShares = amountofShares;
    }
}
