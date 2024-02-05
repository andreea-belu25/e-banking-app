package org.poo.cb;

public abstract class ObjectPortofolio {
    public enum Type {
        ACTION, ACCOUNT;
    }
    public abstract void addAmount(String amount);
    public abstract void removeAmount(String amount);
    public abstract Number getAmount();
}
