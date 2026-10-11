package presentationlayer;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class LoginPanel extends JPanel {

    private final MainFrame mainframe;

    public LoginPanel(MainFrame mainFrame) {
        this.mainframe = mainFrame;

        setLayout(new FlowLayout());
        setBackground(Color.DARK_GRAY);

        JButton goToManager = new JButton("Log as Manager");
        JButton goToCustomer = new JButton("Log as Customer");
        

        goToManager.addActionListener(e -> {
            mainFrame.changeScreen("ManagerPanel");
        });

        goToCustomer.addActionListener(e -> {
            mainFrame.changeScreen("CustomerPanel");
        });

        add(goToManager);
        add(goToCustomer);
        
    }
    
}
