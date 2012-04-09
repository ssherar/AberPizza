package uk.ac.aber.dcs.aberpizza.gui;
import javax.swing.*;

import uk.ac.aber.dcs.aberpizza.controller.ChoiceListener;
import uk.ac.aber.dcs.aberpizza.data.Option;
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
		this.init();
	}
	
	public void init() {
		this.removeAll();
		for(Product p : c) {
			JButton tmp = new JButton(p.getName());
			tmp.addActionListener(l);
			this.add(tmp);
		}
		this.doLayout();
		this.repaint();
	}
	
	public Product find(String name) {
		for(Product p : c) {
			if(p.getName() == name) {
				return p;
			}
		}
		return null;
	}
	
	public void showOptionsPane(Product item) {
		this.removeAll();
		for(Option o : item.getOptions()) {
			JButton tmp = new JButton(""+o.getSize());
			//tmp.addActionListener(l);
			this.add(tmp);
		}
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(l);
		this.add(cancel);
		
		this.doLayout();
		this.repaint();
	}
}


