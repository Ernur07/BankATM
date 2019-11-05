package Entities;

public class Shares {

    protected String CompanyName;
    protected String Tickr;
    protected double SharePrice;
    protected double AmountofShares;

    public Shares(String companyName, String tickr, double sharePrice, double amountofShares) {
        CompanyName = companyName;
        Tickr = tickr;
        SharePrice = sharePrice;
        AmountofShares = amountofShares;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getTickr() {
        return Tickr;
    }

    public void setTickr(String tickr) {
        Tickr = tickr;
    }

    public double getSharePrice() {
        return SharePrice;
    }

    public void setSharePrice(double sharePrice) {
        SharePrice = sharePrice;
    }

    public double getAmountofShares() {
        return AmountofShares;
    }

    public void setAmountofShares(double amountofShares) {
        AmountofShares = amountofShares;
    }
}
