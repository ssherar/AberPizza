package uk.ac.aber.dcs.aberpizza;

import javax.swing.JFrame;
import javax.swing.UIManager;

import uk.ac.aber.dcs.aberpizza.gui.MainFrame;

public class Till {

	public static void main(String[] args) {
		try {
			 UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
			
		}
		JFrame a = new MainFrame();
	}
}
