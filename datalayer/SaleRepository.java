package datalayer;

import datalayer.CustomLinkedLists.SaleLinkedList;
import entitylayer.Sale;

public class SaleRepository {

    private SaleLinkedList sales;

    public SaleRepository() {
        sales = new SaleLinkedList();
    }

    public void addSale(Sale sale) {
        sales.push(sale);
    }

    public Sale getSale(String targetId) {
        return sales.search(targetId);
    }

    public SaleLinkedList getSales() {
        return sales;
    } // must return a copy or maybe wag na?

}
