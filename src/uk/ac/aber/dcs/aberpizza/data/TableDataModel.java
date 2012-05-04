package uk.ac.aber.dcs.aberpizza.data;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import uk.ac.aber.dcs.aberpizza.controller.Manager;

/**
 * This DataModel holds all the data for the products. 
 * @author Samuel B Sherar (sbs1)
 *
 */
public class TableDataModel extends AbstractTableModel{
	
	/** The column names. */
	private String[] columnNames = {"Item Name", "Quantity", "Unitary Price", "Total"};
	
	/** The products. (In hindsight and experience, this should have been type Product) 
	 * The current format for the data is as follows:
	 * Column 1: Product Name
	 * Column 2: Quantity
	 * Column 3: Unitary Price
	 * Column 4: Total Price
	 * Column 5: If it's cancellable or not
	 * Column 6: Parent node (used for Options)
	 */
	private ArrayList<Object[]> data = new ArrayList<Object[]>();
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	@Override
	public int getRowCount() { 
		// TODO Auto-generated method stub
		return data.size();
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	@Override
	public Object getValueAt(int row, int col) {
		if(col > 4) return null;
		if((col == 0) && (this.data.get(row)[5] != "")) {
			return String.format("\t\t\t\t%s", this.data.get(row)[col]);
		}
		return this.data.get(row)[col];
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
	 */
	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#isCellEditable(int, int)
	 */
	public boolean isCellEditable(int row, int col) {
		return false;
	}
	
	/**
	 * Adds the row to the datafield
	 *
	 * @param name the name
	 * @param quantity the quantity
	 * @param price the price
	 */
	public void addRow(String name, int quantity, double price) {
		data.add(new Object[] {name, quantity, price, (price * quantity)});
		this.fireTableRowsInserted(data.size() - 1, 0);
	}
	
	/**
	 * Adds the row.
	 *
	 * @param p the product
	 * @param quantity the quantity
	 */
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
					Manager.round(p.getPrice().multiply(new BigDecimal(quantity))), false, "", 
					(p instanceof Side) ? -2 : -1
			});
			
			this.fireTableRowsInserted(data.size() - 1, 0);
		}
	}
	
	/**
	 * Checks if it is a side.
	 *
	 * @param index the index
	 * @return true, if is side
	 */
	public boolean isSide(int index) {
		return ((Integer) data.get(index)[6] == -2);
	}
	
	/**
	 * Decrement a product.
	 *
	 * @param p the p
	 */
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
	
	/**
	 * Decrement. Removes if it's the only one there
	 *
	 * @param index the index
	 */
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
	
	/**
	 * Removes the row given.
	 *
	 * @param index the index
	 */
	public void remove(int index) {
		this.data.remove(index);
		this.fireTableRowsDeleted(index, index);
	}
	
	/**
	 * Removes the product.
	 *
	 * @param p the p
	 */
	public void remove(Product p) {
		int foundIndex = this.find(p);
		if(foundIndex > -1) {
			this.remove(foundIndex);
		}
	}
	
	/**
	 * Adds the option to a product.
	 *
	 * @param o the option
	 * @param p the product
	 */
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
	
	/**
	 * Calculates total.
	 *
	 * @return the big decimal
	 */
	public BigDecimal calcTotal() {
		BigDecimal total = new BigDecimal(0.00);
		for(Object[] p : data) {
			total = total.add(
					((BigDecimal)p[2]).multiply(new BigDecimal((Integer) p[1]))
			);
		}
		return total;
	}
	
	/**
	 * Clears all.
	 */
	public void clearAll() {
		int rows = this.data.size();
		this.data.clear();
		this.fireTableRowsDeleted(0, rows);
	}
	
	/**
	 * Gets the product name.
	 *
	 * @param index the index
	 * @return the product name
	 */
	public String getProductName(int index) {
		return  ""+data.get(index)[5];
	}
	
	/**
	 * Find the index of a product.
	 *
	 * @param p the product
	 * @return the index
	 */
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
	
	
	/**
	 * Find an option added to a given product.
	 *
	 * @param o the option
	 * @param pIndex the product index
	 * @return the index
	 */
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

	/**
	 * Gets the option name.
	 *
	 * @param selectedRow the selected row
	 * @return the option name
	 */
	public String getOptionName(int selectedRow) {
		return ""+data.get(selectedRow)[0];
	}
	
	/**
	 * Checks if is cancellable.
	 *
	 * @param index the index
	 * @return true, if is cancellable
	 */
	public boolean isCancellable(int index) {
		return (Boolean) data.get(index)[4];
	}
	
	

}
