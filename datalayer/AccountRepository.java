package datalayer;

import datalayer.CustomLinkedLists.AccountLinkedList;
import entitylayer.User;

public class AccountRepository {
    
    private AccountLinkedList accounts;

    public AccountRepository() {
        accounts = new AccountLinkedList();
    }
    
    public void addUser(User user) {
        accounts.push(user);
    }

    public User getUser(String userId) {
        return accounts.search(userId); // returns null if none match
    }

    public AccountLinkedList getUsers() {
        return accounts;
    } // must return a copy or maybe wag na?

}
