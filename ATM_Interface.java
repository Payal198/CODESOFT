package ATM;


	import java.util.Scanner;

	// Class to represent the user's bank account
	class BankAccount {
	    private double balance;

	    // Constructor
	    public BankAccount(double initialBalance) {
	        this.balance = initialBalance;
	    }

	    // Method to get the current balance
	    public double getBalance() {
	        return balance;
	    }

	    // Method to deposit money
	    public void deposit(double amount) {
	        if (amount > 0) {
	            balance += amount;
	            System.out.println("Deposit successful. New balance: " + balance);
	        } else {
	            System.out.println("Invalid deposit amount.");
	        }
	    }

	    // Method to withdraw money
	    public void withdraw(double amount) {
	        if (amount > 0 && amount <= balance) {
	            balance -= amount;
	            System.out.println("Withdrawal successful. New balance: " + balance);
	        } else {
	            System.out.println("Invalid withdrawal amount or insufficient balance.");
	        }
	    }
	}

	// Class to represent the ATM machine
	public class ATM_Interface {
	    private BankAccount userAccount;

	    // Constructor
	    public ATM_Interface(BankAccount account) {
	        this.userAccount = account;
	    }

	    // Method to display the main menu
	    private void displayMenu() {
	        System.out.println("\nATM Menu:");
	        System.out.println("1. Check Balance");
	        System.out.println("2. Deposit");
	        System.out.println("3. Withdraw");
	        System.out.println("4. Exit");
	        System.out.print("Enter your choice: ");
	    }

	    // Method to handle user input and perform actions
	    public void run() {
	        Scanner scanner = new Scanner(System.in);
	        int choice;

	        do {
	            displayMenu();
	            choice = scanner.nextInt();

	            switch (choice) {
	                case 1:
	                    checkBalance();
	                    break;
	                case 2:
	                    deposit();
	                    break;
	                case 3:
	                    withdraw();
	                    break;
	                case 4:
	                    System.out.println("Exiting ATM. Thank you!");
	                    break;
	                default:
	                    System.out.println("Invalid choice. Please try again.");
	            }

	        } while (choice != 4);

	        // Close the scanner
	        scanner.close();
	    }

	    // Method to check the account balance
	    private void checkBalance() {
	        double balance = userAccount.getBalance();
	        System.out.println("Current Balance: " + balance);
	    }

	    // Method to deposit money into the account
	    private void deposit() {
	        Scanner scanner = new Scanner(System.in);
	        System.out.print("Enter the deposit amount: ");
	        double amount = scanner.nextDouble();
	        userAccount.deposit(amount);
	    }

	    // Method to withdraw money from the account
	    private void withdraw() {
	        Scanner scanner = new Scanner(System.in);
	        System.out.print("Enter the withdrawal amount: ");
	        double amount = scanner.nextDouble();
	        userAccount.withdraw(amount);
	    }

	    public static void main(String[] args) {
	        // Create a bank account with an initial balance of $1000
	        BankAccount userAccount = new BankAccount(1000);

	        // Create an ATM and connect it to the user's bank account
	        ATM_Interface atm = new ATM_Interface(userAccount);

	        // Run the ATM
	        atm.run();
	    }
	}
