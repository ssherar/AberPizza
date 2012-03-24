package uk.ac.aber.dcs.aberpizza.gui;

import java.util.LinkedList;

import javax.swing.*;

public class MenuBar extends JMenuBar {
	private JMenu fileMenu;
	private JMenu administrationMenu;
	private JMenu helpMenu;
	
	public MenuBar() {
		fileMenu = new JMenu("File");
		administrationMenu = new JMenu("Administration");
		helpMenu = new JMenu("Help");
		
		this.populateFile();
		this.populateAdministration();
		this.populateHelp();
		
		this.add(fileMenu);
		this.add(administrationMenu);
		this.add(helpMenu);
	}
	
	private void populateFile() {
		LinkedList<JMenuItem> file = new LinkedList<JMenuItem>();
		
		file.add(new JMenuItem("Exit"));
		for(int i = 0; i < file.size(); i++) {
			fileMenu.add(file.get(i));
		}
	}
	
	private void populateAdministration() {
		LinkedList<JMenuItem> administration = new LinkedList<JMenuItem>();
		
		administration.add(new JMenuItem("Sales Report"));
		administration.add(new JMenuItem("Z-Index"));
		for(int i = 0; i < administration.size(); i++) {
			administrationMenu.add(administration.get(i));
		}
	}
	
	private void populateHelp() {
		LinkedList<JMenuItem> help = new LinkedList<JMenuItem>();
		
		help.add(new JMenuItem("About"));
		
		for(int i = 0; i < help.size(); i++) {
			helpMenu.add(help.get(i));
		}
	}
}
