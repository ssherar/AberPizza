package uk.ac.aber.dcs.aberpizza.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import uk.ac.aber.dcs.aberpizza.data.Option;
import uk.ac.aber.dcs.aberpizza.data.Product;
import uk.ac.aber.dcs.aberpizza.gui.Choices;

public class ChoiceListener implements ActionListener {
	private Choices c;
	private ListeningType type;
	
	public ChoiceListener(Choices choices) {
		c = choices;
		type = ListeningType.ROOT;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(this.type == ListeningType.ROOT) {
			Product item = c.find(arg0.getActionCommand());
			if(item != null && item.hasOptions()) {
				c.showOptionsPane(c.find(arg0.getActionCommand()));
			} else {
				
			}
		} else if(this.type == ListeningType.OPTIONS) {
			
		} else {
			
		}
	}

}
