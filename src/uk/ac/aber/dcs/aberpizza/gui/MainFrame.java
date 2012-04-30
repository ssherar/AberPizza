package uk.ac.aber.dcs.aberpizza.gui;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import uk.ac.aber.dcs.aberpizza.controller.Manager;
import uk.ac.aber.dcs.aberpizza.data.TableDataModel;

public class MainFrame extends JFrame{
	private DataPanel dataPane;
	private ControllerPanel controllerPane;
	private Manager manager;
	
	public MainFrame(Manager m) {
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
	
	public JTable getItems() {
		return dataPane.getItems();
	}
	
	public Choices getChoices() {
		return controllerPane.getChoices();
	}
	
	public TableDataModel getModel() {
		return dataPane.getModel();
	}

	public Total getTotal() {
		return dataPane.getTotal();
	}
	
	@Override
	public void setEnabled(boolean set) {
		controllerPane.setEnabled(set, null);
	}
	
}
