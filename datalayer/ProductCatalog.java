package datalayer;

import datalayer.CustomLinkedLists.ProductLinkedList;
import entitylayer.Product;

public class ProductCatalog {

    private ProductLinkedList products;

    public ProductCatalog() {
        products = new ProductLinkedList();
    }
    
    public void addProduct(Product product) {
        products.push(product);
    }

    public Product getProduct(String productId) {
        return products.search(productId); // returns null if none match
    }

    public ProductLinkedList getProducts() {
        return products;
    } // must return a copy or maybe wag na?

}