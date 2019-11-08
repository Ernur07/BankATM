import DatabaseConnection.DatabaseManager;
import Entities.Account;
import Entities.Bank;
import Entities.Client;
import Entities.Transaction;
import Enums.CurrencyExchangeRate;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


/**
 * This page is used by client to transfer money to other accounts
 */
public class TransferPage extends JFrame {
    private Bank bank;
    private Client client;
    private Client receiverClient;
    private DatabaseManager db = new DatabaseManager();

    public TransferPage(Bank bank, Client client){
        this.bank = bank;
        this.client= db.findClient(client.getId());

        setLayout(new GridLayout(3,2));
        add(new JPanel());
        JButton back = new JButton("Back");
        back.addActionListener(e -> {
            this.dispose();
        });
        add(back);

        JPanel senderPanel = new JPanel();
        senderPanel.setLayout(new BoxLayout(senderPanel,BoxLayout.Y_AXIS));
        JLabel senderLabel= new JLabel("Choose account from you want to transfer money and amount of transfer money");
        senderPanel.add(senderLabel);

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


        senderPanel.add(accountsComboBox);
        senderPanel.add(accountBalance);
        JTextField transferTextField = new JTextField();
        senderPanel.add(transferTextField);
        add(senderPanel);

        add(new JPanel());

        JPanel receiverPanel = new JPanel();
        receiverPanel.setLayout(new BoxLayout(receiverPanel,BoxLayout.Y_AXIS));
        JLabel depositLabel= new JLabel("Choose user to whom you want to transfer money and user's account");
        receiverPanel.add(depositLabel);

        JComboBox clientsComboBox;
        JComboBox receiverAccountsComboBox = new JComboBox();
        if(db.getAllClient().isEmpty()){
            clientsComboBox = new JComboBox();
        }else{
            ArrayList<Client> clients= new ArrayList<>(db.getAllClient()) ;
            clientsComboBox = new JComboBox(clients.toArray());
        }
        clientsComboBox.addActionListener(e -> {
            Client receiver = (Client) clientsComboBox.getSelectedItem();
            this.receiverClient = db.findClient(receiver.getId());
            ArrayList<Account> receiverAccounts = receiver.getAllAccounts();
            receiverAccountsComboBox.removeAllItems();
            for(Account account:receiverAccounts){
                receiverAccountsComboBox.addItem(account);
            }
            receiverAccountsComboBox.repaint();
        });
        receiverPanel.add(clientsComboBox);
        receiverPanel.add(receiverAccountsComboBox);
        add(receiverPanel);


        JButton transferButton = new JButton("Transfer");
        transferButton.addActionListener(e->{
            try{
                Account temp = (Account) accountsComboBox.getSelectedItem();
                Account receiverAccount = (Account) receiverAccountsComboBox.getSelectedItem();
                if(Double.parseDouble(transferTextField.getText()) <=0||Double.parseDouble(transferTextField.getText())>temp.getBalance()){
                    JOptionPane.showMessageDialog(new Frame(),"Wrong input. ");
                }else {
                    Double amount = Double.parseDouble(transferTextField.getText());
                    Transaction transaction = new Transaction(receiverAccount,temp,amount);
                    temp.getTransactions().add(transaction);
                    db.add(transaction);
                    if(temp.getCurrency().equals(receiverAccount.getCurrency())){
                        temp.setBalance(temp.getBalance() - ((1+temp.getTransactionFee())*amount));
                        receiverAccount.setBalance(receiverAccount.getBalance()+amount);
                        db.update(temp);
                        db.update(receiverAccount);
                        JOptionPane.showMessageDialog(new Frame(),"Successful operation ");
                    }else{
                        for(CurrencyExchangeRate exchangeRate:CurrencyExchangeRate.values()){
                            if(exchangeRate.getName().equals(temp.getCurrency()+"2"+receiverAccount.getCurrency())){
                                temp.setBalance(temp.getBalance() - ((1+temp.getTransactionFee())*amount));
                                db.update(temp);
                                receiverAccount.setBalance(receiverAccount.getBalance()+ exchangeRate.getRate()*amount);
                                db.update(receiverAccount);
                                break;
                            }
                        }
                        JOptionPane.showMessageDialog(new Frame(),"Successful operation ");
                    }

                }
            }catch (Exception ex){
                JOptionPane.showMessageDialog(new Frame(),"Wrong input. " + ex);
            }
        });
        add(transferButton);

        setTitle(this.client.getLogin()+" Personal Page");
        setSize(700,700);
        setLocation(200,100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
