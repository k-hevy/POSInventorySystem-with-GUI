package applicationlayer;

import datalayer.CustomLinkedLists.ProductLinkedList;
import datalayer.ProductCatalog;
import entitylayer.Product;

public class InventoryService {
    private ProductCatalog productCatalog;

    public InventoryService(ProductCatalog productCatalog) {
        this.productCatalog = productCatalog;
    }

    // Create Product
    public void createProduct(Product product) {
        productCatalog.addProduct(product);
    }

    // Remove Product
    public boolean removeProduct(String productId) {
        Product product = productCatalog.getProduct(productId);
        if (product == null) return false;

        // Custom LinkedList deletion
        return productCatalog.getProducts().remove(productId);
    }

    // Search Product (by ID or Name / Partial Match)
    public ProductLinkedList searchProduct(String query) {
        ProductLinkedList searchResults = new ProductLinkedList();
        if (query == null || query.trim().isEmpty()) {
            return searchResults; // returns null
        }

        String searchQuery = query.trim().toLowerCase();
        datalayer.CustomLinkedLists.ProductNode current = productCatalog.getProducts().getTopNode();

        while (current != null) {
            if (current.product != null) {
                String productId = current.product.getProductID().toLowerCase();
                String productName = current.product.getProductName().toLowerCase();

                // Match exact ID or partial Product Name
                if (productId.equals(searchQuery) || productName.contains(searchQuery)) {
                    searchResults.push(current.product);
                }
            }
            current = current.next;
        }

        return searchResults;
    }

    // Get/Purchase Product (Stock Purchase / Inflow)
    public boolean purchaseProductStock(String productId, int purchaseQuantity) {
        Product product = productCatalog.getProduct(productId);
        if (product == null || purchaseQuantity <= 0) return false;

        int currentStock = product.getProductStock();
        product.setProductStock(currentStock + purchaseQuantity);
        product.setAvailabilityStatus(true);
        return true;
    }

    // Update Product Details
    public boolean updateProduct(String productId, String name, String description, double price) {
        Product product = productCatalog.getProduct(productId);
        if (product == null) return false;

        product.setProductName(name);
        product.setProductDescription(description);
        product.setProductPrice(price);
        return true;
    }

    // View Inventory
    public ProductLinkedList getInventory() {
        return productCatalog.getProducts();
    }

    // Update Stock Threshold / Re-Order Point
    public boolean updateStockThreshold(String productId, int newThreshold) {
        Product product = productCatalog.getProduct(productId);
        if (product == null) return false;

        product.setReOrderPoint(newThreshold);
        return true;
    }

    // Check Stock Availability
    public String checkStockAvailability(String productId) {
        Product product = productCatalog.getProduct(productId);
        if (product == null) { return "NOT_FOUND"; }
        return product.isAvailable() ? "AVAILABLE" : "UNAVAILABLE";
    }

    // Check Stock Thresholds (Low Stock Products)
    public ProductLinkedList getLowStockThresholds() {
        ProductLinkedList lowStockList = new ProductLinkedList();
        datalayer.CustomLinkedLists.ProductNode current = productCatalog.getProducts().getTopNode();

        while (current != null) {
            Product product = current.product;
            if (product != null && product.getProductStock() <= product.getReOrderPoint()) {
                lowStockList.push(product);
            }
            current = current.next;
        }

        return lowStockList;
    }

    // Direct helper method for single product lookup
    public Product getProduct(String productId) {
        return productCatalog.getProduct(productId);
    }

}