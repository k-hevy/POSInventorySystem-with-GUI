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
    
}
