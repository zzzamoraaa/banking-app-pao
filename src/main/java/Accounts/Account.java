package Accounts;

import Card.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Account {
    protected String IBAN;
    protected float amount;
    protected String name;
    protected int clientId;

    protected List<Card> cards = new ArrayList<>();

    private final CardGeneration cardGeneration = new CardGeneration();

    public Account(String IBAN, long amount, String name, int clientId){
        this.IBAN = IBAN;
        this.amount = amount;
        this.name = name;
        this.clientId = clientId;
    }

    public Account(String name, int clientId, int uniqueId) {
        this.IBAN = this.generateIBAN(uniqueId);
        this.amount = 0;
        this.name = name;
        this.clientId = clientId;
    }

    public Account(ResultSet in) throws SQLException {
        this.IBAN = in.getString("IBAN");
        this.amount = in.getFloat("amount");
        this.name = in.getString("name");
        this.clientId = in.getInt("clientId");
    }

    public List<Transaction> filterTransactions(List<Transaction> allTransactions){
        List<Transaction> transactions = new ArrayList<>();
        for(var transaction: allTransactions)
            if(transaction.getFromIBAN().equals(this.IBAN))
                transactions.add(transaction);
        return transactions;
    }

    public List<Transaction> filterTransactions(List<Transaction> allTransactions, int year){
        List<Transaction> transactions = new ArrayList<>();
        for(var transaction: allTransactions)
            if(transaction.getFromIBAN().equals(this.IBAN) && transaction.getTransactionDate().getYear()==year)
                transactions.add(transaction);
        return transactions;
    }

    public String getIBAN(){
        return IBAN;
    }
    public float getAmount()
    {
        return amount;
    }
    public String getName()
    {
        return name;
    }
    public int getClientId()
    {
        return clientId;
    }

    public void addCard(String name){
        Card newCard = cardGeneration.addCard(this.IBAN, name);
        cards.add(newCard);
    }
    public void setAmount(float amount){
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Account{" +
                "IBAN='" + IBAN + '\'' +
                ", amount=" + amount +
                ", name='" + name + '\'' +
                ", clientId=" + clientId +
                '}';
    }
    public String toCSV() {
        return IBAN +
                "," + amount +
                "," + name +
                "," + clientId;
    }

    private String generateIBAN(int uniqueId){
        String bank = "BRD";

        return "RO06" + bank + "B" + uniqueId;
    }


}
