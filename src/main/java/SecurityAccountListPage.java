import Entities.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;



public class SecurityAccountListPage extends JFrame{
    private Client client;

    public SecurityAccountListPage(){//Client client){
        this.client = new Client("a","b","c");


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
         * Security accounts list
         */
        JPanel securityAccountPanel = new JPanel();
        securityAccountPanel.setLayout(new BoxLayout(securityAccountPanel,BoxLayout.Y_AXIS));
        SavingAccountTableModel tm = new SavingAccountTableModel((ArrayList<SavingAccount>) client.getSavingAccounts());
        JTable savingTable = new JTable(tm);
        add(savingTable);
        savingTable.setAutoCreateRowSorter(true);
        JScrollPane scrollPane = new JScrollPane(savingTable);
        scrollPane.setPreferredSize(new Dimension(380,280));
        securityAccountPanel.add(new JLabel(this.client.getLogin()+"'s Saving Accounts"));
        securityAccountPanel.add(scrollPane);
        add(securityAccountPanel);




        setTitle(this.client.getLogin()+" Personal Page");
        setSize(700,700);
        setLocation(200,100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        SecurityAccountListPage page = new SecurityAccountListPage();
    }
}
