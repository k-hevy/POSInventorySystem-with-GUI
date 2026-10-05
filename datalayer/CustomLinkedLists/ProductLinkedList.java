package datalayer.CustomLinkedLists;

import entitylayer.Product;

public class ProductLinkedList { 

    private ProductNode top;
    private int size;

    public ProductLinkedList() {
        top = null; // Once a linkedlist is created the head is null
        size = 0; // starts at 0 to indicate an empty list idk pala if -1 or 0
    }

    public ProductNode getTopNode() {
            return top;
    }

    public Product getTopProduct() {
        if (!isEmpty()) {
            return top.product;
        } else {
            return null;
        }
    }

    public boolean push(Product data) {
        ProductNode node = new ProductNode(data);
        node.next = top;
        top = node;
        size++;
        return true;
    } // pwede rin void nalang

    public Product pop() {
        if (!isEmpty()) {
            Product product = top.product;
            top = top.next;
            size--;
            return product;
        } 
        return null;
    } // returns null to indicate an empty stack

    public Product peek() {
        if (!isEmpty()) {
            return top.product;
        } else {
            return null;
        }
            
    } // returns null if empty

    public boolean isEmpty() {
        return top == null;
    } 
   
    public String display() {
        String temp = "";
        ProductNode current = top;
        while (current != null) {
            temp += current.product.getProductName();
            current = current.next;
        }

        if (!temp.isEmpty()) {
            return temp;
        } else {
            return "No Products Found";
        }

    }

    public Product Search(String targetId) { 
        ProductNode current = top;
        while (current != null) {
            if (current.product.getProductID().equals(targetId)) {
                return current.product;
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

        if (top.product.getProductID().equals(targetId)) {
            top = top.next;
            size--;
            return true;
        } // deletes the head

        ProductNode current = top;
        while(current.next != null) {
            if (current.next.product.getProductID().equals(targetId)) {
                current.next = current.next.next; // ang pointer ng current will point to next Node's 
                size--;
                return true;
            } // if yung kasunod na node nito ay nageequal sa targetId gawin mo ito OR stop when the next node is the target

            current = current.next;
        }

        return false;


    } // Node1 - node2 - target - Node4 - Node5 

} // A linkedlist that stores Products
