import java.util.Scanner;
import java.util.ArrayList;

public class LibraryManagement {
    
    // Book data
    static ArrayList<Integer> bookIds = new ArrayList<>();
    static ArrayList<String> bookNames = new ArrayList<>();
    static ArrayList<String> authorNames = new ArrayList<>();
    static ArrayList<String> categories = new ArrayList<>();
    static ArrayList<Integer> quantities = new ArrayList<>();
    static ArrayList<String> statuses = new ArrayList<>();  // Available or Issued
    
    // Issued book data
    static ArrayList<Integer> issuedBookIds = new ArrayList<>();
    static ArrayList<String> issuedToNames = new ArrayList<>();
    static ArrayList<String> issueDates = new ArrayList<>();
    static ArrayList<String> returnDates = new ArrayList<>();
    
    // Counters
    static int bookIdCounter = 100;
    static int totalBooks = 0;
    static int issuedCount = 0;
    
    static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        
        System.out.println("=============================================");
        System.out.println("      LIBRARY MANAGEMENT SYSTEM ");
        System.out.println("=============================================");
        
        // Add some sample books
        addSampleBooks();
        
        int choice;
        
        // Main menu loop
        do {
            showMainMenu();
            choice = sc.nextInt();
            sc.nextLine(); // buffer clear
            
            switch(choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    viewAllBooks();
                    break;
                case 3:
                    searchBook();
                    break;
                case 4:
                    issueBook();
                    break;
                case 5:
                    returnBook();
                    break;
                case 6:
                    viewIssuedBooks();
                    break;
                case 7:
                    updateBook();
                    break;
                case 8:
                    deleteBook();
                    break;
                case 9:
                    viewStatistics();
                    break;
                case 0:
                    System.out.println("\n Thank you for using Library System!");
                    System.out.println("   Happy Reading! ");
                    break;
                default:
                    System.out.println("\n Invalid choice! Try again.");
            }
            
        } while(choice != 0);
    }
    
    // ============ ADD SAMPLE BOOKS ============
    public static void addSampleBooks() {
        // Book 1
        bookIds.add(++bookIdCounter);
        bookNames.add("Java Programming");
        authorNames.add("James Gosling");
        categories.add("Programming");
        quantities.add(5);
        statuses.add("Available");
        totalBooks++;
        
        // Book 2
        bookIds.add(++bookIdCounter);
        bookNames.add("Python Basics");
        authorNames.add("Guido van Rossum");
        categories.add("Programming");
        quantities.add(3);
        statuses.add("Available");
        totalBooks++;
        
        // Book 3
        bookIds.add(++bookIdCounter);
        bookNames.add("Data Structures");
        authorNames.add("Mark Allen");
        categories.add("Computer Science");
        quantities.add(4);
        statuses.add("Available");
        totalBooks++;
        
        // Book 4
        bookIds.add(++bookIdCounter);
        bookNames.add("Web Development");
        authorNames.add("Jon Duckett");
        categories.add("Web");
        quantities.add(6);
        statuses.add("Available");
        totalBooks++;
        
        // Book 5
        bookIds.add(++bookIdCounter);
        bookNames.add("Database Systems");
        authorNames.add("Raghu Ramakrishnan");
        categories.add("Database");
        quantities.add(2);
        statuses.add("Available");
        totalBooks++;
        
        System.out.println(" Loaded " + totalBooks + " sample books.");
    }
    
    // ============ SHOW MAIN MENU ============
    public static void showMainMenu() {
        System.out.println("\n============ MAIN MENU ============");
        System.out.println("1. 9 Add New Book");
        System.out.println("2.  View All Books");
        System.out.println("3.  Search Book");
        System.out.println("4.  Issue Book");
        System.out.println("5.  Return Book");
        System.out.println("6.  View Issued Books");
        System.out.println("7.   Update Book");
        System.out.println("8.   Delete Book");
        System.out.println("9.  View Statistics");
        System.out.println("0.  Exit");
        System.out.println("====================================");
        System.out.println("Total Books: " + totalBooks + " | Issued: " + issuedCount);
        System.out.print("Enter choice: ");
    }
    
    // ============ ADD NEW BOOK ============
    public static void addBook() {
        System.out.println("\n==========  ADD NEW BOOK ==========");
        
        System.out.print("Enter Book Name: ");
        String bookName = sc.nextLine();
        
        System.out.print("Enter Author Name: ");
        String author = sc.nextLine();
        
        System.out.print("Enter Category: ");
        String category = sc.nextLine();
        
        System.out.print("Enter Quantity: ");
        int qty = sc.nextInt();
        sc.nextLine();
        
        if(qty <= 0) {
            System.out.println(" Invalid quantity!");
            return;
        }
        
        // Add to lists
        int newId = ++bookIdCounter;
        bookIds.add(newId);
        bookNames.add(bookName);
        authorNames.add(author);
        categories.add(category);
        quantities.add(qty);
        statuses.add("Available");
        
        totalBooks++;
        
        System.out.println("\n Book added successfully!");
        System.out.println("   Book ID: " + newId);
        System.out.println("   Name: " + bookName);
        System.out.println("   Author: " + author);
    }
    
    // ============ VIEW ALL BOOKS ============
    public static void viewAllBooks() {
        System.out.println("\n==========  ALL BOOKS ==========");
        
        if(totalBooks == 0) {
            System.out.println("No books in library!");
            return;
        }
        
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("ID\tBook Name\t\tAuthor\t\t\tQty\tStatus");
        System.out.println("--------------------------------------------------------------------------------");
        
        for(int i = 0; i < bookIds.size(); i++) {
            String name = bookNames.get(i);
            String author = authorNames.get(i);
            
            // Adjust spacing
            if(name.length() < 16) name = name + "\t";
            if(author.length() < 16) author = author + "\t";
            
            System.out.println(bookIds.get(i) + "\t" + name + "\t" + author + "\t" + 
                              quantities.get(i) + "\t" + statuses.get(i));
        }
        
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Total: " + totalBooks + " books");
    }
    
    // ============ SEARCH BOOK ============
    public static void searchBook() {
        System.out.println("\n==========  SEARCH BOOK ==========");
        
        if(totalBooks == 0) {
            System.out.println("No books to search!");
            return;
        }
        
        System.out.println("Search by:");
        System.out.println("1. Book ID");
        System.out.println("2. Book Name");
        System.out.println("3. Author Name");
        System.out.println("4. Category");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();
        sc.nextLine();
        
        boolean found = false;
        
        if(choice == 1) {
            // Search by ID
            System.out.print("Enter Book ID: ");
            int searchId = sc.nextInt();
            
            for(int i = 0; i < bookIds.size(); i++) {
                if(bookIds.get(i) == searchId) {
                    displayBookDetails(i);
                    found = true;
                    break;
                }
            }
            
        } else if(choice == 2) {
            // Search by Name
            System.out.print("Enter Book Name: ");
            String searchName = sc.nextLine().toLowerCase();
            
            for(int i = 0; i < bookNames.size(); i++) {
                if(bookNames.get(i).toLowerCase().contains(searchName)) {
                    displayBookDetails(i);
                    found = true;
                }
            }
            
        } else if(choice == 3) {
            // Search by Author
            System.out.print("Enter Author Name: ");
            String searchAuthor = sc.nextLine().toLowerCase();
            
            for(int i = 0; i < authorNames.size(); i++) {
                if(authorNames.get(i).toLowerCase().contains(searchAuthor)) {
                    displayBookDetails(i);
                    found = true;
                }
            }
            
        } else if(choice == 4) {
            // Search by Category
            System.out.print("Enter Category: ");
            String searchCat = sc.nextLine().toLowerCase();
            
            for(int i = 0; i < categories.size(); i++) {
                if(categories.get(i).toLowerCase().contains(searchCat)) {
                    displayBookDetails(i);
                    found = true;
                }
            }
            
        } else {
            System.out.println(" Invalid choice!");
            return;
        }
        
        if(!found) {
            System.out.println("\n No books found!");
        }
    }
    
    // ============ DISPLAY BOOK DETAILS ============
    public static void displayBookDetails(int index) {
        System.out.println("\n------ Book Details ------");
        System.out.println("Book ID   : " + bookIds.get(index));
        System.out.println("Name      : " + bookNames.get(index));
        System.out.println("Author    : " + authorNames.get(index));
        System.out.println("Category  : " + categories.get(index));
        System.out.println("Quantity  : " + quantities.get(index));
        System.out.println("Status    : " + statuses.get(index));
        System.out.println("--------------------------");
    }
    
    // ============ ISSUE BOOK ============
    public static void issueBook() {
        System.out.println("\n==========  ISSUE BOOK ==========");
        
        if(totalBooks == 0) {
            System.out.println("No books available!");
            return;
        }
        
        // Show available books
        System.out.println("\n----- Available Books -----");
        boolean hasAvailable = false;
        
        for(int i = 0; i < bookIds.size(); i++) {
            if(quantities.get(i) > 0) {
                System.out.println(bookIds.get(i) + " - " + bookNames.get(i) + 
                                  " (Qty: " + quantities.get(i) + ")");
                hasAvailable = true;
            }
        }
        
        if(!hasAvailable) {
            System.out.println("No books available for issue!");
            return;
        }
        
        System.out.print("\nEnter Book ID to issue: ");
        int issueId = sc.nextInt();
        sc.nextLine();
        
        // Find book
        int bookIndex = -1;
        for(int i = 0; i < bookIds.size(); i++) {
            if(bookIds.get(i) == issueId) {
                bookIndex = i;
                break;
            }
        }
        
        if(bookIndex == -1) {
            System.out.println(" Book not found!");
            return;
        }
        
        // Check quantity
        if(quantities.get(bookIndex) <= 0) {
            System.out.println(" This book is not available!");
            return;
        }
        
        System.out.print("Enter Student/Member Name: ");
        String memberName = sc.nextLine();
        
        System.out.print("Enter Issue Date (DD/MM/YYYY): ");
        String issueDate = sc.nextLine();
        
        System.out.print("Enter Return Date (DD/MM/YYYY): ");
        String returnDate = sc.nextLine();
        
        // Reduce quantity
        int newQty = quantities.get(bookIndex) - 1;
        quantities.set(bookIndex, newQty);
        
        // Update status if no books left
        if(newQty == 0) {
            statuses.set(bookIndex, "All Issued");
        }
        
        // Add to issued list
        issuedBookIds.add(issueId);
        issuedToNames.add(memberName);
        issueDates.add(issueDate);
        returnDates.add(returnDate);
        
        issuedCount++;
        
        System.out.println("\n Book issued successfully!");
        System.out.println("   Book: " + bookNames.get(bookIndex));
        System.out.println("   Issued to: " + memberName);
        System.out.println("   Issue Date: " + issueDate);
        System.out.println("   Return Date: " + returnDate);
    }
    
    // ============ RETURN BOOK ============
    public static void returnBook() {
        System.out.println("\n==========  RETURN BOOK ==========");
        
        if(issuedCount == 0) {
            System.out.println("No books are issued!");
            return;
        }
        
        // Show issued books
        viewIssuedBooks();
        
        System.out.print("\nEnter Book ID to return: ");
        int returnId = sc.nextInt();
        sc.nextLine();
        
        // Find in issued list
        int issuedIndex = -1;
        for(int i = 0; i < issuedBookIds.size(); i++) {
            if(issuedBookIds.get(i) == returnId) {
                issuedIndex = i;
                break;
            }
        }
        
        if(issuedIndex == -1) {
            System.out.println(" This book was not issued!");
            return;
        }
        
        // Find book in main list
        int bookIndex = -1;
        for(int i = 0; i < bookIds.size(); i++) {
            if(bookIds.get(i) == returnId) {
                bookIndex = i;
                break;
            }
        }
        
        // Show return details
        System.out.println("\nReturning book:");
        System.out.println("Book: " + bookNames.get(bookIndex));
        System.out.println("Issued to: " + issuedToNames.get(issuedIndex));
        System.out.println("Issue Date: " + issueDates.get(issuedIndex));
        System.out.println("Due Date: " + returnDates.get(issuedIndex));
        
        System.out.print("\nConfirm return? (y/n): ");
        String confirm = sc.nextLine();
        
        if(confirm.equalsIgnoreCase("y")) {
            // Increase quantity
            int newQty = quantities.get(bookIndex) + 1;
            quantities.set(bookIndex, newQty);
            statuses.set(bookIndex, "Available");
            
            // Remove from issued list
            issuedBookIds.remove(issuedIndex);
            issuedToNames.remove(issuedIndex);
            issueDates.remove(issuedIndex);
            returnDates.remove(issuedIndex);
            
            issuedCount--;
            
            System.out.println("\n Book returned successfully!");
            System.out.println("   Thank you!");
            
        } else {
            System.out.println(" Return cancelled.");
        }
    }
    
    // ============ VIEW ISSUED BOOKS ============
    public static void viewIssuedBooks() {
        System.out.println("\n==========  ISSUED BOOKS ==========");
        
        if(issuedCount == 0) {
            System.out.println("No books are currently issued.");
            return;
        }
        
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Book ID\tBook Name\t\tIssued To\tIssue Date\tDue Date");
        System.out.println("------------------------------------------------------------------------");
        
        for(int i = 0; i < issuedBookIds.size(); i++) {
            int bookId = issuedBookIds.get(i);
            
            // Find book name
            String bookName = "";
            for(int j = 0; j < bookIds.size(); j++) {
                if(bookIds.get(j) == bookId) {
                    bookName = bookNames.get(j);
                    break;
                }
            }
            
            if(bookName.length() < 16) bookName = bookName + "\t";
            
            System.out.println(bookId + "\t" + bookName + "\t" + 
                              issuedToNames.get(i) + "\t\t" + 
                              issueDates.get(i) + "\t" + 
                              returnDates.get(i));
        }
        
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Total Issued: " + issuedCount + " books");
    }
    
    // ============ UPDATE BOOK ============
    public static void updateBook() {
        System.out.println("\n==========  UPDATE BOOK ==========");
        
        if(totalBooks == 0) {
            System.out.println("No books to update!");
            return;
        }
        
        // Show all books
        viewAllBooks();
        
        System.out.print("\nEnter Book ID to update: ");
        int updateId = sc.nextInt();
        sc.nextLine();
        
        // Find book
        int index = -1;
        for(int i = 0; i < bookIds.size(); i++) {
            if(bookIds.get(i) == updateId) {
                index = i;
                break;
            }
        }
        
        if(index == -1) {
            System.out.println(" Book not found!");
            return;
        }
        
        // Show current details
        System.out.println("\nCurrent Details:");
        displayBookDetails(index);
        
        System.out.println("\nWhat to update?");
        System.out.println("1. Book Name");
        System.out.println("2. Author Name");
        System.out.println("3. Category");
        System.out.println("4. Quantity");
        System.out.println("5. Update All");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();
        sc.nextLine();
        
        if(choice == 1) {
            System.out.print("Enter new Book Name: ");
            String newName = sc.nextLine();
            bookNames.set(index, newName);
            System.out.println(" Book name updated!");
            
        } else if(choice == 2) {
            System.out.print("Enter new Author Name: ");
            String newAuthor = sc.nextLine();
            authorNames.set(index, newAuthor);
            System.out.println(" Author name updated!");
            
        } else if(choice == 3) {
            System.out.print("Enter new Category: ");
            String newCat = sc.nextLine();
            categories.set(index, newCat);
            System.out.println(" Category updated!");
            
        } else if(choice == 4) {
            System.out.print("Enter new Quantity: ");
            int newQty = sc.nextInt();
            quantities.set(index, newQty);
            if(newQty > 0) {
                statuses.set(index, "Available");
            }
            System.out.println(" Quantity updated!");
            
        } else if(choice == 5) {
            System.out.print("Enter new Book Name: ");
            String newName = sc.nextLine();
            
            System.out.print("Enter new Author Name: ");
            String newAuthor = sc.nextLine();
            
            System.out.print("Enter new Category: ");
            String newCat = sc.nextLine();
            
            System.out.print("Enter new Quantity: ");
            int newQty = sc.nextInt();
            
            bookNames.set(index, newName);
            authorNames.set(index, newAuthor);
            categories.set(index, newCat);
            quantities.set(index, newQty);
            
            if(newQty > 0) {
                statuses.set(index, "Available");
            }
            
            System.out.println(" All details updated!");
            
        } else {
            System.out.println(" Invalid choice!");
        }
    }
    
    // ============ DELETE BOOK ============
    public static void deleteBook() {
        System.out.println("\n==========  DELETE BOOK ==========");
        
        if(totalBooks == 0) {
            System.out.println("No books to delete!");
            return;
        }
        
        // Show all books
        viewAllBooks();
        
        System.out.print("\nEnter Book ID to delete: ");
        int deleteId = sc.nextInt();
        sc.nextLine();
        
        // Find book
        int index = -1;
        for(int i = 0; i < bookIds.size(); i++) {
            if(bookIds.get(i) == deleteId) {
                index = i;
                break;
            }
        }
        
        if(index == -1) {
            System.out.println(" Book not found!");
            return;
        }
        
        // Check if book is issued
        for(int i = 0; i < issuedBookIds.size(); i++) {
            if(issuedBookIds.get(i) == deleteId) {
                System.out.println(" Cannot delete! Book is currently issued.");
                return;
            }
        }
        
        // Show book details
        System.out.println("\nBook to delete:");
        displayBookDetails(index);
        
        System.out.print("\nAre you sure? (y/n): ");
        String confirm = sc.nextLine();
        
        if(confirm.equalsIgnoreCase("y")) {
            bookIds.remove(index);
            bookNames.remove(index);
            authorNames.remove(index);
            categories.remove(index);
            quantities.remove(index);
            statuses.remove(index);
            
            totalBooks--;
            
            System.out.println("\n Book deleted successfully!");
            
        } else {
            System.out.println(" Delete cancelled.");
        }
    }
    
    // ============ VIEW STATISTICS ============
    public static void viewStatistics() {
        System.out.println("\n==========  LIBRARY STATISTICS ==========");
        
        // Count categories
        ArrayList<String> uniqueCategories = new ArrayList<>();
        for(int i = 0; i < categories.size(); i++) {
            if(!uniqueCategories.contains(categories.get(i))) {
                uniqueCategories.add(categories.get(i));
            }
        }
        
        // Count total quantity
        int totalQty = 0;
        for(int i = 0; i < quantities.size(); i++) {
            totalQty += quantities.get(i);
        }
        
        // Count available books
        int availableBooks = 0;
        for(int i = 0; i < statuses.size(); i++) {
            if(statuses.get(i).equals("Available")) {
                availableBooks++;
            }
        }
        
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║          LIBRARY STATISTICS          ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.printf("║  Total Book Titles    : %-14d ║%n", totalBooks);
        System.out.printf("║  Total Book Copies    : %-14d ║%n", totalQty);
        System.out.printf("║  Available Titles     : %-14d ║%n", availableBooks);
        System.out.printf("║  Currently Issued     : %-14d ║%n", issuedCount);
        System.out.printf("║  Categories           : %-14d ║%n", uniqueCategories.size());
        System.out.println("╚════════════════════════════════════════╝");
        
        // Category wise breakdown
        System.out.println("\n----- Category Wise Books -----");
        for(int i = 0; i < uniqueCategories.size(); i++) {
            String cat = uniqueCategories.get(i);
            int count = 0;
            
            for(int j = 0; j < categories.size(); j++) {
                if(categories.get(j).equals(cat)) {
                    count++;
                }
            }
            
            System.out.println(cat + ": " + count + " books");
        }
        
        // Most issued member (if any)
        if(issuedCount > 0) {
            System.out.println("\n----- Current Borrowers -----");
            for(int i = 0; i < issuedToNames.size(); i++) {
                System.out.println((i+1) + ". " + issuedToNames.get(i));
            }
        }
    }
}
