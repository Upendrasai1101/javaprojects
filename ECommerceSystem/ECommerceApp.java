import java.util.Scanner;
import java.util.ArrayList;

public class ECommerceApp {
    
    // Products data
    static String[] productNames = {"Laptop", "Mobile", "Headphones", "Mouse", "Keyboard", 
                                    "Monitor", "Tablet", "Smartwatch", "Speaker", "Camera"};
    static double[] productPrices = {50000, 15000, 2000, 500, 1000, 
                                     12000, 25000, 5000, 3000, 35000};
    static int[] productStock = {10, 20, 50, 100, 80, 
                                 15, 25, 30, 40, 12};
    
    // Cart data
    static ArrayList<String> cartItems = new ArrayList<>();
    static ArrayList<Integer> cartQuantity = new ArrayList<>();
    static ArrayList<Double> cartPrices = new ArrayList<>();
    
    // Order data
    static ArrayList<String> orderList = new ArrayList<>();
    static ArrayList<Double> orderAmounts = new ArrayList<>();
    static int orderCount = 0;
    
    // User data
    static String userName = "";
    static double walletBalance = 100000; // Starting balance
    
    static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        
        System.out.println("==========================================");
        System.out.println("   🛒 WELCOME TO E-COMMERCE STORE 🛒");
        System.out.println("==========================================");
        
        // Get user name
        System.out.print("\nEnter your name: ");
        userName = sc.nextLine();
        
        System.out.println("\nHello " + userName + "! Happy Shopping! 🎉");
        System.out.println("Your wallet balance: Rs." + walletBalance);
        
        int choice;
        
