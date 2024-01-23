package org.poo.cb;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ExchangeRates {
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
    public void parseInput(String path) {
        try {
            String filePath = "../../../../resources/common/exchangeRates.csv";
            File fileToRead = new File(filePath);
            Scanner input = new Scanner(fileToRead);

            for (int i = 0; i < noOfCurrencies; i++) {
                String line = input.nextLine();
                String[] values = line.split(",");

                System.arraycopy(values, 0, this.rates[i], 0, noOfCurrencies);
            }
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
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

        System.out.println(indexSourceCurrency);
        System.out.println(indexDestinationCurrency);

        Double exchangeAmount = Double.parseDouble(rates[indexDestinationCurrency][indexSourceCurrency]);
        exchangeAmount = exchangeAmount * Double.parseDouble(amount);
        return exchangeAmount;
    }
}
