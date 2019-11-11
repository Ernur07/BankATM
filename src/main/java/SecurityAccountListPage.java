import Entities.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;


public class SecurityAccountListPage extends JFrame{
    private Client client;

    public SecurityAccountListPage(Bank bank, Client c){
        //this.client = new Client("a","b","c","c");
        this.client = c;


        setLayout(new GridLayout(3,1));

        /**
         * Security accounts list
         */
        JPanel securityAccountPanel = new JPanel();
//        securityAccountPanel.setLayout(new BoxLayout(securityAccountPanel,BoxLayout.Y_AXIS));
//        SecurityAccountTableModel tm = new SecurityAccountTableModel((ArrayList<SecurityAccount>) client.getSecurityAccounts());
//        JTable savingTable = new JTable(tm);
//        add(savingTable);
//        savingTable.setAutoCreateRowSorter(true);
//        JScrollPane scrollPane = new JScrollPane(savingTable);
//        scrollPane.setPreferredSize(new Dimension(380,280));
//        securityAccountPanel.add(new JLabel(this.client.getLogin()+"'s Security Accounts"));
//        securityAccountPanel.add(scrollPane);
//        add(securityAccountPanel);

        DefaultListModel listModel = new DefaultListModel();
        JList sourceList = new JList();
        listModel.setSize(client.getSecurityAccounts().size());
        for(int i = 0; i < client.getSecurityAccounts().size(); i++){
            SecurityAccount account = client.getSecurityAccounts().get(i);
            listModel.addElement(String.valueOf(i) + " Security Account name: " + account.getName() + " Id: " + account.getId() + " Balance: " + account.getBalance());

        }
        sourceList.setModel(listModel);
        sourceList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane sourceListScroller = new JScrollPane(sourceList);
        sourceListScroller.setPreferredSize(new Dimension(380,280));
        sourceListScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        add(sourceListScroller);




        /**
         * Manage button
         **/
        JButton manage = new JButton("Manage");
        manage.addActionListener(e ->{

            String val = (String)sourceList.getSelectedValue();
            if(val == null){
                JOptionPane.showMessageDialog(null,"You need to choose a share first!");
            }else {
                String[] split = val.split(" ");
                int accountIdx = Integer.parseInt(split[0]);
                SecurityAccount account = client.getSecurityAccounts().get(accountIdx);
                new SecurityAccountPage(bank, account, client);
            }
        });
        manage.setSize(1,1);
        add(manage);

        /**
         * Go back button
         */
        JButton back = new JButton("Back");
        back.addActionListener(e -> {
            this.dispose();
        });
        back.setSize(1,1);
        add(back);





        setTitle(this.client.getLogin()+" Personal Page");
        setSize(700,700);
        setLocation(200,100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        Client client = new Client();
        SecurityAccount account = new SecurityAccount("sec", 0, "c", client);
        List<SecurityAccount> list = new ArrayList<>();
        list.add(account);
        client.setSecurityAccounts(list);
        SavingAccount saccount = new SavingAccount("sav", 1000, 1.0, "USD", client);
        List<SavingAccount> list2 = new ArrayList<>();
        list2.add(saccount);
        client.setSavingAccounts((ArrayList<SavingAccount>) list2);
        Bank bank = new Bank(null, null);
        SecurityAccountListPage page = new SecurityAccountListPage(bank, client);
    }
}
