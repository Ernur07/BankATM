import Entities.Account;
import Entities.Client;
import Entities.Loan;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This page is used by client to pay monthly payment of loans
 */
public class LoanPayingPage extends JFrame {
    private Client client;
    private Loan loan;

    public LoanPayingPage(Client client, Loan loan){
        this.client = client;
        this.loan=loan;
        setLayout(new GridLayout(2,2));
        add(new JPanel());
        JButton back = new JButton("Back");
        back.addActionListener(e -> {
            this.dispose();
        });
        add(back);

        JPanel loanPaymentPanel = new JPanel();
        loanPaymentPanel.setLayout(new BoxLayout(loanPaymentPanel,BoxLayout.Y_AXIS));
        JLabel loanPaymentLabel= new JLabel("Choose account from you want to pay monthly payment for your loan");
        loanPaymentPanel.add(loanPaymentLabel);

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

        loanPaymentPanel.add(accountsComboBox);
        loanPaymentPanel.add(accountBalance);

        JButton loanPaymentButton = new JButton("Pay monthly fee");
        loanPaymentButton.addActionListener(e->{
            try{
                Account temp = (Account) accountsComboBox.getSelectedItem();
                if(this.loan.getMonthlyPayment()>temp.getBalance()){
                    JOptionPane.showMessageDialog(new Frame(),"Not sufficient amount of money in the account");
                }else {
                    temp.setBalance(temp.getBalance()-this.loan.getMonthlyPayment());
                    this.loan.setTotalPayment(this.loan.getTotalPayment()-this.loan.getMonthlyPayment());
                    if(this.loan.getTotalPayment()<0){
                        this.client.getLoans().remove(loan);
                    }
                    JOptionPane.showMessageDialog(new Frame(),"Successful operation ");
                }
            }catch (Exception ex){
                JOptionPane.showMessageDialog(new Frame(),"Wrong input." + ex);
            }
        });
        add(loanPaymentPanel);
        add(loanPaymentButton);




        setTitle(this.client.getLogin()+" Personal Page");
        setSize(700,700);
        setLocation(200,100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
