import DatabaseConnection.DatabaseManager;
import Entities.*;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * This page is used by manger to change the price of share
 */
public class ChangePricePage extends JFrame {
    private Bank bank;
    private ArrayList<Shares> shares;
    private DatabaseManager db;
    private JLabel titleLabel;
    private JLabel priceLabel;
    private JTextField priceText;
    private JButton submit;
    private JButton back;
    private JButton refresh;
    private JButton addShare;
    private JTable stockTable;
    private JScrollPane sp;

    public ChangePricePage(Bank bank) {
        this.bank = bank;
        db = new DatabaseManager();


        titleLabel = new JLabel("Select one to change price: ");
        Font labelFont = new Font(Font.DIALOG,  Font.BOLD, 20);
        titleLabel.setFont(labelFont);
        Font labelFont2 = new Font(Font.DIALOG, Font.BOLD,15);
        priceLabel = new JLabel("New Price");
        priceLabel.setFont(labelFont2);
        priceText = new JTextField();
        submit = new JButton("Submit");
        //refresh = new JButton("Refresh");
        addShare = new JButton("Add Share");
        back = new JButton("Back");

        // get all shares from database
        shares = (ArrayList<Shares>) db.getAllShares();

//        shares = new ArrayList<>();
//        shares.add(new Shares("Apple", "AAPL", 255.82, 100));
//        shares.add(new Shares("Tencent", "TCEHY", 41.09, 300));
//        shares.add(new Shares("Alibaba", "BABA", 176.46, 200));
//        shares.add(new Shares("Google", "GOOGL", 1272.50, 1100));
//        shares.add(new Shares("Uber", "UBER", 31.37, 1500));
//        shares.add(new Shares("Microsoft", "MSFT", 143.72, 600));

        StockMarketTableModel stockMarketTableModel = new StockMarketTableModel(shares);

        stockTable = new JTable(stockMarketTableModel);
        stockTable.setAutoCreateRowSorter(true);
        sp = new JScrollPane(stockTable);


        titleLabel.setBounds(200, 10, 300, 50);
        sp.setBounds(10, 70, 680, 450);
        priceLabel.setBounds(190, 530, 220, 50);
        priceText.setBounds(275, 530, 110, 50);
        submit.setBounds(395, 530, 110, 50);

        addShare.setBounds(190, 590, 152, 50);
        //refresh.setBounds(298, 590, 98, 50);
        back.setBounds(352, 590, 152,50);

        // Create the panel to place the buttons on
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Add each button to the panel
        panel.add(titleLabel);
        panel.add(priceLabel);
        panel.add(priceText);
        panel.add(submit);
        panel.add(sp);
        panel.add(addShare);
        //panel.add(refresh);
        panel.add(back);

        // Add the panel to the frame
        add(panel);



        setTitle("Change Stock Price");
        setSize(700,700);
        setLocation(200,100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        addShare.addActionListener(e -> {
            //System.out.println(stockMarketTableModel.getRowCount());
            CreateSharesPage createSharesPage = new CreateSharesPage(stockMarketTableModel);
        });

        back.addActionListener(e -> {
            setVisible(false);
        });

        submit.addActionListener(e -> {
            if (stockTable.getSelectedRow() == -1) {
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
                        stockTable.getModel().setValueAt(doubleValue, stockTable.getSelectedRow(), 3);
                        //refresh the JTable
                        stockTable.repaint();
                        JOptionPane.showMessageDialog(null, "Success! " +
                                "Change the price to " + doubleValue);

                        // update the price in database
                        Integer id = (Integer) stockTable.getValueAt(stockTable.getSelectedRow(), 0);
                        Shares share = db.findShares(id);
                        share.setSharePrice(doubleValue);
                        db.update(share);
                        db.updatePrivateShares(share);
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
