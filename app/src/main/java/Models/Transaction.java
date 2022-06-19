package Models;

import java.util.Date;

public class Transaction {
    private int transactionID;
    private int userID;
    private Item item;
    private int count;
    private int TotalPrice;
    private String transactionDate;

    public Transaction(int transactionID, int userID, Item item, int count, int totalPrice, String transactionDate) {
        this.transactionID = transactionID;
        this.userID = userID;
        this.item = item;
        this.count = count;
        TotalPrice = totalPrice;
        this.transactionDate = transactionDate;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionID=" + transactionID +
                ", userID=" + userID +
                ", item=" + item +
                ", count=" + count +
                ", TotalPrice=" + TotalPrice +
                ", transactionDate='" + transactionDate + '\'' +
                '}';
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        TotalPrice = totalPrice;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }
}
