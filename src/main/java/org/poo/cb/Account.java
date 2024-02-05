package org.poo.cb;

public class Account extends ObjectPortofolio {
    private final String currencyType;
    private Double amount;

    public Account(String currencyType, Double amount) {
        this.currencyType = currencyType;
        this.amount = amount;
    }

    public String getCurrencyType() {
        return currencyType;
    }
    public Double getAmount() {
        return amount;
    }

    public void addAmount (String amount) {
        this.amount = this.amount + Double.parseDouble(amount);
    }
    public void removeAmount (String amount) {
        this.amount = this.amount - Double.parseDouble(amount);
    }


}
