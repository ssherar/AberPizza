package uk.ac.aber.dcs.aberpizza.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import uk.ac.aber.dcs.aberpizza.data.Option;
import uk.ac.aber.dcs.aberpizza.data.Product;
import uk.ac.aber.dcs.aberpizza.data.ProductModel;
import uk.ac.aber.dcs.aberpizza.gui.Choices;


/**
 * This listener listens to events from the Choise panel, and adds data to
 * a product model, where it gets sent off to the observer.
 *
 * @author Samuel B Sherar (sbs1)
 * @see uk.ac.aber.dcs.aberpizza.data.ProductModel.java
 */
public class ChoiceListener implements ActionListener {
	
	/** The choices panel which we will later manipulate. */
	private Choices c;
	
	/** The data model which sends data to the observer. */
	private ProductModel model;
	
	/**
	 * An Enum which allows for different listening modes, implemented
	 * this way for later expansion for recursive options (e.g.  extra toppings)
	 */
	private ListeningType type;
	
	/** The current product in question. */
	private Product current;
	
	/**
	 * Setting all the default values for the global variables.
	 *
	 * @param choices The choices panel
	 * @param p The product model created in the choices panel
	 */
	public ChoiceListener(Choices choices, ProductModel p) {
		c = choices;
		type = ListeningType.ROOT;
		current = null;
		model = p;
	}
	
	/**
	 * Default constructor to reset the ListeningType.
	 */
	public ChoiceListener() {
		type = null;
	}
	
	/**
	 * Handles the events: Checks which listening mode is set, and acts accordingly
	 * by either creating a new item or adding an option to an existing product.
	 *
	 * @param arg0 the arg0
	 */
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
