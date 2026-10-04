package entitylayer;

public class CartItem implements datalayer.Identifiable {

    Product product;
    int quantity;

     public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    // implements identifiable interface   
    @Override
    public String getId() {
        return product.getProductID();
    }

    public double getCartItemTotal() {
        return product.getProductPrice() * quantity;
    }

    //getters
    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    
    //setters
    public void setProduct(Product product) { this.product = product; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    

}
