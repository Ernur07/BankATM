import Entities.Client;
import Entities.Loan;
import Entities.LoanTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This page is used by manager to see all loans of client
 */
public class ManagerClientLoanPage extends JFrame {
    private Client client;

    public ManagerClientLoanPage(Client client){
        this.client = client;

        setLayout(new GridLayout(2,1));
        JButton back = new JButton("Back");
        back.addActionListener(e -> {
            this.dispose();
        });
        add(back);
        JPanel loanListPanel = new JPanel();
        loanListPanel.setLayout(new BoxLayout(loanListPanel,BoxLayout.Y_AXIS));
        LoanTableModel tm = new LoanTableModel((ArrayList<Loan>) client.getLoans());
        JTable loanTable = new JTable(tm);
        add(loanTable);
        loanTable.setAutoCreateRowSorter(true);
        JScrollPane scrollPane = new JScrollPane(loanTable);
        scrollPane.setPreferredSize(new Dimension(380,280));
        loanListPanel.add(new JLabel(this.client.getLogin()+"'s Loans"));
        loanListPanel.add(scrollPane);
        add(loanListPanel);



        setTitle("Manager Menu");
        setSize(700,700);
        setLocation(200,100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
