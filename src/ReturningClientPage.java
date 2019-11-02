import Entities.Bank;
import Entities.Client;

import javax.swing.*;
import java.awt.*;

/**
 * Log in page for returning clients
 */
public class ReturningClientPage extends JFrame {
    private Bank bank;

    public ReturningClientPage(Bank bank){
        this.bank=bank;
        this.bank=bank;
        setTitle("Client login page");
        setLayout(new GridLayout(2,2));
        JLabel label = new JLabel("Please enter your login");
        add(label);
        add(new JPanel());


        JTextField login = new JTextField();
        add(login);

        JButton submit=new JButton("Submit");
        submit.addActionListener(e -> {
            Client client = this.bank.getPlayerByLogin(login.getText());
            if(login.getText().equals("")){
                JOptionPane.showMessageDialog(new Frame(),"Wrong input");
            }else if(client==null){
                JOptionPane.showMessageDialog(new Frame(),"No client found");
            }else{
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