        // Main menu loop
        do {
            showMainMenu();
            choice = sc.nextInt();
            sc.nextLine(); // buffer clear
            
            switch(choice) {
                case 1:
                    viewProducts();
                    break;
                case 2:
                    addToCart();
                    break;
                case 3:
                    viewCart();
                    break;
                case 4:
                    removeFromCart();
                    break;
                case 5:
                    checkout();
                    break;
                case 6:
                    viewOrders();
                    break;
                case 7:
                    addMoney();
                    break;
                case 0:
                    System.out.println("\nThank you " + userName + "! Visit again! 🙏");
                    break;
                default:
                    System.out.println("\n❌ Invalid choice! Try again.");
            }
            
        } while(choice != 0);
    }
    
    // ============ SHOW MAIN MENU ============
    public static void showMainMenu() {
        System.out.println("\n========== MAIN MENU ==========");
        System.out.println("1.  View Products");
        System.out.println("2.  Add to Cart");
        System.out.println("3.  View Cart");
        System.out.println("4.  Remove from Cart");
        System.out.println("5.  Checkout");
        System.out.println("6.  View My Orders");
        System.out.println("7.  Add Money to Wallet");
        System.out.println("0.  Exit");
        System.out.println("================================");
        System.out.println("Wallet: Rs." + walletBalance + " | Cart Items: " + cartItems.size());
        System.out.print("Enter choice: ");
    }
    
    // ============ VIEW ALL PRODUCTS ============
    public static void viewProducts() {
        System.out.println("\n==========  OUR PRODUCTS ==========");
        System.out.println("----------------------------------------------");
        System.out.println("ID\tProduct\t\tPrice\t\tStock");
        System.out.println("----------------------------------------------");
        
        for(int i = 0; i < productNames.length; i++) {
            String stockStatus = productStock[i] > 0 ? productStock[i] + " left" : "Out of Stock";
            System.out.println((i+1) + "\t" + productNames[i] + "\t\tRs." + productPrices[i] + "\t" + stockStatus);
        }
        
        System.out.println("----------------------------------------------");
    }
    
    // ============ ADD TO CART ============
    public static void addToCart() {
        System.out.println("\n==========  ADD TO CART ==========");
        
        // Show products first
        viewProducts();
        
        System.out.print("\nEnter Product ID to add: ");
        int productId = sc.nextInt();
        
        // Validate product ID
        if(productId < 1 || productId > productNames.length) {
            System.out.println(" Invalid Product ID!");
            return;
        }
        
        int index = productId - 1;
        
        // Check stock
        if(productStock[index] <= 0) {
            System.out.println(" Sorry! " + productNames[index] + " is out of stock!");
            return;
        }
        
        System.out.print("Enter Quantity: ");
        int qty = sc.nextInt();
        
        // Check quantity
        if(qty <= 0) {
            System.out.println(" Invalid quantity!");
            return;
        }
        
        if(qty > productStock[index]) {
            System.out.println(" Only " + productStock[index] + " items available!");
            return;
        }
        
        // Check if item already in cart
        boolean itemExists = false;
        for(int i = 0; i < cartItems.size(); i++) {
            if(cartItems.get(i).equals(productNames[index])) {
                // Update quantity
                int newQty = cartQuantity.get(i) + qty;
                cartQuantity.set(i, newQty);
                cartPrices.set(i, productPrices[index] * newQty);
                itemExists = true;
                break;
            }
        }
        
        // Add new item to cart
        if(!itemExists) {
            cartItems.add(productNames[index]);
            cartQuantity.add(qty);
            cartPrices.add(productPrices[index] * qty);
        }
        
        System.out.println("\n✅ " + productNames[index] + " x" + qty + " added to cart!");
        System.out.println("   Total: Rs." + (productPrices[index] * qty));
    }
    
    // ============ VIEW CART ============
    public static void viewCart() {
        System.out.println("\n==========  YOUR CART ==========");
        
        if(cartItems.size() == 0) {
            System.out.println("Your cart is empty! ");
            System.out.println("Add some products to cart.");
            return;
        }
        
        System.out.println("--------------------------------------------------");
        System.out.println("No\tItem\t\tQty\tPrice");
        System.out.println("--------------------------------------------------");
        
        double grandTotal = 0;
        
        for(int i = 0; i < cartItems.size(); i++) {
            System.out.println((i+1) + "\t" + cartItems.get(i) + "\t\t" + 
                              cartQuantity.get(i) + "\tRs." + cartPrices.get(i));
            grandTotal += cartPrices.get(i);
        }
        
        System.out.println("--------------------------------------------------");
        System.out.println("GRAND TOTAL: Rs." + grandTotal);
        System.out.println("--------------------------------------------------");
    }
    
    // ============ REMOVE FROM CART ============
    public static void removeFromCart() {
        System.out.println("\n==========  REMOVE FROM CART ==========");
        
        if(cartItems.size() == 0) {
            System.out.println("Your cart is already empty!");
            return;
        }
        
        // Show cart first
        viewCart();
        
        System.out.print("\nEnter item number to remove: ");
        int itemNo = sc.nextInt();
        
        // Validate
        if(itemNo < 1 || itemNo > cartItems.size()) {
            System.out.println("Invalid item number!");
            return;
        }
        
        int index = itemNo - 1;
        String removedItem = cartItems.get(index);
        
        // Remove item
        cartItems.remove(index);
        cartQuantity.remove(index);
        cartPrices.remove(index);
        
        System.out.println("\n✅ " + removedItem + " removed from cart!");
    }
    
    // ============ CHECKOUT ============
    public static void checkout() {
        System.out.println("\n==========  CHECKOUT ==========");
        
        if(cartItems.size() == 0) {
            System.out.println("Your cart is empty! Add items first.");
            return;
        }
        
        // Show cart summary
        viewCart();
        
        // Calculate total
        double grandTotal = 0;
        for(int i = 0; i < cartPrices.size(); i++) {
            grandTotal += cartPrices.get(i);
        }
        
        System.out.println("\nYour Wallet Balance: Rs." + walletBalance);
        System.out.println("Amount to Pay: Rs." + grandTotal);
        
        // Check balance
        if(grandTotal > walletBalance) {
            System.out.println("\n❌ Insufficient balance!");
            System.out.println("Please add Rs." + (grandTotal - walletBalance) + " to wallet.");
            return;
        }
        
        System.out.print("\nConfirm order? (y/n): ");
        String confirm = sc.nextLine();
        
        if(confirm.equalsIgnoreCase("y")) {
            // Deduct money
            walletBalance -= grandTotal;
            
            // Update stock
            for(int i = 0; i < cartItems.size(); i++) {
                String itemName = cartItems.get(i);
                int qty = cartQuantity.get(i);
                
                // Find product and reduce stock
                for(int j = 0; j < productNames.length; j++) {
                    if(productNames[j].equals(itemName)) {
                        productStock[j] -= qty;
                        break;
                    }
                }
            }
            
            // Create order
            orderCount++;
            String orderDetails = "Order #" + orderCount + " - Items: " + cartItems.size();
            orderList.add(orderDetails);
            orderAmounts.add(grandTotal);
            
            // Clear cart
            cartItems.clear();
            cartQuantity.clear();
            cartPrices.clear();
            
            System.out.println("\n🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉");
            System.out.println("   ORDER PLACED SUCCESSFULLY!");
            System.out.println("🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉🎉");
            System.out.println("\nOrder ID: #" + orderCount);
            System.out.println("Amount Paid: Rs." + grandTotal);
            System.out.println("Remaining Balance: Rs." + walletBalance);
            System.out.println("\nThank you for shopping! ");
            
        } else {
            System.out.println("\n❌ Order cancelled.");
        }
    }
    
    // ============ VIEW ORDERS ============
    public static void viewOrders() {
        System.out.println("\n========== 📋 MY ORDERS ==========");
        
        if(orderList.size() == 0) {
            System.out.println("You haven't placed any orders yet!");
            return;
        }
        
        System.out.println("--------------------------------------");
        System.out.println("Order\t\t\t\tAmount");
        System.out.println("--------------------------------------");
        
        double totalSpent = 0;
        
        for(int i = 0; i < orderList.size(); i++) {
            System.out.println(orderList.get(i) + "\t\tRs." + orderAmounts.get(i));
            totalSpent += orderAmounts.get(i);
        }
        
        System.out.println("--------------------------------------");
        System.out.println("Total Orders: " + orderList.size());
        System.out.println("Total Spent: Rs." + totalSpent);
        System.out.println("--------------------------------------");
    }
    
    // ============ ADD MONEY TO WALLET ============
    public static void addMoney() {
        System.out.println("\n========== 💰 ADD MONEY ==========");
        System.out.println("Current Balance: Rs." + walletBalance);
        
        System.out.print("Enter amount to add: Rs.");
        double amount = sc.nextDouble();
        
        if(amount <= 0) {
            System.out.println("❌ Invalid amount!");
            return;
        }
        
        walletBalance += amount;
        
        System.out.println("\n✅ Rs." + amount + " added successfully!");
        System.out.println("New Balance: Rs." + walletBalance);
    }
}
