//import Entities.Bank;
//import Entities.Client;

import DatabaseConnection.DatabaseManager;
import Entities.*;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.List;

public class SecurityAccountPage extends JFrame{
    DatabaseManager db = new DatabaseManager();

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

        //accountList = new String[] {"account1", "account2"};
        accountList = buildSavingAccount(client);


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


        /*String[][] data = {
                { "Apple", "AAPL", "255.82" },
                { "Tencent", "TCEHY", "41.09" },
                { "Alibaba", "BABA", "176.46" },
                { "Google", "GOOGL", "1272.50" },
                { "Uber", "UBER", "31.37" },
                { "Microsoft", "MSFT", "143.72" },
        };*/

        // Column Names
        //String[] columnNames = { "Company", "Code", "Price" };

        //StockTableModel stockTableModel = new StockTableModel();

        ArrayList<Shares> data = (ArrayList<Shares>) db.getAllShares();
        StockMarketTableModel sm = new StockMarketTableModel(data);

        stockTable = new JTable(sm);
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


        listModel.setSize(account.getShares().size());
        for(int i = 0; i < account.getShares().size(); i++){
            PrivateShares pShare = account.getShares().get(i);
            listModel.addElement(String.valueOf(i) + " Company: " + pShare.getCompanyName() + " Amount: " + pShare.getAmountofShares() + " Price: " + pShare.getSharePrice());

        }


        //listModel.addElement("temp");
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
        exit.addActionListener(e -> {
            System.out.println("exit");
            this.j.dispose();
        });
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
                        if (currIdx < 0 || currIdx >= accountList.length) {
                            JOptionPane.showMessageDialog(null,
                                    "Please select valid account!",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        String accountName = accountList[currIdx];
                        System.out.println("selected" + accountName);
                        int idx = Integer.parseInt(accountName.split(" ")[0]);
                        SavingAccount savingAccount = client.getSavingAccounts().get(idx);

                        //find stock
                        int stockIdx = stockTable.getSelectedRow();


                        //Integer stockId = Integer.parseInt((String)stockTable.getValueAt(stockTable.getSelectedRow(), 0));
                        //Integer stockId = 1;

                        //This line not checked yet -> may have error
                        Integer stockId = (Integer) stockTable.getValueAt(stockTable.getSelectedRow(), 0);


                        //buy the share
                        if (!buyShare(bank, client, account, savingAccount, doubleValue, stockId)) {
                            JOptionPane.showMessageDialog(null,
                                    "Transaction failed!",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            this.j.dispose();
                            SecurityAccountPage newPage = new SecurityAccountPage(bank, account, client);
                        }

                    }
                }
            }
        });


        sell.addActionListener(e -> {
            String value = sellAmount.getText();
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
                    int currIdx = fromBox2.getSelectedIndex()-1;
                    if (currIdx < 0 || currIdx >= accountList.length) {
                        JOptionPane.showMessageDialog(null,
                                "Please select valid account!",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    String accountName = accountList[currIdx];
                    System.out.println("selected" + accountName);
                    int idx = Integer.parseInt(accountName.split(" ")[0]);
                    SavingAccount savingAccount = client.getSavingAccounts().get(idx);

                    //get the share to sell
                    String val = (String)sourceList.getSelectedValue();
                    if(val == null){
                        JOptionPane.showMessageDialog(null,"You need to choose a share first!");
                    }else{
                        String[] split = val.split(" ");
                        int shareIdx = Integer.parseInt(split[0]);
                        PrivateShares pShare = account.getShares().get(shareIdx);

                        //try to sell the share
                        if (!sellShare(bank, client, account, savingAccount, pShare, doubleValue)) {
                            JOptionPane.showMessageDialog(null,
                                    "Transaction failed!",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }

                        //check if share is 0. if so delete the share
                        if (pShare.getAmountofShares() == 0) {
                            List<PrivateShares> pList = account.getShares();
                            db.remove(pShare);
                            pList.remove(shareIdx);


                            //TODO. update the db that the share is deleted
                        }
                        this.j.dispose();
                        SecurityAccountPage newPage = new SecurityAccountPage(bank, account, client);
                    }
                }
            }

        });




        }

    public boolean buyShare(Bank bank, Client client, SecurityAccount securityAccount, SavingAccount savingAccount, Double value, Integer stockId) {
        DatabaseManager db = new DatabaseManager();

        //update stock share
        //TOCHECK: get stockShare
        Shares stockShare = db.findShares(stockId);
        SecurityAccount sec = db.findSecurityAccount(securityAccount.getId());
        SavingAccount sav = db.findSavingAccount(savingAccount.getId());
        //Shares stockShare = new Shares("TempName", "tickr", 100, 10000);

        long amount = Math.round(value);
        if (amount > stockShare.getAmountofShares())
            return false;
        if (savingAccount.getBalance() - amount * stockShare.getSharePrice() < 0)
            return false;
        stockShare.setAmountofShares(stockShare.getAmountofShares() - amount);
        db.update(stockShare);

        //add new share to sec account
        PrivateShares newShare = new PrivateShares(stockShare.getCompanyName(), stockShare.getTickr(), stockShare.getSharePrice(), amount, stockShare.getSharePrice(), sec);
        securityAccount.addShare(newShare);
        db.add(newShare);

        //update saving
        savingAccount.setBalance(savingAccount.getBalance() - amount * stockShare.getSharePrice());
        db.update(savingAccount);

        return true;
    }

    private boolean sellShare(Bank bank, Client client, SecurityAccount securityAccount, SavingAccount savingAccount, PrivateShares share, Double amount) {
        DatabaseManager db = new DatabaseManager();

        //update stock share
        //TODO: use db, and get stockShare
        //Shares stockShare = db.findShares(stockId);
        Shares stockShare = new Shares("TempName", "tickr", 100, 10000);

        int id = share.getId();
        String companyname = share.getCompanyName();
        String tickr = share.getTickr();
        Double price = share.getSharePrice();
        double Totalamount = share.getAmountofShares();
        if (amount > Totalamount)
            return false;
        //TODO: put the share back to compnay. Difficulty: how to know which share to return?


        //put money back to saving account
        double earn = amount * price;
        savingAccount.setBalance(savingAccount.getBalance() + earn);
        share.setAmountofShares(Totalamount - amount);
        db.update(savingAccount);
        db.update(share);
        //db.update(//the stock company share)

        return true;
    }



    private String[] buildSavingAccount(Client client) {
        List<SavingAccount> list = client.getSavingAccounts();
        String[] ans = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            SavingAccount account = list.get(i);
            String s = String.valueOf(i) +" " + account.getName() + " " + account.getId() + " " + account.getBalance();
            ans[i] = s;
        }
        return ans;

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



//    public static void main(String[] args) {
//        //SecurityAccountPage page = new SecurityAccountPage();
//
//    }
}


