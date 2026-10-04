package entitylayer;

import datalayer.CustomLinkedList;
import datalayer.Node;

public class Cart {

    String cartID;
    CustomLinkedList<CartItem> cartItems;

    public Cart(String cartID) {
        this.cartID = cartID;
        this.cartItems = new CustomLinkedList<>();
    }

    public double calculateTotal() {
        double total = 0.0;
        Node<CartItem> current = cartItems.getHead();
        while (current != null) {
            // total += cartItem.data.getQuantity() * cartItem.data.getProduct().getProductPrice(); or 
            total += current.data.getCartItemTotal();
            current = current.next;
        }
        return total;
    } // temporary place for now, might be moved to CustomerService

    public void showItems() {
        Node<CartItem> current = cartItems.getHead();
        String temp = "";
        int i = 0;
        while(current !=null) {
            temp += "[" + i++ + "] " + current.data.getQuantity() + " " + current.data.getProduct().getProductName() + " x " + current.data.getProduct().getProductPrice() +  "\n";
            current = current.next;
        }

        if (!temp.isEmpty()) {
            System.out.println(temp);
            System.out.println("Total: " + calculateTotal());
        } else {
            System.out.println("Cart is empty");
        }

    } // also migt be temporary

    public void addProduct(CartItem cartItem) {
        cartItems.insertData(cartItem);
    } // temporary place for now, will be movbe to customer class later

    public void removeProduct(int index) {
        cartItems.deletAtIndex(index);
    } // list down all products then user will choose wich one ot delete?

    // Getters 
    public String getCartID() { return cartID; }
    public CustomLinkedList<CartItem> getItems() { return cartItems; }
    
}
