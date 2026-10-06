package datalayer.CustomLinkedLists;

import entitylayer.CartItem;

public class CartItemLinkedList { 

    private CartItemNode top;
    private int size;

    public CartItemLinkedList() {
        top = null; // Once a linkedlist is created the head is null
        size = 0; // starts at 0 to indicate an empty list idk pala if -1 or 0
    }

    public CartItemNode getTopNode() {
            return top;
    }

    public CartItem getTopCartItem() {
        if (!isEmpty()) {
            return top.cartItem;
        } else {
            return null;
        }
    }

    public boolean push(CartItem data) {
        CartItemNode node = new CartItemNode(data);
        node.next = top;
        top = node;
        size++;
        return true;
    } // pwede rin void nalang

    public CartItem pop() {
        if (!isEmpty()) {
            CartItem cartItem = top.cartItem;
            top = top.next;
            size--;
            return cartItem;
        } 
        return null;
    } // returns null to indicate an empty stack

    public CartItem peek() {
        if (!isEmpty()) {
            return top.cartItem;
        } else {
            return null;
        }
            
    } // returns null if empty

    public boolean isEmpty() {
        return top == null;
    } 
   
    public String display() {
        String temp = "";
        CartItemNode current = top;
        while (current != null) {
            temp += current.cartItem.toString() + "\n";
            current = current.next;
        }

        if (!temp.isEmpty()) {
            return temp;
        } else {
            return "No Items Found";
        }

    } // btw this returns string cuz this shoudlnt print

    public CartItem search(String targetId) { 
        CartItemNode current = top;
        while (current != null) {
            if (current.cartItem.getProduct().getProductID().equals(targetId)) {
                return current.cartItem;
            }
            current = current.next;
        }
        return null;
    }  // I think wala naman duplicating IDs so safe lang na ireturn is 1 and hindi list

    public int getSize() {
        return size;
    }

    public boolean remove(String targetId) {

        if (isEmpty()) {
            return false;
        } // cannot delete an empty list

        if (top.cartItem.getProduct().getProductID().equals(targetId)) {
            top = top.next;
            size--;
            return true;
        } // deletes the head

        CartItemNode current = top;
        while(current.next != null) {
            if (current.next.cartItem.getProduct().getProductID().equals(targetId)) {
                current.next = current.next.next; // ang pointer ng current will point to next Node's 
                size--;
                return true;
            } // if yung kasunod na node nito ay nageequal sa targetId gawin mo ito OR stop when the next node is the target

            current = current.next;
        }

        return false;


    } // Node1 - node2 - target - Node4 - Node5 

    public boolean removeAtIndex(int index) {

        if (index < 0 || index >= size || isEmpty()) {
            return false;
        }

        if (index == 0) {
            top = top.next;
        } else {
            CartItemNode current = top;
            CartItemNode target;
            for (int i = 0; i < index -1; i++) {
                current = current.next;
            }
            target = current.next;
            current.next = target.next;
        }

        size--;
        return true;       

    } // starts at zero index based 

} // A linkedlist that stores Products
