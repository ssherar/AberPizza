package uk.ac.aber.dcs.aberpizza.gui;

import java.awt.*;
import java.math.BigDecimal;

import javax.swing.*;

import uk.ac.aber.dcs.aberpizza.controller.Manager;
import uk.ac.aber.dcs.aberpizza.data.TableDataModel;

/**
 * The parent pane which sets out the Total pane, and also the JTable where
 * all the order items are held
 * @author Samuel B Sherar (sbs1)
 * @see uk.ac.aber.dcs.aberpizza.gui.Total
 *
 */
public class DataPanel extends JPanel {

	/** The items. */
	private JTable items;
	
	/** The table model. */
	private TableDataModel dModel;
	
	/** The manager. */
	private Manager manager;
	
	/** The total pane. */
	private Total bottom;
	
	/**
	 * Instantiates a new data panel.
	 *
	 * @param m the m
	 */
	public DataPanel(Manager m) {
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

	/**
	 * Gets the items.
	 *
	 * @return the items
	 */
	public JTable getItems() {
		return items;
	}

	/**
	 * Sets the items.
	 *
	 * @param items the new items
	 */
	public void setItems(JTable items) {
		this.items = items;
	}
	
	/**
	 * Gets the model.
	 *
	 * @return the model
	 */
	public TableDataModel getModel() {
		return dModel;
	}
	
	/**
	 * Gets the total.
	 *
	 * @return the total
	 */
	public Total getTotal() {
		return bottom;
	}
	
	
}
