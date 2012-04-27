package uk.ac.aber.dcs.aberpizza.gui;
import javax.swing.*;

import uk.ac.aber.dcs.aberpizza.controller.ChoiceListener;
import uk.ac.aber.dcs.aberpizza.controller.Till;
import uk.ac.aber.dcs.aberpizza.data.Option;
import uk.ac.aber.dcs.aberpizza.data.Product;
import uk.ac.aber.dcs.aberpizza.data.ProductModel;

import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


public class Choices extends JPanel{
	
	private ArrayList<Product> c;
	private ChoiceListener l;
	private BigDecimal currentPrice;
	private Till manager;
	private ProductModel p;
	
	public Choices(Till m) {
		super(new GridLayout(0,4));
		manager = m;
		p = new ProductModel(m);
		l = new ChoiceListener(this, p);
		currentPrice = new BigDecimal(0.00);
	}
	
	public void init(ArrayList<Product> choices) {
		c = choices;
		this.init();
	}
	
	public void init(Option o) {
		//dataPane(currentPrice);
		this.init();
	}
	
	public void init() {
		this.removeAll();
		this.setLayout(new GridLayout(0,4));
		currentPrice  = new BigDecimal(0.00);
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
		this.setLayout(new GridLayout(0,2));
		
		for(Option o : item.getOptions()) {
			JButton tmp = new JButton(o.getSize().toString());
			tmp.addActionListener(l);
			this.add(tmp);
		}
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(l);
		this.add(cancel);
		
		this.doLayout();
		this.repaint();
	}
	
	public void setPrice(BigDecimal bigDecimal) {
		currentPrice = bigDecimal;
		this.init();
	}
	
	private String format(String s) {
		return (s.substring(0, 1)) + (s.substring(1).toLowerCase());
	}

	
	

}


