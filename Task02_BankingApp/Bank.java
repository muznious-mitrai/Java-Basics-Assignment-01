package Task02_BankingApp;

import javax.lang.model.type.PrimitiveType;
import java.util.*;

public class Bank {

    private ArrayList<Customer> customers;
    private ArrayList<BankAccount> bankAccounts;

    public Bank() {
        this.customers = new ArrayList<>();
        this.bankAccounts = new ArrayList<>();
    }

    public static void main(String[] args) {

        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);

//        bank.testInput(scanner);


        System.out.println("Welcome to Our Bank\n");

        // Creating customers and bank accounts from user input
        Customer customer = bank.createCustomer(scanner);
        BankAccount bankAccount = bank.createBankAccount(scanner, customer.getName());

        // Store in lists
        bank.addCustomer(customer);
        bank.addBankAccount(bankAccount);

        System.out.println("\n\nAccount Created Successfully!");

        scanner.close();

        bank.displayCustomers();
        bank.displayBankAccounts();

    }


    void testInput(Scanner scanner) {

        System.out.println("If Account Holder Name is different, enter the correct name");
        validateDataType(scanner,"[a-zA-Z]+", "Account Name");
        String accName = scanner.next();
        System.out.println("acc name: "+accName);
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
        System.out.println("ID\t\t|\tName\t|\tEmailAddress\t|\tPhoneNumber");
        for (Customer customer : this.customers) {
            System.out.println(customer.getId()
                    + "\t|\t" + customer.getName()
                    + "\t|\t" + customer.getEmailAddress()
                    + "\t|\t" + customer.getPhoneNumber()
            );
        }
    }

    public void displayBankAccounts() {
        System.out.println("Acc#\t|\tAccountName\t|\tBalance\t|\tAccountType");
        for (BankAccount bankAccount : this.bankAccounts) {
            System.out.println(bankAccount.getAccountNumber()
                    + "\t|\t" + bankAccount.getAccountName()
                    + "\t|\t" + bankAccount.getBalance()
                    + "\t|\t" + bankAccount.getAccountType()
            );
        }
    }

    public Customer createCustomer(Scanner sc) {
        System.out.println("Please provide customer details");
        System.out.println("Name:");
        String cName = sc.next();
        System.out.println("Email address:");
        String cEmail = sc.next();
        System.out.println("Phone number:");
        String cPhone = sc.next();

        // auto generate index based on the size of the array
        int cIndex = this.customers.size() + 1000;
        Customer customer = new Customer(cIndex, cName, cEmail, cPhone);
        return customer;
    }

    public boolean validateDataType(Scanner scanner, String type, String prompt) {

        System.out.println(prompt);

        switch (type){
            case "int":
            case "long":
                while (!scanner.hasNextLong()){
                    System.out.println("Invalid Input.\nPlease enter correct "+prompt);
                    scanner.next();
                }
                break;
            case "double":
                while (!scanner.hasNextDouble()){
                    System.out.println("Invalid Input.\nPlease enter correct "+prompt);
                    scanner.next();
                }
                break;

            default:
                while (!scanner.hasNext(type)){
                    System.out.println("Invalid Input.\nPlease enter correct "+prompt);
                    scanner.next();
                }
        }

        return true;
    }
    public BankAccount createBankAccount(Scanner scanner, String customerName) {
        System.out.println("Please provide bank account details");

        System.out.println("Account Holder Name: " + customerName +"");
        System.out.println("Press [Y] to continue with the current customer name");
        validateDataType(scanner,"[a-zA-Z]+", "Account Holder Name: [Y] or type the correct name here");
        String accName = scanner.next();
        if (accName.equalsIgnoreCase("y")) {
            accName = customerName;
        }

        validateDataType(scanner, "long", "Account Number:");
        long accNumber = scanner.nextLong();

        validateDataType(scanner, "double", "Account Balance:");
        double accBalance = scanner.nextDouble();

        AccountType accType = AccountType.SAVINGS;
        System.out.println("Account Types:");
        System.out.println("S - Savings Account (Default)");
        System.out.println("C - Current Account");
        System.out.println("F - Fixed Deposit Account");

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

        BankAccount bankAccount = new BankAccount(accNumber, accName, accBalance, accType);
        return bankAccount;
    }

}
