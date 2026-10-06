package applicationlayer;
import datalayer.AccountRepository;
import datalayer.ProductCatalog;
import datalayer.SaleRepository;
import entitylayer.CartItem;
import entitylayer.Customer;
import entitylayer.Product;
import entitylayer.Sale;
import entitylayer.User;

public class POSInventorySystem {

     // A customer will have a Cart, a cart contains a list of CartItems, a cartItem contains a prduct and quantity
    static User user;
    static SaleRepository sales;
    static ProductCatalog products;
    static AccountRepository accounts;
    // static Scanner input;
    // static Console console;

    public static void main(String[] args) {

        sales = new SaleRepository();
        products = new ProductCatalog();
        accounts = new AccountRepository();
        // input = new Scanner(System.in);
        //Initialize the repositories and catalog

        Customer customer1 = new Customer("001", "Carlie-Kirk", "1235"); // create a customer
        accounts.addUser(customer1);

        System.out.println(accounts.getUsers().display()); // working

        products.addProduct(new Product("P001", "Apple", "red-apples", 10, 100.0, true, 10, "Fruits"));
        products.addProduct(new Product("P002", "Banana", "red-apples", 10, 90.0, true, 10, "Fruits"));
        products.addProduct(new Product("P004", "Carrots", "red-apples", 10, 1000.0, true, 10, "Fruits"));
        products.addProduct(new Product("P006", "Dog", "red-apples", 10, 10.0, true, 10, "Fruits"));
        products.addProduct(new Product("P008", "Galvanied Steel plates", "red-apples", 10, 60.0, true, 10, "Fruits"));
        products.addProduct(new Product("P004", "Galvanied Steel plates", "red-apples", 10, 60.0, true, 10, "Fruits"));

        System.out.println(products.getProducts().display()); // working

        customer1.getCart().addItem(new CartItem(products.getProduct("P001"), 3));
        customer1.getCart().addItem(new CartItem(products.getProduct("P002"), 3));
        customer1.getCart().addItem(new CartItem(products.getProduct("P006"), 3));
        customer1.getCart().addItem(new CartItem(products.getProduct("P004"), 3));
        customer1.getCart().addItem(new CartItem(products.getProduct("P002"), 3));

        customer1.getCart().showItems(); // working

        sales.addSale(new Sale("101", 101, null, null, null));
        sales.addSale(new Sale("102", 101, null, null, null));
        sales.addSale(new Sale("103", 101, null, null, null));
        sales.addSale(new Sale("104", 101, null, null, null));

        System.out.println(sales.getSales().display()); // working

    }
}