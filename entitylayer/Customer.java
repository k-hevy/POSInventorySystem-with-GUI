package entitylayer;

public class Customer extends User {
    
    Cart cart;
    
    public Customer(String userID, String name, String password) {
        super(userID, name, password, "Customer");
        this.cart = new Cart("CART-001");
    }

    public Cart getCart() {
        return cart;
    }

    //Void addItem()
    // Void removeItem()
    // void checkout()
    // Void viewReceipt()
    // void generateReceipt()
    // Void viewMyReceipts
    // Void viewCart()
    // void clearCart()
    // Double
    // updateItemQuantity()
    // boolean isCartEmpty()
    // Void searchItems()
    // Void searchReceipts()
    // void calculateTotaBilll()

    
}
