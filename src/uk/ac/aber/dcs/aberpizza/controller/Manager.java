package uk.ac.aber.dcs.aberpizza.controller;
import javax.swing.*;

import uk.ac.aber.dcs.aberpizza.data.*;
import uk.ac.aber.dcs.aberpizza.gui.*;

import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.util.*;

public class Manager implements Observer {
	private MainFrame window;
	private TableDataModel items;
	private Choices choicesPanel;
	private Total total;
	
	public Manager() {
		window = new MainFrame(this);
		items = window.getModel();
		choicesPanel = window.getChoices();
		total = window.getTotal();
		
		/*ArrayList<Product> choices = new ArrayList<Product>();
		choices.add(new Pizza("Margherita", 7.99, "Cheese and Tomato Pizza"));
		choices.add(new Pizza("Meatfeast", 9.99, "Chicken, Sausage, Pepperoni and Sausage"));
		choices.add(new Pizza("Texas BBQ", 9.99, "Chicken Strips with Peppers, Mushrooms and Sweetcorn with a BBQ sause base"));
		choices.add(new Pizza("Chicken", 9.99, "Chicken, Sweetcorn"));
		choices.add(new Pizza("Seafood Special", 9.99, "Tuna, Prawns, Salmon, Olives and Sweetcorn"));
		choices.add(new Pizza("Veggie", 9.99, "Roast selection of Vegetables and olives with Goats Cheese"));
		choices.add(new Pizza("Pepperoni", 8.99, "Just Pepperoni"));
		choices.add(new Beverage("Coke", 0.70, "Coca-cola"));
		choices.add(new Beverage("Fanta", 0.70, "Fanta"));
		choices.add(new Beverage("Dr. Pepper", 0.70, "Dr. Pepper"));
		choices.add(new Beverage("7-UP", 0.70, "7-UP"));
		choices.add(new Beverage("Water", 0.50, "Still Water"));
		XMLParser parser = new XMLParser();
		parser.save(choices);*/
		
		XMLParser parser = new XMLParser();
		ArrayList<Product> p =  parser.load("products.xml").getProducts();
		choicesPanel.init(p);
	}

	@Override
	public void update(Observable o, Object s) {
		if(!(s instanceof String)) {
			return;
		}
		if(o instanceof ProductModel) {
			ProductModel p = (ProductModel) o;
			if(s.equals("priceIncreased")) {
				items.addRow(p.getProduct(), 1, true);
			} else if(s.equals("optionAdded")) {;
				items.addOption(p.getOption(), p.getProduct());
			} else if(s.equals("priceCancelled")) {
				items.decrement(p.getProduct());
			}
		} else if(o instanceof PaymentListener) {
			PaymentListener p = (PaymentListener) o;
			if(s.equals("cashedOff")) {
				//showRecipt;
				items.clearAll();
			} else if(s.equals("cancelOrder")) {
				int n = JOptionPane.showOptionDialog(window,"Are you sure that you want to cancel this order. THIS CANNOT BE UNDONE",
								"Warning!", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE, null, null, s);
				if(n == 0) {
					items.clearAll();
				}
			}
		}
		BigDecimal totalValue = items.calcTotal();
		total.setValue(totalValue);
	}
	
	public static BigDecimal round(BigDecimal r) {
		return r.setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}
	
}
