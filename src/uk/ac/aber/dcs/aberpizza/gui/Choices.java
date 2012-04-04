package uk.ac.aber.dcs.aberpizza.gui;
import javax.swing.*;

import uk.ac.aber.dcs.aberpizza.controller.ChoiceListener;
import uk.ac.aber.dcs.aberpizza.data.Product;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class Choices extends JPanel {
	
	private ArrayList<Product> c;
	private ChoiceListener l;
	
	public Choices() {
		super(new GridLayout(0,4));
		l = new ChoiceListener(this);
	}
	
	public void init(ArrayList<Product> choices) {
		c = choices;
		for(Product p : c) {
			JButton tmp = new JButton(p.getName());
			tmp.addActionListener(l);
			this.add(tmp);
		}
		this.doLayout();
	}
	
	public Product find(String name) {
		for(Product p : c) {
			if(p.getName() == name) {
				return p;
			}
		}
		return null;
	}
}


