package Entities;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Class, which is used to crate reliable JTable in GUI
 */
public class CheckingAccountTableModel extends AbstractTableModel {
    private String[] columnNames={"Name","Balance","Currency"};
    private ArrayList<CheckingAccount> accounts;

    public CheckingAccountTableModel(ArrayList<CheckingAccount> accounts){
        this.accounts=accounts;
    }
    @Override
    public int getRowCount() {
        return this.accounts.size();
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
                result = this.accounts.get(rowIndex).getName();
                break;
            case 1:
                result = this.accounts.get(rowIndex).getBalance();
                break;
            case 2:
                result = this.accounts.get(rowIndex).getCurrency();
                break;
        }
        return result;
    }

    public String getColumnName(int col){
        return this.columnNames[col];
    }
}
