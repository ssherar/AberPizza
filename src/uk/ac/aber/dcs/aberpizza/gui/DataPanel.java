package uk.ac.aber.dcs.aberpizza.gui;

import java.awt.*;
import java.math.BigDecimal;

import javax.swing.*;

import uk.ac.aber.dcs.aberpizza.data.TableDataModel;

public class DataPanel extends JPanel {

	private JTable items;
	private TableDataModel dModel;
	
	public DataPanel() {
		super(new BorderLayout());
		dModel = new TableDataModel();
		items = new JTable(dModel);
		
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
