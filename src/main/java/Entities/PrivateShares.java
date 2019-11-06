package Entities;

import javax.persistence.*;

@Entity
public class PrivateShares extends Shares {

    @ManyToOne(fetch = FetchType.EAGER, cascade ={CascadeType.MERGE,CascadeType.DETACH,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "security_account_id")
    private SecurityAccount securityAccount;
    private double boughtPrice;
    public PrivateShares(String companyName, String tickr, double sharePrice, double amountofShares, double boughtPrice, SecurityAccount securityAccount) {
        super(companyName, tickr, sharePrice, amountofShares);
        this.boughtPrice = boughtPrice;
        this.securityAccount=securityAccount;
    }

    public PrivateShares() {}

    public double getBoughtPrice() {
        return this.boughtPrice;
    }

    public void setBoughtPrice(double boughtPrice) {
        this.boughtPrice = boughtPrice;
    }
}
