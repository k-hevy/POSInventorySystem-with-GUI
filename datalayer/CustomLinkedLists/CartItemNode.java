package datalayer.CustomLinkedLists;

import entitylayer.CartItem;

public class CartItemNode {

    public CartItem cartItem;
    public CartItemNode next;

    public CartItemNode(CartItem cartItem) {
        this.cartItem = cartItem;
        this.next = null;
    } // Creating Nodes in the constructor = Initializes Product and product.next to null
    
}
