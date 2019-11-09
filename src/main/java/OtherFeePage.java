import DatabaseConnection.DatabaseManager;
import Entities.Account;
import Entities.Client;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.util.ArrayList;

/**
 * This page is used by client to pay other fees
 */
public class OtherFeePage extends JFrame {
    private Client client;
    private DatabaseManager db= new DatabaseManager();

    public OtherFeePage(Client client){
        this.client=db.findClient(client.getId());

        setLayout(new GridLayout(2,2));
        JLabel otherFee = new JLabel("Other fee: "+this.client.getOtherFee());
        add(otherFee);
        JButton back = new JButton("Back");
        back.addActionListener(e -> {
            this.dispose();
        });
        back.setSize(2,2);
        add(back);

        JPanel otherFeePanel = new JPanel();
        otherFeePanel.setLayout(new BoxLayout(otherFeePanel,BoxLayout.Y_AXIS));
        JLabel otherFeeLabel= new JLabel("Choose account from you want to transfer money for other fee and amount of money");
        otherFeePanel.add(otherFeeLabel);

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
            accountBalance.setText("Balance: "+temp.getBalance());
            accountBalance.repaint();
        });


        otherFeePanel.add(accountsComboBox);
        otherFeePanel.add(accountBalance);
        JTextField otherFeeTextField = new JTextField();
        otherFeePanel.add(otherFeeTextField);
        add(otherFeePanel);

        JButton otherFeeButton = new JButton("Transfer");
        otherFeeButton.addActionListener(e->{
            try{
                Account temp = (Account) accountsComboBox.getSelectedItem();
                if(Double.parseDouble(otherFeeTextField.getText()) <=0||Double.parseDouble(otherFeeTextField.getText())>temp.getBalance()){
                    JOptionPane.showMessageDialog(new Frame(),"Wrong input. ");
                }else {
                    this.client.setOtherFee(this.client.getOtherFee()-Double.parseDouble(otherFeeTextField.getText()));
                    temp.setBalance(temp.getBalance() - Double.parseDouble(otherFeeTextField.getText()));
                    db.update(this.client);
                    db.update(temp);
                    otherFee.setText("Other fee: "+this.client.getOtherFee());
                    otherFee.repaint();

                    JOptionPane.showMessageDialog(new Frame(),"Successful operation ");
                }
            }catch (Exception ex){
                JOptionPane.showMessageDialog(new Frame(),"Wrong input. " + ex);
            }
        });
        add(otherFeeButton);

        setTitle(this.client.getLogin()+" Personal Page");
        setSize(700,700);
        setLocation(200,100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
