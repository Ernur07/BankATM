import Entities.Bank;
import Entities.Client;

import javax.swing.*;
import java.awt.*;

/**
 * This page is used by client to create new client profile
 */
public class ClientLoginPage extends JFrame {
    private Bank bank;
    public ClientLoginPage(Bank bank) {
        this.bank=bank;
        setTitle("Client login page");
        setLayout(new GridLayout(5,2));
        JLabel label = new JLabel("Please enter your name and surname");
        add(label);
        add(new JPanel());

        JTextField clientName = new JTextField();
        add(clientName);
        add(new JLabel("Your name"));

        JTextField clientSurname = new JTextField();
        add(clientSurname);
        add(new JLabel("Your Surname"));


        JTextField login = new JTextField();
        add(login);
        add(new JLabel("Your Login"));
        add(new JPanel());

        JButton submit=new JButton("Submit");
        submit.addActionListener(e -> {
            Client client = new Client(clientName.getText(),clientSurname.getText(),login.getText());
            if(clientName.getText().equals("")||clientSurname.getText().equals("")){
                JOptionPane.showMessageDialog(new Frame(),"Wrong input");
            }else if(this.bank.getClients().contains(client)){
                JOptionPane.showMessageDialog(new Frame(),"Client with this login is already existed");
            }else{
                this.bank.getClients().add(client);
                ClientPersonalPage clientPersonalPage = new ClientPersonalPage(bank,client);
            }
        });
        add(submit);

        setSize(700,700);
        setLocation(200,100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
