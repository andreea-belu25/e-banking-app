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
    public Integer getNoOfStocks() {
        return noOfStocks;
    }
}
