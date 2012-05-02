package uk.ac.aber.dcs.aberpizza.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
		ReceiptButtonRenderer rend = new ReceiptButtonRenderer();
		table.getColumn("Receipt").setCellRenderer(rend);
		table.getColumn("Receipt").setCellEditor(new ButtonEditor(new JCheckBox()));
		table.setRowHeight(36);
		this.add(table);

		this.validate();
		this.repaint();
		this.doLayout();
		setVisible(true);


	}

	public class TableModel extends AbstractTableModel {
		private String[] columnNames = {"Date", "Name", "Total", "Receipt"};
		private ArrayList<Object[]> data = new ArrayList<Object[]>();

		TableModel() {
			data.add(new Object[] {"17/01/92:01:29:30", "Sam Sherar", "£95.99", ""});
		}

		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return 4;
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

		public TableCellRenderer getCellRenderer( int row, int column ) {
			return new ReceiptButtonRenderer();
		}

	}

	public class ReceiptButtonRenderer extends JButton implements TableCellRenderer {
		private int selectedRow;
		private int selectedCol;
		
		public ReceiptButtonRenderer() {
			super("Receipt");
		}


		public Component getTableCellRendererComponent(JTable table, Object value,
				boolean isSelected, boolean hasFocus, int row, int column) {
			if (isSelected) {
				setForeground(table.getSelectionForeground());
				setBackground(table.getSelectionBackground());
			} else {
				setForeground(table.getForeground());
				setBackground(UIManager.getColor("Button.background"));
			}
			setText((value == null) ? "" : value.toString());
			return this;
		}

	}
	
	class ButtonEditor extends DefaultCellEditor {
		  protected JButton button;

		  private String label;

		  private boolean isPushed;

		  public ButtonEditor(JCheckBox checkBox) {
		    super(checkBox);
		    button = new JButton();
		    button.setOpaque(true);
		    button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				fireEditingStopped();
			}
		    });
		  }

		  public Component getTableCellEditorComponent(JTable table, Object value,
		      boolean isSelected, int row, int column) {
		    if (isSelected) {
		      button.setForeground(table.getSelectionForeground());
		      button.setBackground(table.getSelectionBackground());
		    } else {
		      button.setForeground(table.getForeground());
		      button.setBackground(table.getBackground());
		    }
		    label = (value == null) ? "" : value.toString();
		    button.setText(label);
		    isPushed = true;
		    return button;
		  }

		  public Object getCellEditorValue() {
		    if (isPushed) {
		      // 
		      // 
		      JOptionPane.showMessageDialog(button, label + ": Ouch!");
		      // System.out.println(label + ": Ouch!");
		    }
		    isPushed = false;
		    return new String(label);
		  }

		  public boolean stopCellEditing() {
		    isPushed = false;
		    return super.stopCellEditing();
		  }

		  protected void fireEditingStopped() {
		    super.fireEditingStopped();
		  }
	}
}
