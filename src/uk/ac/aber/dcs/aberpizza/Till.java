package uk.ac.aber.dcs.aberpizza;

import javax.swing.UIManager;

import uk.ac.aber.dcs.aberpizza.controller.Manager;;

public class Till {

	public static void main(String[] args) {
		try {
			 UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
			
		}
		new Manager();
	}
}
