package entitylayer;

import applicationlayer.InventoryService;
import applicationlayer.SaleService;
import datalayer.CustomLinkedLists.ProductLinkedList;
import datalayer.CustomLinkedLists.SaleLinkedList;

public class Customer extends User {

    private Cart cart;
    private InventoryService inventoryService;
    private SaleService saleService;

    public Customer(String userID, String name, String password, InventoryService inventoryService, SaleService saleService) {
        super(userID, name, password, "Customer");
        this.cart = new Cart("CART-001");
        this.inventoryService = inventoryService;
        this.saleService = saleService;
    }

    public Cart getCart() {
        return cart;
    }

    // Add Item to Cart
    public boolean addItemToCart(String productId, int quantity) {
        if (quantity <= 0) return false;

        Product product = inventoryService.getProduct(productId);
        if (product == null || !product.isAvailable() || product.getProductStock() < quantity) {
            return false;
        }

        CartItem item = new CartItem(product, quantity);
        getCart().addItem(item);
        return true;
    }

    // Remove Item from Cart
    public boolean removeItemFromCart(String productId, int quantity) {
        if (productId == null || quantity <= 0) return false;
        getCart().removeItem(productId);
        return true;
    }

    // Update Item Quantity
    public boolean updateCartItemQuantity(String productId, int newQuantity) {
        if (productId == null) return false;
        return getCart().updateCartItemQuantity(productId, newQuantity);
    }

    // Clear Cart
    public void clearCart() {
        getCart().clear();
    }

    //calculate
    public double getActiveBill() {
        return getCart().calculateTotal(); 
    }

    // Check Out Cart
    public Sale CheckoutCart(String saleId) {
        return saleService.checkout(this, saleId);
    }

    // get Customer reciepts
    public SaleLinkedList getMyReceipts() {
        return saleService.getCustomerSales(this);
    }

    // View Cart
    public void viewCart() {
        getCart().getCartItems();
    }

    // Search Product Catalogue (by ID or Name)
    public ProductLinkedList searchProduct(String query) {
        return inventoryService.searchProduct(query);
    }

    // Search Receipt by Id
    public Sale searchMyReceiptById(String targetId) {
       return saleService.customerSearchSaleById(this, targetId);
    }

    public ProductLinkedList getproducts() {
        return inventoryService.getInventory();
    }

}

