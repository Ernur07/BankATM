import DatabaseConnection.DatabaseManager;
import Entities.Bank;
import Entities.Client;

import javax.swing.*;
import java.awt.*;

/**
 * Log in page for returning clients
 */
public class ReturningClientPage extends JFrame {
    private Bank bank;
    private DatabaseManager db = new DatabaseManager();

    public ReturningClientPage(Bank bank){
        this.bank=bank;
        this.bank=bank;
        setTitle("Client login page");
        setLayout(new GridLayout(3,2));
        JLabel label = new JLabel("Please enter your login");
        add(label);
        JTextField login = new JTextField();
        add(login);


        JLabel passwordLabel = new JLabel("Please enter your password");
        add(passwordLabel);
        JTextField password = new JTextField();
        add(password);
        add(new JPanel());

        JButton submit=new JButton("Submit");
        submit.addActionListener(e -> {
            //Client client = this.bank.getPlayerByLogin(login.getText());
            if(login.getText().equals("")){
                JOptionPane.showMessageDialog(new Frame(),"Wrong input");
            }else if(db.isRegistered(login.getText(),password.getText())){
                JOptionPane.showMessageDialog(new Frame(),"No client found");
            }else{
                Client client = this.db.findClientByLogin(login.getText());
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
