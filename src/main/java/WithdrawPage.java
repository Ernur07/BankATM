import Entities.Account;
import Entities.Bank;
import Entities.Client;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.InputMismatchException;


/**
 * Page which is used to withdraw money from accounts
 */
public class WithdrawPage extends JFrame {
    private Client client;

    public WithdrawPage(Client client) throws HeadlessException {
        this.client=client;

        setLayout(new GridLayout(2,2));
        add(new JPanel());
        JButton back = new JButton("Back");
        back.addActionListener(e -> {
            this.dispose();
        });
        back.setSize(2,2);
        add(back);

        JPanel withdrawPanel = new JPanel();
        withdrawPanel.setLayout(new BoxLayout(withdrawPanel,BoxLayout.Y_AXIS));
        JLabel withdarawLabel= new JLabel("Choose account from you want to withdraw money and amount of withdrawal money");
        withdrawPanel.add(withdarawLabel);

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


        withdrawPanel.add(accountsComboBox);
        withdrawPanel.add(accountBalance);
        JTextField withdrawTextField = new JTextField();
        withdrawPanel.add(withdrawTextField);
        add(withdrawPanel);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(e->{
            try{
                Account temp = (Account) accountsComboBox.getSelectedItem();
                if(Double.parseDouble(withdrawTextField.getText()) <=0||Double.parseDouble(withdrawTextField.getText())>temp.getBalance()){
                    JOptionPane.showMessageDialog(new Frame(),"Wrong input. ");
                }else {
                    temp.setBalance(temp.getBalance() - Double.parseDouble(withdrawTextField.getText()));
                    JOptionPane.showMessageDialog(new Frame(),"Successful operation ");
                }
            }catch (Exception ex){
                JOptionPane.showMessageDialog(new Frame(),"Wrong input. " + ex);
            }
        });
        add(withdrawButton);

        setTitle(this.client.getLogin()+" Personal Page");
        setSize(700,700);
        setLocation(200,100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
