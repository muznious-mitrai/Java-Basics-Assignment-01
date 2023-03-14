package Task02_BankingApp;

enum AccountType {
    SAVINGS, CURRENT, FIXED_DEPOSIT
}

public class BankAccount {

    private long accountNumber;
    private String accountName;
    private double balance;
    private AccountType accountType;

    public BankAccount(long accountNumber, String accountName, double balance, AccountType accountType) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.balance = balance;
        this.accountType = accountType;
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
}
