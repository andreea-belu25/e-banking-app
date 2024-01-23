package org.poo.cb;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        if(args == null) {
            System.out.println("Running Main");
            return;
        }

        String src = "src/main/resources/";
//        String argss = "";
//        for (int i = 0; i < args.length; i++)
//            argss += args[i] + "|";
//        System.out.println(argss);
        args = new String[]{"a", "b", "c"};

        UserService userService = UserService.InstantaUserService();  //  Singleton
        ParseInput input = new ParseInput();
        ArrayList<ArrayList<String>> raggedMatrix = new ArrayList<>();
        raggedMatrix = input.read(src + args[2]);

        ExchangeRates inputExchangeRates = ExchangeRates.InstantaExchangeRates();
        inputExchangeRates.parseInput(src + args[0]);
        StockValues inputStockValues = StockValues.InstantaStockValues();
        inputStockValues.parseInput(src + args[1]);

        for (int indexRow = 0; indexRow < raggedMatrix.toArray().length; indexRow++) {
            ArrayList<String> row = raggedMatrix.get(indexRow);
            for (String data: row) {
                String[] parts = data.split(" ");
                String action = parts[0] + " " + parts[1];

                if (action.equals("CREATE USER")) {
                    userService.splitElementsCreateUser(parts);
                    continue;
                }

                if (action.equals("ADD FRIEND")) {
                    userService.splitElementsAddFriend(parts);
                    continue;
                }

                if (action.equals("LIST USER")) {
                    userService.splitElementsListUser(parts);
                    continue;
                }

                if (action.equals("ADD ACCOUNT")) {
                    userService.splitElementsAddAccount(parts);
                    continue;
                }

                if (action.equals("ADD MONEY")) {
                    userService.splitElementsAddMoney(parts);
                    continue;
                }

                if (action.equals("LIST PORTFOLIO")) {
                    userService.splitElementsListPortofolio(parts);
                    continue;
                }

                if (action.equals("TRANSFER MONEY")) {
                    userService.splitElementsTransferMoney(parts);
                    continue;
                }

                if (action.equals("BUY STOCKS")) {
                    userService.splitElementsBuyStocks(parts);
                    continue;
                }

                if (action.equals("EXCHANGE MONEY")) {
                    userService.splitElementsExchangeMoney(parts);
                    continue;
                }

                if (action.equals("RECOMMEND STOCKS")) {
                    userService.recommendStocks();
                    continue;
                }

                if (action.equals("BUY PREMIUM")) {
                    userService.splitElementsBuyPremium(parts);
                    continue;
                }
            }
        }
    }
}