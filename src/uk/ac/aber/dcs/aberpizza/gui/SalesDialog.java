package uk.ac.aber.dcs.aberpizza.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.EventObject;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.event.CellEditorListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import uk.ac.aber.dcs.aberpizza.data.Order;

public class SalesDialog extends JDialog implements ActionListener {
	private TableModel tm;
	private JTable table;

	public SalesDialog(ArrayList<Order> order) {
		this.setLayout(new BorderLayout());
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
				return null; /*data.get(row).getDate();*/
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
