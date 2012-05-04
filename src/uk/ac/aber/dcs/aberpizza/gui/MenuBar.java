package uk.ac.aber.dcs.aberpizza.gui;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

import javax.swing.*;

import uk.ac.aber.dcs.aberpizza.controller.Manager;

// TODO: Auto-generated Javadoc
/**
 * This is the menu bar for the GUI.
 *
 * @author samuelsherar
 */
public class MenuBar extends JMenuBar {
	
	/** The file menu. */
	private JMenu fileMenu;
	
	/** The administration menu. */
	private JMenu administrationMenu;
	
	/** The manager. */
	private Manager manager;
	
	/** The shortcut key to work X-platform. */
	private int shortcutKey = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

	/**
	 * Instantiates a new menu bar.
	 *
	 * @param m the m
	 */
	public MenuBar(Manager m) {
		fileMenu = new JMenu("File");
		administrationMenu = new JMenu("Administration");
		
		manager = m;
		
		this.populateFile();
		this.populateAdministration();
		
		this.add(fileMenu);
		this.add(administrationMenu);
	}
	
	/**
	 * Populate file menu.
	 */
	private void populateFile() {
		LinkedList<JMenuItem> file = new LinkedList<JMenuItem>();
		
		file.add(new JMenuItem("New Day"));
		file.add(new JMenuItem("Load Day..."));
		file.add(new JMenuItem("Save Day..."));
		file.add(new JMenuItem("Exit"));
		
		file.get(0).setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, shortcutKey));
		file.get(1).setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, shortcutKey));
		file.get(2).setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, shortcutKey));
		file.get(3).setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, shortcutKey));
		for(int i = 0; i < file.size(); i++) {
			file.get(i).addActionListener(manager);
			fileMenu.add(file.get(i));
		}
	}
	
	/**
	 * Populate administration menu.
	 */
	private void populateAdministration() {
		LinkedList<JMenuItem> administration = new LinkedList<JMenuItem>();
		
		administration.add(new JMenuItem("Sales Report"));
		administration.add(new JMenuItem("Z-Index"));
		for(int i = 0; i < administration.size(); i++) {
			
			administration.get(i).addActionListener(manager);
			administrationMenu.add(administration.get(i));
		}
	}
	
}
