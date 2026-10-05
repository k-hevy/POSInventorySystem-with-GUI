package datalayer.CustomLinkedLists;

import entitylayer.Product;

public class ProductNode {

    public Product product;
    public ProductNode next;

    public ProductNode(Product product) {
        this.product = product;
        this.next = null;
    } // Creating Nodes in the constructor = Initializes Product and product.next to null
    
}
