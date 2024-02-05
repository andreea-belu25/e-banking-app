package org.poo.cb;

public interface ICSVVisitor {
    public void visit(ExchangeRates rates, String path);
    public void visit(StockValues values, String path);
}
