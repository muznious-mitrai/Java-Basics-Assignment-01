package Task02_BankingApp;

import java.time.format.DateTimeFormatter;
import java.util.*;

public class Bank {

    private final ArrayList<Customer> customers;
    private final HashMap<Long, BankAccount> bankAccounts;

    public Bank() {
        this.customers = new ArrayList<>();
        this.bankAccounts = new HashMap<>();
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
            try {
                int option = bank.getIntegerInput(scanner, "Option:");
                switch (option) {
                    case 1: // create customer
                        bank.createCustomer(scanner);
                        break;
                    case 2: // make transactions
                        bank.makeTransactions(scanner);
                        break;
                    case 3: // display customers
                        bank.displayCustomers();
                        break;
                    case 4: // display Bank Accounts
                        bank.displayBankAccounts();
                        break;
                    case 5: // display Bank Account Transactions
                        bank.displayTransactions(scanner);
                        break;
                    case 99: // exit
                        scanner.close();
                        System.exit(0);
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println("\nPress [Enter] to continue");
                scanner.nextLine();
            }
        }

    }

    void addDummyData() {
        this.customers.addAll(Arrays.asList(new Customer(1000, "Gihan Liyanage", "gihan@gmail.com", "+94710854933"), new Customer(1001, "Muzny M", "muzny@gmail.com", "+94710230983"), new Customer(1002, "Iona Norman", "iona@gmail.com", "+94710450983")));

        this.bankAccounts.putAll(Map.of(5051000001L, new BankAccount(5051000001L, "Anna Reynolds", 5000, AccountType.CURRENT), 5051001001L, new BankAccount(5051001001L, "Bianca Oliver", 3000, AccountType.SAVINGS), 5051002001L, new BankAccount(5051002001L, "Iona Norman", 8500, AccountType.FIXED_DEPOSIT)));
    }

    public void addCustomer(Customer customer) {
        this.customers.add(customer);
    }

    public void removeCustomer(int index) {
        this.customers.remove(index);
    }

