package presentationlayer;

import applicationlayer.InventoryService;
import applicationlayer.SaleService;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {

    private final InventoryService inventoryService;
    private final SaleService saleService;

    private final CardLayout cardLayout;
    private final JPanel mainContainer;
    private final LoginPanel loginPanel;
    private final CustomerPanel customerPanel;
    private final ManagerPanel managerPanel;
    
    public MainFrame(InventoryService inventoryService, SaleService saleService) {

        this.inventoryService = inventoryService;
        this.saleService = saleService;

        cardLayout = new CardLayout();
        mainContainer = new JPanel(this.cardLayout);

        loginPanel = new LoginPanel(this); 
        customerPanel = new CustomerPanel(this);
        managerPanel = new ManagerPanel(this);

        mainContainer.add(loginPanel, "LoginPanel");
        mainContainer.add(customerPanel, "CustomerPanel");
        mainContainer.add(managerPanel, "ManagerPanel");
        
        add(mainContainer);

        cardLayout.show(mainContainer, "LoginPanel");

        setContentPane(this.mainContainer);
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void changeScreen(String screenName)  {
        cardLayout.show(mainContainer, screenName);
    }
    
}