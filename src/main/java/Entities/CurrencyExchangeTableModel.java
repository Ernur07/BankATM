package Entities;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Class, which is used to crate reliable JTable in GUI
 */
public class CurrencyExchangeTableModel extends AbstractTableModel {
    private String[] columnNames={"ID","Currency 1","Currency 2","Exchange Rate"};
    private ArrayList<CurrencyExchange> currencyExchanges;

    public CurrencyExchangeTableModel(ArrayList<CurrencyExchange> currencyExchange){
        this.currencyExchanges=currencyExchange;
    }
    @Override
    public int getRowCount() {
        return this.currencyExchanges.size();
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
                result = this.currencyExchanges.get(rowIndex).getId();
                break;
            case 1:
                result = this.currencyExchanges.get(rowIndex).getCurrency1().getName();
                break;
            case 2:
                result = this.currencyExchanges.get(rowIndex).getCurrency2().getName();
                break;
            case 3:
                result = this.currencyExchanges.get(rowIndex).getExchangeRate();
                break;
        }
        return result;
    }

    public String getColumnName(int col){
        return this.columnNames[col];
    }
}
