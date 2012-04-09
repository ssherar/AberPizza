package uk.ac.aber.dcs.aberpizza.gui;

import java.awt.*;
import java.math.BigDecimal;

import javax.swing.*;

public class DataPanel extends JPanel {

	private JTable items;
	
	public DataPanel() {
		super(new BorderLayout());

		Object[][] data = {{new Integer(1), "Pepperoni - Small", new Integer(1), new Double(9.95), new Double(9.95)}};
		Object[] columnNames = {"UID", "Item Name", "Quantity", "Unitary Price", "Total"};
		items = new JTable(data, columnNames);
		
		JScrollPane scroll = new JScrollPane(items);
		items.setFillsViewportHeight(true);
		
		this.add(scroll, BorderLayout.CENTER);
		
		Total bottom = new Total();
		this.add(bottom, BorderLayout.SOUTH);
		
	}

	public JTable getItems() {
		return items;
	}

	public void setItems(JTable items) {
		this.items = items;
	}
	
	
}
