package uk.ac.aber.dcs.aberpizza.gui;

import java.awt.event.KeyEvent;
import java.util.LinkedList;

import javax.swing.*;

import uk.ac.aber.dcs.aberpizza.controller.Manager;

/**
 * This is the menu bar for the GUI
 * @author samuelsherar
 * @version
 *
 */
public class MenuBar extends JMenuBar {
	private JMenu fileMenu;
	private JMenu administrationMenu;
	private JMenu helpMenu;
	private Manager manager;

	/**
	 * 
	 * @param m
	 */
	public MenuBar(Manager m) {
		fileMenu = new JMenu("File");
		administrationMenu = new JMenu("Administration");
		helpMenu = new JMenu("Help");
		
		manager = m;
		
		this.populateFile();
		this.populateAdministration();
		this.populateHelp();
		
		this.add(fileMenu);
		this.add(administrationMenu);
		this.add(helpMenu);
	}
	
	private void populateFile() {
		LinkedList<JMenuItem> file = new LinkedList<JMenuItem>();
		
		file.add(new JMenuItem("New Day"));
		file.add(new JMenuItem("Load Day..."));
		file.add(new JMenuItem("Save Day..."));
		file.add(new JMenuItem("Exit"));
		
		file.get(0).setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.META_MASK));
		for(int i = 0; i < file.size(); i++) {
			file.get(i).addActionListener(manager);
			fileMenu.add(file.get(i));
		}
	}
	
	private void populateAdministration() {
		LinkedList<JMenuItem> administration = new LinkedList<JMenuItem>();
		
		administration.add(new JMenuItem("Sales Report"));
		administration.add(new JMenuItem("Z-Index"));
		for(int i = 0; i < administration.size(); i++) {
			
			administration.get(i).addActionListener(manager);
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
