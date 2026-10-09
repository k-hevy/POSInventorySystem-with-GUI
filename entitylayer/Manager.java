package entitylayer;

import applicationlayer.InventoryService;
import applicationlayer.SaleService;
import datalayer.CustomLinkedLists.ProductLinkedList;
import datalayer.CustomLinkedLists.SaleLinkedList;

public class Manager extends User {

    private InventoryService inventoryService;
    private SaleService saleService;
    
    public Manager(String userID, String name, String password, InventoryService inventoryService, SaleService saleService) {
        super(userID, name, password, "Manager");
        this.inventoryService = inventoryService;
        this.saleService = saleService;
    }

    // INVENTORY

     // Create Product
    public void createProduct(Product product) {
        inventoryService.createProduct(product);
    }

    // Remove Product
    public boolean removeProduct(String productId) {
        return inventoryService.removeProduct(productId);
    }

    // Search Product (by ID or Name / Partial Match)
    public ProductLinkedList searchProduct(String query) {
        return inventoryService.searchProduct(query);
    }

    // Get/Purchase Product (Stock Purchase / Inflow)
    public boolean purchaseProductStock(String productId, int purchaseQuantity) {
        return inventoryService.purchaseProductStock(productId, purchaseQuantity);
    }

    // Update Product Details
    public boolean updateProduct(String productId, String name, String description, double price) {
        return inventoryService.updateProduct(productId, name, description, price);
    }

    // Direct helper method for single product lookup
    public Product getProduct(String productId) {
        return inventoryService.getProduct(productId);
    }

    // View Inventory
    public ProductLinkedList getInventory() {
        return inventoryService.getInventory();
    }

    // Update Stock Threshold / Re-Order Point
    public boolean updateStockThreshold(String productId, int newThreshold) {
        return inventoryService.updateStockThreshold(productId, newThreshold);
    }

    // Check Stock Availability
    public String checkStockAvailability(String productId) {
       return inventoryService.checkStockAvailability(productId);
    }

    // Check Stock Thresholds (Low Stock Products)
    public ProductLinkedList getLowStockThresholds() {
       return inventoryService.getLowStockThresholds();
    }

    
    // SALES / REPORTS

    // View Sales
    public SaleLinkedList getAllSales() {
        return saleService.getAllSales();
    }

    public Sale searchSaleById(String targetSaleId) {
        return saleService.managerSearchSaleById(targetSaleId);
    }

     // Generate Sales Report Part 1: Items Sold / Sales History
    public SaleLinkedList generateCategorySalesReport() {
        return saleService.getAllSales();
    } // idk paano to sabi daw ni clark sa UI eh

    // Generate Sales Report Part 2: Items With Low Stock
    public ProductLinkedList generateLowStockReport() {
        return inventoryService.getLowStockThresholds();
    }
    
}