    public void addBankAccount(BankAccount bankAccount) {
        this.bankAccounts.put(bankAccount.getAccountNumber(), bankAccount);
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
            System.out.println(customer.getId() + "\t|\t" + customer.getName() + "\t|\t" + customer.getEmailAddress() + "\t|\t" + customer.getPhoneNumber());
        }
        System.out.println("----------------------------------------------------------");
    }

    public void displayBankAccounts() {
        System.out.println("\nBANK ACCOUNTS");
        System.out.println("----------------------------------------------------------");
        System.out.println("Acc#\t|\tAccountName\t|\tBalance\t|\tAccountType");
        System.out.println("----------------------------------------------------------");
        this.bankAccounts.values().forEach(bankAccount -> System.out.println(bankAccount.getAccountNumber() + "\t|\t" + bankAccount.getAccountName() + "\t|\t" + bankAccount.getBalance() + "\t|\t" + bankAccount.getAccountType()));
        System.out.println("----------------------------------------------------------");
    }

    public void displayTransactions(Scanner scanner) throws Exception {

        System.out.println("Please provide Bank Account details");
        long accNumber = getLongInput(scanner, "Account Number:");
        BankAccount bankAccount = getBankAccountByAccountNumber(scanner, accNumber);

        System.out.println("\nTRANSACTIONS \t\tAccount Number: " + bankAccount.getAccountNumber());
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("TrxId#\t|\tTransaction Date\t|\tAmount\t|\tTransactionType");
        System.out.println("-------------------------------------------------------------------------------------");
        bankAccount.getTransactions().forEach(transaction -> {
            System.out.println(transaction.getId() + "\t|\t" + transaction.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\t|\t" + transaction.getAmount() + "\t|\t" + transaction.getTransactionType());
        });
        System.out.println("-------------------------------------------------------------------------------------");
    }

    public int getIntegerInput(Scanner scanner, String prompt) {
        System.out.println(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid Input.\nPlease enter correct " + prompt);
            scanner.nextLine();
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // this will return carriage return /r/n only.
        return value;
    }

    public long getLongInput(Scanner scanner, String prompt) {
        System.out.println(prompt);
        while (!scanner.hasNextLong()) {
            System.out.println("Invalid Input.\nPlease enter correct " + prompt);
            scanner.nextLine();
        }
        long value = scanner.nextLong();
        scanner.nextLine();
        return value;
    }

    public double getDoubleInput(Scanner scanner, String prompt) {
         System.out.println(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid Input.\nPlease enter correct " + prompt);
            scanner.nextLine();
        }
        double value = scanner.nextDouble();
        scanner.nextLine();
        return value;
    }

    public String getRegexInput(Scanner scanner, String regex, String prompt) {
        System.out.println(prompt);
        while (!scanner.hasNext(regex)) {
            System.out.println("Invalid Input.\nPlease enter correct " + prompt);
            scanner.nextLine();
        }
        return scanner.nextLine();
    }

    public void createCustomer(Scanner scanner) {
        System.out.println("\nPlease provide customer details");

        String cName = getRegexInput(scanner, "[a-zA-Z]+", "Name:");
        String cEmail = getRegexInput(scanner, "[a-zA-Z0-9+.]+@[a-zA-Z0-9-]+\\.[a-zA-Z]+", "Email address:");
        String cPhone = getRegexInput(scanner, "[0-9+]+", "Phone number:");

        // Auto generate index based on the size of the array
        int cIndex = this.customers.size() + 1000;
        Customer customer = new Customer(cIndex, cName, cEmail, cPhone);
        this.addCustomer(customer);

        // Create Bank Account
        this.createBankAccount(scanner, customer);
    }

    public void createBankAccount(Scanner scanner, Customer customer) {
        System.out.println("\nPlease provide bank account details");
        System.out.println("Account Holder Name: " + customer.getName() + "");
        System.out.println("Press [Y] to continue with the current customer name");
        String accName = getRegexInput(scanner, "[a-zA-Z]+", "Account Holder Name: [Y] or type the correct name here");
        if (accName.equalsIgnoreCase("y")) {
            accName = customer.getName();
        }

        // auto generate account number
        long accNumber = Long.parseLong("505" + customer.getId() + "001");
        double accBalance = getDoubleInput(scanner, "Account Balance:");
        AccountType accType = getAccountTypeFromInput(scanner);

        BankAccount bankAccount = new BankAccount(accNumber, accName, accBalance, accType);
        this.addBankAccount(bankAccount);
    }

    public BankAccount getBankAccountByAccountNumber(Scanner scanner, long accNumber) throws Exception {
        if (!this.bankAccounts.containsKey(accNumber)) {
            throw new Exception("Account Number not found. Please check and try again");
        }
        return bankAccounts.get(accNumber);
    }

    private AccountType getAccountTypeFromInput(Scanner scanner) {
        AccountType accType = AccountType.SAVINGS;
        System.out.println("Account Types:");
        System.out.println("[S] - Savings Account (Default)");
        System.out.println("[C] - Current Account");
        System.out.println("[F] - Fixed Deposit Account");

        String type = getRegexInput(scanner, "s|c|f|S|C|F", "Account Type: [S] [C] [F]");
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

        String type = getRegexInput(scanner, "d|w|D|W", "Transaction Type: [D] [W]");
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

    public void makeTransactions(Scanner scanner) throws Exception {

        System.out.println("Please provide transaction details");

        long accNumber = getLongInput(scanner, "Account Number:");
        BankAccount bankAccount = getBankAccountByAccountNumber(scanner, accNumber);
        double txAmount = getDoubleInput(scanner, "Amount:");
        TransactionType txType = getTransactionTypeFromInput(scanner);
        switch (txType) {
            case DEPOSIT:
                bankAccount.deposit(txAmount);
                break;
            case WITHDRAWAL:
                bankAccount.withdraw(txAmount);
                break;
        }
    }

}
