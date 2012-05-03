package uk.ac.aber.dcs.aberpizza.data;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import uk.ac.aber.dcs.aberpizza.controller.Manager;

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
		if(col > 4) return null;
		if((col == 0) && (this.data.get(row)[5] != "")) {
			return String.format("\t\t\t\t%s", this.data.get(row)[col]);
		}
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
		int foundIndex = this.find(p);

		if(foundIndex > -1) {
			Object[] tmp = data.get(foundIndex);
			tmp[1] = (Integer) tmp[1] + 1;
			
			BigDecimal totalPrice = ((BigDecimal)tmp[2]).multiply(new BigDecimal((Integer) tmp[1]));
			tmp[3] = Manager.round(totalPrice);
			data.set(foundIndex, tmp);
			this.fireTableDataChanged();
		} else {
			data.add(new Object[] {p.getName(), quantity, Manager.round(p.getPrice()), 
					Manager.round(p.getPrice().multiply(new BigDecimal(quantity))), false, "", -1
			});
			
			this.fireTableRowsInserted(data.size() - 1, 0);
		}
	}
	
	public void decrement(Product p) {
		int foundIndex = this.find(p);
		
		if(foundIndex > -1) {
			Object[] tmp = data.get(foundIndex);
			if((Integer) tmp[1] == 1) {
				this.remove(foundIndex);
			} else {
				tmp[1] = (Integer) tmp[1] - 1;
				
				BigDecimal totalPrice = ((BigDecimal)tmp[2]).multiply(new BigDecimal((Integer) tmp[1]));
				tmp[3] = Manager.round(totalPrice);
				data.set(foundIndex, tmp);
				this.fireTableDataChanged();
			}
		}
	}
	
	public void decrement(int index) {
		Object[] tmp = data.get(index);
		if((Integer) tmp[1] == 1) {
			int pIndex = (Integer) data.get(index)[6];
			this.remove(index);
			if(pIndex > -1)
				this.decrement(pIndex);
		} else {
			tmp[1] = (Integer) tmp[1] - 1;
			
			BigDecimal totalPrice = ((BigDecimal)tmp[2]).multiply(new BigDecimal((Integer) tmp[1]));
			tmp[3] = Manager.round(totalPrice);
			data.set(index, tmp);
			int pIndex = (Integer) data.get(index)[6];
			if(pIndex > -1)
				this.decrement(pIndex);
			this.fireTableDataChanged();
		}
	}
	
	public void remove(int index) {
		this.data.remove(index);
		this.fireTableRowsDeleted(index, index);
	}
	
	public void remove(Product p) {
		int foundIndex = this.find(p);
		if(foundIndex > -1) {
			this.remove(foundIndex);
		}
	}
	
	public void addOption(Option o, Product p) {
		int i = this.find(p);
		if(i > -1) {
			int j = find(o, i);
			if(j > -1) {
				Object[] tmp = data.get(j);
				tmp[1] = (Integer) tmp[1] + 1;
				
				BigDecimal totalPrice = ((BigDecimal)tmp[2]).multiply(new BigDecimal((Integer) tmp[1]));
				tmp[3] = Manager.round(totalPrice);
				data.set(j, tmp);
				this.fireTableDataChanged();
			} else {
				this.data.add(i + 1, new Object[] {
						o.getSize(), 1, Manager.round(o.getPrice()), Manager.round(o.getPrice().multiply(new BigDecimal(1))), true, p.getName(), i
				});
				this.fireTableRowsInserted(i + 1, 0);
			}
		}
	}
	
	public BigDecimal calcTotal() {
		BigDecimal total = new BigDecimal(0.00);
		for(Object[] p : data) {
			total = total.add(
					((BigDecimal)p[2]).multiply(new BigDecimal((Integer) p[1]))
			);
		}
		return total;
	}
	
	public void clearAll() {
		int rows = this.data.size();
		this.data.clear();
		this.fireTableRowsDeleted(0, rows);
	}
	
	public String getProductName(int index) {
		return  ""+data.get(index)[5];
	}
	
	private int find(Product p) {
		int foundIndex = -1;
		for(int i = 0; i < data.size(); ++i) {
			if(data.get(i)[0] == p.getName()) {
				foundIndex = i;
				break;
			}
		}
		return foundIndex;
	}
	
	private int find(String product) {
		int foundIndex = -1;
		for(int i = 0; i < data.size(); ++i) {
			if(data.get(i)[0] == product) {
				foundIndex = i;
				break;
			}
		}
		return foundIndex;
	}
	
	private int find(Option o, int pIndex) {
		int foundIndex = -1;
		for(int i = 0; i < data.size(); i++) {
			if(((String)data.get(i)[5] == (String)data.get(pIndex)[0]) && (o.getSize() == data.get(i)[0])) {
				foundIndex = i;
				break;
			}
		}
		
		return foundIndex;
	}

	public String getOptionName(int selectedRow) {
		return ""+data.get(selectedRow)[0];
	}
	
	public boolean isCancellable(int index) {
		return (Boolean) data.get(index)[4];
	}
	
	

}
