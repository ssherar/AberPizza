package uk.ac.aber.dcs.aberpizza.gui;

import java.awt.*;
import java.math.BigDecimal;

import javax.swing.*;

import uk.ac.aber.dcs.aberpizza.controller.Till;
import uk.ac.aber.dcs.aberpizza.data.TableDataModel;

public class DataPanel extends JPanel {

	private JTable items;
	private TableDataModel dModel;
	private Till manager;
	private Total bottom;
	
	public DataPanel(Till m) {
		super(new BorderLayout());
		dModel = new TableDataModel();
		items = new JTable(dModel);
		
		manager = m;
		
		JScrollPane scroll = new JScrollPane(items);
		items.setFillsViewportHeight(true);
		
		this.add(scroll, BorderLayout.CENTER);
		
		bottom = new Total();
		this.add(bottom, BorderLayout.SOUTH);
		
	}

	public JTable getItems() {
		return items;
	}

	public void setItems(JTable items) {
		this.items = items;
	}
	
	public TableDataModel getModel() {
		return dModel;
	}
	
	public Total getTotal() {
		return bottom;
	}
	
	
}
