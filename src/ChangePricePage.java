import Entities.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;


/**
 * This page is used by manger to change the price of share
 */
public class ChangePricePage extends JFrame {
    private Bank bank;
    private JLabel titleLabel;
    private JLabel priceLabel;
    private JTextField priceText;
    private JButton submit;
    private JButton back;
    private JTable stockTable;
    private JScrollPane sp;

    public ChangePricePage(Bank bank) {
        this.bank = bank;

        //setLayout(new GridLayout(2,2));

        titleLabel = new JLabel("Select one to change price: ");
        Font labelFont = new Font(Font.DIALOG,  Font.BOLD, 20);
        titleLabel.setFont(labelFont);
        Font labelFont2 = new Font(Font.DIALOG, Font.BOLD,15);
        priceLabel = new JLabel("New Price");
        priceLabel.setFont(labelFont2);
        priceText = new JTextField();
        submit = new JButton("Submit");
        back = new JButton("Back");

        // Data to be displayed in the JTable
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
        sp = new JScrollPane(stockTable);


        titleLabel.setBounds(200, 10, 300, 50);
        sp.setBounds(10, 70, 680, 450);
        priceLabel.setBounds(190, 530, 220, 50);
        priceText.setBounds(275, 530, 110, 50);
        submit.setBounds(395, 530, 110, 50);
        back.setBounds(190, 590, 315, 50);

        // Create the panel to place the buttons on
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Add each button to the panel
        panel.add(titleLabel);
        panel.add(priceLabel);
        panel.add(priceText);
        panel.add(submit);
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
    }

    public class StockTableModel extends AbstractTableModel {
        private String[] columnNames = { "Company", "Code", "Price" };
        private String[][] data = {
                { "Apple", "AAPL", "255.82" },
                { "Tencent", "TCEHY", "41.09" },
                { "Alibaba", "BABA", "176.46" },
        };


        @Override
        public int getRowCount() {
            return this.data.length;
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return data[rowIndex][columnIndex];
        }

        public String getColumnName(int col){
            return this.columnNames[col];
        }
    }

    public static void main(String[] args) {
        ChangePricePage changePricePage = new ChangePricePage(new Bank(
                new Manager("", "", ""), new ArrayList<Client>()));
    }

}
