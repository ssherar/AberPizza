package uk.ac.aber.dcs.aberpizza.controller;
import javax.swing.*;

import uk.ac.aber.dcs.aberpizza.data.*;
import uk.ac.aber.dcs.aberpizza.gui.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Manager implements Observer {
	private MainFrame window;
	private JTable items;
	private Choices choicesPanel;
	
	public Manager() {
		window = new MainFrame();
		items = window.getItems();
		choicesPanel = window.getChoices();
		
		ArrayList<Product> choices = new ArrayList<Product>();
		choices.add(new Pizza("Margherita", 9.99, "12\" Pizza"));
		choices.add(new Pizza("Meatfeast", 9.99, "12\" Pizza"));
		choices.add(new Pizza("Texas BBQ", 9.99, "12\" Pizza"));
		choices.add(new Pizza("Chicken", 9.99, "12\" Pizza"));
		choices.add(new Beverage("Coke", 1.15, "Bottle of Coke"));
		choicesPanel.init(choices);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		System.out.println("Hello World!");
	}
	
	
	
}
