package Entities;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Class, which is used to crate reliable JTable in GUI
 */
public class TransactionTableModel extends AbstractTableModel {
    private String[] columnNames={"receiver","receiverAccount","amount","date"};
    private ArrayList<Transaction> transactions;

    public TransactionTableModel(ArrayList<Transaction> transactions){
        this.transactions=transactions;
    }
    @Override
    public int getRowCount() {
        return this.transactions.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object result = null;
        switch (columnIndex){
            /*case 0:
                result = this.transactions.get(rowIndex).getReceiver().getLogin();
                break;*/
            case 1:
                result = this.transactions.get(rowIndex).getReceiverAccount().getName();
                break;
            case 2:
                result = this.transactions.get(rowIndex).getAmount();
                break;
            case 3:
                result = this.transactions.get(rowIndex).getDate();
                break;
        }
        return result;
    }

    public String getColumnName(int col){
        return this.columnNames[col];
    }
}
