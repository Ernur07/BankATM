import DatabaseConnection.DatabaseManager;
import Entities.Account;
import Entities.Client;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.util.ArrayList;


/**
 * Here client can deposit money to his account
 */
public class DepositPage extends JFrame {
    private Client client;
    private DatabaseManager db = new DatabaseManager();

    public DepositPage(Client client) throws HeadlessException {
        this.client=db.findClient(client.getId());

        setLayout(new GridLayout(2,2));
        add(new JPanel());
        JButton back = new JButton("Back");
        back.addActionListener(e -> {
            this.dispose();
        });
        back.setSize(2,2);
        add(back);

        JPanel depositPanel = new JPanel();
        depositPanel.setLayout(new BoxLayout(depositPanel,BoxLayout.Y_AXIS));
        JLabel depositLabel= new JLabel("Choose account from you want to deposit money and amount of deposit money");
        depositPanel.add(depositLabel);

        JLabel accountBalance = new JLabel("Balance: ");
        JComboBox accountsComboBox;
        if(this.client.getSavingAccounts().isEmpty()&&this.client.getCheckingAccounts().isEmpty()){
            accountsComboBox = new JComboBox();
        }else{
            ArrayList<Account> accounts=this.client.getAllAccounts();
            accountsComboBox = new JComboBox(accounts.toArray());
        }
        accountsComboBox.addActionListener(e->{
            Account temp = (Account) accountsComboBox.getSelectedItem();
            System.out.println(temp.getBalance());
            accountBalance.setText("Balance: "+temp.getBalance());
            accountBalance.repaint();
        });


        depositPanel.add(accountsComboBox);
        depositPanel.add(accountBalance);
        JTextField depositTextField = new JTextField();
        depositPanel.add(depositTextField);
        add(depositPanel);

        JButton depositButton = new JButton("Deposit");
        depositButton.addActionListener(e->{
            try{
                Account temp = (Account) accountsComboBox.getSelectedItem();
                if(Double.parseDouble(depositTextField.getText()) <=0){
                    JOptionPane.showMessageDialog(new Frame(),"Wrong input. ");
                }else {
                    temp.setBalance(temp.getBalance() + Double.parseDouble(depositTextField.getText()));
                    db.update(temp);
                    JOptionPane.showMessageDialog(new Frame(),"Successful operation ");
                }
            }catch (Exception ex){
                JOptionPane.showMessageDialog(new Frame(),"Wrong input. " + ex);
            }
        });
        add(depositButton);

        setTitle(this.client.getLogin()+" Personal Page");
        setSize(700,700);
        setLocation(200,100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
