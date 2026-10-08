package applicationlayer;

import datalayer.SaleRepository;
import datalayer.CustomLinkedLists.ProductLinkedList;
import datalayer.CustomLinkedLists.SaleLinkedList;

public class ManagerService {
    private InventoryService inventoryService;
    private SaleRepository saleRepository;

    public ManagerService(InventoryService inventoryService, SaleRepository saleRepository) {
        this.inventoryService = inventoryService;
        this.saleRepository = saleRepository;
    }

    // View Sales
    public SaleLinkedList viewSales() {
        return saleRepository.getSales();
    }

   // Generate Sales Report Part 1: Items Sold / Sales History
    public SaleLinkedList generateCategorySalesReport() {
        return saleRepository.getSales();
    }

    // Generate Sales Report Part 2: Items With Low Stock
    public ProductLinkedList generateLowStockReport() {
        return inventoryService.checkLowStockThresholds();
    }

    public InventoryService getInventoryService() {
        return inventoryService;
    }
}