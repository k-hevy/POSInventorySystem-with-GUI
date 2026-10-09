package entitylayer;

import datalayer.CustomLinkedLists.CartItemLinkedList;
import datalayer.CustomLinkedLists.CartItemNode;

public class Cart {

    String cartID;
    CartItemLinkedList cartItems;

    public Cart(String cartID) {
        this.cartID = cartID;
        this.cartItems = new CartItemLinkedList();
    }

    public double calculateTotal() {
        double total = 0.0;
        CartItemNode current = cartItems.getTopNode();
        while (current != null) {
            total += current.cartItem.getCartItemTotal();
            current = current.next;
        }
        return total;
    } 

    public CartItemLinkedList getCartItems() {
        return cartItems;
    } 

    public void addItem(CartItem cartItem) {
        cartItems.push(cartItem);
    } // temporary place for now, will be movbe to customer class later

    public void removeItem(String targetId) {
        cartItems.remove(targetId);
    } 

    public void popItem() {
        cartItems.pop();
    }

    public void clear() {
        // Pop items until cart is empty
        while (cartItems.getTopNode() != null) {
            cartItems.pop();
        }
    }

    public boolean updateCartItemQuantity(String productId, int newQuantity) {

        // If new quantity is 0 or negative, remove the item from the cart completely
        if (newQuantity <= 0) {
            removeItem(productId);
            return true; // Successfully found and updated
        }

        // Traverse the cart's linked list to locate the product and update quantity
        CartItemNode current =  cartItems.getTopNode();
        while (current != null) {
            if (current.cartItem != null && current.cartItem.getProduct().getProductID().equals(productId)) {
                current.cartItem.setQuantity(newQuantity);
                return true; // Successfully found and updated
            }
            current = current.next;
        }

        return false; // Product was not found in the customer's cart
    }

    // Getters 
    public String getCartID() { return cartID; }
    public CartItemLinkedList getItems() { return cartItems;}
    
} // As of now parang wrapper methods lang mga to IDK if magstastay pa mga to here






// public void calculate() {
//         CartItemNode current = cartItems.getTopNode();
//         String temp = "";
//         int i = 0;
//         while(current !=null) {
//             temp += "[" + i++ + "] " + current.cartItem.getQuantity() + " " + current.cartItem.getProduct().getProductName() + " x " + current.cartItem.getProduct().getProductPrice() +  "\n";
//             current = current.next;
//         }

//         if (!temp.isEmpty()) {
//             System.out.println(temp);
//             System.out.println("Total: " + calculateTotal());
//         } else {
//             System.out.println("Cart is empty");
//         }

//     } 
