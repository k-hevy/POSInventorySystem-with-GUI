package datalayer.CustomLinkedLists;

import entitylayer.User;

public class AccountNode {
   
    public AccountNode next;
    public User account;

    public AccountNode(User account) {
        this.account = account;
        this.next = null;
    } // Creating Nodes in the constructor = Initializes Product and product.next to null
    
}


