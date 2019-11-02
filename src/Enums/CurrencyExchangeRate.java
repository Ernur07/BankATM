package Enums;
/**
 * This class is used to save exchange rate for currencies, which is used in this bank
 */
public enum CurrencyExchangeRate {

    USA2JPN("USA2JPN",2.2),USA2EUR("USA2EUR",1.2),
    EUR2JPN("EUR2JPN",2.2),EUR2USD("EUR2USD",1.2),
    JPN2EUR("JPN2EUR",2.2),JPN2USD("JPN2USD",1.2);

    private String name;
    private Double rate;

    CurrencyExchangeRate(String name, Double rate) {
        this.name = name;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}
