import DatabaseConnection.DatabaseManager;
import Entities.*;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;

public class CurrenceyExchangePage extends JFrame {
    private Bank bank;
    private ArrayList<CurrencyExchange> exchange;
    private DatabaseManager db;
    private JLabel titleLabel;
    private JLabel priceLabel;
    private JLabel addLabel;
    private JTextField addText;
    private JButton addButton;
    private JTextField priceText;
    private JButton submit;
    private JButton back;
    private JTable exchangeTable;
    private JScrollPane sp;

    public CurrenceyExchangePage(Bank bank) {
        this.bank = bank;
        db = new DatabaseManager();


        titleLabel = new JLabel("Select one to change price: ");
        Font labelFont = new Font(Font.DIALOG,  Font.BOLD, 20);
        titleLabel.setFont(labelFont);
        Font labelFont2 = new Font(Font.DIALOG, Font.BOLD,15);
        priceLabel = new JLabel("New rate");
        priceLabel.setFont(labelFont2);
        addLabel = new JLabel("Add currency");
        addLabel.setFont(labelFont2);
        addText = new JTextField();
        addButton = new JButton("Add");
        priceText = new JTextField();
        submit = new JButton("Submit");
        back = new JButton("Back");

        // get all shares from database
        exchange = (ArrayList<CurrencyExchange>) db.getAllCurrencyExchange();

//        shares = new ArrayList<>();
//        shares.add(new Shares("Apple", "AAPL", 255.82, 100));
//        shares.add(new Shares("Tencent", "TCEHY", 41.09, 300));
//        shares.add(new Shares("Alibaba", "BABA", 176.46, 200));
//        shares.add(new Shares("Google", "GOOGL", 1272.50, 1100));
//        shares.add(new Shares("Uber", "UBER", 31.37, 1500));
//        shares.add(new Shares("Microsoft", "MSFT", 143.72, 600));
        CurrencyExchangeTableModel exchangeTableModel = new CurrencyExchangeTableModel(exchange);

        exchangeTable = new JTable(exchangeTableModel);
        exchangeTable.setAutoCreateRowSorter(true);
        sp = new JScrollPane(exchangeTable);


        titleLabel.setBounds(200, 10, 300, 50);
        sp.setBounds(10, 70, 680, 450);
        priceLabel.setBounds(190, 530, 220, 25);
        priceText.setBounds(275, 530, 110, 25);
        submit.setBounds(395, 530, 110, 25);
        addLabel.setBounds(190, 565, 220, 25);
        addText.setBounds(275, 565, 110, 25);
        addButton.setBounds(395, 565, 110, 25);
        back.setBounds(190, 600, 315, 50);

        // Create the panel to place the buttons on
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Add each button to the panel
        panel.add(titleLabel);
        panel.add(priceLabel);
        panel.add(priceText);
        panel.add(submit);
        panel.add(addLabel);
        panel.add(addText);
        panel.add(addButton);
        panel.add(sp);
        //panel.add(stockTable);
        panel.add(back);

        // Add the panel to the frame
        add(panel);



        setTitle("Change Stock Price");
        setSize(700,700);
        setLocation(200,100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        back.addActionListener(e -> {
            setVisible(false);
        });

        submit.addActionListener(e -> {
            if (exchangeTable.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null,
                        "No item selected",
                        "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                String value = priceText.getText();
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
                        exchangeTable.getModel().setValueAt(doubleValue, exchangeTable.getSelectedRow(), 3);
                        //refresh the JTable
                        exchangeTable.repaint();
                        JOptionPane.showMessageDialog(null, "Success! " +
                                "Change the rate to " + doubleValue);

                        // update the price in database
                        Integer id = (Integer) exchangeTable.getValueAt(exchangeTable.getSelectedRow(), 0);
                        CurrencyExchange rate = db.findCurrencyExchange(id);
                        rate.setExchangeRate(doubleValue);
                        CurrencyExchange rate2 = db.findExchangeByCurrencies(rate.getCurrency2(),rate.getCurrency1());
                        rate2.setExchangeRate(1/doubleValue);
                        db.update(rate);
                        db.update(rate2);
                    }
                }
            }
        });
        addButton.addActionListener(e -> {
            ArrayList<Currency> currencies = (ArrayList<Currency>) db.getAllCurrency();
            String currencyName = addText.getText();
            if(currencyName.equals("")||!db.currencyNameCheck(currencyName)){
                JOptionPane.showMessageDialog(null,"Wrong input");
            }else{

                Currency newCurrency= new Currency(currencyName);
                if(currencies.size() == 0){
                    db.add(newCurrency);
                }else{
                    System.out.println(newCurrency.getId());
                    db.add(newCurrency);
                    for(Currency curr:currencies){
                        CurrencyExchange c1 =  new CurrencyExchange(newCurrency,curr,0);
                        CurrencyExchange c2 =  new CurrencyExchange(curr,newCurrency,0);
                        db.add(c1);
                        db.add(c2);
                        JOptionPane.showMessageDialog(null,"Currency successfully added");
                    }

                }
            }

        });

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
}
