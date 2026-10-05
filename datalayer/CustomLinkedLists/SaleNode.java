package datalayer.CustomLinkedLists;

import entitylayer.Sale;

public class SaleNode {
    
    public Sale sale;
    public SaleNode next;

    public SaleNode(Sale sale) {
        this.sale = sale;
        this.next = null;
    } // Creating Nodes in the constructor = Initializes Product and product.next to null
    


}
