package Entities;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Class, which is used to crate reliable JTable in GUI
 */
public class ClientTableModel extends AbstractTableModel {
    private String[] columnNames={"firstname","surname","login"};
    private ArrayList<Client> clients;

    public ClientTableModel(ArrayList<Client> loans){
        this.clients=loans;
    }
    @Override
    public int getRowCount() {
        return this.clients.size();
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
                result = this.clients.get(rowIndex).getFirstname();
                break;
            case 1:
                result = this.clients.get(rowIndex).getSurname();
                break;
            case 2:
                result = this.clients.get(rowIndex).getLogin();
                break;
        }
        return result;
    }

    public String getColumnName(int col){
        return this.columnNames[col];
    }
}
