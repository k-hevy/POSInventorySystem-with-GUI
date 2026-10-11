package applicationlayer;

import datalayer.AccountRepository;
import datalayer.ProductCatalog;
import datalayer.SaleRepository;
import presentationlayer.MainFrame;

public class POSInventorySystem {

    public static void main(String[] args) {

        AccountRepository accounts = new AccountRepository();
        SaleRepository sales = new SaleRepository();
        ProductCatalog products = new ProductCatalog();

        InventoryService inventoryService = new InventoryService(products);
        SaleService saleService = new SaleService(sales);

        MainFrame mainFrame = new MainFrame(inventoryService, saleService);
    }

    

}