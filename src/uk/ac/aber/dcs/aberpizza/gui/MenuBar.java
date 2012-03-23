package uk.ac.aber.dcs.aberpizza.gui;

import java.util.LinkedList;

import javax.swing.*;

public class MenuBar extends JMenuBar {
	private LinkedList<JMenuItem> file;
	private LinkedList<JMenuItem> administration;
	private JMenu fileMenu;
	private JMenu administrationMenu;
	
	public MenuBar() {
		file = new LinkedList<JMenuItem>();
		administration = new LinkedList<JMenuItem>();
		
		fileMenu = new JMenu("File");
		this.populateFile();
		this.add(fileMenu);
	}
	
	private void populateFile() {
		file.add(new JMenuItem("Exit"));
		for(int i = 0; i < file.size(); i++) {
			fileMenu.add(file.get(i));
		}
	}
	
	private void populateAdministration() {
		administration.add(new JMenuItem("Sales Report"));
		administration.add(new JMenuItem("Z-Index"));
	}
}
