package uk.ac.aber.dcs.aberpizza.data;

import javax.swing.table.AbstractTableModel;

public class TableDataModel extends AbstractTableModel{
	private String[] columnNames = {"UID", "Item Name", "Quantity", "Unitary Price", "Total"};
	private Object[][] data = {};
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public int getRowCount() { 
		// TODO Auto-generated method stub
		return data.length;
	}

	@Override
	public Object getValueAt(int row, int col) {
		return this.data[row][col];
	}
	
	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	public boolean isCellEditable(int row, int col) {
		return false;
	}

}
