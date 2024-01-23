package org.poo.cb;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class StockValues {
    //  to do -- lista valori pe ultimele zile
    private HashMap<String, ArrayList<String>> values;
    private static StockValues stockValues;
    private StockValues() {}
    public static StockValues InstantaStockValues() {
        if (stockValues == null) {
            stockValues = new StockValues();
        }
        return stockValues;
    }
    public HashMap<String, ArrayList<String>> getValues() {
        return this.values;
    }
    public void parseInput(String path) {
        try {
            String filePath = "../../../../resources/test2/stockValues.csv";
            File fileToRead = new File(filePath);

            Scanner input = new Scanner(fileToRead);
            this.values = new HashMap<>();
            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] values = line.split(",");

                this.values.put(values[0], new ArrayList<>());
                for (int i = 1; i < values.length; i++)
                    this.values.get(values[0]).add(values[i]);
            }

        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
    }
    public Double getStockValueOnDay(String company, Integer day) {
        HashMap<String, ArrayList<String>> values = this.getValues();
        ArrayList<String> stockValues = values.get(company);
        double value = Double.parseDouble(stockValues.get(day));
        value = Math.floor(value * 100) / 100;
        return value;
    }
    public Double getStockValue(String company) {
        return this.getStockValueOnDay(company, 9);
    }
    public ArrayList<String> recommendStocks() {
        HashMap<String, ArrayList<String>> values = this.getValues();
        ArrayList<String> recommendedStocks = new ArrayList<>();
        for (String key : values.keySet()) {
            double fiveDaysSum = 0;
            double allDaysSum = 0;

            if (key.equals("Stock"))
                continue;
            for (int index = 0; index < 5; index++)
                fiveDaysSum += this.getStockValueOnDay(key, index);

            allDaysSum += fiveDaysSum;
            for (int index = 5; index < 10; index++)
                allDaysSum += this.getStockValueOnDay(key, index);

            double averageFiveDays = fiveDaysSum * 0.2;
            double averageAllDays = allDaysSum * 0.1;
            if (averageFiveDays < averageAllDays) {
                recommendedStocks.add(key);
            }
        }
        return recommendedStocks;
    }
}
