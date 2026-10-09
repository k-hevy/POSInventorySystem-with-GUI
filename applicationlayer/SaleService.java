package applicationlayer;

import datalayer.CustomLinkedLists.CartItemNode;
import datalayer.CustomLinkedLists.SaleLinkedList;
import datalayer.CustomLinkedLists.SaleNode;
import datalayer.SaleRepository;
import entitylayer.Cart;
import entitylayer.CartItem;
import entitylayer.Customer;
import entitylayer.Sale;
import java.time.Instant;

public class SaleService {

    SaleRepository saleRepository;

    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public Sale checkout(Customer customer, String saleId) {
        Cart cart = customer.getCart();
        double total = cart.calculateTotal();

        if (total <= 0) {
            return null; // Empty cart cannot checkout
        }

        // Create a copy of the cart for the sale record so clearing the customer cart doesn't wipe receipt items
        Cart snapshotCart = new Cart(cart.getCartID());
        CartItemNode current = cart.getItems().getTopNode();
        
        while (current != null) {
            if (current.cartItem != null) {
                snapshotCart.addItem(new CartItem(current.cartItem.getProduct(), current.cartItem.getQuantity()));
            }
            current = current.next;
        }

        Sale sale = new Sale(saleId, total, snapshotCart, Instant.now(), customer.getUserID());
        saleRepository.addSale(sale);

        // Clear customer cart after saving the snapshot
        customer.getCart().clear();

        return sale;

    }

    public SaleLinkedList getCustomerSales(Customer customer) {

        SaleLinkedList customerSales = new SaleLinkedList();

        if (customer == null || customer.getUserID().trim().isEmpty() || customer.getUserID() == null) {
            return customerSales; // empty list
        } // returns empty list

        SaleLinkedList sales = saleRepository.getSales();
        

        SaleNode current = sales.getTopNode();

        if (current == null) {
            return customerSales;
        } // returns emptyt list

        while (current != null) {
            Sale sale = current.sale;
            if (sale != null && customer.getUserID().equals(sale.getCustomerID())) {
                customerSales.push(sale);
            }
            current = current.next;
        }

        return customerSales;
    }

    // Search Receipt by ID (customer)
    public Sale customerSearchSaleById(Customer customer, String targetSaleId) {

        if (customer == null || targetSaleId == null || targetSaleId.isEmpty()) { return null; }

        SaleNode current = saleRepository.getSales().getTopNode();

        while (current != null) {
            Sale sale = current.sale;
            if (sale != null && sale.getSaleId().equals(targetSaleId)) {
                if (customer.getUserID().equals(sale.getCustomerID())) {
                    return sale;
                } else {
                    return null;
                }
            }
            current = current.next;
        }
        return null;
       
    }

    // Search Receipt by ID (Manager)
    public Sale managerSearchSaleById(String targetSaleId) {

        if (targetSaleId == null || targetSaleId.isEmpty()) { return null; }

        SaleNode current = saleRepository.getSales().getTopNode();

        while (current != null) {
            Sale sale = current.sale;
            if (sale != null && sale.getSaleId().equals(targetSaleId)) {
                return sale;
            }
            current = current.next;
        }
        return null;
    
    }

    // get all sales
    public SaleLinkedList getAllSales() {
        return saleRepository.getSales();
    }




}

