import Entities.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This page shows all checking and saving accounts of the client
 */
public class AccountListPage extends JFrame {
    private Client client;

    public AccountListPage(Client client){
        this.client = client;


        setLayout(new GridLayout(3,1));
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
         * Saving accounts list
         */
        JPanel savingAccountPanel = new JPanel();
        savingAccountPanel.setLayout(new BoxLayout(savingAccountPanel,BoxLayout.Y_AXIS));
        SavingAccountTableModel tm = new SavingAccountTableModel(new ArrayList<SavingAccount>(client.getSavingAccounts()));
        JTable savingTable = new JTable(tm);
        add(savingTable);
        savingTable.setAutoCreateRowSorter(true);
        JScrollPane scrollPane = new JScrollPane(savingTable);
        scrollPane.setPreferredSize(new Dimension(380,280));
        savingAccountPanel.add(new JLabel(this.client.getLogin()+"'s Saving Accounts"));
        savingAccountPanel.add(scrollPane);
        add(savingAccountPanel);

        /**
         * Checking accounts list
         */
        JPanel checkingAccountPanel = new JPanel();
        checkingAccountPanel.setLayout(new BoxLayout(checkingAccountPanel,BoxLayout.Y_AXIS));
        CheckingAccountTableModel tm2 = new CheckingAccountTableModel(new ArrayList<CheckingAccount> (client.getCheckingAccounts()));
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
