package applicationlayer;

import datalayer.ProductCatalog;
import datalayer.SaleRepository;
import datalayer.CustomLinkedLists.ProductLinkedList;
import datalayer.CustomLinkedLists.SaleLinkedList;
import entitylayer.Customer;
import entitylayer.Product;

public class POSInventorySystem {

    static InventoryService inventoryService;
    static CustomerService customerService;
    static ManagerService managerService;

    public static void main(String[] args) {

        // Testing services
        SaleRepository saleRepository = new SaleRepository();
        ProductCatalog productCatalog = new ProductCatalog();

        inventoryService = new InventoryService(productCatalog);
        customerService = new CustomerService(inventoryService, saleRepository);
        managerService = new ManagerService(inventoryService, saleRepository);

        System.out.println("=== 1. INVENTORY SERVICE TESTS ===");
        // [1] Create
        inventoryService.createProduct(new Product("P001", "Apple", "Fresh red", 10, 100.0, true, 5, "Fruits"));
        inventoryService.createProduct(new Product("P002", "Banana", "Yellow", 2, 50.0, true, 5, "Fruits")); // Low stock
        
        // [6] View Inventory
        System.out.println("Inventory Catalog: " + (inventoryService.viewInventory().getTopNode() != null ? "PASSED" : "FAILED"));

        // [3] Search
        System.out.println("Search 'Apple': " + (inventoryService.searchProduct("Apple").getTopNode() != null ? "PASSED" : "FAILED"));

        // [4] Purchase/Restock
        inventoryService.purchaseProductStock("P001", 10);
        System.out.println("Restock P001 (New Stock 20): " + inventoryService.getProduct("P001").getProductStock());

        // [5] Update Details
        inventoryService.updateProduct("P001", "Red Apple", "Fresh", 105.0);
        System.out.println("Update P001 Name: " + inventoryService.getProduct("P001").getProductName());

        // [7] Update Threshold & [9] Check Low Stock
        inventoryService.updateStockThreshold("P002", 3);
        System.out.println("Low Stock List Empty?: " + (inventoryService.checkLowStockThresholds().getTopNode() == null));


        System.out.println("\n=== 2. CUSTOMER SERVICE TESTS ===");
        Customer customer = new Customer("C001", "Juan Dela Cruz", "");

        // [1] Add to Cart & [5] View Cart
        customerService.addItemToCart(customer, "P001", 2);
        customerService.addItemToCart(customer, "P002", 1);
        System.out.println("Cart Populated: " + (customer.getCart().getItems().getTopNode() != null));

        // [7] Update Item Quantity
        customerService.updateCartItemQuantity(customer, "P001", 3);

        // [3] Checkout
        System.out.println("Checkout Result: " + (customerService.checkout(customer, "SALE-001") != null ? "SUCCESS" : "FAILED"));

        // [6] Clear Cart (Verified post-checkout)
        System.out.println("Cart Cleared Post-Checkout: " + (customer.getCart().getItems().getTopNode() == null));

        // [4] View Receipts
        System.out.println();
        System.out.println(customerService.viewReceipts());

        // [8] Search Catalogue & [9] Search Receipt by Date
        System.out.println("Search Catalogue via Customer: " + (customerService.searchProduct("Banana").getTopNode() != null));
        
        System.out.println("\n=== 3. MANAGER SERVICE TESTS ===");
        // [1] View Sales
        System.out.println("Manager View Sales: " + (managerService.viewSales().getTopNode() != null ? "PASSED" : "FAILED"));

        // [2] Generate Sales Report
        SaleLinkedList reportSales = managerService.generateCategorySalesReport();
        ProductLinkedList reportLowStock = managerService.generateLowStockReport();

        System.out.println("Sales Report - Sales Data: " + (reportSales.getTopNode() != null ? "PASSED" : "FAILED"));
        System.out.println("Sales Report - Low Stock Data: " + (reportLowStock.getTopNode() != null ? "PASSED" : "FAILED"));


        System.out.println("Remove P002: " + inventoryService.removeProduct("P002"));
    }
}