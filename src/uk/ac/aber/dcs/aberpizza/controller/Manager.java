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
		
		ArrayList<Product> choices = new ArrayList<Product>();
		choices.add(new Pizza("Margherita", 9.99, "12\" Pizza"));
		choices.add(new Pizza("Meatfeast", 9.99, "12\" Pizza"));
		choices.add(new Pizza("Texas BBQ", 9.99, "12\" Pizza"));
		choices.add(new Pizza("Chicken", 9.99, "12\" Pizza"));
		choices.add(new Beverage("Coke", 1.15, "Bottle of Coke"));
		choicesPanel.init(choices);
	}

	@Override
	public void update(Observable o, Object s) {
		if(!(s instanceof String)) {
			return;
		}
		
		if(s.equals("priceIncreased")) {
			ProductModel p = (ProductModel) o;
			BigDecimal currentTotal = total.getValue();
			currentTotal  = currentTotal.add(p.getRunningPrice());
			total.setValue(round(currentTotal));
		} else if(s.equals("priceCancelled")) {
			ProductModel p = (ProductModel) o;
			BigDecimal currentTotal = total.getValue();
			currentTotal = currentTotal.subtract(p.getRunningPrice());
			total.setValue(round(currentTotal));
		}
		
		
	}
	
	private BigDecimal round(BigDecimal r) {
		return r.setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}
	
}
