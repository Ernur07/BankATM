import DatabaseConnection.DatabaseManager;
import Entities.Account;
import Entities.Bank;
import Entities.Client;
import Entities.Loan;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This page shows all possible actions of ATM to client
 */
public class ClientPersonalPage extends JFrame {
    private Bank bank;
    private Client client;
    private DatabaseManager db = new DatabaseManager();

    public ClientPersonalPage(Bank bank, Client client ) throws HeadlessException {
        this.bank=bank;
        this.client=db.findClient(client.getId());
        setLayout(new GridLayout(6,2));

        JLabel nameLabel = new JLabel("Welcome "+client.getFirstname()+" "+client.getSurname());
        add(nameLabel);

        JButton logOut=new JButton("Logout");
        logOut.addActionListener(e -> {
            OpeningMenu openingMenu = new OpeningMenu(bank);
            dispose();
        });
        add(logOut);

        JButton deposit = new JButton("Deposit");
        deposit.addActionListener(e -> {
            DepositPage depositPage = new DepositPage(this.client);
        });
        add(deposit);

        JButton withdraw = new JButton("Withdraw");
        withdraw.addActionListener(e -> {
            WithdrawPage withdrawPage = new WithdrawPage(this.client);
        });
        add(withdraw);

        JButton transfer = new JButton("Transfer");
        transfer.addActionListener(e -> {
            TransferPage transferPage = new TransferPage(this.bank,this.client);
        });
        add(transfer);

        JButton accountManagement = new JButton("Manage Accounts");
        accountManagement.addActionListener(e -> {
            AccountManagementPage accountManagementPage = new AccountManagementPage(this.client);
        });
        add(accountManagement);

        JButton accountsListPage = new JButton("Show all accounts");
        accountsListPage.addActionListener(e -> {
            AccountListPage accountListPage = new AccountListPage(this.client);
        });
        add(accountsListPage);

        JButton loanPageButton = new JButton("Loan");
        loanPageButton.addActionListener(e -> {
            LoanPage loanPage = new LoanPage(this.client);
        });
        add(loanPageButton);

        JButton transactionPageButton = new JButton("Transactions");
        transactionPageButton.addActionListener(e -> {
            TransactionsListPage transactionsListPage = new TransactionsListPage(this.client);
        });
        add(transactionPageButton);

        JButton otherFeePageButton = new JButton("Other fee");
        otherFeePageButton.addActionListener(e -> {
            OtherFeePage otherFeePage = new OtherFeePage(this.client);
        });
        add(otherFeePageButton);

        JButton securityButton = new JButton("Manage Security");
        securityButton.addActionListener(e -> {
            SecurityAccountListPage page = new SecurityAccountListPage(bank, this.client);
        });
        add(securityButton);



        setTitle(this.client.getLogin()+" Personal Page");
        setSize(700,700);
        setLocation(200,100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
