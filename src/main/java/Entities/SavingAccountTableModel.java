package Entities;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Class, which is used to crate reliable JTable in GUI
 */
public class SavingAccountTableModel extends AbstractTableModel {
    private String[] columnNames={"Name","Balance","Currency","Interest rate"};
    private ArrayList<SavingAccount> accounts;

    public SavingAccountTableModel(ArrayList<SavingAccount> accounts){
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
            case 3:
                result = this.accounts.get(rowIndex).getInterestRate();
                break;
        }
        return result;
    }

    public String getColumnName(int col){
        return this.columnNames[col];
    }
}
