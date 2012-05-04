package uk.ac.aber.dcs.aberpizza.gui;
import javax.swing.*;

import uk.ac.aber.dcs.aberpizza.controller.ChoiceListener;
import uk.ac.aber.dcs.aberpizza.controller.Manager;
import uk.ac.aber.dcs.aberpizza.data.*;

import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


/**
 * The menu panel where the Product buttons populate
 * @author Samuel B Sherar (sbs1)
 *
 */
public class Choices extends JPanel{
	
	/** The Array of the products. */
	private ArrayList<Product> c;
	
	/** The listener. */
	private ChoiceListener l;
	
	/** The current price. */
	private BigDecimal currentPrice;
	
	/** The manager. */
	private Manager manager;
	
	/** The data model. */
	private ProductModel p;
	
	/**
	 * Instantiates a new choices.
	 *
	 * @param m the manager
	 */
	public Choices(Manager m) {
		super(new GridLayout(0,4));
		manager = m;
		p = new ProductModel(m);
		l = new ChoiceListener(this, p);
		currentPrice = new BigDecimal(0.00);
	}
	
	/**
	 * Inits the panel with just standard menu choices.
	 *
	 * @param choices the choices
	 * @see #init()
	 */
	public void init(ArrayList<Product> choices) {
		c = choices;
		this.init();
	}
	
	/**
	 * Inits the panel back to standard menu choises.
	 *
	 * @param o the o
	 * @deprecated
	 */
	public void init(Option o) {
		this.init();
	}
	
	/**
	 * Shows the menu choices.
	 */
	public void init() {
		this.removeAll();
		this.setLayout(new GridLayout(0,4));
		currentPrice  = new BigDecimal(0.00);
		for(Item p : c) {
			JButton tmp = new JButton(p.getName());
			if(p instanceof Pizza) tmp.setBackground(Color.red.brighter());
			else if (p instanceof Beverage) tmp.setBackground(Color.yellow);
			else if (p instanceof Side) tmp.setBackground(Color.green);
			tmp.addActionListener(l);
			this.add(tmp);
		}
		this.doLayout();
		this.repaint();
	}
	
	/**
	 * Find the product in the ArrayList.
	 *
	 * @param name the name
	 * @return the product
	 */
	public Product find(String name) {
		for(Product p : c) {
			if(p.getName() == name) {
				return p;
			}
		}
		return null;
	}
	
	/**
	 * Show options pane.
	 *
	 * @param item the item
	 */
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
	
	/**
	 * Sets the price.
	 *
	 * @param bigDecimal the new price
	 */
	public void setPrice(BigDecimal bigDecimal) {
		currentPrice = bigDecimal;
		this.init();
	}
}


