import Entities.CheckingAccountTableModel;
import Entities.Client;
import Entities.SavingAccountTableModel;
import Entities.TransactionTableModel;

import javax.swing.*;
import java.awt.*;

/**
 * Page which shows transactions of particular client
 */
public class TransactionsListPage extends JFrame {
    private Client client;

    public TransactionsListPage(Client client){
        this.client=client;


        setLayout(new GridLayout(2,1));

        JButton back = new JButton("Back");
        back.addActionListener(e -> {
            this.dispose();
        });
        back.setSize(2,2);
        add(back);

        JPanel transactionPanel = new JPanel();
        transactionPanel.setLayout(new BoxLayout(transactionPanel,BoxLayout.Y_AXIS));
        TransactionTableModel tm = new TransactionTableModel(this.client.getAllTransactions());
        JTable transactionTable = new JTable(tm);
        add(transactionTable);
        transactionTable.setAutoCreateRowSorter(true);
        JScrollPane scrollPane = new JScrollPane(transactionTable);
        scrollPane.setPreferredSize(new Dimension(380,280));
        transactionPanel.add(new JLabel(this.client.getLogin()+"'s Transactions"));
        transactionPanel.add(scrollPane);
        add(transactionPanel);



        setTitle(this.client.getLogin()+" Personal Page");
        setSize(700,700);
        setLocation(200,100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
