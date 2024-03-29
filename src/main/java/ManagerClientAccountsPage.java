import DatabaseConnection.DatabaseManager;
import Entities.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This page is used by manager to see all registered clients
 */
public class ManagerClientAccountsPage extends JFrame {
    private Client client;
    private DatabaseManager db= new DatabaseManager();

    public ManagerClientAccountsPage(Client client){
        this.client=db.findClient(client.getId());

        setLayout(new GridLayout(3,2));
        add(new JLabel(client.getLogin()+"'s accounts"));
        JButton back = new JButton("Back");
        back.addActionListener(e -> {
            this.dispose();
        });
        back.setSize(2,2);
        add(back);

        /**
         * Saving account table
         */
        JPanel savingAccountPanel = new JPanel();
        savingAccountPanel.setLayout(new BoxLayout(savingAccountPanel,BoxLayout.Y_AXIS));
        SavingAccountTableModel tm = new SavingAccountTableModel(new ArrayList<>(client.getSavingAccounts()));
        JTable savingTable = new JTable(tm);
        add(savingTable);
        savingTable.setAutoCreateRowSorter(true);
        JScrollPane scrollPane = new JScrollPane(savingTable);
        scrollPane.setPreferredSize(new Dimension(380,280));
        savingAccountPanel.add(new JLabel(this.client.getLogin()+"'s Saving Accounts"));
        savingAccountPanel.add(scrollPane);
        add(savingAccountPanel);

        /**
         * Pay interest rate to selected saving account button
         */
        JButton payInterestRate = new JButton("Pay Interest Rate");
        payInterestRate.addActionListener(e -> {
            int savingId=savingTable.getSelectedRow();

            if(savingId==-1){
                JOptionPane.showMessageDialog(new Frame(),"Select saving account from the list");
            }else{
                SavingAccount selectedAccount =  client.getSavingAccounts().get(savingId);
                selectedAccount.setBalance((1+selectedAccount.getInterestRate())*selectedAccount.getBalance());
                db.update(selectedAccount);
                JOptionPane.showMessageDialog(new Frame(),"Interest rate payment is applied to given account");
            }
        });
        add(payInterestRate);

        /**
         * Checking account table panel
         */
        JPanel checkingAccountPanel = new JPanel();
        checkingAccountPanel.setLayout(new BoxLayout(checkingAccountPanel,BoxLayout.Y_AXIS));
        CheckingAccountTableModel tm2 = new CheckingAccountTableModel(new ArrayList<>(client.getCheckingAccounts()));
        JTable checkingTable = new JTable(tm2);
        add(checkingTable);
        checkingTable.setAutoCreateRowSorter(true);
        JScrollPane scrollPane2 = new JScrollPane(checkingTable);
        scrollPane.setPreferredSize(new Dimension(380,280));
        checkingAccountPanel.add(new JLabel(this.client.getLogin()+"'s Checking Accounts"));
        checkingAccountPanel.add(scrollPane2);
        add(checkingAccountPanel);


        setTitle(this.client.getLogin()+" Personal Page");
        setSize(700,700);
        setLocation(200,100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
