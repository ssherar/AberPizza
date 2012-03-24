package uk.ac.aber.dcs.aberpizza.gui;

import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {
	DataPanel dataPane;
	ControllerPanel controllerPane;
	
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("AberPizza");
		setSize(new Dimension(1024,800));
		setMinimumSize(new Dimension(1024,800));
		setVisible(true);
		
		dataPane = new DataPanel();
		controllerPane = new ControllerPanel();

		this.setJMenuBar(new MenuBar());
		this.add(dataPane, BorderLayout.CENTER);
		this.add(controllerPane, BorderLayout.EAST);
		this.pack();
		
		this.repaint();
		this.doLayout();
		
	}
	
	public JTable getItems() {
		return dataPane.getItems();
	}
}
