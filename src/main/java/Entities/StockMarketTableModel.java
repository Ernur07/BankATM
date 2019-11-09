package Entities;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Class, which is used to crate reliable JTable in GUI
 */
public class StockMarketTableModel extends AbstractTableModel {
    private String[] columnNames = { "ID", "Company", "Code", "Price", "Amount" };
    private ArrayList<Shares> shares;

    public StockMarketTableModel(ArrayList<Shares> shares){
        this.shares = shares;
    }
    @Override
    public int getRowCount() {
        return this.shares.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object result = null;
        switch (columnIndex){
            case 0:
                result = this.shares.get(rowIndex).getId();
                break;
            case 1:
                result = this.shares.get(rowIndex).getCompanyName();
                break;
            case 2:
                result = this.shares.get(rowIndex).getTickr();
                break;
            case 3:
                result = this.shares.get(rowIndex).getSharePrice();
                break;
            case 4:
                result = this.shares.get(rowIndex).getAmountofShares();
                break;
        }
        return result;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //data[rowIndex][columnIndex] = (String) aValue;
        switch (columnIndex){
            case 0:
                shares.get(rowIndex).setId((Integer) aValue);
                break;
            case 1:
                shares.get(rowIndex).setCompanyName((String) aValue);
                break;
            case 2:
                shares.get(rowIndex).setTickr((String) aValue);
                break;
            case 3:
                shares.get(rowIndex).setSharePrice((Double) aValue);
                break;
            case 4:
                shares.get(rowIndex).setAmountofShares((Integer) aValue);
                break;
        }
    }

    public String getColumnName(int col){
        return this.columnNames[col];
    }
}
