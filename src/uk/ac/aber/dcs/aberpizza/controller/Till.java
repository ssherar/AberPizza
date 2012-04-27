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

public class Till implements Observer, ActionListener {
	private transient MainFrame window; 
	private transient TableDataModel items;
	private transient Choices choicesPanel;
	private transient Total total;
	private static transient Till tillInstance;
	private Order currentOrder = null;
	
	public Till() {
		
	}
	
	public Till(boolean startup) {
		window = new MainFrame(this);
		items = window.getModel();
		choicesPanel = window.getChoices();
		total = window.getTotal();
		
		
		XMLParser parser = new XMLParser();
		ArrayList<Product> p =  parser.load("products.xml").getProducts();
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
				items.addOption(p.getOption(), p.getProduct());
			} else if(s.equals("priceCancelled")) {
				items.decrement(p.getProduct());
			}
		} else if(o instanceof PaymentListener) {
			PaymentListener p = (PaymentListener) o;
			if(s.equals("cashedOff")) {
				//showRecipt;
				new ReceiptDialog();
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
		XMLEncoder encoder;
		Calendar date = new GregorianCalendar();
		String filename = "till_" + date.get(Calendar.YEAR) + "_" + date.get(Calendar.MONTH) + "_" + date.get(Calendar.DAY_OF_MONTH)+".xml";
		try {
			//check if there is a day open...
			encoder = new XMLEncoder(
			        new BufferedOutputStream(
			           new FileOutputStream(filename)));
			encoder.writeObject(this);
			encoder.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public static Till getInstance() {
		if(tillInstance == null) {
			tillInstance = new Till();
		}
		return tillInstance;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd == "New Day") {
			
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
