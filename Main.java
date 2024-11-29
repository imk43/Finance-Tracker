import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Transaction {
    private String type; // "Income" or "Expense"
    private double amount;
    private String description;
    
    public Transaction(String type, double amount, String description) {
        this.type = type;
        this.amount = amount;
        this.description = description;
    }
    
    public String getType() {
        return type;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public String getDescription() {
        return description;
    }
}

class Budget {
    private double income;
    private double expenses;

    public void addIncome(double amount) {
        income += amount;
    }

    public void addExpense(double amount) {
        expenses += amount;
    }

    public double getBalance() {
        return income - expenses;
    }

    public double getIncome() {
        return income;
    }

    public double getExpenses() {
        return expenses;
    }
}

class FinanceTracker {
    private List<Transaction> transactions;
    private Budget budget;

    public FinanceTracker() {
        transactions = new ArrayList<>();
        budget = new Budget();
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        if (transaction.getType().equals("Income")) {
            budget.addIncome(transaction.getAmount());
        } else if (transaction.getType().equals("Expense")) {
            budget.addExpense(transaction.getAmount());
        }
    }

    public void viewTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }
        for (Transaction t : transactions) {
            System.out.println(t.getType() + ": " + t.getDescription() + " - " + t.getAmount());
        }
    }

    public void viewSummary() {
        System.out.println("Income: " + budget.getIncome());
        System.out.println("Expenses: " + budget.getExpenses());
        System.out.println("Balance: " + budget.getBalance());
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FinanceTracker tracker = new FinanceTracker();
        boolean running = true;
        
        System.out.println("Welcome to the Personal Finance Tracker!");
        
        while (running) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add transaction");
            System.out.println("2. View transactions");
            System.out.println("3. View budget summary");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (choice) {
                case 1:
                    // Add a transaction
                    System.out.print("Enter transaction type (Income/Expense): ");
                    String type = scanner.nextLine();
                    System.out.print("Enter amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter description: ");
                    String description = scanner.nextLine();
                    
                    if (type.equalsIgnoreCase("Income") || type.equalsIgnoreCase("Expense")) {
                        tracker.addTransaction(new Transaction(type, amount, description));
                        System.out.println("Transaction added successfully!");
                    } else {
                        System.out.println("Invalid transaction type. Please choose 'Income' or 'Expense'.");
                    }
                    break;

                case 2:
                    // View all transactions
                    System.out.println("\nAll Transactions:");
                    tracker.viewTransactions();
                    break;

                case 3:
                    // View budget summary
                    System.out.println("\nBudget Summary:");
                    tracker.viewSummary();
                    break;

                case 4:
                    // Exit
                    System.out.println("Thank you for using the Personal Finance Tracker. Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }
        
        scanner.close();
    }
}
