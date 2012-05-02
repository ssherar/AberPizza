package uk.ac.aber.dcs.aberpizza.gui;

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

public class SalesDialog extends JDialog {
	private TableModel tm;
	private JTable table;
	

	public SalesDialog() {
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		tm = new TableModel();
		table = new JTable(tm);
		table.setRowHeight(36);
		this.add(table);

		this.validate();
		this.repaint();
		this.doLayout();
		setVisible(true);


	}

	public class TableModel extends AbstractTableModel {
		private String[] columnNames = {"Date", "Name", "Total"};
		private ArrayList<Object[]> data = new ArrayList<Object[]>();

		TableModel() {
			data.add(new Object[] {"17/01/92:01:29:30", "Sam Sherar", "£95.99"});
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
			return data.get(row)[col];
		}

		@Override
		public String getColumnName(int col) {
			return columnNames[col];
		}

	}

}
