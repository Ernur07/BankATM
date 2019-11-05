import Entities.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This page is used to show all loans of the client, also here client can get new loan
 */
public class LoanPage extends JFrame {
    private Client client;

    public LoanPage(Client client){
        this.client=client;

        setLayout(new GridLayout(3,2));
        add(new JPanel());

        JButton back = new JButton("Back");
        back.addActionListener(e -> {
            this.dispose();
        });
        back.setSize(2,2);
        add(back);

        /**
         * Creating ne Loan panel
         */
        JPanel newLoanPanel = new JPanel();
        newLoanPanel.setLayout(new BoxLayout(newLoanPanel,BoxLayout.Y_AXIS));
        JLabel newLoanLabel = new JLabel("Enter amount of money for loan");
        newLoanPanel.add(newLoanLabel);

        JTextField newLoanTextField = new JTextField();
        newLoanPanel.add(newLoanTextField);

        JLabel numberOfYearsLabel = new JLabel("Number of years");
        newLoanPanel.add(numberOfYearsLabel);

        Integer[] years = {1,2,3,4,5};
        JComboBox yearsBox = new JComboBox(years);
        newLoanPanel.add(yearsBox);

        add(newLoanPanel);

        JPanel loanListPanel = new JPanel();

        JButton loanButton = new JButton("Loan");
        loanButton.addActionListener(e->{
            try{
                Double loanAmount=Double.parseDouble(newLoanTextField.getText());
                Integer year = (Integer) yearsBox.getSelectedItem();
                Loan loan = new Loan(0.6,year,loanAmount);
                this.client.getLoans().add(loan);
                JOptionPane.showMessageDialog(new Frame(),"New Loan created");
            }catch (Exception ex){
                JOptionPane.showMessageDialog(new Frame(),"Wrong input. " + ex);
            }
        });
        add(loanButton);

        /**
         * Table of all client's loans
         */
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

        /**
         * Monthly payment button
         */
        JButton loanPaymentButton = new JButton("Pay monthly fee");
        loanPaymentButton.addActionListener(e -> {
            int loanId=loanTable.getSelectedRow();
            if(loanId==-1){
                JOptionPane.showMessageDialog(new Frame(),"Select loan from the list");
            }else{
                LoanPayingPage loanPayingPage = new LoanPayingPage(this.client,this.client.getLoans().get(loanId));
            }

        });

        add(loanPaymentButton);

        setTitle(this.client.getLogin()+" Personal Page");
        setSize(700,700);
        setLocation(200,100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
