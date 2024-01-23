package org.poo.cb;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserService {
    HashMap<String, User> users = new HashMap<>();
    private static UserService userService;
    private UserService() {}
    public static UserService InstantaUserService() {
        if (userService == null)
            userService = new UserService();
        return userService;
    }
    public void createUser(String email, String firstName, String lastName, String address, HashMap<String, User> friends) throws IOException {
        User user = new User(email, firstName, lastName, address);
        if (users.containsKey(email)) {
            String message = String.format("User with %s already exists", email);
            System.out.println(message);
            return;
        }

        users.put(email, user);
    }
    public void splitElementsCreateUser(String[] parts) {
        String email = parts[2];
        String firstName = parts[3];
        String lastName = parts[4];
        String address = "";
        for (int indexParts = 5; indexParts < parts.length - 1; indexParts++) {
            address = address + parts[indexParts];
            address = address + " ";
        }
        address = address + parts[parts.length - 1];
        try {
            createUser(email, firstName, lastName, address, null);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public User getUserByEmail (String email) {
        return users.get(email);
    }

    public void addFriend(String emailUser, String emailFriend) {
        if (!users.containsKey(emailUser)) {
            String message = String.format("User with %s doesn't exist", emailUser);
            System.out.println(message);
            return;
        }

        if (!users.containsKey(emailFriend)) {
            String message = String.format("User with %s doesn't exist", emailFriend);
            System.out.println(message);
            return;
        }

        User user = getUserByEmail(emailUser);
        if (user.hasFriend(emailFriend)) {
            String message = String.format("User with %s is already a friend", emailFriend);
            System.out.println(message);
            return;
        }

        User friend = getUserByEmail(emailFriend);
        user.addFriendToUser(friend);
        friend.addFriendToUser(user);
    }

    public void splitElementsAddFriend(String[] parts) {
        String emailUser = parts[2];
        String emailFriend = parts[3];
        addFriend(emailUser, emailFriend);
    }
    public void listUser(String email) {
        User userToList = getUserByEmail(email);
        if (!users.containsKey(email)) {
            String message =  String.format("User with %s doesn't exist", email);
            System.out.println(message);
            return;
        }

        String message = "{\"email\":\"" + userToList.getEmail() + "\"," + "\"firstname\":\"" + userToList.getFirstName() + "\"," + "\"lastname\":\""
                + userToList.getLastName() + "\"," + "\"address\":\" " + userToList.getAddress() + "\"," + "\"friends\":[";
        String emailFriend = "";
        for (Map.Entry<String, User> entry : userToList.getFriends().entrySet()) {
            emailFriend = entry.getKey();
            message = message + "\"" + emailFriend + "\"" + ",";
        }
        if (!emailFriend.isEmpty())
            message = message.substring(0, message.length() - 1) + "]}";
        else
            message = message + "]}";
        System.out.println(message);
    }
    public void splitElementsListUser(String[] parts) {
        String email = parts[2];
        listUser(email);
    }
    public void addPortofolioObject(String type, String objectIdentifier, ArrayList<Object> params, User user) {
        PortofolioFactory portofolioFactory = PortofolioFactory.InstantaPortofolioFactory();
        ObjectPortofolio objectPortofolio = portofolioFactory.createPortofolioObject(type, params);
        user.addObjectPortofolio(type, objectIdentifier, objectPortofolio);
    }
    public void addAccount(String email, String currencyType) {
        User user = getUserByEmail(email);
        if (user.hasAccount(currencyType)) {
            String message =  String.format("Account in currency %s already exists for user", currencyType);
            System.out.println(message);
            return;
        }

        ArrayList<Object> params = new ArrayList<>();
        params.add(currencyType);
        params.add(0.00);
        addPortofolioObject(String.valueOf(ObjectPortofolio.Type.ACCOUNT), currencyType, params, user);
    }
    public void splitElementsAddAccount (String[] parts) {
        String email = parts[2];
        String currencyType = parts[3];

        addAccount(email, currencyType);
    }
    public void addMoney (String email, String currency, String amount) {
        User user = getUserByEmail(email);
        ObjectPortofolio objectPortofolio = user.getObjectPortofolio(String.valueOf(ObjectPortofolio.Type.ACCOUNT), currency);
        Account account = (Account) objectPortofolio;
        account.addMoney (amount);
    }
    public void splitElementsAddMoney (String[] parts) {
        String email = parts[2];
        String currency = parts[3];
        String amount = parts[4];

        addMoney(email, currency, amount);
    }
    public void listPortofolio (String email) {
        if (!users.containsKey(email)) {
            String message =  String.format("User with %s doesn’t exist", email);
            System.out.println(message);
            return;
        }

        User user = getUserByEmail(email);
        HashMap<String, ObjectPortofolio> portofolio = user.getPortofolio();
        String messageAccounts = "";
        String messageStocks = "";
        for (ObjectPortofolio object: portofolio.values()) {
            if (object instanceof Account account) {
                Double amount = account.getAmount();
                String stringAmount = String.format("%.2f", amount);
                String stringAmountDott = stringAmount.replace(',', '.');
                messageAccounts = messageAccounts + ",{\"currencyName\":\"" + account.getCurrencyType() + "\",\"amount\":\"" + stringAmountDott + "\"}";
            } else if (object instanceof Action action) {
                messageStocks = messageStocks + ",{\"stockName\":\"" + action.getName() + "\",\"amount\":\"" + action.getNoOfStocks() + "\"}";
            }
        }
        if (!messageAccounts.isEmpty())
            messageAccounts = messageAccounts.substring(1);
        if (!messageStocks.isEmpty())
            messageStocks = messageStocks.substring(1);

        String message = "{\"stocks\":[" + messageStocks + "]," + "\"accounts\":[" + messageAccounts + "]}";
        System.out.println(message);
    }
    public void splitElementsListPortofolio (String[] parts) {
        String email = parts[2];
        listPortofolio(email);
    }
    public void transferMoney(String email, String emailFriend, String currency, String amount) {
        User user = getUserByEmail(email);
        if(!user.hasFriend(emailFriend)) {
            String message =  String.format("You are not allowed to transfer money to %s", emailFriend);
            System.out.println(message);
            return;
        }

        Double currentAmount = user.getAccountAmount(currency);
        if (currentAmount < Double.parseDouble(amount)) {
            String message =  String.format("Insufficient amount in account %s for transfer", currency);
            System.out.println(message);
            return;
        }

        user.removeAccountMoney(currency, amount);
        User friend = getUserByEmail(emailFriend);
        friend.addAccountMoney(currency, amount);
    }
    public void splitElementsTransferMoney(String[] parts) {
        String email = parts[2];
        String emailFriend = parts[3];
        String currency = parts[4];
        String amount = parts[5];

        transferMoney(email, emailFriend, currency, amount);
    }
    public void buyStocks(String email, String company, String noOfStocks) {
        User user = getUserByEmail(email);
        Double amountUSD = user.getAccountAmount("USD");
        StockValues stockValues = StockValues.InstantaStockValues();
        Double valueStock = stockValues.getStockValue(company);
        Double priceForStocks = Double.parseDouble(noOfStocks) * valueStock;

        if (user.hasPremium()) {
            StockValues values = StockValues.InstantaStockValues();
            ArrayList<String> recommendedStocks = values.recommendStocks();
            if (recommendedStocks.contains(company))
                priceForStocks = priceForStocks - priceForStocks * 0.05;
        }

        if (priceForStocks > amountUSD) {
            System.out.println("Insufficient amount in account for buying stocks");
            return;
        }

        user.removeAccountMoney("USD", priceForStocks.toString());
        ArrayList<Object> params = new ArrayList<>();
        params.add(company);
        params.add(Integer.parseInt(noOfStocks));
        addPortofolioObject(String.valueOf(ObjectPortofolio.Type.ACTION), company, params, user);
    }
    public void splitElementsBuyStocks(String[] parts) {
        String email = parts[2];
        String company = parts[3];
        String noOfStocks = parts[4];

        buyStocks(email, company, noOfStocks);
    }
    public void exchangeMoney(String email, String sourceCurrency, String destinationCurrency, String amount) {
        ExchangeRates exchangeRates = ExchangeRates.InstantaExchangeRates();
        Double exchangeAmount = exchangeRates.getExchangeAmount(sourceCurrency, destinationCurrency, amount);
        User user = getUserByEmail(email);

        System.out.println(exchangeAmount);
        if (!user.hasPremium() && exchangeAmount > 0.5 * user.getAccountAmount(sourceCurrency))
            exchangeAmount = exchangeAmount + 0.01 * exchangeAmount;

        if (exchangeAmount > user.getAccountAmount(sourceCurrency) ) {
            String message = String.format("Insufficient amount in account %s for exchange", sourceCurrency);
            System.out.println(message);
            return;
        }

        user.removeAccountMoney(sourceCurrency, exchangeAmount.toString());
        user.addAccountMoney(destinationCurrency, amount);
    }
    public void splitElementsExchangeMoney(String[] parts) {
        String email = parts[2];
        String sourceCurrency = parts[3];
        String destinationCurrency = parts[4];
        String amount = parts[5];

        exchangeMoney(email, sourceCurrency, destinationCurrency, amount);
    }
    public void recommendStocks() {
        StockValues stockValues = StockValues.InstantaStockValues();
        ArrayList<String> recommendedStocks = stockValues.recommendStocks();
//        System.out.println(recommendedStocks);
        // {"stocksToBuy":["MSFT","NFLX","META","GOOGL","GOOG","NVDA","AMZN"]}
        String message = "{\"stocksToBuy\":[";
        for (int index = 0; index < recommendedStocks.size(); index++) {
            message = message + String.format("\"%s\",", recommendedStocks.get(index));
        }
        if (!recommendedStocks.isEmpty()) {
            message = message.substring(0, message.length() - 1);
            message = message + "]}";
        }
        System.out.println(message);
    }
    public void buyPremium(String email) {
        if (!users.containsKey(email)) {
            String message = String.format("User with %s doesn’t exist", email);
            System.out.println(message);
            return;
        }

        User user = getUserByEmail(email);
        if (user.hasPremium())
            return;

        Double amount = user.getAccountAmount("USD");
        if (amount < 100) {
            System.out.println("Insufficient amount in account for buying premium option");
            return;
        }

        user.removeAccountMoney("USD", "100");
        user.setHasPremium(true);
    }
    public void splitElementsBuyPremium(String[] parts) {
        String email = parts[2];
        buyPremium(email);
    }
}
