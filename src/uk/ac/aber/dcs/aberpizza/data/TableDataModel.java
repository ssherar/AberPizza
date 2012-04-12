package uk.ac.aber.dcs.aberpizza.data;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class TableDataModel extends AbstractTableModel{
	private String[] columnNames = {"Item Name", "Quantity", "Unitary Price", "Total"};
	private ArrayList<Object[]> data = new ArrayList<Object[]>();
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public int getRowCount() { 
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		return this.data.get(row)[col];
	}
	
	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	public boolean isCellEditable(int row, int col) {
		return false;
	}
	
	public void addRow(String name, int quantity, double price) {
		data.add(new Object[] {name, quantity, price, (price * quantity)});
		this.fireTableRowsInserted(data.size() - 1, 0);
	}
	
	public void addRow(Product p, int quantity) {
		data.add(new Object[] {p.getName(), quantity, p.getPrice(), (p.getPrice().multiply(new BigDecimal(quantity)))});
		this.fireTableRowsInserted(data.size() - 1, 0);
	}

}
