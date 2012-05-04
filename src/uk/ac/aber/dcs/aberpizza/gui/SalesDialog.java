package uk.ac.aber.dcs.aberpizza.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import uk.ac.aber.dcs.aberpizza.data.Order;

/**
 * Displays the orders for the day using a JTable. We are also able to display the receipt
 * with the itemised order
 * @author Samuel B Sherar (sbs1)
 * @see uk.ac.aber.dcs.aberpizza.gui.ReceiptDialog
 *
 */
public class SalesDialog extends JDialog implements ActionListener {
	
	/** The Table Model. */
	private TableModel tm;
	
	/** The table. */
	private JTable table;

	/**
	 * Instantiates a new sales dialog.
	 *
	 * @param order the array of orders
	 */
	public SalesDialog(ArrayList<Order> order) {
		this.setLayout(new BorderLayout());
		setTitle("Sales for today");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		tm = new TableModel(order);
		table = new JTable(tm);
		table.setRowHeight(36);
		this.add(table, BorderLayout.CENTER);
		
		JButton showReceipt = new JButton("Show Receipt...");
		showReceipt.addActionListener(this);
		this.add(showReceipt, BorderLayout.SOUTH);

		this.validate();
		this.repaint();
		this.doLayout();
		setVisible(true);
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(table.getSelectedRow() > -1) {
			Order o = tm.getOrder(table.getSelectedRow());
			new ReceiptDialog(o);
		}
	}

	/**
	 * The Class TableModel.
	 */
	public class TableModel extends AbstractTableModel {
		
		/** The column names. */
		private String[] columnNames = {"Date", "Name", "Total"};
		
		/** The data. */
		private ArrayList<Order> data;

		/**
		 * Instantiates a new table model.
		 *
		 * @param orders the orders
		 */
		TableModel(ArrayList<Order> orders) {
			data = orders;
		}

		/* (non-Javadoc)
		 * @see javax.swing.table.TableModel#getColumnCount()
		 */
		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return 3;
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
			// TODO Auto-generated method stub
			if(col == 0) {
				return data.get(row).getDate();
			} else if(col == 1) {
				return data.get(row).getCustomerName();
			} else if(col == 2) {
				return data.get(row).getTotal();
			} else return null;
		}

		/* (non-Javadoc)
		 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
		 */
		@Override
		public String getColumnName(int col) {
			return columnNames[col];
		}
		
		/**
		 * Gets the order.
		 *
		 * @param row the row index
		 * @return the order at the selected row
		 */
		public Order getOrder(int row) {
			return data.get(row);
		}

	}

}
