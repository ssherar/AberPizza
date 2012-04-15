package uk.ac.aber.dcs.aberpizza.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;

import uk.ac.aber.dcs.aberpizza.data.Option;
import uk.ac.aber.dcs.aberpizza.data.Product;
import uk.ac.aber.dcs.aberpizza.data.ProductModel;
import uk.ac.aber.dcs.aberpizza.gui.Choices;

public class ChoiceListener implements ActionListener {
	private Choices c;
	private ProductModel model;
	private ListeningType type;
	private Product current;
	
	public ChoiceListener(Choices choices, ProductModel p) {
		c = choices;
		type = ListeningType.ROOT;
		current = null;
		model = p;
	}
	
	public ChoiceListener() {
		type = null;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(this.type == ListeningType.ROOT) {
			Product item = c.find(arg0.getActionCommand());
			if(item != null && item.hasOptions()) {
				this.type = ListeningType.OPTIONS;
				current = item;
				c.showOptionsPane(item);
			}
			model.addItem(item);
		} else if(this.type == ListeningType.OPTIONS) {
			//first check if cancelled.
			if(arg0.getActionCommand() == "Cancel") {
				this.type = ListeningType.ROOT;
				model.cancelItem();
				current = null;
				c.init();
				return;
			}
			
			Option o = current.findOption(arg0.getActionCommand());
			if(o != null) {
				this.type = ListeningType.ROOT;
				current = null;
				model.addOption(o);
				c.init(o);
			}
			
		} else {

		}
	}

}
