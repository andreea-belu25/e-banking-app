package org.poo.cb;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class StockValues implements ICSVReader {
    private HashMap<String, ArrayList<Double>> values;
    private static StockValues stockValues;
    private StockValues() {}
    public static StockValues InstantaStockValues() {
        if (stockValues == null) {
            stockValues = new StockValues();
        }
        return stockValues;
    }

    public void setValues(HashMap<String, ArrayList<Double>> values) {
        this.values = values;
    }
    public HashMap<String, ArrayList<Double>> getValues() {
        return this.values;
    }

    public void accept(ICSVVisitor visitor, String path) {
        visitor.visit(this, path);
    }

    public Double getStockValueOnDay(String company, Integer day) {
        HashMap<String, ArrayList<Double>> values = this.getValues();
        ArrayList<Double> stockValues = values.get(company);
        return stockValues.get(day);
    }

    public Double getStockValue(String company) {
        return this.getStockValueOnDay(company, 0);
    }
    public ArrayList<String> recommendStocks() {
        HashMap<String, ArrayList<Double>> values = this.getValues();
        ArrayList<String> recommendedStocks = new ArrayList<>();
        for (String key : values.keySet()) {
            double fiveDaysSum = 0.0;
            double allDaysSum = 0.0;

            int offset = 100000000;
            if (key.equals("Stock") || key.isEmpty())
                continue;
            for (int index = 0; index < 5; index++) {
                Double a = fiveDaysSum;
                fiveDaysSum = fiveDaysSum * offset + this.getStockValueOnDay(key, index) * offset;
                fiveDaysSum = fiveDaysSum / offset;
            }
//            allDaysSum += fiveDaysSum;
            for (int index = 0; index < 10; index++) {
                Double a = allDaysSum;
                allDaysSum = allDaysSum * offset + this.getStockValueOnDay(key, index) * offset;
                allDaysSum = allDaysSum / offset;
            }
            double averageFiveDays = fiveDaysSum * 0.20f;
            double averageAllDays = allDaysSum * 0.10f;
            if (averageFiveDays >= averageAllDays) {
                recommendedStocks.add(key);
            }
        }
        return recommendedStocks;
    }
}
