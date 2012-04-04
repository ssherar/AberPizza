package uk.ac.aber.dcs.aberpizza.controller;
import javax.swing.*;

import uk.ac.aber.dcs.aberpizza.data.*;
import uk.ac.aber.dcs.aberpizza.gui.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Manager {
	private MainFrame window;
	private JTable items;
	private Choices choicesPanel;
	
	public Manager() {
		window = new MainFrame();
		items = window.getItems();
		choicesPanel = window.getChoices();
		
		ArrayList<Product> choices = new ArrayList<Product>();
		choices.add(new Product("Margherita", 9.99, "12\" Pizza"));
		choices.add(new Product("Meatfeast", 9.99, "12\" Pizza"));
		choices.add(new Product("Texas BBQ", 9.99, "12\" Pizza"));
		choices.add(new Product("Chicken", 9.99, "12\" Pizza"));
		choicesPanel.init(choices);
		
	}
	
	
}
