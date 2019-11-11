import DatabaseConnection.DatabaseManager;
import Entities.Shares;
import Entities.StockMarketTableModel;

import javax.swing.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.util.ArrayList;

public class CreateSharesPage extends JFrame {
    private DatabaseManager db;
    private JLabel companyName;
    private JLabel companyCode;
    private JLabel price;
    private JLabel amount;
    private JTextField companyNameText;
    private JTextField companyCodeText;
    private JTextField priceText;
    private JButton create;
    private JButton back;
    private JComboBox amountBox;
    private StockMarketTableModel stockMarketTableModel;


    // Constructor
    public CreateSharesPage(StockMarketTableModel stockMarketTableModel) {
        db = new DatabaseManager();
        this.stockMarketTableModel = stockMarketTableModel;

        companyName = new JLabel("Company Name");
        companyCode = new JLabel("Company Code");
        amount = new JLabel("Share Amount ");
        price = new JLabel("Price");

        companyNameText = new JTextField();
        companyCodeText = new JTextField();
        priceText = new JTextField();
        amountBox = new JComboBox();

        amountBox.addItem("100");
        amountBox.addItem("200");
        amountBox.addItem("500");
        amountBox.addItem("1000");
        amountBox.addItem("5000");
        amountBox.addItem("10000");

        // Create the buttons
        create = new JButton( "Create");
        back = new JButton("Back");

        companyName.setBounds(10,10, 300, 30);
        companyNameText.setBounds(10, 50, 300, 30);
        companyCode.setBounds(10, 90, 300, 30);
        companyCodeText.setBounds(10, 130, 300, 30);
        price.setBounds(10, 170, 300, 30);
        priceText.setBounds(10, 210, 300, 30);
        amount.setBounds(10, 250, 300, 30);
        amountBox.setBounds(10,290, 300, 30);
        create.setBounds(50, 330, 90, 30);
        back.setBounds(160, 330, 90, 30);


        // Create the panel to place the buttons on
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Add each button to the panel
        panel.add(companyName);
        panel.add(companyNameText);
        panel.add(companyCode);
        panel.add(companyCodeText);
        panel.add(amount);
        panel.add(amountBox);
        panel.add(price);
        panel.add(priceText);
        panel.add(create);
        panel.add(back);

        // Add the panel to the frame
        add(panel);

        setTitle("Create Share");
        setSize( 320, 500 );
        setLocation( 200, 100 );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        back.addActionListener(e -> {
            setVisible(false);
        });

        create.addActionListener(e -> {
            if (companyNameText.getText().equals("") || companyCodeText.getText().equals("")
                    || priceText.getText().equals("") || amountBox.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null,
                        "Input should not be empty!.",
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
                        String companyNameStr = companyNameText.getText();
                        String companyCodeStr = companyCodeText.getText();
                        double amountNum = Double.parseDouble((String) amountBox.getSelectedItem());
                        Shares share = new Shares(companyNameStr, companyCodeStr, doubleValue, amountNum);
                        stockMarketTableModel.addShare(share);

                        // add the share to database
                        db.add(share);

                        JOptionPane.showMessageDialog(null, "Success!" );
                        setVisible(false);
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

    public static void main(String[] args) {
        CreateSharesPage createSharesPage = new CreateSharesPage(new StockMarketTableModel(new ArrayList<>()));
    }
}