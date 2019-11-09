//import Entities.Bank;
//import Entities.Client;

import DatabaseConnection.DatabaseManager;
import Entities.*;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

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

    JTable stockTable;
    JScrollPane stockSp;

    String DEFAULT_OPTION = "select account";




    //public SecurityAccountPage(){//Bank bank, Client client ){
    public SecurityAccountPage(Bank bank, SecurityAccount account, Client client){
        //TODO: add account list from bank/client
        accountList = new String[] {"account1", "account2"};



        fromBox = new JComboBox(accountList);
        fromBox2 = new JComboBox(accountList);


        j.setResizable(false);
        j.setLayout(new GridBagLayout());
        j.setTitle("ATM");
        j.setSize(900, 600);



        //     area.setText(bankATM.log);
//        area.setEditable(false);
//        area.setFont(font);
//        sp.setPreferredSize(new Dimension(150, 250));
//        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.weightx = 0.5;
//        c.weighty = 0.5;
//        c.gridwidth = 3;
//        c.gridheight = 3;
//        c.gridx = 0;
//        c.gridy = 0;
//        j.add(sp, c);


        String[][] data = {
                { "Apple", "AAPL", "255.82" },
                { "Tencent", "TCEHY", "41.09" },
                { "Alibaba", "BABA", "176.46" },
                { "Google", "GOOGL", "1272.50" },
                { "Uber", "UBER", "31.37" },
                { "Microsoft", "MSFT", "143.72" },
        };

        // Column Names
        String[] columnNames = { "Company", "Code", "Price" };

        //StockTableModel stockTableModel = new StockTableModel();

        stockTable = new JTable(data, columnNames);
        stockTable.setAutoCreateRowSorter(true);
        stockSp = new JScrollPane(stockTable);
        stockSp.setPreferredSize(new Dimension(150, 250));
        stockSp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridwidth = 3;
        c.gridheight = 3;
        c.gridx = 0;
        c.gridy = 0;
        j.add(stockSp, c);

















//        for (int i = 0; i < bankATM.getCustomers().size(); i++) {
//            Customer customer = bankATM.getCustomers().get(i);
//            listModel.addElement(String.valueOf(i+1) + " customer name: " + customer.getName() + "accounts: " + customer.listAccounts());
//        }

        listModel.addElement("temp");
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
        c.gridheight = 3;
        c.gridx = 0;
        c.gridy = 3;
        j.add(sourceListScroller, c);

        fromBox.setFont(new Font("Noto Sans",1,20));
        //fromBox.setText("Please type your withdraw amount!");
        //fromBox.setHorizontalAlignment(JLabel.CENTER);
        fromBox.setModel(new DefaultComboBoxModel<String>() {
            private static final long serialVersionUID = 1L;
            boolean selectionAllowed = true;

            @Override
            public void setSelectedItem(Object anObject) {
                if (!DEFAULT_OPTION.equals(anObject)) {
                    super.setSelectedItem(anObject);
                } else if (selectionAllowed) {
                    // Allow this just once
                    selectionAllowed = false;
                    super.setSelectedItem(anObject);
                }
            }
        });
        fromBox.addItem(DEFAULT_OPTION);
        for (String s : accountList)
            fromBox.addItem(s);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0;
        c.weighty = 1;
        c.gridx = 4;
        c.gridy = 0;
        c.gridwidth = 2;
        c.gridheight = 1;
        j.add(fromBox, c);

        buyAmount.setFont(font);
        buyAmount.setText("amount(number)");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 1;
        c.gridx = 4;
        c.gridy = 1;
        c.gridwidth = 2;
        c.gridheight = 1;
        j.add(buyAmount, c);

        buy.setFont(font);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 1;
        c.gridx = 4;
        c.gridy = 2;
        c.gridwidth = 2;
        c.gridheight = 1;
        j.add(buy, c);



        fromBox2.setFont(new Font("Noto Sans",1,20));
//        fromBox.setHorizontalAlignment(JLabel.CENTER);
        fromBox2.setModel(new DefaultComboBoxModel<String>() {
            private static final long serialVersionUID = 1L;
            boolean selectionAllowed = true;

            @Override
            public void setSelectedItem(Object anObject) {
                if (!DEFAULT_OPTION.equals(anObject)) {
                    super.setSelectedItem(anObject);
                } else if (selectionAllowed) {
                    // Allow this just once
                    selectionAllowed = false;
                    super.setSelectedItem(anObject);
                }
            }
        });
        fromBox2.addItem(DEFAULT_OPTION);
        for (String s : accountList)
            fromBox2.addItem(s);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0;
        c.weighty = 1;
        c.gridx = 4;
        c.gridy = 3;
        c.gridwidth = 2;
        c.gridheight = 1;
        j.add(fromBox2, c);

        sellAmount.setFont(font);
        sellAmount.setText("amount(number)");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 1;
        c.gridx = 4;
        c.gridy = 4;
        c.gridwidth = 2;
        c.gridheight = 1;
        j.add(sellAmount, c);

        sell.setFont(font);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 1;
        c.gridx = 4;
        c.gridy = 5;
        c.gridwidth = 2;
        c.gridheight = 1;
        j.add(sell, c);

        exit.setFont(new Font("Noto Sans",1,20));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridwidth = 1;
        c.gridx = 5;
        c.gridy = 7;
        j.add(exit, c);
        listModel.setSize(6);



        j.setVisible(true);



        buy.addActionListener(e -> {
            if (stockTable.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null,
                        "No item selected",
                        "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                String value = buyAmount.getText();
                if (!isNumeric(value)) {
                    JOptionPane.showMessageDialog(null,
                            "Input should be numbers!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    double doubleValue = Double.valueOf(value);
                    if (doubleValue < 0) {
                        JOptionPane.showMessageDialog(null,
                                "Input should not less than 0!",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        //get account
                        int currIdx = fromBox.getSelectedIndex()-1;
                        String accountName = accountList[currIdx];
                        System.out.println("selected" + accountName);
                        //TODO: pick saving account
                        SavingAccount savingAccount = new SavingAccount();

                        //find stock
                        int stockIdx = stockTable.getSelectedRow();
                        Integer stockId = (Integer) stockTable.getValueAt(stockTable.getSelectedRow(), 0);

                        buyShare(bank, client, account, savingAccount, doubleValue, stockId);

                    }
                }
            }
        });


//        sell.addActionListener(e -> {
//            if (stockTable.getSelectedRow() == -1) {
//                JOptionPane.showMessageDialog(null,
//                        "No item selected",
//                        "Error", JOptionPane.ERROR_MESSAGE);
//            } else {
//                String value = buyAmount.getText();
//                if (!isNumeric(value)) {
//                    JOptionPane.showMessageDialog(null,
//                            "Input should be numbers!",
//                            "Error", JOptionPane.ERROR_MESSAGE);
//                } else {
//                    double doubleValue = Double.valueOf(value);
//                    if (doubleValue < 0) {
//                        JOptionPane.showMessageDialog(null,
//                                "Input should not less than 0!",
//                                "Error", JOptionPane.ERROR_MESSAGE);
//                    } else {
//                        //get account
//                        int currIdx = fromBox.getSelectedIndex()-1;
//                        String accountName = accountList[currIdx];
//                        System.out.println("selected" + accountName);
//                        //TODO: pick saving account
//                        SavingAccount savingAccount = new SavingAccount();
//
//                        //find stock
//                        int stockIdx = stockTable.getSelectedRow();
//                        Integer stockId = (Integer) stockTable.getValueAt(stockTable.getSelectedRow(), 0);
//
//                        buyShare(bank, client, account, savingAccount, doubleValue, stockId);
//
//                    }
//                }
//            }
//        });


    }

    public boolean buyShare(Bank bank, Client client, SecurityAccount securityAccount, SavingAccount savingAccount, Double value, Integer stockId) {
        DatabaseManager db = new DatabaseManager();

        //update stock share
        Shares stockShare = db.findShares(stockId);
        long amount = Math.round(value);
        if (amount > stockShare.getAmountofShares())
            return false;
        if (savingAccount.getBalance() - amount * stockShare.getSharePrice() < 0)
            return false;
        stockShare.setAmountofShares(stockShare.getAmountofShares() - amount);
        db.update(stockShare);

        //add new share to sec account
        PrivateShares newShare = new PrivateShares(stockShare.getCompanyName(), stockShare.getTickr(), stockShare.getSharePrice(), amount, stockShare.getSharePrice(), securityAccount);
        securityAccount.addShare(newShare);
        db.update(securityAccount);

        //update saving
        savingAccount.setBalance(savingAccount.getBalance() - amount * stockShare.getSharePrice());
        db.update(savingAccount);


        return true;
    }

    private boolean isNumeric(String str) {
        String bigStr;
        try {
            bigStr = new BigDecimal(str).toString();
        } catch (Exception e) {
            return false;
        }
        return true;
    }



    public static void main(String[] args) {
        //SecurityAccountPage page = new SecurityAccountPage();

    }
}


