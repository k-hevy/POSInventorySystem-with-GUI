package applicationlayer;
import datalayer.AccountRepository;
import datalayer.ProductCatalog;
import datalayer.SaleRepository;
import entitylayer.CartItem;
import entitylayer.Customer;
import entitylayer.Product;
import entitylayer.User;
import java.util.Scanner;

public class POSInventorySystem {
    
     // A customer will have a Cart, a cart contains a list of CartItems, a cartItem contains a prduct and quantity
    static User user;
    static SaleRepository sales;
    static ProductCatalog products;
    static AccountRepository accounts;
    static Scanner input;
    // static Console console;

    public static void main(String[] args) {

        //Initialize the repositories and catalog
        sales = new SaleRepository();
        products = new ProductCatalog();
        accounts = new AccountRepository();
        input = new Scanner(System.in);


        Customer customer1 = new Customer("001", "Carlie-Kirk", "1235");

        customer1.getCart().addItem(new CartItem(new Product("P001", "Apple", "red-apples", 10, 100.0, true, 10, "Fruits"), 1));
        customer1.getCart().addItem(new CartItem(new Product("P002", "Banana", "red-apples", 10, 90.0, true, 10, "Fruits"), 1));
        customer1.getCart().addItem(new CartItem(new Product("P004", "Carrots", "red-apples", 10, 1000.0, true, 10, "Fruits"), 1));
        customer1.getCart().addItem(new CartItem(new Product("P006", "Dog", "red-apples", 10, 10.0, true, 10, "Fruits"), 1));
        customer1.getCart().addItem(new CartItem(new Product("P008", "Galvanied Steel plates", "red-apples", 10, 60.0, true, 10, "Fruits"), 1));
        customer1.getCart().addItem(new CartItem(new Product("P004", "Galvanied Steel plates", "red-apples", 10, 60.0, true, 10, "Fruits"), 1));

        

        customer1.getCart().showItems();
        customer1.getCart().removeItem("P002"); // successfully rmeoved banana
        customer1.getCart().showItems();
        customer1.getCart().popItem();
        customer1.getCart().showItems();
        customer1.getCart().addItem(new CartItem(new Product("P004", "makati Fukiko", "red-apples", 10, 60.0, true, 10, "Femiine care"), 1)); // Removed Galvanized Steel plates\
        customer1.getCart().showItems();


    }
}