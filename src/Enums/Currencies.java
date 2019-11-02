package Enums;

/**
 * This class is used to save currencies, which is used in this bank
 */
public enum Currencies {
    USADollar("USD"), EURO("EUR"), JapanYen("JPY");

    private String value;

    Currencies(String usd) {
        this.value = usd;
    }
    public String getValue(){
        return this.value;
    }


    @Override
    public String toString() {
        return this.value;
    }
}
