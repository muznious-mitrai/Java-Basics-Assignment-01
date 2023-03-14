package Task02_BankingApp;

import java.util.Date;

enum TransactionType{
    CHECK, WITHDRAWAL, DEPOSIT
}

public class Transaction {

    private int id;
    private Date date;
    private double amount;
    private TransactionType transactionType;

    public Transaction(int id, Date date, double amount, TransactionType transactionType) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.transactionType = transactionType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
}
