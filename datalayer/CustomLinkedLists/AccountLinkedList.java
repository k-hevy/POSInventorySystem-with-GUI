package datalayer.CustomLinkedLists;

import entitylayer.User;

public class AccountLinkedList { 

    private AccountNode top;
    private int size;

    public AccountLinkedList() {
        top = null; // Once a linkedlist is created the head is null
        size = 0; // starts at 0 to indicate an empty list idk pala if -1 or 0
    }

    public AccountNode getTopNode() {
            return top;
    }

    public User getTopAccount() {
        if (!isEmpty()) {
            return top.account;
        } else {
            return null;
        }
    }

    public boolean push(User data) {
        AccountNode node = new AccountNode(data);
        node.next = top;
        top = node;
        size++;
        return true;
    } // pwede rin void nalang

    public User pop() {
        if (!isEmpty()) {
            User account = top.account;
            top = top.next;
            size--;
            return account;
        } 
        return null;
    } // returns null to indicate an empty stack

    public User peek() {
        if (!isEmpty()) {
            return top.account;
        } else {
            return null;
        }
            
    } // returns null if empty

    public boolean isEmpty() {
        return top == null;
    } 
   
    public String display() {
        String temp = "";
        AccountNode current = top;
        while (current != null) {
            temp += current.account.getUserID() + "\n";
            current = current.next;
        }

        if (!temp.isEmpty()) {
            return temp;
        } else {
            return "No Accounts Found";
        }

    } // btw this returns string cuz this shoudlnt print

    public User search(String targetId) { 
        AccountNode current = top;
        while (current != null) {
            if (current.account.getUserID().equals(targetId)) {
                return current.account;
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

        if (top.account.getUserID().equals(targetId)) {
            top = top.next;
            size--;
            return true;
        } // deletes the head

        AccountNode current = top;
        while(current.next != null) {
            if (current.next.account.getUserID().equals(targetId)) {
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
            AccountNode current = top;
            AccountNode target;
            for (int i = 0; i < index -1; i++) {
                current = current.next;
            }
            target = current.next;
            current.next = target.next;
        }

        size--;
        return true;       

    } // starts at zero index based 

} // A linkedlist that stores Accounts
