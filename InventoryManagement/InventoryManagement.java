import java.util.Scanner;
import java.util.ArrayList;

public class InventoryManagement {
    
    // Product data
    static ArrayList<Integer> productIds = new ArrayList<>();
    static ArrayList<String> productNames = new ArrayList<>();
    static ArrayList<String> categories = new ArrayList<>();
    static ArrayList<Integer> quantities = new ArrayList<>();
    static ArrayList<Double> buyPrices = new ArrayList<>();
    static ArrayList<Double> sellPrices = new ArrayList<>();
    
    // Counters
    static int productIdCounter = 1000;
    static int totalProducts = 0;
    
    // Transaction history
    static ArrayList<String> transactionHistory = new ArrayList<>();
    
    static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        
        System.out.println("==============================================");
        System.out.println("      INVENTORY MANAGEMENT SYSTEM ");
        System.out.println("==============================================");
        
        // Add sample products
        addSampleProducts();
        
        int choice;
        
        // Main menu loop
        do {
            showMainMenu();
            choice = sc.nextInt();
            sc.nextLine(); // buffer clear
            
            switch(choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    viewAllProducts();
                    break;
                case 3:
                    searchProduct();
                    break;
                case 4:
                    updateProduct();
                    break;
                case 5:
                    deleteProduct();
                    break;
                case 6:
                    stockIn();
                    break;
                case 7:
                    stockOut();
                    break;
                case 8:
                    lowStockAlert();
                    break;
                case 9:
                    viewTransactions();
                    break;
                case 10:
                    viewStatistics();
                    break;
                case 0:
                    System.out.println("\n Thank you for using Inventory System!");
                    System.out.println("   Goodbye! ");
                    break;
                default:
                    System.out.println("\n Invalid choice! Try again.");
            }
            
        } while(choice != 0);
    }
    
    // ============ ADD SAMPLE PRODUCTS ============
    public static void addSampleProducts() {
        // Product 1
        productIds.add(++productIdCounter);
        productNames.add("Laptop");
        categories.add("Electronics");
        quantities.add(25);
        buyPrices.add(40000.0);
        sellPrices.add(50000.0);
        totalProducts++;
        
        // Product 2
        productIds.add(++productIdCounter);
        productNames.add("Mouse");
        categories.add("Electronics");
        quantities.add(100);
        buyPrices.add(300.0);
        sellPrices.add(500.0);
        totalProducts++;
        
        // Product 3
        productIds.add(++productIdCounter);
        productNames.add("Keyboard");
        categories.add("Electronics");
        quantities.add(80);
        buyPrices.add(600.0);
        sellPrices.add(1000.0);
        totalProducts++;
        
        // Product 4
        productIds.add(++productIdCounter);
        productNames.add("Notebook");
        categories.add("Stationery");
        quantities.add(200);
        buyPrices.add(30.0);
        sellPrices.add(50.0);
        totalProducts++;
        
        // Product 5
        productIds.add(++productIdCounter);
        productNames.add("Pen Box");
        categories.add("Stationery");
        quantities.add(150);
        buyPrices.add(100.0);
        sellPrices.add(150.0);
        totalProducts++;
        
        System.out.println(" Loaded " + totalProducts + " sample products.");
    }
    
    // ============ SHOW MAIN MENU ============
    public static void showMainMenu() {
        System.out.println("\n============= MAIN MENU =============");
        System.out.println("1.   Add New Product");
        System.out.println("2.   View All Products");
        System.out.println("3.   Search Product");
        System.out.println("4.    Update Product");
        System.out.println("5.    Delete Product");
        System.out.println("6.   Stock In (Add Stock)");
        System.out.println("7.   Stock Out (Sell/Remove)");
        System.out.println("8.    Low Stock Alert");
        System.out.println("9.   View Transactions");
        System.out.println("10.  View Statistics");
        System.out.println("0.  Exit");
        System.out.println("======================================");
        System.out.println("Total Products: " + totalProducts);
        System.out.print("Enter choice: ");
    }
    
    // ============ ADD NEW PRODUCT ============
    public static void addProduct() {
        System.out.println("\n==========  ADD NEW PRODUCT ==========");
        
        System.out.print("Enter Product Name: ");
        String name = sc.nextLine();
        
        System.out.print("Enter Category: ");
        String category = sc.nextLine();
        
        System.out.print("Enter Quantity: ");
        int qty = sc.nextInt();
        
        System.out.print("Enter Buy Price: Rs.");
        double buyPrice = sc.nextDouble();
        
        System.out.print("Enter Sell Price: Rs.");
        double sellPrice = sc.nextDouble();
        sc.nextLine();
        
        // Validation
        if(qty < 0 || buyPrice < 0 || sellPrice < 0) {
            System.out.println(" Invalid values!");
            return;
        }
        
        // Add to lists
        int newId = ++productIdCounter;
        productIds.add(newId);
        productNames.add(name);
        categories.add(category);
        quantities.add(qty);
        buyPrices.add(buyPrice);
        sellPrices.add(sellPrice);
        
        totalProducts++;
        
        // Add transaction
        transactionHistory.add("ADDED: " + name + " (Qty: " + qty + ")");
        
        System.out.println("\n Product added successfully!");
        System.out.println("   Product ID: " + newId);
        System.out.println("   Name: " + name);
        System.out.println("   Quantity: " + qty);
    }
    
    // ============ VIEW ALL PRODUCTS ============
    public static void viewAllProducts() {
        System.out.println("\n==========  ALL PRODUCTS ==========");
        
        if(totalProducts == 0) {
            System.out.println("No products in inventory!");
            return;
        }
        
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println("ID\tName\t\tCategory\tQty\tBuy Price\tSell Price\tProfit");
        System.out.println("-----------------------------------------------------------------------------------");
        
        for(int i = 0; i < productIds.size(); i++) {
            String name = productNames.get(i);
            String category = categories.get(i);
            int qty = quantities.get(i);
            double buyPrice = buyPrices.get(i);
            double sellPrice = sellPrices.get(i);
            double profit = sellPrice - buyPrice;
            
            // Stock warning
            String warning = "";
            if(qty <= 10) {
                warning = " ";
            }
            
            // Adjust spacing
            if(name.length() < 8) name = name + "\t";
            if(category.length() < 8) category = category + "\t";
            
            System.out.println(productIds.get(i) + "\t" + name + "\t" + category + "\t" + 
                              qty + warning + "\tRs." + buyPrice + "\t\tRs." + sellPrice + 
                              "\t\tRs." + profit);
        }
        
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println("Total: " + totalProducts + " products");
    }
    
    // ============ SEARCH PRODUCT ============
    public static void searchProduct() {
        System.out.println("\n==========  SEARCH PRODUCT ==========");
        
        if(totalProducts == 0) {
            System.out.println("No products to search!");
            return;
        }
        
        System.out.println("Search by:");
        System.out.println("1. Product ID");
        System.out.println("2. Product Name");
        System.out.println("3. Category");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();
        sc.nextLine();
        
        boolean found = false;
        
        if(choice == 1) {
            System.out.print("Enter Product ID: ");
            int searchId = sc.nextInt();
            
            for(int i = 0; i < productIds.size(); i++) {
                if(productIds.get(i) == searchId) {
                    displayProductDetails(i);
                    found = true;
                    break;
                }
            }
            
        } else if(choice == 2) {
            System.out.print("Enter Product Name: ");
            String searchName = sc.nextLine().toLowerCase();
            
            for(int i = 0; i < productNames.size(); i++) {
                if(productNames.get(i).toLowerCase().contains(searchName)) {
                    displayProductDetails(i);
                    found = true;
                }
            }
            
        } else if(choice == 3) {
            System.out.print("Enter Category: ");
            String searchCat = sc.nextLine().toLowerCase();
            
            for(int i = 0; i < categories.size(); i++) {
                if(categories.get(i).toLowerCase().contains(searchCat)) {
                    displayProductDetails(i);
                    found = true;
                }
            }
            
        } else {
            System.out.println(" Invalid choice!");
            return;
        }
        
        if(!found) {
            System.out.println("\n No products found!");
        }
    }
    
    // ============ DISPLAY PRODUCT DETAILS ============
    public static void displayProductDetails(int index) {
        double profit = sellPrices.get(index) - buyPrices.get(index);
        double profitPercent = (profit / buyPrices.get(index)) * 100;
        double stockValue = quantities.get(index) * buyPrices.get(index);
        
        System.out.println("\n------ Product Details ------");
        System.out.println("Product ID   : " + productIds.get(index));
        System.out.println("Name         : " + productNames.get(index));
        System.out.println("Category     : " + categories.get(index));
        System.out.println("Quantity     : " + quantities.get(index));
        System.out.println("Buy Price    : Rs." + buyPrices.get(index));
        System.out.println("Sell Price   : Rs." + sellPrices.get(index));
        System.out.println("Profit/Item  : Rs." + profit + " (" + String.format("%.1f", profitPercent) + "%)");
        System.out.println("Stock Value  : Rs." + stockValue);
        System.out.println("------------------------------");
    }
    
    // ============ UPDATE PRODUCT ============
    public static void updateProduct() {
        System.out.println("\n==========  UPDATE PRODUCT ==========");
        
        if(totalProducts == 0) {
            System.out.println("No products to update!");
            return;
        }
        
        viewAllProducts();
        
        System.out.print("\nEnter Product ID to update: ");
        int updateId = sc.nextInt();
        sc.nextLine();
        
        // Find product
        int index = -1;
        for(int i = 0; i < productIds.size(); i++) {
            if(productIds.get(i) == updateId) {
                index = i;
                break;
            }
        }
        
        if(index == -1) {
            System.out.println(" Product not found!");
            return;
        }
        
        // Show current details
        System.out.println("\nCurrent Details:");
        displayProductDetails(index);
        
        System.out.println("\nWhat to update?");
        System.out.println("1. Product Name");
        System.out.println("2. Category");
        System.out.println("3. Buy Price");
        System.out.println("4. Sell Price");
        System.out.println("5. Update All");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();
        sc.nextLine();
        
        if(choice == 1) {
            System.out.print("Enter new Name: ");
            String newName = sc.nextLine();
            productNames.set(index, newName);
            System.out.println(" Name updated!");
            
        } else if(choice == 2) {
            System.out.print("Enter new Category: ");
            String newCat = sc.nextLine();
            categories.set(index, newCat);
            System.out.println(" Category updated!");
            
        } else if(choice == 3) {
            System.out.print("Enter new Buy Price: Rs.");
            double newBuyPrice = sc.nextDouble();
            buyPrices.set(index, newBuyPrice);
            System.out.println(" Buy price updated!");
            
        } else if(choice == 4) {
            System.out.print("Enter new Sell Price: Rs.");
            double newSellPrice = sc.nextDouble();
            sellPrices.set(index, newSellPrice);
            System.out.println(" Sell price updated!");
            
        } else if(choice == 5) {
            System.out.print("Enter new Name: ");
            String newName = sc.nextLine();
            
            System.out.print("Enter new Category: ");
            String newCat = sc.nextLine();
            
            System.out.print("Enter new Buy Price: Rs.");
            double newBuyPrice = sc.nextDouble();
            
            System.out.print("Enter new Sell Price: Rs.");
            double newSellPrice = sc.nextDouble();
            
            productNames.set(index, newName);
            categories.set(index, newCat);
            buyPrices.set(index, newBuyPrice);
            sellPrices.set(index, newSellPrice);
            
            System.out.println(" All details updated!");
            
        } else {
            System.out.println(" Invalid choice!");
        }
        
        transactionHistory.add("UPDATED: " + productNames.get(index));
    }
    
    // ============ DELETE PRODUCT ============
    public static void deleteProduct() {
        System.out.println("\n==========  DELETE PRODUCT ==========");
        
        if(totalProducts == 0) {
            System.out.println("No products to delete!");
            return;
        }
        
        viewAllProducts();
        
        System.out.print("\nEnter Product ID to delete: ");
        int deleteId = sc.nextInt();
        sc.nextLine();
        
        // Find product
        int index = -1;
        for(int i = 0; i < productIds.size(); i++) {
            if(productIds.get(i) == deleteId) {
                index = i;
                break;
            }
        }
        
        if(index == -1) {
            System.out.println(" Product not found!");
            return;
        }
        
        // Show product details
        System.out.println("\nProduct to delete:");
        displayProductDetails(index);
        
        System.out.print("\nAre you sure? (y/n): ");
        String confirm = sc.nextLine();
        
        if(confirm.equalsIgnoreCase("y")) {
            String deletedName = productNames.get(index);
            
            productIds.remove(index);
            productNames.remove(index);
            categories.remove(index);
            quantities.remove(index);
            buyPrices.remove(index);
            sellPrices.remove(index);
            
            totalProducts--;
            
            transactionHistory.add("DELETED: " + deletedName);
            
            System.out.println("\n Product deleted successfully!");
            
        } else {
            System.out.println(" Delete cancelled.");
        }
    }
    
    // ============ STOCK IN ============
    public static void stockIn() {
        System.out.println("\n==========  STOCK IN ==========");
        
        if(totalProducts == 0) {
            System.out.println("No products in inventory!");
            return;
        }
        
        viewAllProducts();
        
        System.out.print("\nEnter Product ID: ");
        int productId = sc.nextInt();
        
        // Find product
        int index = -1;
        for(int i = 0; i < productIds.size(); i++) {
            if(productIds.get(i) == productId) {
                index = i;
                break;
            }
        }
        
        if(index == -1) {
            System.out.println(" Product not found!");
            return;
        }
        
        System.out.println("\nProduct: " + productNames.get(index));
        System.out.println("Current Stock: " + quantities.get(index));
        
        System.out.print("Enter quantity to add: ");
        int addQty = sc.nextInt();
        
        if(addQty <= 0) {
            System.out.println(" Invalid quantity!");
            return;
        }
        
        // Update stock
        int newQty = quantities.get(index) + addQty;
        quantities.set(index, newQty);
        
        transactionHistory.add("STOCK IN: " + productNames.get(index) + " +" + addQty);
        
        System.out.println("\n Stock added successfully!");
        System.out.println("   Previous Stock: " + (newQty - addQty));
        System.out.println("   Added: " + addQty);
        System.out.println("   New Stock: " + newQty);
    }
    
    // ============ STOCK OUT ============
    public static void stockOut() {
        System.out.println("\n==========  STOCK OUT ==========");
        
        if(totalProducts == 0) {
            System.out.println("No products in inventory!");
            return;
        }
        
        viewAllProducts();
        
        System.out.print("\nEnter Product ID: ");
        int productId = sc.nextInt();
        
        // Find product
        int index = -1;
        for(int i = 0; i < productIds.size(); i++) {
            if(productIds.get(i) == productId) {
                index = i;
                break;
            }
        }
        
        if(index == -1) {
            System.out.println(" Product not found!");
            return;
        }
        
        System.out.println("\nProduct: " + productNames.get(index));
        System.out.println("Current Stock: " + quantities.get(index));
        System.out.println("Sell Price: Rs." + sellPrices.get(index));
        
        System.out.print("Enter quantity to sell/remove: ");
        int removeQty = sc.nextInt();
        
        if(removeQty <= 0) {
            System.out.println(" Invalid quantity!");
            return;
        }
        
        if(removeQty > quantities.get(index)) {
            System.out.println(" Not enough stock!");
            System.out.println("   Available: " + quantities.get(index));
            return;
        }
        
        // Update stock
        int newQty = quantities.get(index) - removeQty;
        quantities.set(index, newQty);
        
        double saleAmount = removeQty * sellPrices.get(index);
        double profit = removeQty * (sellPrices.get(index) - buyPrices.get(index));
        
        transactionHistory.add("STOCK OUT: " + productNames.get(index) + " -" + removeQty + " (Rs." + saleAmount + ")");
        
        System.out.println("\n Stock removed successfully!");
        System.out.println("   Sold: " + removeQty + " units");
        System.out.println("   Sale Amount: Rs." + saleAmount);
        System.out.println("   Profit: Rs." + profit);
        System.out.println("   Remaining Stock: " + newQty);
        
        // Low stock warning
        if(newQty <= 10) {
            System.out.println("\n WARNING: Low stock! Only " + newQty + " left.");
        }
    }
    
    // ============ LOW STOCK ALERT ============
    public static void lowStockAlert() {
        System.out.println("\n==========  LOW STOCK ALERT ==========");
        
        if(totalProducts == 0) {
            System.out.println("No products in inventory!");
            return;
        }
        
        System.out.println("Products with stock <= 10:\n");
        
        boolean found = false;
        int lowStockCount = 0;
        
        System.out.println("----------------------------------------------");
        System.out.println("ID\tName\t\tCategory\tStock");
        System.out.println("----------------------------------------------");
        
        for(int i = 0; i < productIds.size(); i++) {
            if(quantities.get(i) <= 10) {
                String name = productNames.get(i);
                if(name.length() < 8) name = name + "\t";
                
                String warning = "";
                if(quantities.get(i) == 0) {
                    warning = "  OUT OF STOCK";
                } else if(quantities.get(i) <= 5) {
                    warning = "  CRITICAL";
                } else {
                    warning = "  LOW";
                }
                
                System.out.println(productIds.get(i) + "\t" + name + "\t" + 
                                  categories.get(i) + "\t\t" + quantities.get(i) + warning);
                found = true;
                lowStockCount++;
            }
        }
        
        System.out.println("----------------------------------------------");
        
        if(!found) {
            System.out.println(" All products have sufficient stock!");
        } else {
            System.out.println("Total low stock items: " + lowStockCount);
            System.out.println("\n Tip: Use 'Stock In' option to restock.");
        }
    }
    
    // ============ VIEW TRANSACTIONS ============
    public static void viewTransactions() {
        System.out.println("\n==========  TRANSACTION HISTORY ==========");
        
        if(transactionHistory.size() == 0) {
            System.out.println("No transactions yet!");
            return;
        }
        
        System.out.println("----------------------------------------------");
        
        for(int i = 0; i < transactionHistory.size(); i++) {
            System.out.println((i+1) + ". " + transactionHistory.get(i));
        }
        
        System.out.println("----------------------------------------------");
        System.out.println("Total Transactions: " + transactionHistory.size());
    }
    
    // ============ VIEW STATISTICS ============
    public static void viewStatistics() {
        System.out.println("\n==========  INVENTORY STATISTICS ==========");
        
        if(totalProducts == 0) {
            System.out.println("No products in inventory!");
            return;
        }
        
        // Calculate totals
        int totalQty = 0;
        double totalStockValue = 0;
        double totalPotentialSale = 0;
        double totalPotentialProfit = 0;
        int lowStockCount = 0;
        int outOfStockCount = 0;
        
        for(int i = 0; i < productIds.size(); i++) {
            int qty = quantities.get(i);
            double buyPrice = buyPrices.get(i);
            double sellPrice = sellPrices.get(i);
            
            totalQty += qty;
            totalStockValue += (qty * buyPrice);
            totalPotentialSale += (qty * sellPrice);
            
            if(qty <= 10) lowStockCount++;
            if(qty == 0) outOfStockCount++;
        }
        
        totalPotentialProfit = totalPotentialSale - totalStockValue;
        
        // Count categories
        ArrayList<String> uniqueCategories = new ArrayList<>();
        for(int i = 0; i < categories.size(); i++) {
            if(!uniqueCategories.contains(categories.get(i))) {
                uniqueCategories.add(categories.get(i));
            }
        }
        
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║            INVENTORY SUMMARY               ║");
        System.out.println("╠══════════════════════════════════════════════╣");
        System.out.printf("║  Total Products       : %-20d ║%n", totalProducts);
        System.out.printf("║  Total Quantity       : %-20d ║%n", totalQty);
        System.out.printf("║  Categories           : %-20d ║%n", uniqueCategories.size());
        System.out.printf("║  Low Stock Items      : %-20d ║%n", lowStockCount);
        System.out.printf("║  Out of Stock         : %-20d ║%n", outOfStockCount);
        System.out.println("╠══════════════════════════════════════════════╣");
        System.out.printf("║  Total Stock Value    : Rs.%-17.2f ║%n", totalStockValue);
        System.out.printf("║  Potential Sale Value : Rs.%-17.2f ║%n", totalPotentialSale);
        System.out.printf("║  Potential Profit     : Rs.%-17.2f ║%n", totalPotentialProfit);
        System.out.println("╚══════════════════════════════════════════════╝");
        
        // Category wise breakdown
        System.out.println("\n----- Category Wise Products -----");
        for(int i = 0; i < uniqueCategories.size(); i++) {
            String cat = uniqueCategories.get(i);
            int count = 0;
            int catQty = 0;
            
            for(int j = 0; j < categories.size(); j++) {
                if(categories.get(j).equals(cat)) {
                    count++;
                    catQty += quantities.get(j);
                }
            }
            
            System.out.println(cat + ": " + count + " products (" + catQty + " units)");
        }
    }
}
