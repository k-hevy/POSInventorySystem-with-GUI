package applicationlayer;

import datalayer.CustomLinkedLists.ProductLinkedList;
import datalayer.CustomLinkedLists.SaleLinkedList;
import datalayer.ProductCatalog;
import datalayer.SaleRepository;
import entitylayer.Customer;
import entitylayer.Manager;
import entitylayer.Product;

public class POSInventorySystem {

    static InventoryService inventoryService;
    static SaleService saleService;

    public static void main(String[] args) {

        // Testing services
        SaleRepository saleRepository = new SaleRepository();
        ProductCatalog productCatalog = new ProductCatalog();
        saleService = new SaleService(saleRepository);
        inventoryService = new InventoryService(productCatalog);

        Manager manager = new Manager("999", "Bien", "bie123", inventoryService, saleService);
        Customer customer = new Customer("C001", "Clark Dela Cruz", "", inventoryService, saleService);
  

        System.out.println("=== 1. INVENTORY SERVICE TESTS ===");
        // [1] Create
        manager.createProduct(new Product("P001", "Apple", "Fresh red", 10, 100.0, true, 5, "Fruits"));
        manager.createProduct(new Product("P002", "Banana", "Yellow", 2, 50.0, true, 5, "Fruits")); // Low stock
        
        // [6] View Inventory
        System.out.println("Inventory Catalog: " + (manager.getInventory().getTopNode() != null ? "PASSED" : "FAILED"));

        // [3] Search
        System.out.println("Search 'Apple': " + (manager.searchProduct("Apple").getTopNode() != null ? "PASSED" : "FAILED"));

        // [4] Purchase/Restock
        manager.purchaseProductStock("P001", 10);
        System.out.println("Restock P001 (New Stock 20): " + manager.getProduct("P001").getProductStock());

        // [5] Update Details
        manager.updateProduct("P001", "Red Apple", "Fresh", 105.0);
        System.out.println("Update P001 Name: " + manager.getProduct("P001").getProductName());

        // [7] Update Threshold & [9] Check Low Stock
        manager.updateStockThreshold("P002", 3);
        System.out.println("Low-Stock List Empty?: " + (manager.getLowStockThresholds().getTopNode() == null));



        System.out.println("\n=== 2. CUSTOMER ACTIONS TESTS ===");
        

        // [1] Add to Cart & [5] View Cart
        customer.addItemToCart("P001", 2);
        customer.addItemToCart( "P002", 1);
        System.out.println("Cart Populated: " + (customer.getCart().getItems().getTopNode() != null));

        // [7] Update Item Quantity
        customer.updateCartItemQuantity( "P001", 3);

        // [3] Checkout
        System.out.println("Checkout Result: " + (customer.CheckoutCart( "SALE-001") != null ? "SUCCESS" : "FAILED"));

        // [6] Clear Cart (Verified post-checkout)
        System.out.println("Cart Cleared Post-Checkout: " + (customer.getCart().getItems().getTopNode() == null));

        // [4] View/get receipts Receipts
        System.out.println();
        System.out.println(customer.getMyReceipts());

        // [8] Search Catalogue & [9] Search Receipt by Date
        System.out.println("Search Catalogue via Customer: " + (customer.searchProduct("Banana").getTopNode() != null));
        


        System.out.println("\n=== 3. OTHER ACTIONS TESTS ===");
        // [1] View Sales
        System.out.println("Manager View Sales: " + (manager.getAllSales().getTopNode() != null ? "PASSED" : "FAILED"));

        // [2] Generate Sales Report
        SaleLinkedList reportSales = manager.generateCategorySalesReport();
        ProductLinkedList reportLowStock = manager.generateLowStockReport();

        System.out.println("Sales Report - Sales Data: " + (reportSales.getTopNode() != null ? "PASSED" : "FAILED"));
        System.out.println("Sales Report - Low Stock Data: " + (reportLowStock.getTopNode() != null ? "PASSED" : "FAILED"));
        System.out.println("Remove P002: " + manager.removeProduct("P002"));
        
    }
}