package uk.ac.aber.dcs.aberpizza.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import uk.ac.aber.dcs.aberpizza.data.Option;
import uk.ac.aber.dcs.aberpizza.data.Product;
import uk.ac.aber.dcs.aberpizza.gui.Choices;

public class ChoiceListener implements ActionListener {
	Choices c;
	
	public ChoiceListener(Choices choices) {
		c = choices;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Product item = c.find(arg0.getActionCommand());
		if(item != null && item.hasOptions()) {
			c.showOptionsPane(c.find(arg0.getActionCommand()));
		} else {
			
		}
	}

}
