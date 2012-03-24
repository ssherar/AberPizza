package uk.ac.aber.dcs.aberpizza.gui;

import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("AberPizza");
		setSize(new Dimension(1024,800));
		setMinimumSize(new Dimension(1024,800));
		setVisible(true);
		
		DataPanel dataPane = new DataPanel();
		ControllerPanel controllerPane = new ControllerPanel();

		this.setJMenuBar(new MenuBar());
		this.add(dataPane, BorderLayout.CENTER);
		this.add(controllerPane, BorderLayout.EAST);
		this.pack();
		
		this.repaint();
		this.doLayout();
		
	}
}
