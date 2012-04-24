package uk.ac.aber.dcs.aberpizza.controller;
import javax.swing.*;

import uk.ac.aber.dcs.aberpizza.data.*;
import uk.ac.aber.dcs.aberpizza.gui.*;

import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.util.*;

public class Till implements Observer {
	private MainFrame window;
	private TableDataModel items;
	private Choices choicesPanel;
	private Total total;
	
	public Till() {
		window = new MainFrame(this);
		items = window.getModel();
		choicesPanel = window.getChoices();
		total = window.getTotal();
		
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
	
	public static void main(String[] args) {
		new Till();
	}
	
}
