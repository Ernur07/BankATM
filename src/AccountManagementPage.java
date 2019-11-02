import Entities.*;
import Enums.Currencies;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This page is used by client to delete and create accounts
 */
public class AccountManagementPage extends JFrame {
    private Client client;
    private String accountRadioChoice="";

    public AccountManagementPage(Client client) throws HeadlessException {

        this.client=client;
        setLayout(new GridLayout(3,2));
        JLabel nameLabel = new JLabel("Welcome "+client.getFirstname()+" "+client.getSurname());
        add(nameLabel);

        /**
         * Go back button
         */
        JButton back = new JButton("Back");
        back.addActionListener(e -> {
            this.dispose();
        });
        back.setSize(2,2);
        add(back);

        /**
         * Delete account panel
         */
        JPanel deletePanel=new JPanel();
        JLabel deleteLabel=new JLabel("Choose account to delete");
        deletePanel.add(deleteLabel);

        JComboBox accountsComboBox;
        if(this.client.getSavingAccounts().isEmpty()&&this.client.getCheckingAccounts().isEmpty()){
            accountsComboBox = new JComboBox();
        }else{
            ArrayList<Account> accounts=this.client.getAllAccounts();
            accountsComboBox = new JComboBox(accounts.toArray());
        }
        deletePanel.add(accountsComboBox);
        add(deletePanel);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
            if(accountsComboBox.getSelectedItem() instanceof CheckingAccount){
                this.client.getCheckingAccounts().remove(accountsComboBox.getSelectedItem());
                this.client.setOtherFee(this.client.getOtherFee()+Account.getAccountClosingFee());
                JOptionPane.showMessageDialog(new Frame(),"Account successfully deleted");
            }else if(accountsComboBox.getSelectedItem() instanceof SavingAccount){
                this.client.getSavingAccounts().remove(accountsComboBox.getSelectedItem());
                this.client.setOtherFee(this.client.getOtherFee()+Account.getAccountClosingFee());
                JOptionPane.showMessageDialog(new Frame(),"Account successfully deleted");
            }else{
                JOptionPane.showMessageDialog(new Frame(),"Wrong input");
            }
        });
        add(deleteButton);

        /**
         * Adding new account panel
         */
        JPanel addAccount = new JPanel();
        addAccount.setLayout(new BoxLayout(addAccount,BoxLayout.Y_AXIS));
        JLabel addAccountLabel = new JLabel("Add new Account");
        addAccount.add(addAccountLabel);
        JTextField addAccountTextField = new JTextField();
        addAccount.add(addAccountTextField);

        JRadioButton checkingAccount = new JRadioButton("Checking Account");
        checkingAccount.addActionListener(e -> {
            this.accountRadioChoice="Checking Account";
        });
        JRadioButton savingAccount = new JRadioButton("Saving Account");
        savingAccount.addActionListener(e -> {
            this.accountRadioChoice="Saving Account";
        });
        ButtonGroup accountType = new ButtonGroup();
        accountType.add(savingAccount);
        accountType.add(checkingAccount);
        addAccount.add(savingAccount);
        addAccount.add(checkingAccount);


        JComboBox currency = new JComboBox(Currencies.values());
        addAccount.add(currency);

        add(addAccount);
        JButton addAccountButton = new JButton("Add");
        addAccountButton.addActionListener(e -> {

            if(addAccountTextField.getText().equals("")||this.accountRadioChoice.equals("")){
                JOptionPane.showMessageDialog(new Frame(),"Wrong input");
            }else{
                if(this.accountRadioChoice.equals("Checking Account")){
                    Currencies c = (Currencies) currency.getSelectedItem();

                    CheckingAccount checkingAccount1 = new CheckingAccount(addAccountTextField.getText(),0,c.getValue());
                    if(this.client.getAllAccounts().contains(checkingAccount1)){
                        JOptionPane.showMessageDialog(new Frame(),"Account with given name is already exited");
                    }else{
                        this.client.getCheckingAccounts().add(checkingAccount1);
                        JOptionPane.showMessageDialog(new Frame(),"Account successfully created");
                    }
                }else if(this.accountRadioChoice.equals("Saving Account")){
                    Currencies c = (Currencies) currency.getSelectedItem();
                    SavingAccount savingAccount1=new SavingAccount(addAccountTextField.getText(),0,2.5,c.getValue());
                    if(this.client.getAllAccounts().contains(savingAccount1)){
                        JOptionPane.showMessageDialog(new Frame(),"Account with given name is already exited");
                    }else{
                        this.client.getSavingAccounts().add(savingAccount1);
                        JOptionPane.showMessageDialog(new Frame(),"Account successfully created");
                    }
                }
                this.client.setOtherFee(this.client.getOtherFee()+Account.getAccountOpeningFee());
            }
        });
        add(addAccountButton);




        setTitle(client.getLogin()+" Personal Page");
        setSize(700,700);
        setLocation(200,100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


}
