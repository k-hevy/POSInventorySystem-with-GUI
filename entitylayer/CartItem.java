package entitylayer;

public class CartItem {

    Product product;
    int quantity;

     public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double getCartItemTotal() {
        return product.getProductPrice() * quantity;
    }

    @Override 
    public String toString() {
        return product.getProductName() + " x " + quantity;
    }

    //getters
    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    
    //setters
    public void setProduct(Product product) { this.product = product; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    

}
