package entitylayer;

public class Product {

    private String productID;
    private String productName;
    private String productDescription;
    private int productStock;
    private double productPrice;
    private boolean availabilityStatus;
    private int reOrderPoint;
    private String category;

    public Product(String productID, String productName, String productDescription, int productStock, 
        double productPrice, boolean availabilityStatus, int reOrderPoint, String category) {
        this.productID = productID;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productStock = productStock;
        this.productPrice = productPrice;
        this.availabilityStatus = availabilityStatus;
        this.reOrderPoint = reOrderPoint;
        this.category = category;
    }

    //getters
    public String getProductID() { return productID; }
    public String getProductName() { return productName; }
    public String getProductDescription() { return productDescription; }
    public int getProductStock() { return productStock; }
    public double getProductPrice() { return productPrice; }
    public boolean isAvailable() { return availabilityStatus; }
    public int getReOrderPoint() { return reOrderPoint; }
    public String getCategory() { return category; }

    //setters
    public void setProductID(String productID) { this.productID = productID; }
    public void setProductName(String productName) { this.productName = productName; }
    public void setProductDescription(String productDescription) { this.productDescription = productDescription; }
    public void setProductStock(int productStock) { this.productStock = productStock; }
    public void setProductPrice(double productPrice) { this.productPrice = productPrice; }
    public void setAvailabilityStatus(boolean availabilityStatus) { this.availabilityStatus = availabilityStatus; }
    public void setReOrderPoint(int reOrderPoint) { this.reOrderPoint = reOrderPoint; }
    public void setCategory(String category) { this.category = category; }
}

