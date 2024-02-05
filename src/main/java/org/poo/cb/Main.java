package org.poo.cb;

import org.poo.cb.Commands.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        if(args == null) {
            System.out.println("Running Main");
            return;
        }

        String src = "src/main/resources/";

        ParseInput input = new ParseInput();
        ArrayList<ArrayList<String>> raggedMatrix;
        raggedMatrix = input.read(src + args[2]);
        CSVVisitor visitor = new CSVVisitor();

        ExchangeRates inputExchangeRates = ExchangeRates.InstantaExchangeRates();
        inputExchangeRates.accept(visitor, src + args[0]);
        StockValues inputStockValues = StockValues.InstantaStockValues();
        inputStockValues.accept(visitor, src + args[1]);

        for (int indexRow = 0; indexRow < raggedMatrix.toArray().length; indexRow++) {
            ArrayList<String> row = raggedMatrix.get(indexRow);
            for (String data: row) {
                String[] parts = data.split(" ");
                String action = parts[0] + " " + parts[1];

                if (action.equals("CREATE USER")) {
                    Command command = new CommandCreateUser();
                    command.execute(parts);
                    continue;
                }

                if (action.equals("ADD FRIEND")) {
                    Command command = new CommandAddFriend();
                    command.execute(parts);
                    continue;
                }

                if (action.equals("LIST USER")) {
                    Command command = new CommandListUser();
                    command.execute(parts);
                    continue;
                }

                if (action.equals("ADD ACCOUNT")) {
                    Command command = new CommandAddAccount();
                    command.execute(parts);
                    continue;
                }

                if (action.equals("ADD MONEY")) {
                    Command command = new CommandAddMoney();
                    command.execute(parts);
                    continue;
                }

                if (action.equals("LIST PORTFOLIO")) {
                    Command command = new CommandListPortofolio();
                    command.execute(parts);
                    continue;
                }

                if (action.equals("TRANSFER MONEY")) {
                    Command command = new CommandTransferMoney();
                    command.execute(parts);
                    continue;
                }

                if (action.equals("BUY STOCKS")) {
                    Command command = new CommandBuyStocks();
                    command.execute(parts);
                    continue;
                }

                if (action.equals("EXCHANGE MONEY")) {
                    Command command = new CommandExchangeMoney();
                    command.execute(parts);
                    continue;
                }

                if (action.equals("RECOMMEND STOCKS")) {
                    Command command = new CommandRecommendStocks();
                    command.execute(parts);
                    continue;
                }

                if (action.equals("BUY PREMIUM")) {
                    Command command = new CommandBuyPremium();
                    command.execute(parts);
                }
            }
        }
    }
}