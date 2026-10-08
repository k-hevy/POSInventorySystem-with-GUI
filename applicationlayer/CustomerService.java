package applicationlayer;

import datalayer.SaleRepository;
import datalayer.CustomLinkedLists.ProductLinkedList;
import datalayer.CustomLinkedLists.SaleLinkedList;
import entitylayer.*;

import java.time.Instant;

public class CustomerService {
    private InventoryService inventoryService;
    private SaleRepository saleRepository;

    public CustomerService(InventoryService inventoryService, SaleRepository saleRepository) {
        this.inventoryService = inventoryService;
        this.saleRepository = saleRepository;
    }

    // Add Item to Cart
    public boolean addItemToCart(Customer customer, String productId, int quantity) {
        if (quantity <= 0) return false;

        Product product = inventoryService.getProduct(productId);
        if (product == null || !product.isAvailable() || product.getProductStock() < quantity) {
            return false;
        }

        CartItem item = new CartItem(product, quantity);
        customer.getCart().addItem(item);
        return true;
    }

    // Remove Item from Cart
    public boolean removeItemFromCart(Customer customer, String productId, int quantity) {
        if (productId == null || quantity <= 0) return false;
        customer.getCart().removeItem(productId);
        return true;
    }

    // Check Out Cart
    public Sale checkout(Customer customer, String saleId) {
        Cart cart = customer.getCart();
        double total = cart.calculateTotal();

        if (total <= 0) {
            return null; // Empty cart cannot checkout
        }

        // Create a copy of the cart for the sale record so clearing the customer cart doesn't wipe receipt items
        Cart snapshotCart = new Cart(cart.getCartID());
        datalayer.CustomLinkedLists.CartItemNode current = cart.getItems().getTopNode();
        while (current != null) {
            if (current.cartItem != null) {
                snapshotCart.addItem(new CartItem(current.cartItem.getProduct(), current.cartItem.getQuantity()));
            }
            current = current.next;
        }

        Sale sale = new Sale(saleId, total, snapshotCart, Instant.now(), customer.getUserID());
        saleRepository.addSale(sale);

        // Clear customer cart after saving the snapshot
        clearCart(customer);

        return sale;
    }

    // View Receipts (naka format na to)
    public String viewReceipts() {
        SaleLinkedList receipts = saleRepository.getSales();
        datalayer.CustomLinkedLists.SaleNode current = receipts.getTopNode();

        if (current == null) {
            return "No receipts found.";
        }

        StringBuilder builder = new StringBuilder();
        builder.append("===== VIEW RECEIPTS =======\n");

        java.time.format.DateTimeFormatter formatter = 
            java.time.format.DateTimeFormatter.ofPattern("M/d/yyyy").withZone(java.time.ZoneId.systemDefault());

        while (current != null) {
            Sale sale = current.sale;
            if (sale != null) {
                // Format Transaction Date
                String dateStr = (sale.getDateStamp() != null) ? formatter.format(sale.getDateStamp()) : "1/1/2026";
                builder.append(dateStr).append("\n");

                // Format Cart Items
                if (sale.getCart() != null && sale.getCart().getItems() != null) {
                    datalayer.CustomLinkedLists.CartItemNode itemNode = sale.getCart().getItems().getTopNode();
                    while (itemNode != null) {
                        if (itemNode.cartItem != null && itemNode.cartItem.getProduct() != null) {
                            int qty = itemNode.cartItem.getQuantity();
                            String name = itemNode.cartItem.getProduct().getProductName();
                            double price = itemNode.cartItem.getProduct().getProductPrice();

                            builder.append(qty).append("x ").append(name).append(" - P").append(price).append("\n");
                        }
                        itemNode = itemNode.next;
                    }
                }

                // Format Total Price
                builder.append("Total Price: P").append(sale.getTotalAmount()).append("\n");
                builder.append("---------------------------------\n");
            }
            current = current.next;
        }

        builder.append("====================");
        return builder.toString();
    }

    // View Cart
    public void viewCart(Customer customer) {
        customer.getCart().showItems();
    }

    // Clear Cart
    public void clearCart(Customer customer) {
        // Pop items until cart is empty
        while (customer.getCart().getItems().getTopNode() != null) {
            customer.getCart().popItem();
        }
    }

    // Update Item Quantity
    public boolean updateCartItemQuantity(Customer customer, String productId, int newQuantity) {
        if (productId == null) return false;

        // If new quantity is 0 or negative, remove the item from the cart completely
        if (newQuantity <= 0) {
            customer.getCart().removeItem(productId);
            return true; // Successfully found and updated
        }

        // Traverse the cart's linked list to locate the product and update quantity
        datalayer.CustomLinkedLists.CartItemNode current = customer.getCart().getItems().getTopNode();
        while (current != null) {
            if (current.cartItem != null && current.cartItem.getProduct().getProductID().equals(productId)) {
                current.cartItem.setQuantity(newQuantity);
                return true; // Successfully found and updated
            }
            current = current.next;
        }

        return false; // Product was not found in the customer's cart
    }

    // Search Product Catalogue (by ID or Name)
    public ProductLinkedList searchProduct(String query) {
        return inventoryService.searchProduct(query);
    }

    // Search Receipt by Date
    public SaleLinkedList searchReceipt(String searchDate) {
        SaleLinkedList results = new SaleLinkedList();
        if (searchDate == null || searchDate.trim().isEmpty()) {
            return results;
        }

        String formattedSearchDate = searchDate.trim();
        java.time.format.DateTimeFormatter formatter = 
            java.time.format.DateTimeFormatter.ofPattern("M/d/yyyy").withZone(java.time.ZoneId.systemDefault());

        datalayer.CustomLinkedLists.SaleNode current = saleRepository.getSales().getTopNode();

        while (current != null) {
            if (current.sale != null) {
                String dateStr = "";
                if (current.sale.getDateStamp() != null) {
                    dateStr = formatter.format(current.sale.getDateStamp());
                }

                if (dateStr.equals(formattedSearchDate)) {
                    results.push(current.sale);
                }
            }
            current = current.next;
        }

        return results;
    }

    public double calculateActiveBill(Customer customer) {
        return customer.getCart().calculateTotal();
    }
}