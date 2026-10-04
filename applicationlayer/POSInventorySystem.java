package applicationlayer;
import entitylayer.CartItem;
import entitylayer.Customer;
import entitylayer.Product;

public class POSInventorySystem {
    public static void main(String[] args) {

        // Main will create: 
        // Storage (TransactionLedger, ProductCatalog, AccountRepository )
        // Services (CustomerService, InventoryService, ManagerService ) This will be delegated to teh correpsonding User

        // A customer will have a Cart, a cart contains a list of CartItems, a cartItem contains a prduct and quantity
        
        System.out.println("POS-Inventory System");
        Customer customer1 = new Customer("001", "Carlie-Kirk", "1235");

        customer1.getCart().addProduct(new CartItem(new Product("P001", "Apple", "red-apples", 10, 100.0, true, 10, "Fruits"), 1));
        customer1.getCart().addProduct(new CartItem(new Product("P00", "Banana", "red-apples", 10, 90.0, true, 10, "Fruits"), 1));
        customer1.getCart().addProduct(new CartItem(new Product("P001", "Carrots", "red-apples", 10, 1000.0, true, 10, "Fruits"), 1));
        customer1.getCart().addProduct(new CartItem(new Product("P001", "Dog", "red-apples", 10, 10.0, true, 10, "Fruits"), 1));
        customer1.getCart().addProduct(new CartItem(new Product("P001", "Galvanied Steel plates", "red-apples", 10, 60.0, true, 10, "Fruits"), 1));
        customer1.getCart().addProduct(new CartItem(new Product("P001", "Galvanied Steel plates", "red-apples", 10, 60.0, true, 10, "Fruits"), 1));

        customer1.getCart().showItems();
        customer1.getCart().removeProduct(2); // remove carrot
         
        customer1.getCart().showItems();
        customer1.getCart().removeProduct(0); // remove apple

        customer1.getCart().showItems();

        // As of now ive putted CustomerServices in Cart
    
    

       
        
        

 

    }
}


   // private String productID;
    // private String productName;
    // private String productDescription;
    // private int productStock;
    // private double productPrice;
    // private boolean availabilityStatus;
    // private int reOrderPoint;
    // private String category;