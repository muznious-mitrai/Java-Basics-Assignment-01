package Task02_BankingApp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Bank {

    private final ArrayList<Customer> customers;
    private final ArrayList<BankAccount> bankAccounts;
    private ArrayList<Transaction> transactions;

    public Bank() {
        this.customers = new ArrayList<>();
        this.bankAccounts = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

    public static void main(String[] args) {

        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);

        bank.addDummyData();

        while (true) {
            System.out.println("=====================================");
            System.out.println("Welcome to Our Banking App");
            System.out.println("=====================================");
            System.out.println("[1] - Create Customer");
            System.out.println("[2] - Make Transaction");
            System.out.println("[3] - Display Customers");
            System.out.println("[4] - Display Bank Accounts");
            System.out.println("[5] - Display Transactions");
            System.out.println("[99] - Exit");
            System.out.println("Enter an option:");
            try {
                int option = scanner.nextInt();
                scanner.nextLine(); // this will return carriage return /r/n only.
                switch (option) {
                    case 1: // create customer
                        bank.createCustomer(scanner);
                        scanner.nextLine(); // clear buffer
                        break;
                    case 2: // make transactions
                        bank.makeTransactions(scanner);
                        scanner.nextLine(); // clear buffer
                        break;
                    case 3: // display customers
                        bank.displayCustomers();
                        break;
                    case 4: // display Bank Accounts
                        bank.displayBankAccounts();
                        break;
                    case 5: // display Bank Accounts
                        bank.displayTransactions();
                        break;
                    case 99: // exit
                        scanner.close();
                        System.exit(0);
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println("Press [Enter] to continue");
                scanner.nextLine();
            }
        }

    }

    void addDummyData() {
        this.customers.addAll(Arrays.asList(
                new Customer(1000, "Gihan Liyanage", "gihan@gmail.com", "+94710854933"),
                new Customer(1001, "Muzny M", "muzny@gmail.com", "+94710230983"),
                new Customer(1002, "Iona Norman", "iona@gmail.com", "+94710450983"))
        );

        this.bankAccounts.addAll(Arrays.asList(
                new BankAccount(5051000001L, "Gihan Liyanage", 5000, AccountType.CURRENT),
                new BankAccount(5051001001L, "Muzny M", 3000, AccountType.SAVINGS),
                new BankAccount(5051002001L, "Iona Norman", 8500, AccountType.FIXED_DEPOSIT))
        );
    }

    public void addCustomer(Customer customer) {
        this.customers.add(customer);
    }

    public void removeCustomer(int index) {
        this.customers.remove(index);
    }

    public void addBankAccount(BankAccount bankAccount) {
        this.bankAccounts.add(bankAccount);
    }

    public void removeBankAccount(int index) {
        this.bankAccounts.remove(index);
    }

    public void displayCustomers() {
        System.out.println("\nCUSTOMERS");
        System.out.println("----------------------------------------------------------");
        System.out.println("ID\t\t|\tName\t|\tEmailAddress\t|\tPhoneNumber");
        System.out.println("----------------------------------------------------------");
        for (Customer customer : this.customers) {
            System.out.println(customer.getId()
                    + "\t|\t" + customer.getName()
                    + "\t|\t" + customer.getEmailAddress()
                    + "\t|\t" + customer.getPhoneNumber()
            );
        }
        System.out.println("----------------------------------------------------------");
    }

    public void displayBankAccounts() {
        System.out.println("\nBANK ACCOUNTS");
        System.out.println("----------------------------------------------------------");
        System.out.println("Acc#\t|\tAccountName\t|\tBalance\t|\tAccountType");
        System.out.println("----------------------------------------------------------");
        for (BankAccount bankAccount : this.bankAccounts) {
            System.out.println(bankAccount.getAccountNumber()
                    + "\t|\t" + bankAccount.getAccountName()
                    + "\t|\t" + bankAccount.getBalance()
                    + "\t|\t" + bankAccount.getAccountType()
            );
        }
        System.out.println("----------------------------------------------------------");
    }

    public void displayTransactions() {
        System.out.println("\nTRANSACTIONS");
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("TrxId#\t|\tTransaction Date\t|\tAccountNumber\t|\tAmount\t|\tTransactionType");
        System.out.println("-------------------------------------------------------------------------------------");
        for (Transaction transaction : this.transactions) {
            System.out.println(transaction.getId()
                    + "\t|\t" + transaction.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                    + "\t|\t" + transaction.getAccountNumber()
                    + "\t|\t" + transaction.getAmount()
                    + "\t|\t" + transaction.getTransactionType()
            );
        }
        System.out.println("-------------------------------------------------------------------------------------");
    }

    public boolean validateDataType(Scanner scanner, String type, String prompt) {

        System.out.println(prompt);

        switch (type) {
            case "int":
            case "long":
                while (!scanner.hasNextLong()) {
                    System.out.println("Invalid Input.\nPlease enter correct " + prompt);
                    scanner.next();
                }
                break;
            case "double":
                while (!scanner.hasNextDouble()) {
                    System.out.println("Invalid Input.\nPlease enter correct " + prompt);
                    scanner.next();
                }
                break;

            default:
                while (!scanner.hasNext(type)) {
                    System.out.println("Invalid Input.\nPlease enter correct " + prompt);
                    scanner.next();
                }
        }

        return true;
    }

    public void createCustomer(Scanner scanner) {
        System.out.println("Please provide customer details");

        validateDataType(scanner, "[a-zA-Z]+", "Name:");
        String cName = scanner.nextLine();

        validateDataType(scanner, "[a-zA-Z0-9+.]+@[a-zA-Z0-9-]+\\.[a-zA-Z]+", "Email address:");
        String cEmail = scanner.next();

        validateDataType(scanner, "[0-9+]+", "Phone number:");
        String cPhone = scanner.next();

        // Auto generate index based on the size of the array
        int cIndex = this.customers.size() + 1000;
        Customer customer = new Customer(cIndex, cName, cEmail, cPhone);
        this.addCustomer(customer);

        // Create Bank Account
        this.createBankAccount(scanner, customer);
        System.out.println(" Customer ID: " + customer.getId());
    }

    public void createBankAccount(Scanner scanner, Customer customer) {
        System.out.println("Please provide bank account details");

        System.out.println("Account Holder Name: " + customer.getName() + "");
        System.out.println("Press [Y] to continue with the current customer name");
        validateDataType(scanner, "[a-zA-Z]+", "Account Holder Name: [Y] or type the correct name here");
        String accName = scanner.nextLine();
        if (accName.equalsIgnoreCase("y")) {
            accName = customer.getName();
        }

        // auto generate account number
        long accNumber = Long.parseLong("505" + customer.getId() + "001");

        validateDataType(scanner, "double", "Account Balance:");
        double accBalance = scanner.nextDouble();

        AccountType accType = getAccountTypeFromInput(scanner);

        BankAccount bankAccount = new BankAccount(accNumber, accName, accBalance, accType);
        this.addBankAccount(bankAccount);
        System.out.print("Account Created Successfully!\nAccount Number: " + accNumber);
    }


    public BankAccount getBankAccountByAccountNumber(Scanner scanner, Map<Long, BankAccount> bankAccounts, long accNumber) {
        if (!bankAccounts.containsKey(accNumber)) {
            System.out.println("Account Number not found. Please check and try again");
            return null;
        }
        return bankAccounts.get(accNumber);
    }

    private AccountType getAccountTypeFromInput(Scanner scanner) {
        AccountType accType = AccountType.SAVINGS;
        System.out.println("Account Types:");
        System.out.println("[S] - Savings Account (Default)");
        System.out.println("[C] - Current Account");
        System.out.println("[F] - Fixed Deposit Account");

        validateDataType(scanner, "s|c|f|S|C|F", "Account Type: [S] [C] [F]");
        String type = scanner.next();
        switch (type.toLowerCase()) {
            case "s":
                accType = AccountType.SAVINGS;
                break;
            case "c":
                accType = AccountType.CURRENT;
                break;
            case "f":
                accType = AccountType.FIXED_DEPOSIT;
                break;
        }
        return accType;
    }

    public TransactionType getTransactionTypeFromInput(Scanner scanner) {
        TransactionType txType = TransactionType.DEPOSIT;
        System.out.println("Transaction Types:");
        System.out.println("[D] - Deposit (Default)");
        System.out.println("[W] - Withdrawal");

        validateDataType(scanner, "d|w|D|W", "Transaction Type: [D] [W]");
        String type = scanner.next();
        switch (type.toLowerCase()) {
            case "d":
                txType = TransactionType.DEPOSIT;
                break;
            case "w":
                txType = TransactionType.WITHDRAWAL;
                break;
        }
        return txType;
    }

    public void makeTransactions(Scanner scanner) {

        Map<Long, BankAccount> bankAccountsMap = this.bankAccounts.stream().collect(
                Collectors.toMap(BankAccount::getAccountNumber, Function.identity())
        ); //Function.identity() => bankAccount -> bankAccount

        System.out.println("Please provide transaction details");

        validateDataType(scanner, "long", "Account Number:");
        long accNumber = scanner.nextLong();

        BankAccount bankAccount = getBankAccountByAccountNumber(scanner, bankAccountsMap, accNumber);
        if (bankAccount == null) return;

        TransactionType txType = getTransactionTypeFromInput(scanner);

        validateDataType(scanner, "double", "Amount:");
        double txAmount = scanner.nextDouble();
        boolean txDone = false;
        switch (txType) {
            case DEPOSIT:
                txDone = bankAccount.deposit(txAmount);
                break;
            case WITHDRAWAL:
                txDone = bankAccount.withdraw(txAmount);
                break;
        }

        if (txDone) {
            int txId = this.transactions.size() + 1000;
            Transaction transaction = new Transaction(txId, LocalDateTime.now(), txAmount, txType);
            transaction.setAccountNumber(accNumber);
            this.transactions.add(transaction);
            System.out.println("Transaction Completed!");
        }else {
            System.out.println("Transaction Not Completed. Please try again!");
        }
    }

}
