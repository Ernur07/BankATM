package Entities;

public class PrivateShares extends Shares {

    private double BoughtPrice;
    public PrivateShares(String companyName, String tickr, double sharePrice, double amountofShares, double BoughtPrice) {
        super(companyName, tickr, sharePrice, amountofShares);
    }

    public double getBoughtPrice() {
        return BoughtPrice;
    }

    public void setBoughtPrice(double boughtPrice) {
        this.BoughtPrice = boughtPrice;
    }
}
