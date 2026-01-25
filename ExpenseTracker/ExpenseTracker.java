import java.util.Scanner;
import java.util.ArrayList;

public class ExpenseTracker {
    
    // Transaction data
    static ArrayList<String> types = new ArrayList<>();        // Income or Expense
    static ArrayList<String> categories = new ArrayList<>();   // Food, Travel, etc.
    static ArrayList<String> descriptions = new ArrayList<>(); // Description
    static ArrayList<Double> amounts = new ArrayList<>();      // Amount
    static ArrayList<String> dates = new ArrayList<>();        // Date
    
    // Totals
    static double totalIncome = 0;
    static double totalExpense = 0;
    static int transactionCount = 0;
    
    // Categories
    static String[] expenseCategories = {"Food", "Travel", "Shopping", "Bills", "Entertainment", "Health", "Education", "Other"};
    static String[] incomeCategories = {"Salary", "Freelance", "Business", "Gift", "Other"};
    
    static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        
        System.out.println("==========================================");
        System.out.println("      EXPENSE TRACKER APPLICATION ");
        System.out.println("==========================================");
        
        int choice;
        
        // Main menu loop
        do {
            showMainMenu();
            choice = sc.nextInt();
            sc.nextLine(); // buffer clear
            
            switch(choice) {
                case 1:
                    addIncome();
                    break;
                case 2:
                    addExpense();
                    break;
                case 3:
                    viewAllTransactions();
                    break;
                case 4:
                    viewByCategory();
                    break;
                case 5:
                    viewSummary();
                    break;
                case 6:
                    searchTransaction();
                    break;
                case 7:
                    deleteTransaction();
                    break;
                case 8:
                    viewBalance();
                    break;
                case 0:
                    System.out.println("\n Thank you for using Expense Tracker!");
                    System.out.println("   Keep tracking, Keep saving! ");
                    break;
                default:
                    System.out.println("\n Invalid choice! Try again.");
            }
            
        } while(choice != 0);
    }
    
    // ============ SHOW MAIN MENU ============
    public static void showMainMenu() {
        System.out.println("\n============ MAIN MENU ============");
        System.out.println("1.  Add Income");
        System.out.println("2.  Add Expense");
        System.out.println("3.  View All Transactions");
        System.out.println("4.  View by Category");
        System.out.println("5.  View Summary");
        System.out.println("6.  Search Transaction");
        System.out.println("7.   Delete Transaction");
        System.out.println("8.  View Balance");
        System.out.println("0.  Exit");
        System.out.println("====================================");
        
        double balance = totalIncome - totalExpense;
        System.out.println(" Current Balance: Rs." + balance);
        System.out.print("Enter choice: ");
    }
    
    // ============ ADD INCOME ============
    public static void addIncome() {
        System.out.println("\n==========  ADD INCOME ==========");
        
        // Show income categories
        System.out.println("\nSelect Category:");
        for(int i = 0; i < incomeCategories.length; i++) {
            System.out.println((i+1) + ". " + incomeCategories[i]);
        }
        
        System.out.print("Enter category number: ");
        int catNo = sc.nextInt();
        sc.nextLine();
        
        // Validate category
        if(catNo < 1 || catNo > incomeCategories.length) {
            System.out.println(" Invalid category!");
            return;
        }
        
        String category = incomeCategories[catNo - 1];
        
        System.out.print("Enter amount: Rs.");
        double amount = sc.nextDouble();
        sc.nextLine();
        
        if(amount <= 0) {
            System.out.println(" Invalid amount!");
            return;
        }
        
        System.out.print("Enter description: ");
        String description = sc.nextLine();
        
        System.out.print("Enter date (DD/MM/YYYY): ");
        String date = sc.nextLine();
        
        // Add to lists
        types.add("INCOME");
        categories.add(category);
        amounts.add(amount);
        descriptions.add(description);
        dates.add(date);
        
        totalIncome += amount;
        transactionCount++;
        
        System.out.println("\n Income added successfully!");
        System.out.println("   Category: " + category);
        System.out.println("   Amount: Rs." + amount);
        System.out.println("   New Balance: Rs." + (totalIncome - totalExpense));
    }
    
    // ============ ADD EXPENSE ============
    public static void addExpense() {
        System.out.println("\n========== ➖ ADD EXPENSE ==========");
        
        // Show expense categories
        System.out.println("\nSelect Category:");
        for(int i = 0; i < expenseCategories.length; i++) {
            System.out.println((i+1) + ". " + expenseCategories[i]);
        }
        
        System.out.print("Enter category number: ");
        int catNo = sc.nextInt();
        sc.nextLine();
        
        // Validate category
        if(catNo < 1 || catNo > expenseCategories.length) {
            System.out.println(" Invalid category!");
            return;
        }
        
        String category = expenseCategories[catNo - 1];
        
        System.out.print("Enter amount: Rs.");
        double amount = sc.nextDouble();
        sc.nextLine();
        
        if(amount <= 0) {
            System.out.println(" Invalid amount!");
            return;
        }
        
        // Check if balance is sufficient
        double balance = totalIncome - totalExpense;
        if(amount > balance) {
            System.out.println("\n Warning: This expense exceeds your balance!");
            System.out.println("   Current Balance: Rs." + balance);
            System.out.print("   Continue anyway? (y/n): ");
            String confirm = sc.nextLine();
            if(!confirm.equalsIgnoreCase("y")) {
                System.out.println(" Expense cancelled.");
                return;
            }
        }
        
        System.out.print("Enter description: ");
        String description = sc.nextLine();
        
        System.out.print("Enter date (DD/MM/YYYY): ");
        String date = sc.nextLine();
        
        // Add to lists
        types.add("EXPENSE");
        categories.add(category);
        amounts.add(amount);
        descriptions.add(description);
        dates.add(date);
        
        totalExpense += amount;
        transactionCount++;
        
        System.out.println("\n Expense added successfully!");
        System.out.println("   Category: " + category);
        System.out.println("   Amount: Rs." + amount);
        System.out.println("   Remaining Balance: Rs." + (totalIncome - totalExpense));
    }
    
    // ============ VIEW ALL TRANSACTIONS ============
    public static void viewAllTransactions() {
        System.out.println("\n==========  ALL TRANSACTIONS ==========");
        
        if(transactionCount == 0) {
            System.out.println("No transactions found!");
            System.out.println("Add some income or expenses first.");
            return;
        }
        
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("ID\tType\t\tCategory\tAmount\t\tDate");
        System.out.println("--------------------------------------------------------------------------");
        
        for(int i = 0; i < types.size(); i++) {
            String type = types.get(i);
            String symbol = type.equals("INCOME") ? "+" : "-";
            
            System.out.println((i+1) + "\t" + type + "\t\t" + categories.get(i) + 
                              "\t\t" + symbol + "Rs." + amounts.get(i) + 
                              "\t" + dates.get(i));
        }
        
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("Total Transactions: " + transactionCount);
    }
    
    // ============ VIEW BY CATEGORY ============
    public static void viewByCategory() {
        System.out.println("\n==========  VIEW BY CATEGORY ==========");
        
        if(transactionCount == 0) {
            System.out.println("No transactions found!");
            return;
        }
        
        System.out.println("\n1. View Expense Categories");
        System.out.println("2. View Income Categories");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();
        sc.nextLine();
        
        if(choice == 1) {
            // Expense categories
            System.out.println("\n----- EXPENSE BY CATEGORY -----");
            
            for(int i = 0; i < expenseCategories.length; i++) {
                String cat = expenseCategories[i];
                double catTotal = 0;
                int catCount = 0;
                
                for(int j = 0; j < types.size(); j++) {
                    if(types.get(j).equals("EXPENSE") && categories.get(j).equals(cat)) {
                        catTotal += amounts.get(j);
                        catCount++;
                    }
                }
                
                if(catCount > 0) {
                    System.out.println(cat + ": Rs." + catTotal + " (" + catCount + " transactions)");
                }
            }
            
            System.out.println("--------------------------------");
            System.out.println("Total Expenses: Rs." + totalExpense);
            
        } else if(choice == 2) {
            // Income categories
            System.out.println("\n----- INCOME BY CATEGORY -----");
            
            for(int i = 0; i < incomeCategories.length; i++) {
                String cat = incomeCategories[i];
                double catTotal = 0;
                int catCount = 0;
                
                for(int j = 0; j < types.size(); j++) {
                    if(types.get(j).equals("INCOME") && categories.get(j).equals(cat)) {
                        catTotal += amounts.get(j);
                        catCount++;
                    }
                }
                
                if(catCount > 0) {
                    System.out.println(cat + ": Rs." + catTotal + " (" + catCount + " transactions)");
                }
            }
            
            System.out.println("-------------------------------");
            System.out.println("Total Income: Rs." + totalIncome);
            
        } else {
            System.out.println(" Invalid choice!");
        }
    }
    
    // ============ VIEW SUMMARY ============
    public static void viewSummary() {
        System.out.println("\n==========  SUMMARY ==========");
        
        double balance = totalIncome - totalExpense;
        
        System.out.println("----------------------------------");
        System.out.println(" Total Income   : Rs." + totalIncome);
        System.out.println(" Total Expense  : Rs." + totalExpense);
        System.out.println("----------------------------------");
        
        if(balance >= 0) {
            System.out.println(" Balance        : Rs." + balance + " ");
        } else {
            System.out.println(" Balance        : Rs." + balance + "  (Deficit!)");
        }
        
        System.out.println("----------------------------------");
        System.out.println(" Total Transactions: " + transactionCount);
        
        // Count income and expense transactions
        int incomeCount = 0;
        int expenseCount = 0;
        
        for(int i = 0; i < types.size(); i++) {
            if(types.get(i).equals("INCOME")) {
                incomeCount++;
            } else {
                expenseCount++;
            }
        }
        
        System.out.println("   - Income Entries  : " + incomeCount);
        System.out.println("   - Expense Entries : " + expenseCount);
        System.out.println("----------------------------------");
        
        // Find highest expense category
        if(expenseCount > 0) {
            String highestCat = "";
            double highestAmount = 0;
            
            for(int i = 0; i < expenseCategories.length; i++) {
                String cat = expenseCategories[i];
                double catTotal = 0;
                
                for(int j = 0; j < types.size(); j++) {
                    if(types.get(j).equals("EXPENSE") && categories.get(j).equals(cat)) {
                        catTotal += amounts.get(j);
                    }
                }
                
                if(catTotal > highestAmount) {
                    highestAmount = catTotal;
                    highestCat = cat;
                }
            }
            
            System.out.println(" Highest Expense Category: " + highestCat);
            System.out.println("   Amount: Rs." + highestAmount);
        }
        
        // Savings percentage
        if(totalIncome > 0) {
            double savingsPercent = (balance / totalIncome) * 100;
            System.out.println("\n Savings Rate: " + String.format("%.1f", savingsPercent) + "%");
            
            if(savingsPercent >= 30) {
                System.out.println("   Great job! Keep saving! ");
            } else if(savingsPercent >= 10) {
                System.out.println("   Good! Try to save more. ");
            } else if(savingsPercent >= 0) {
                System.out.println("   Try to reduce expenses. ");
            } else {
                System.out.println("   Warning: You're spending more than earning! ");
            }
        }
    }
    
    // ============ SEARCH TRANSACTION ============
    public static void searchTransaction() {
        System.out.println("\n==========  SEARCH TRANSACTION ==========");
        
        if(transactionCount == 0) {
            System.out.println("No transactions to search!");
            return;
        }
        
        System.out.println("Search by:");
        System.out.println("1. Description");
        System.out.println("2. Date");
        System.out.println("3. Amount");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();
        sc.nextLine();
        
        boolean found = false;
        
        if(choice == 1) {
            System.out.print("Enter keyword to search: ");
            String keyword = sc.nextLine().toLowerCase();
            
            System.out.println("\n----- Search Results -----");
            
            for(int i = 0; i < descriptions.size(); i++) {
                if(descriptions.get(i).toLowerCase().contains(keyword)) {
                    String type = types.get(i);
                    String symbol = type.equals("INCOME") ? "+" : "-";
                    
                    System.out.println("\nID: " + (i+1));
                    System.out.println("Type: " + type);
                    System.out.println("Category: " + categories.get(i));
                    System.out.println("Description: " + descriptions.get(i));
                    System.out.println("Amount: " + symbol + "Rs." + amounts.get(i));
                    System.out.println("Date: " + dates.get(i));
                    
                    found = true;
                }
            }
            
        } else if(choice == 2) {
            System.out.print("Enter date (DD/MM/YYYY): ");
            String searchDate = sc.nextLine();
            
            System.out.println("\n----- Search Results -----");
            
            for(int i = 0; i < dates.size(); i++) {
                if(dates.get(i).equals(searchDate)) {
                    String type = types.get(i);
                    String symbol = type.equals("INCOME") ? "+" : "-";
                    
                    System.out.println("\nID: " + (i+1));
                    System.out.println("Type: " + type);
                    System.out.println("Category: " + categories.get(i));
                    System.out.println("Description: " + descriptions.get(i));
                    System.out.println("Amount: " + symbol + "Rs." + amounts.get(i));
                    
                    found = true;
                }
            }
            
        } else if(choice == 3) {
            System.out.print("Enter amount: Rs.");
            double searchAmount = sc.nextDouble();
            
            System.out.println("\n----- Search Results -----");
            
            for(int i = 0; i < amounts.size(); i++) {
                if(amounts.get(i) == searchAmount) {
                    String type = types.get(i);
                    String symbol = type.equals("INCOME") ? "+" : "-";
                    
                    System.out.println("\nID: " + (i+1));
                    System.out.println("Type: " + type);
                    System.out.println("Category: " + categories.get(i));
                    System.out.println("Description: " + descriptions.get(i));
                    System.out.println("Date: " + dates.get(i));
                    
                    found = true;
                }
            }
            
        } else {
            System.out.println(" Invalid choice!");
            return;
        }
        
        if(!found) {
            System.out.println("No transactions found!");
        }
    }
    
    // ============ DELETE TRANSACTION ============
    public static void deleteTransaction() {
        System.out.println("\n==========  DELETE TRANSACTION ==========");
        
        if(transactionCount == 0) {
            System.out.println("No transactions to delete!");
            return;
        }
        
        // Show all transactions
        viewAllTransactions();
        
        System.out.print("\nEnter transaction ID to delete: ");
        int id = sc.nextInt();
        sc.nextLine();
        
        // Validate ID
        if(id < 1 || id > types.size()) {
            System.out.println(" Invalid ID!");
            return;
        }
        
        int index = id - 1;
        
        // Show transaction details
        System.out.println("\nTransaction to delete:");
        System.out.println("Type: " + types.get(index));
        System.out.println("Category: " + categories.get(index));
        System.out.println("Amount: Rs." + amounts.get(index));
        System.out.println("Description: " + descriptions.get(index));
        System.out.println("Date: " + dates.get(index));
        
        System.out.print("\nAre you sure? (y/n): ");
        String confirm = sc.nextLine();
        
        if(confirm.equalsIgnoreCase("y")) {
            // Update totals
            if(types.get(index).equals("INCOME")) {
                totalIncome -= amounts.get(index);
            } else {
                totalExpense -= amounts.get(index);
            }
            
            // Remove from all lists
            types.remove(index);
            categories.remove(index);
            amounts.remove(index);
            descriptions.remove(index);
            dates.remove(index);
            
            transactionCount--;
            
            System.out.println("\n Transaction deleted successfully!");
            
        } else {
            System.out.println(" Delete cancelled.");
        }
    }
    
    // ============ VIEW BALANCE ============
    public static void viewBalance() {
        System.out.println("\n==========  BALANCE ==========");
        
        double balance = totalIncome - totalExpense;
        
        System.out.println("╔═══════════════════════════════════╗");
        System.out.println("║                                   ║");
        System.out.printf("║    Income  : Rs.%-15.2f ║%n", totalIncome);
        System.out.printf("║    Expense : Rs.%-15.2f ║%n", totalExpense);
        System.out.println("║   ─────────────────────────────   ║");
        
        if(balance >= 0) {
            System.out.printf("║    Balance : Rs.%-15.2f ║%n", balance);
            System.out.println("║                                ║");
        } else {
            System.out.printf("║    Balance : Rs.%-15.2f ║%n", balance);
            System.out.println("║                                ║");
        }
        
        System.out.println("║                                   ║");
        System.out.println("╚═══════════════════════════════════╝");
    }
}
