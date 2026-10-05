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
    } // temporary place for now, might be moved to CustomerService

    public void showItems() {
        CartItemNode current = cartItems.getTopNode();
        String temp = "";
        int i = 0;
        while(current !=null) {
            temp += "[" + i++ + "] " + current.cartItem.getQuantity() + " " + current.cartItem.getProduct().getProductName() + " x " + current.cartItem.getProduct().getProductPrice() +  "\n";
            current = current.next;
        }

        if (!temp.isEmpty()) {
            System.out.println(temp);
            System.out.println("Total: " + calculateTotal());
        } else {
            System.out.println("Cart is empty");
        }

    } // also migt be temporary

    public void addItem(CartItem cartItem) {
        cartItems.push(cartItem);
    } // temporary place for now, will be movbe to customer class later

    public void removeItem(String targetId) {
        cartItems.remove(targetId);
    } // list down all products then user will choose wich one ot delete?

    public void popItem() {
        cartItems.pop();
    }

    // Getters 
    public String getCartID() { return cartID; }
    public CartItemLinkedList getItems() { return cartItems;}
    
} // As of now parang wrapper methods lang mga to IDK if magstastay pa mga to here
