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
		if(col == 4) return null;
		
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
	
	public void addRow(Product p, int quantity, boolean cancellable) {
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
					Manager.round(p.getPrice().multiply(new BigDecimal(quantity))), cancellable, ""
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
						o.getSize(), 1, Manager.round(o.getPrice()), Manager.round(o.getPrice().multiply(new BigDecimal(1))), false, p.getName()
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
	

}
