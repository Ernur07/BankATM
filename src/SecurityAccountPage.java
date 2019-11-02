import Entities.Bank;
import Entities.Client;

import javax.swing.*;
import java.awt.*;

public class SecurityAccountPage {
    JFrame j = new JFrame();

    GridBagConstraints c = new GridBagConstraints();
    Font font = new Font("Noto Sans",Font.CENTER_BASELINE,14);
    DefaultListModel listModel = new DefaultListModel();
    JList sourceList = new JList();

    JLabel report = new JLabel("Report");
    JButton exit = new JButton("Exit");
    JButton view = new JButton("View Customer Detail");
    Dimension dimension = new Dimension(150,80);

    JButton buy = new JButton("Buy");
    JTextField buyAmount = new JTextField();
    String[] accountList;// = {"account1", "account2"};
    JComboBox fromBox;// = new JComboBox(accountList);


    JButton sell = new JButton("Sell");
    JTextField sellAmount = new JTextField();
    JComboBox fromBox2;


    JTextArea area = new JTextArea("Report");
    JScrollPane sp = new JScrollPane(area);




    public SecurityAccountPage(){//Bank bank, Client client ){
        //TODO: add account list from bank/client
        accountList = new String[] {"account1", "account2"};
        fromBox = new JComboBox(accountList);
        fromBox2 = new JComboBox(accountList);


        j.setResizable(false);
        j.setLayout(new GridBagLayout());
        j.setTitle("ATM");
        j.setSize(900, 600);
        j.setVisible(true);

        buy.setFont(font);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 5;
        c.gridy = 0;
        c.insets = new Insets(0,0,0,30);
        j.add(buy, c);

        buyAmount.setFont(font);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 4;
        c.gridy = 0;
        c.insets = new Insets(0,0,0,30);
        j.add(buyAmount, c);


//        report.setFont(new Font("Noto Sans",1,20));
//        report.setHorizontalAlignment(JLabel.CENTER);
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.weightx = 0.2;
//        c.weighty = 0.5;
//        c.gridx = 3;
//        c.gridy = 0;
//        j.add(report, c);

        fromBox.setFont(new Font("Noto Sans",1,20));
        //fromBox.setHorizontalAlignment(JLabel.CENTER);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 3;
        c.gridy = 0;
        j.add(fromBox, c);



        sell.setFont(font);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 5;
        c.gridy = 1;
        c.insets = new Insets(0,0,0,30);
        j.add(sell, c);

        sellAmount.setFont(font);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 4;
        c.gridy = 1;
        c.insets = new Insets(0,0,0,30);
        j.add(sellAmount, c);

        fromBox2.setFont(new Font("Noto Sans",1,20));
        //fromBox.setHorizontalAlignment(JLabel.CENTER);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 3;
        c.gridy = 1;
        j.add(fromBox2, c);



        //area.setText(bankATM.log);
        area.setEditable(false);
        area.setFont(font);
        sp.setPreferredSize(new Dimension(150, 250));
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 0;
        j.add(sp, c);




//        for (int i = 0; i < bankATM.getCustomers().size(); i++) {
//            Customer customer = bankATM.getCustomers().get(i);
//            listModel.addElement(String.valueOf(i+1) + " customer name: " + customer.getName() + "accounts: " + customer.listAccounts());
//        }

        sourceList.setModel(listModel);
        sourceList.setFont(font);
        sourceList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane sourceListScroller = new JScrollPane(sourceList);
        sourceListScroller.setPreferredSize(new Dimension(150, 250));
        sourceListScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        j.add(sourceListScroller, c);


//        view.setFont(new Font("Noto Sans",1,20));
//        c.weightx = 0.5;
//        c.weighty = 0.5;
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.gridwidth = 1;
//        c.gridx = 3;
//        c.gridy = 1;
//        j.add(view, c);


        exit.setFont(new Font("Noto Sans",1,20));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridwidth = 1;
        c.gridx = 3;
        c.gridy = 2;
        j.add(exit, c);
        listModel.setSize(6);
    }

    public static void main(String[] args) {
        SecurityAccountPage page = new SecurityAccountPage();

    }
}


