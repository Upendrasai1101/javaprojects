import java.util.*;

public class ECommerceSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Store store = new Store();
        store.seedData();

        System.out.println("Welcome to the Java E-Commerce Shopping System!");

        while (true) {
            System.out.println("\n1. Register\n2. Login\n3. Exit");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    store.registerUser(sc);
                    break;
                case 2:
                    store.loginUser(sc);
                    break;
                case 3:
                    System.out.println("Thank you for visiting!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option!");
            }
        }
    }
}

//------------------------------------------------------------
// Store class – handles users, products, and main operations
//------------------------------------------------------------
class Store {
    private List<Product> products = new ArrayList<>();
    private Map<String, User> users = new HashMap<>();
    private User loggedInUser;

    public void seedData() {
        products.add(new Product(1, "Laptop", 70000, 4.5));
        products.add(new Product(2, "Smartphone", 35000, 4.2));
        products.add(new Product(3, "Headphones", 2500, 4.1));
        users.put("admin", new User("admin", "admin123"));
    }

    public void registerUser(Scanner sc) {
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        if (users.containsKey(username)) {
            System.out.println("Username already exists!");
            return;
        }
        System.out.print("Enter password: ");
        String password = sc.nextLine();
        users.put(username, new User(username, password));
        System.out.println("Registration successful!");
    }

    public void loginUser(Scanner sc) {
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();

        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            loggedInUser = user;
            System.out.println("Login successful!");
            userMenu(sc);
        } else {
            System.out.println("Invalid credentials!");
        }
    }

    private void userMenu(Scanner sc) {
        Cart cart = new Cart();
        while (true) {
            System.out.println("\n1. View Products\n2. Add to Cart\n3. View Cart\n4. Checkout\n5. View Orders\n6. Logout");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    displayProducts();
                    break;
                case 2:
                    addToCart(cart, sc);
                    break;
                case 3:
                    cart.displayCart();
                    break;
                case 4:
                    checkout(cart, sc);
                    break;
                case 5:
                    loggedInUser.displayOrders();
                    break;
                case 6:
                    loggedInUser = null;
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    private void displayProducts() {
        System.out.println("\nAvailable Products:");
        for (Product p : products) {
            System.out.println(p);
        }
    }

    private void addToCart(Cart cart, Scanner sc) {
        displayProducts();
        System.out.print("Enter product ID to add: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (Product p : products) {
            if (p.getId() == id) {
                cart.addProduct(p);
                System.out.println("Added to cart!");
                return;
            }
        }
        System.out.println("Product not found!");
    }

    private void checkout(Cart cart, Scanner sc) {
        if (cart.getItems().isEmpty()) {
            System.out.println("Your cart is empty!");
            return;
        }
        System.out.println("Choose Payment Method: 1. Card  2. UPI  3. COD");
        int method = sc.nextInt();
        sc.nextLine();
        String paymentMethod = (method == 1) ? "Card" : (method == 2) ? "UPI" : "COD";
        Order order = new Order(cart.getItems(), paymentMethod);
        loggedInUser.addOrder(order);
        cart.clearCart();
        System.out.println("Order placed successfully! Payment via " + paymentMethod);
    }
}

//------------------------------------------------------------
// User class – manages user data and order history
//------------------------------------------------------------
class User {
    private String username;
    private String password;
    private List<Order> orders = new ArrayList<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void displayOrders() {
        if (orders.isEmpty()) {
            System.out.println("No orders found!");
            return;
        }
        System.out.println("Your Order History:");
        for (Order o : orders) {
            System.out.println(o);
        }
    }
}

//------------------------------------------------------------
// Product class – represents individual store items
//------------------------------------------------------------
class Product {
    private int id;
    private String name;
    private double price;
    private double rating;

    public Product(int id, String name, double price, double rating) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public String toString() {
        return id + ". " + name + " - Rs." + price + " (Rating: " + rating + ")";
    }
}

//------------------------------------------------------------
// Cart class – handles cart operations
//------------------------------------------------------------
class Cart {
    private List<Product> items = new ArrayList<>();

    public void addProduct(Product p) {
        items.add(p);
    }

    public void displayCart() {
        if (items.isEmpty()) {
            System.out.println("Cart is empty!");
            return;
        }
        System.out.println("Items in Cart:");
        double total = 0;
        for (Product p : items) {
            System.out.println(p);
            total += p.getPrice();
        }
        System.out.println("Total: Rs." + total);
    }

    public List<Product> getItems() {
        return items;
    }

    public void clearCart() {
        items.clear();
    }
}

//------------------------------------------------------------
// Order class – represents an order placed by user
//------------------------------------------------------------
class Order {
    private static int counter = 1;
    private int orderId;
    private Date date;
    private List<Product> items;
    private String paymentMethod;

    public Order(List<Product> items, String paymentMethod) {
        this.orderId = counter++;
        this.items = new ArrayList<>(items);
        this.paymentMethod = paymentMethod;
        this.date = new Date();
    }

    public String toString() {
        return "Order ID: " + orderId + ", Items: " + items.size() + ", Payment: " + paymentMethod + ", Date: " + date;
    }
}
