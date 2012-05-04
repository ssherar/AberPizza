package uk.ac.aber.dcs.aberpizza.gui;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import uk.ac.aber.dcs.aberpizza.controller.Manager;
import uk.ac.aber.dcs.aberpizza.data.TableDataModel;

/**
 * The parent frame for the GUI
 * @author Samuel B Sherar (sbs1)
 * @see uk.ac.aber.dcs.aberpizza.gui.DataPanel
 * @see uk.ac.aber.dcs.aberpizza.gui.ControllerPanel
 *
 */
public class MainFrame extends JFrame{
	
	/** The data pane. */
	private DataPanel dataPane;
	
	/** The controller pane. */
	private ControllerPanel controllerPane;
	
	/** The manager. */
	private Manager manager;
	
	/**
	 * Instantiates a new main frame.
	 *
	 * @param m the m
	 */
	public MainFrame(Manager m) {
		/* Sets the GUI to follow the operatings systems styling */
		try {
			 UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
			
		}
		
		manager = m;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("AberPizza");
		setSize(new Dimension(1024,800));
		setMinimumSize(new Dimension(1024,800));
		setVisible(true);
		
		dataPane = new DataPanel(m);
		controllerPane = new ControllerPanel(m);

		this.setJMenuBar(new MenuBar(manager));
		this.add(dataPane, BorderLayout.CENTER);
		this.add(controllerPane, BorderLayout.EAST);
		this.pack();
		
		this.repaint();
		this.doLayout();

	}
	
	/**
	 * Gets the items.
	 *
	 * @return the items
	 */
	public JTable getItems() {
		return dataPane.getItems();
	}
	
	/**
	 * Gets the choices.
	 *
	 * @return the choices
	 */
	public Choices getChoices() {
		return controllerPane.getChoices();
	}
	
	/**
	 * Gets the model.
	 *
	 * @return the model
	 */
	public TableDataModel getModel() {
		return dataPane.getModel();
	}

	/**
	 * Gets the total.
	 *
	 * @return the total
	 */
	public Total getTotal() {
		return dataPane.getTotal();
	}
	
	/**
	 * Gets the table.
	 *
	 * @return the table
	 */
	public JTable getTable() {
		return dataPane.getItems();
	}
	
	/**
	 * Overrides the setEnabled() function to enable the controller pane or not
	 * as the case maybe.
	 */
	@Override
	public void setEnabled(boolean set) {
		controllerPane.setEnabled(set, null);
	}
	
}
