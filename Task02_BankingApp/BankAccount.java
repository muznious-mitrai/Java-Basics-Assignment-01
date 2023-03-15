package Task02_BankingApp;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

enum AccountType {
    SAVINGS, CURRENT, FIXED_DEPOSIT
}

public class BankAccount {

    private long accountNumber;
    private String accountName;
    private double balance = 0.0;
    private AccountType accountType;

    private ArrayList<Transaction> transactions;

    public BankAccount(long accountNumber, String accountName, double balance, AccountType accountType) {
        this.transactions = new ArrayList<>();
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.accountType = accountType;

        System.out.println("Account Created Successfully!\nAccount Number: " + accountNumber);

        // Initial deposit
        this.deposit(balance);
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public boolean deposit(double amount) {

        if (amount <= 0) {
            System.out.println("Transaction Failed!");
            System.out.println("Cannot deposit the amount. The amount should be greater than 0");
            return false;
        }

        this.balance += amount;
        doTransaction(amount, TransactionType.DEPOSIT);
        return true;
    }

    public boolean withdraw(double amount) {

        if (this.balance < amount || amount <= 0) {
            System.out.println("Transaction Failed!");
            System.out.println("Cannot withdraw the amount. The amount should be less than account balance and greater than 0\nCurrent Balance: " + this.balance);
            return false;
        }
        this.balance -= amount;
        doTransaction(amount, TransactionType.WITHDRAWAL);
        return true;
    }

    public void doTransaction(double txAmount, TransactionType txType) {
        int txId = Integer.parseInt(String.valueOf(accountNumber).substring(2, 8) + this.transactions.size());
        Transaction transaction = new Transaction(txId, LocalDateTime.now(), txAmount, txType);
        this.transactions.add(transaction);
        System.out.println("Transaction Completed!");
    }
}
