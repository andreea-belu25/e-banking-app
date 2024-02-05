package org.poo.cb;


public class ExchangeRates implements ICSVReader {
    private final Integer noOfCurrencies = 6;
    private String[][] rates = new String[noOfCurrencies][noOfCurrencies];
    private static ExchangeRates exchangeRates;
    private ExchangeRates() {}
    public String[][] getRates() {
        return this.rates;
    }
    public static ExchangeRates InstantaExchangeRates() {
        if (exchangeRates == null) {
            exchangeRates = new ExchangeRates();
        }
        return exchangeRates;
    }

    public Integer getNoOfCurrencies() {
        return noOfCurrencies;
    }

    public void accept(ICSVVisitor visitor, String path) {
        visitor.visit(this, path);
    }

    public Double getExchangeAmount(String sourceCurrency, String destinationCurrency, String amount) {
        String[][] rates = this.getRates();

        int indexDestinationCurrency = 0;
        for (int indexLine = 0; indexLine < noOfCurrencies; indexLine++)
            if (rates[indexLine][0].equals(destinationCurrency))
                indexDestinationCurrency = indexLine;

        int indexSourceCurrency = 0;
        for (int indexColumn = 0; indexColumn < noOfCurrencies; indexColumn++)
            if (rates[0][indexColumn].equals(sourceCurrency))
                indexSourceCurrency = indexColumn;

        Double exchangeAmount = Double.parseDouble(rates[indexDestinationCurrency][indexSourceCurrency]);
        exchangeAmount = exchangeAmount * Double.parseDouble(amount);
        return exchangeAmount;
    }
}
