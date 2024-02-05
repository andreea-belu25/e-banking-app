package org.poo.cb;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class User {
    private String email;  // unique for each user
    private String lastName, firstName;
    private String address;
    private boolean hasPremium = false;

    ///   to do portofoliu  --  conturi in diverse valute (currencies.txt) + actiuni (stocks.txt)
    private LinkedHashMap<String, ObjectPortofolio> portofolio;

    private HashMap<String, User> friends;

    //  !!! Orice comanda de transfer sau cumparare de
    //      actiuni/criptomonede va trebui sa se reflecte in starea conturilor și a portofoliului unui
    //      utilizator !!!


    public User(String email, String firstName, String lastName, String address) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.friends = new HashMap<>();
        this.portofolio = new LinkedHashMap<>();
    }

    public String getEmail() {
        return email;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getAddress() {
        return address;
    }

    public HashMap<String, User> getFriends() {
        return friends;
    }
    public LinkedHashMap<String, ObjectPortofolio> getPortofolio() {
        return portofolio;
    }

    public void addFriendToUser(User friend) {
        this.friends.put(friend.getEmail(), friend);
    }

    public boolean hasAccount(String currencyType) {
        if (this.getPortofolio().containsKey("#" + currencyType))
            return true;
        return false;
    }

    public boolean hasAction(String currencyType) {
        if (this.getPortofolio().containsKey(currencyType))
            return true;
        return false;
    }

    public void addObjectPortofolio(String type, String objectIdentifier, ObjectPortofolio objectPortofolio) {
        if (type.equals(String.valueOf(ObjectPortofolio.Type.ACCOUNT)))
            this.portofolio.put("#" + objectIdentifier, objectPortofolio);
        else
            this.portofolio.put(objectIdentifier, objectPortofolio);
    }

    public ObjectPortofolio getObjectPortofolio(String type, String key) {
        if (type.equals(String.valueOf(ObjectPortofolio.Type.ACCOUNT)))
            return this.portofolio.get("#" + key);
        return this.portofolio.get(key);
    }

    public Account getAccount(String currency) {
        ObjectPortofolio objectPortofolio = this.getObjectPortofolio(String.valueOf(ObjectPortofolio.Type.ACCOUNT), currency);
        return (Account) objectPortofolio;
    }

    public Double getAccountAmount(String currency) {
        Account account = this.getAccount(currency);
        return account.getAmount();
    }

    public void removeAccountMoney(String currency, String amount) {
        Account account = this.getAccount(currency);
        account.removeAmount(amount);
    }

    public void addAccountMoney(String currency, String amount) {
        Account account = this.getAccount(currency);
        account.addAmount(amount);
    }

    public boolean hasFriend (String emailFriend) {
        return this.getFriends().containsKey(emailFriend);
    }

    public void setHasPremium(boolean hasPremium) {
        this.hasPremium = hasPremium;
    }

    public boolean hasPremium() {
        return hasPremium;
    }
}
