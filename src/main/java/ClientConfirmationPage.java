import Entities.Bank;

import javax.swing.*;
import java.awt.*;

/**
 * This page is used during client's authorization process
 */
public class ClientConfirmationPage extends JFrame{
    private Bank bank;
    public ClientConfirmationPage(Bank bank) {
        this.bank=bank;

        setLayout(new GridLayout(5,2));

        JLabel nameLabel = new JLabel("Choose you option for ATM identification");
        add(nameLabel);

        JButton back=new JButton("Back");
        back.addActionListener(e -> {
            dispose();
        });
        add(back);

        JButton returnedClient = new JButton("Registered client");
        returnedClient.addActionListener(e -> {
            ReturningClientPage returningClientPage=new ReturningClientPage(this.bank);
            dispose();
        });
        add(returnedClient);

        JButton newClient = new JButton("New client");
        newClient.addActionListener(e -> {
            ClientLoginPage clientLoginPage = new ClientLoginPage(this.bank);
        });
        add(newClient);


        setSize(700,700);
        setLocation(200,100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


}
