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

public class SalesDialog extends JDialog implements ActionListener {
	private TableModel tm;
	private JTable table;

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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(table.getSelectedRow() > -1) {
			Order o = tm.getOrder(table.getSelectedRow());
			new ReceiptDialog(o);
		}
	}

	public class TableModel extends AbstractTableModel {
		private String[] columnNames = {"Date", "Name", "Total"};
		private ArrayList<Order> data;

		TableModel(ArrayList<Order> orders) {
			data = orders;
		}

		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return 3;
		}

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return data.size();
		}

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

		@Override
		public String getColumnName(int col) {
			return columnNames[col];
		}
		
		public Order getOrder(int row) {
			return data.get(row);
		}

	}

}
