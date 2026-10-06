package datalayer.CustomLinkedLists;

import entitylayer.Sale;

public class SaleLinkedList { 

    private SaleNode top;
    private int size;

    public SaleLinkedList() {
        top = null; // Once a linkedlist is created the head is null
        size = 0; // starts at 0 to indicate an empty list idk pala if -1 or 0
    }

    public SaleNode getTopNode() {
            return top;
    }

    public Sale getTopSale() {
        if (!isEmpty()) {
            return top.sale;
        } else {
            return null;
        }
    }

    public boolean push(Sale data) {
        SaleNode node = new SaleNode(data);
        node.next = top;
        top = node;
        size++;
        return true;
    } // pwede rin void nalang

    public Sale pop() {
        if (!isEmpty()) {
            Sale sale = top.sale;
            top = top.next;
            size--;
            return sale;
        } 
        return null;
    } // returns null to indicate an empty stack

    public Sale peek() {
        if (!isEmpty()) {
            return top.sale;
        } else {
            return null;
        }
            
    } // returns null if empty

    public boolean isEmpty() {
        return top == null;
    } 
   
    public String display() {
        String temp = "";
        SaleNode current = top;
        while (current != null) {
            temp += current.sale.getSaleId() + "\n";
            current = current.next;
        }

        if (!temp.isEmpty()) {
            return temp;
        } else {
            return "No Sales Found";
        }

    } // btw this returns string cuz this shoudlnt print

    public Sale search(String targetId) { 
        SaleNode current = top;
        while (current != null) {
            if (current.sale.getSaleId().equals(targetId)) {
                return current.sale;
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

        if (top.sale.getSaleId().equals(targetId)) {
            top = top.next;
            size--;
            return true;
        } // deletes the head

        SaleNode current = top;
        while(current.next != null) {
            if (current.next.sale.getSaleId().equals(targetId)) {
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
            SaleNode current = top;
            SaleNode target;
            for (int i = 0; i < index -1; i++) {
                current = current.next;
            }
            target = current.next;
            current.next =target.next;
        }

        size--;
        return true;       

    } // starts at zero index based 

} // A linkedlist that stores Products
