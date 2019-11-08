import DatabaseConnection.DatabaseManager;
import Entities.Bank;
import Entities.Client;

import javax.swing.*;
import java.awt.*;

/**
 * This page is used by client to create new client profile
 */
public class ClientLoginPage extends JFrame {
    private Bank bank;
    private DatabaseManager db = new DatabaseManager();
    public ClientLoginPage(Bank bank) {
        this.bank=bank;
        setTitle("Client login page");
        setLayout(new GridLayout(5,2));


        JTextField clientName = new JTextField();
        add(clientName);
        add(new JLabel("Your name"));

        JTextField clientSurname = new JTextField();
        add(clientSurname);
        add(new JLabel("Your Surname"));


        JTextField login = new JTextField();
        add(login);
        add(new JLabel("Your Login"));


        JTextField password = new JTextField();
        add(password);
        add(new JLabel("Your Password"));

        JButton submit=new JButton("Submit");
        submit.addActionListener(e -> {
            //Client client = new Client(clientName.getText(),clientSurname.getText(),login.getText(),password.getText());
            if(clientName.getText().equals("")||clientSurname.getText().equals("")||login.getText().equals("")||password.getText().equals("")){
                JOptionPane.showMessageDialog(new Frame(),"Wrong input");
            }else if(db.loginCheck(login.getText())){
                JOptionPane.showMessageDialog(new Frame(),"Client with this login is already existed");
            }else{
                Client client = new Client(clientName.getText(),clientSurname.getText(),login.getText(),password.getText());
                this.bank.getClients().add(client);
                db.add(client);
                Client dataClient = db.findClient(client.getId());
                ClientPersonalPage clientPersonalPage = new ClientPersonalPage(bank,dataClient);
            }
        });
        add(submit);

        setSize(700,700);
        setLocation(200,100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
