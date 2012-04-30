package uk.ac.aber.dcs.aberpizza.controller;
import javax.swing.*;

import uk.ac.aber.dcs.aberpizza.data.*;
import uk.ac.aber.dcs.aberpizza.gui.*;

import java.awt.*;
import java.awt.event.*;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.*;

public class Manager implements Observer, ActionListener {
	private transient MainFrame window; 
	private transient TableDataModel items;
	private transient Choices choicesPanel;
	private transient Total total;
	private static transient Manager tillInstance;
	private Order currentOrder = null;
	private Till till;
	
	public Manager() {
		
	}
	
	public Manager(boolean startup) {
		window = new MainFrame(this);
		window.setEnabled(false);
		items = window.getModel();
		choicesPanel = window.getChoices();
		total = window.getTotal();
		
		
		XMLParser parser = new XMLParser();
		ArrayList<Product> p =  parser.load("products.xml").getProducts();
		p.add(new Side("Garlic Bread", 1.99, "Garlic Bread"));
		p.add(new Side("Chips", 1.20, "Chips"));
		p.add(new Side("Wedges", 1.50, "Wedges"));
		p.add(new Side("Ketchup", 0.2, "Ketchup"));
		parser.save(p);
		choicesPanel.init(p);
		this.save();
	}

	@Override
	public void update(Observable o, Object s) {
		if(!(s instanceof String)) {
			return;
		}
		if(o instanceof ProductModel) {
			ProductModel p = (ProductModel) o;
			if(s.equals("priceIncreased")) {
				currentOrder.addItem(new OrderItem(p.getProduct()));
				items.addRow(p.getProduct(), 1, true);
			} else if(s.equals("optionAdded")) {;
				currentOrder.addOption(p.getOption(), new OrderItem(p.getProduct()));
				items.addOption(p.getOption(), p.getProduct());
			} else if(s.equals("priceCancelled")) {
				currentOrder.decrement(new OrderItem(p.getProduct()));
				items.decrement(p.getProduct());
			}
		} else if(o instanceof PaymentListener) { 
			PaymentListener p = (PaymentListener) o;
			if(s.equals("cashedOff")) {
				//showRecipt;
				new ReceiptDialog(currentOrder, "Cash");
				till.addOrder(currentOrder);
				currentOrder = null;
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
	
	
	public void save() {
	
		
	}
	
	public static Manager getInstance() {
		if(tillInstance == null) {
			tillInstance = new Manager();
		}
		return tillInstance;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd == "New Day") {
			window.setEnabled(true);
			till = new Till();
		} else if(cmd == "Load Day...") {
			
		} else if(cmd == "Save Day...") {
			
		} else if(cmd == "Exit") {
			System.exit(0);
		}
	}
	
	private boolean findToday() {
		return false;
	}
	
	public boolean currentOrderSet() {
		if(this.currentOrder == null) {
			return false;
		}
		return true;
	}
	
	public void setCurrentOrder(Order o) {
		this.currentOrder = o;
	}
	
	public void setCustomerName(String s) {
		this.currentOrder.setCustomerName(s);
	}
	
}
