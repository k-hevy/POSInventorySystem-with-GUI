package entitylayer;
import java.time.Instant;

public class Transaction implements datalayer.Identifiable { 

    private String transactionID;
    private double totalAmount;
    private Cart cart;
    private Instant dateStamp;
    private String customerID;

    @Override
    public String getId() {
        return transactionID;
    }

    public Transaction(String transactionID, double totalAmount, Cart cart, Instant dateStamp, String customerID) {
        this.transactionID = transactionID;
        this.totalAmount = totalAmount;
        this.cart = cart;
        this.dateStamp = dateStamp;
        this.customerID = customerID;
    }

    //getters
    public String getTransactionID() { return transactionID; }
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