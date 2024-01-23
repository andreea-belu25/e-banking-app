package org.poo.cb;

public class Account extends ObjectPortofolio {
    private String currencyType;
    private Double amount;

    public Account(String currencyType, Double amount) {
        this.currencyType = currencyType;
        this.amount = amount;
    }
    public void addMoney (String amount) {
        this.amount = this.amount + Double.parseDouble(amount);
    }
    public void removeMoney (String amount) {
        this.amount = this.amount - Double.parseDouble(amount);
    }
    public String getCurrencyType() {
        return currencyType;
    }

    public Double getAmount() {
        return amount;
    }

}
