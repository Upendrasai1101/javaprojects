import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

class Expense {
    private int id;
    private String description;
    private double amount;
    private String category;
    private LocalDate date;

    public Expense(int id, String description, double amount, String category, LocalDate date) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    public int getId() { return id; }
    public String getDescription() { return description; }
    public double getAmount() { return amount; }
    public String getCategory() { return category; }
    public LocalDate getDate() { return date; }

    public void setDescription(String description) { this.description = description; }
    public void setAmount(double amount) { this.amount = amount; }
    public void setCategory(String category) { this.category = category; }
    public void setDate(LocalDate date) { this.date = date; }

    @Override
    public String toString() {
        return String.format("ID: %d | Desc: %s | Amount: %.2f | Category: %s | Date: %s",
                             id, description, amount, category, date);
    }
}

class ExpenseTracker {
    private List<Expense> expenses;
    private int nextId;

    public ExpenseTracker() {
        expenses = new ArrayList<>();
        nextId = 1;
    }

    public void addExpense(String description, double amount, String category, LocalDate date) {
        expenses.add(new Expense(nextId++, description, amount, category, date));
        System.out.println("Expense added successfully.");
    }

    public boolean updateExpense(int id, String description, Double amount, String category, LocalDate date) {
        for (Expense e : expenses) {
            if (e.getId() == id) {
                if (description != null) e.setDescription(description);
                if (amount != null) e.setAmount(amount);
                if (category != null) e.setCategory(category);
                if (date != null) e.setDate(date);
                System.out.println("Expense updated successfully.");
                return true;
            }
        }
        System.out.println("Expense with specified ID not found.");
        return false;
    }

    public boolean deleteExpense(int id) {
        for (Expense e : expenses) {
            if (e.getId() == id) {
                expenses.remove(e);
                System.out.println("Expense deleted successfully.");
                return true;
            }
        }
        System.out.println("Expense with specified ID not found.");
        return false;
    }

    public void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses to show.");
            return;
        }
        System.out.println("All Expenses:");
        for (Expense e : expenses) {
            System.out.println(e);
        }
    }

    public void viewSummary() {
        double total = 0;
        for (Expense e : expenses) {
            total += e.getAmount();
        }
        System.out.printf("Total Expenses: %.2f | Number of expenses: %d%n", total, expenses.size());
    }

    public void viewMonthlySummary(int year, int month) {
        double total = 0;
        List<Expense> filtered = new ArrayList<>();
        for (Expense e : expenses) {
            LocalDate d = e.getDate();
            if (d != null && d.getYear() == year && d.getMonthValue() == month) {
                filtered.add(e);
                total += e.getAmount();
            }
        }
        if (filtered.isEmpty()) {
            System.out.println("No expenses found for specified month.");
            return;
        }
        System.out.printf("Expenses for %d-%02d:%n", year, month);
        for (Expense e : filtered) {
            System.out.println(e);
        }
        System.out.printf("Total for month: %.2f%n", total);
    }
}

public class ExpenseTrackerApp {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) {
        ExpenseTracker tracker = new ExpenseTracker();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nExpense Tracker Menu:");
            System.out.println("1. Add Expense");
            System.out.println("2. Update Expense");
            System.out.println("3. Delete Expense");
            System.out.println("4. View All Expenses");
            System.out.println("5. View Summary");
            System.out.println("6. View Monthly Summary");
            System.out.println("7. Exit");
            System.out.print("Choose an option (1-7): ");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    addExpenseCLI(tracker, sc);
                    break;
                case "2":
                    updateExpenseCLI(tracker, sc);
                    break;
                case "3":
                    deleteExpenseCLI(tracker, sc);
                    break;
                case "4":
                    tracker.viewExpenses();
                    break;
                case "5":
                    tracker.viewSummary();
                    break;
                case "6":
                    monthlySummaryCLI(tracker, sc);
                    break;
                case "7":
                    System.out.println("Exiting Expense Tracker. Goodbye!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addExpenseCLI(ExpenseTracker tracker, Scanner sc) {
        System.out.print("Enter description: ");
        String desc = sc.nextLine();
        double amount = readDouble(sc, "Enter amount: ");
        System.out.print("Enter category (optional): ");
        String category = sc.nextLine();
        LocalDate date = readDate(sc, "Enter date (YYYY-MM-DD): ");
        
        tracker.addExpense(desc, amount, category.isEmpty() ? null : category, date);
    }

    private static void updateExpenseCLI(ExpenseTracker tracker, Scanner sc) {
        int id = readInt(sc, "Enter expense ID to update: ");
        System.out.print("New description (leave blank to keep unchanged): ");
        String desc = sc.nextLine();
        System.out.print("New amount (leave blank to keep unchanged): ");
        String amountStr = sc.nextLine();
        Double amount = null;
        if (!amountStr.isEmpty()) {
            try {
                amount = Double.parseDouble(amountStr);
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount format. Update cancelled.");
                return;
            }
        }
        System.out.print("New category (leave blank to keep unchanged): ");
        String category = sc.nextLine();
        System.out.print("New date (YYYY-MM-DD) (leave blank to keep unchanged): ");
        String dateStr = sc.nextLine();
        LocalDate date = null;
        if (!dateStr.isEmpty()) {
            try {
                date = LocalDate.parse(dateStr, DATE_FORMAT);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Update cancelled.");
                return;
            }
        }
        if (desc.isEmpty()) desc = null;
        if (category.isEmpty()) category = null;

        tracker.updateExpense(id, desc, amount, category, date);
    }

    private static void deleteExpenseCLI(ExpenseTracker tracker, Scanner sc) {
        int id = readInt(sc, "Enter expense ID to delete: ");
        tracker.deleteExpense(id);
    }

    private static void monthlySummaryCLI(ExpenseTracker tracker, Scanner sc) {
        int year = readInt(sc, "Enter year (YYYY): ");
        int month = readInt(sc, "Enter month (1-12): ");
        if (month < 1 || month > 12) {
            System.out.println("Invalid month. Please enter a value between 1 and 12.");
            return;
        }
        tracker.viewMonthlySummary(year, month);
    }

    private static double readDouble(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format. Try again.");
            }
        }
    }

    private static int readInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid integer format. Try again.");
            }
        }
    }

    private static LocalDate readDate(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine();
            try {
                return LocalDate.parse(input, DATE_FORMAT);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Use YYYY-MM-DD.");
            }
        }
    }
}
