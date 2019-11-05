package Entities;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Class, which is used to crate reliable JTable in GUI
 */
public class LoanTableModel extends AbstractTableModel {
    private String[] columnNames={"annualInterestRate","numberOfYears","loanAmount","loanDate","totalPayment"};
    private ArrayList<Loan> loans;

    public LoanTableModel(ArrayList<Loan> loans){
        this.loans=loans;
    }
    @Override
    public int getRowCount() {
        return this.loans.size();
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
                result = this.loans.get(rowIndex).getAnnualInterestRate();
                break;
            case 1:
                result = this.loans.get(rowIndex).getNumberOfYears();
                break;
            case 2:
                result = this.loans.get(rowIndex).getLoanAmount();
                break;
            case 3:
                result = this.loans.get(rowIndex).getLoanDate();
                break;
            case 4:
                result = this.loans.get(rowIndex).getTotalPayment();
                break;
        }
        return result;
    }

    public String getColumnName(int col){
        return this.columnNames[col];
    }
}
