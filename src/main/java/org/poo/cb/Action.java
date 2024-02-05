package org.poo.cb;

public class Action extends ObjectPortofolio {
    private String name;
    private Integer noOfStocks;

    public Action(String name, Integer noOfStocks) {
        this.name = name;
        this.noOfStocks = noOfStocks;
    }
    public String getName() {
        return name;
    }
    public Integer getAmount() {
        return noOfStocks;
    }

    public void addAmount (String amount) {
        this.noOfStocks = this.noOfStocks + Integer.parseInt(amount);
    }
    public void removeAmount (String amount) {
        this.noOfStocks = this.noOfStocks - Integer.parseInt(amount);
    }
}
