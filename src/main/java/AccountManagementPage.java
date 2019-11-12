import DatabaseConnection.DatabaseManager;
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
    private DatabaseManager db = new DatabaseManager();

    public AccountManagementPage(Client client) throws HeadlessException {

        this.client=db.findClient(client.getId());
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
                CheckingAccount checkingAccount= (CheckingAccount) accountsComboBox.getSelectedItem();
                db.remove(checkingAccount);
                this.client.setOtherFee(this.client.getOtherFee()+Account.getAccountClosingFee());
                JOptionPane.showMessageDialog(new Frame(),"Account successfully deleted");
            }else if(accountsComboBox.getSelectedItem() instanceof SavingAccount){
                this.client.getSavingAccounts().remove(accountsComboBox.getSelectedItem());
                SavingAccount savingAccount= (SavingAccount) accountsComboBox.getSelectedItem();
                db.remove(savingAccount);
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
        JRadioButton securityAccount = new JRadioButton("Security Account");
        securityAccount.addActionListener(e -> {
            this.accountRadioChoice="Security Account";
        });
        ButtonGroup accountType = new ButtonGroup();
        accountType.add(savingAccount);
        accountType.add(checkingAccount);
        accountType.add(securityAccount);
        addAccount.add(savingAccount);
        addAccount.add(checkingAccount);
        addAccount.add(securityAccount);

        ArrayList<Currency> currencies = (ArrayList<Currency>) db.getAllCurrency();
        JComboBox currency = new JComboBox(currencies.toArray());
        addAccount.add(currency);

        add(addAccount);
        JButton addAccountButton = new JButton("Add");
        addAccountButton.addActionListener(e -> {

            if(addAccountTextField.getText().equals("")||this.accountRadioChoice.equals("")){
                JOptionPane.showMessageDialog(new Frame(),"Wrong input");
            }else{
                if(this.accountRadioChoice.equals("Checking Account")){
                    Currency c = (Currency) currency.getSelectedItem();

                    CheckingAccount checkingAccount1 = new CheckingAccount(addAccountTextField.getText(),0,c,this.client);
                    if(this.client.getAllAccounts().contains(checkingAccount1)){
                        JOptionPane.showMessageDialog(new Frame(),"Account with given name is already exited");
                    }else{
                        this.client.getCheckingAccounts().add(checkingAccount1);
                        db.add(checkingAccount1);
                        JOptionPane.showMessageDialog(new Frame(),"Account successfully created");
                        accountsComboBox.addItem(checkingAccount1);
                        accountsComboBox.repaint();
                    }
                }else if(this.accountRadioChoice.equals("Saving Account")){
                    Currency c = (Currency) currency.getSelectedItem();
                    SavingAccount savingAccount1=new SavingAccount(addAccountTextField.getText(),0,2.5,c,this.client);
                    if(this.client.getAllAccounts().contains(savingAccount1)){
                        JOptionPane.showMessageDialog(new Frame(),"Account with given name is already exited");
                    }else{
                        this.client.getSavingAccounts().add(savingAccount1);
                        db.add(savingAccount1);
                        JOptionPane.showMessageDialog(new Frame(),"Account successfully created");
                        accountsComboBox.addItem(savingAccount1);
                        accountsComboBox.repaint();
                    }
                }else if(this.accountRadioChoice.equals("Security Account")){
                    Currency c = (Currency) currency.getSelectedItem();
                    System.out.println();
                    SecurityAccount securityAccount1 = new SecurityAccount(addAccountTextField.getText(),0,c,this.client);
                    if(this.client.getAllAccounts().contains(securityAccount1)){
                        JOptionPane.showMessageDialog(new Frame(),"Account with given name is already exited");
                    }else if(db.getUsersBalance(this.client)<SecurityAccount.minimumBalance){
                        JOptionPane.showMessageDialog(new Frame(),"Your balance is low for creating SecurityAccount");
                    }
                    else{
                        this.client.getSecurityAccounts().add(securityAccount1);
                        db.add(securityAccount1);
                        JOptionPane.showMessageDialog(new Frame(),"Account successfully created");
                        accountsComboBox.addItem(securityAccount1);
                        accountsComboBox.repaint();
                    }
                }
                this.client.setOtherFee(this.client.getOtherFee()+Account.getAccountOpeningFee());
                db.update(this.client);
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
