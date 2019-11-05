import Entities.Bank;
import Entities.ClientTableModel;
import Entities.Manager;
import Entities.SavingAccountTableModel;

import javax.swing.*;
import java.awt.*;


/**
 * Main GUI for manager
 */
public class ManagerMenu extends JFrame {
    private Bank bank;
    public ManagerMenu(Bank bank) {
        this.bank = bank;

        setLayout(new GridLayout(2,2));

        JLabel nameLabel = new JLabel("Manager Menu");
        add(nameLabel);

        JButton logOut=new JButton("Logout");
        logOut.addActionListener(e -> {
            OpeningMenu openingMenu = new OpeningMenu(bank);
            dispose();
        });
        add(logOut);

        JPanel clientListPanel = new JPanel();
        clientListPanel.setLayout(new BoxLayout(clientListPanel,BoxLayout.Y_AXIS));
        JLabel clientListLabel= new JLabel("List of clients");
        clientListPanel.add(clientListLabel);

        ClientTableModel tm = new ClientTableModel(bank.getClients());
        JTable clientsTable = new JTable(tm);
        add(clientsTable);
        clientsTable.setAutoCreateRowSorter(true);
        JScrollPane scrollPane = new JScrollPane(clientsTable);
        scrollPane.setPreferredSize(new Dimension(380,280));
        clientListPanel.add(scrollPane);
        add(clientListPanel);

        JPanel clientActionPanel = new JPanel();
        JButton clientAccountButton = new JButton("Accounts");
        clientAccountButton.addActionListener(e -> {
            int clientId = clientsTable.getSelectedRow();
            if(clientId==-1){
                JOptionPane.showMessageDialog(new Frame(),"Select client from the list");
            }else{
                ManagerClientAccountsPage managerClientAccountsPage = new ManagerClientAccountsPage(this.bank.getClients().get(clientId));
            }
        });
        clientActionPanel.add(clientAccountButton);

        JButton clientLoanButton = new JButton("Loans");
        clientLoanButton.addActionListener(e -> {
            int clientId = clientsTable.getSelectedRow();
            if(clientId==-1){
                JOptionPane.showMessageDialog(new Frame(),"Select client from the list");
            }else{
                ManagerClientLoanPage managerClientLoanPage = new ManagerClientLoanPage(this.bank.getClients().get(clientId));
            }
        });
        clientActionPanel.add(clientLoanButton);

        JButton clientTransactionPage = new JButton("Transactions");
        clientTransactionPage.addActionListener(e -> {
            int clientId = clientsTable.getSelectedRow();
            if(clientId==-1){
                JOptionPane.showMessageDialog(new Frame(),"Select client from the list");
            }else{
                TransactionsListPage transactionsListPage = new TransactionsListPage(this.bank.getClients().get(clientId));
            }
        });
        clientActionPanel.add(clientTransactionPage);



        add(clientActionPanel);


        /*JButton listOfClients = new JButton("Clients");
        listOfClients.addActionListener(e -> {

        });
        add(listOfClients);

        JButton loans = new JButton("Loans");
        loans.addActionListener(e -> {

        });
        add(loans);

        JButton transactions = new JButton("Transactions");
        loans.addActionListener(e -> {
        });
        add(transactions);*/


        setTitle("Manager Menu");
        setSize(700,700);
        setLocation(200,100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}