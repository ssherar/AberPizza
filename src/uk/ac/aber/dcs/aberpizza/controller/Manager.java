package uk.ac.aber.dcs.aberpizza.controller;
import javax.swing.*;

import uk.ac.aber.dcs.aberpizza.data.*;
import uk.ac.aber.dcs.aberpizza.gui.*;

import java.awt.event.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

public class Manager implements Observer, ActionListener {
	private MainFrame window; 
	private TableDataModel items;
	private Choices choicesPanel;
	private Total total;
	private Order currentOrder = null;
	private Till till;
	
	
	public Manager() {
		window = new MainFrame(this);
		window.setEnabled(false);
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
				currentOrder.addItem(new OrderItem(p.getProduct()));
				items.addRow(p.getProduct(), 1);
			} else if(s.equals("optionAdded")) {;
				currentOrder.addOption(p.getOption(), new OrderItem(p.getProduct()));
				items.addOption(p.getOption(), p.getProduct());
			} else if(s.equals("priceCancelled")) {
				currentOrder.decrement(new OrderItem(p.getProduct()));
				items.decrement(p.getProduct());
			}
		} else if(o instanceof PaymentListener) { 
			if(s.equals("cashedOff")) {
				//showRecipt;
				new ReceiptDialog(currentOrder, "Cash");
				till.addOrder(currentOrder);
				till.save();
				currentOrder = null;
				choicesPanel.setVisible(false);
				items.clearAll();
			} else if(s.equals("cancelOrder")) {
				int n = JOptionPane.showOptionDialog(window,"Are you sure that you want to cancel this order. THIS CANNOT BE UNDONE",
								"Warning!", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE, null, null, s);
				if(n == 0) {
					items.clearAll();
					choicesPanel.setVisible(false);
					currentOrder = null;
				}
			} else if(s.equals("itemVoid")) {
				String pName = items.getProductName(window.getTable().getSelectedRow());
				String oName = items.getOptionName(window.getTable().getSelectedRow());
				
				if(items.isCancellable(window.getTable().getSelectedRow())) {
					items.decrement(window.getTable().getSelectedRow());
					currentOrder.decrementOption(pName, oName);
				} if (items.isSide(window.getTable().getSelectedRow())) {
					items.decrement(window.getTable().getSelectedRow());
					currentOrder.decrementSide(oName);
				}
				
				
			}
		}
		BigDecimal totalValue = items.calcTotal();
		total.setValue(totalValue);

	}
	
	public static BigDecimal round(BigDecimal r) {
		return r.setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd == "New Day") {
			window.setEnabled(true);
			till = new Till();
		} else if(cmd == "Load Day...") {
			try {
				till = Till.load();
				window.setEnabled(true);
			} catch(IOException ie) {
				
			}
		} else if(cmd == "Save Day...") {
			till.save();
		} else if(cmd == "Exit") {
			System.exit(0);
		} else if (cmd == "Sales Report") {
			if(till != null)
				new SalesDialog(till.getOrders());
			else
				JOptionPane.showMessageDialog(window, "Till has not been loaded", 
						"Warning", JOptionPane.ERROR_MESSAGE);
		} else if (cmd == "Z-Index") {
			JOptionPane.showMessageDialog(window, "Total for the day: " + round(till.getTotalForDay()));
		}
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
