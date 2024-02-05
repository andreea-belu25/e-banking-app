package org.poo.cb;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CSVVisitor implements ICSVVisitor {
    public void visit(ExchangeRates rates, String path) {
        try {
            String filePath = "../../../../resources/common/exchangeRates.csv";
            File fileToRead = new File(filePath);
            Scanner input = new Scanner(fileToRead);

            for (int i = 0; i < rates.getNoOfCurrencies(); i++) {
                String line = input.nextLine();
                String[] values = line.split(",");

                System.arraycopy(values, 0, rates.getRates()[i], 0, rates.getNoOfCurrencies());
            }
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
    }
    public void visit(StockValues values, String path) {
        try {
            String filePath = "../../../../resources/test9_bonus/stockValues.csv";
            File fileToRead = new File(filePath);

            Scanner input = new Scanner(fileToRead);
            HashMap<String, ArrayList<Double>> stockValues = new HashMap<>();
            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] data = line.split(",");
                if (data[0].equals("Stock") || data[0].isEmpty())
                    continue;

                stockValues.put(data[0], new ArrayList<>());
                for (int i = 1; i < data.length; i++)
                    stockValues.get(data[0]).add(0, Double.parseDouble(data[i]));
            }
            values.setValues(stockValues);
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
    }
}
