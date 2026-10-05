package entitylayer;
import java.time.Instant;

public class Sale  { 

    private String saleId;
    private double totalAmount;
    private Cart cart;
    private Instant dateStamp;
    private String customerID;

    public Sale(String saleId, double totalAmount, Cart cart, Instant dateStamp, String customerID) {
        this.saleId = saleId;
        this.totalAmount = totalAmount;
        this.cart = cart;
        this.dateStamp = dateStamp;
        this.customerID = customerID;
    }

    //getters
    public String getSaleId() { return saleId; }
    public double getTotalAmount() { return totalAmount; }
    public Cart getCart() { return cart; }
    public Instant getDateStamp() { return dateStamp; }
    public String getCustomerID() { return customerID; }

    //setters I Since receipt i dont think need ng setter
    // public void setTransactionID(String transactionID) { this.transactionID = transactionID; }
    // public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
    // public void setCart(Cart cart) { this.cart = cart; }
    // public void setDateStamp(Instant dateStamp) { this.dateStamp = dateStamp; }
    // public void setCustomerID(String customerID) { this.customerID = customerID; }

}