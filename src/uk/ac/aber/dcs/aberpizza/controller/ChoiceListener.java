package uk.ac.aber.dcs.aberpizza.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import uk.ac.aber.dcs.aberpizza.data.Product;
import uk.ac.aber.dcs.aberpizza.gui.Choices;

public class ChoiceListener implements ActionListener {
	Choices c;
	
	public ChoiceListener(Choices choices) {
		c = choices;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.print(arg0.getActionCommand()+"\t");
		System.out.println(c.find(arg0.getActionCommand()).getOptions());
	}

}
