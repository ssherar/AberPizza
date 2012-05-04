package uk.ac.aber.dcs.aberpizza.controller;
import javax.swing.*;

import uk.ac.aber.dcs.aberpizza.data.*;
import uk.ac.aber.dcs.aberpizza.gui.*;

import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * The manager is the startpoint for the program. It handles all the data to be 
 * passed onto the data and gui classes
 * @author Samuel B Sherar (sbs1)
 *
 */
public class Manager implements Observer, ActionListener {
	
	/** The window. */
	private MainFrame window; 
	
	/** The TableModel for the items. */
	private TableDataModel items;
	
	/** The choices panel. */
	private Choices choicesPanel;
	
	/** The total label. */
	private Total total;
	
	/** The current order. It is saved locally before adding into the till*/
	private Order currentOrder = null;
	
	/** The till. */
	private Till till;
	
	/** The discount class*/
	private Discount discount;
	
	
	/**
	 * Instantiates a new manager with default variables. It also loads 
	 * the products and creates several discounts
	 */
	public Manager() {
		window = new MainFrame(this);
		window.setEnabled(false);
		items = window.getModel();
		choicesPanel = window.getChoices();
		total = window.getTotal();
		
		discount = new Discount();
		discount.createDiscount(Sizes.LARGE, 3);
		discount.createDiscount(Sizes.MEDIUM, 4);
		discount.createDiscount(Sizes.SMALL, 5);
		discount.createDiscount(new BigDecimal(15), 25);
		discount.createDiscount(new BigDecimal(30), 50);
		
		
		XMLParser parser = new XMLParser();
		ArrayList<Product> p =  parser.load("products.xml").getProducts();

		choicesPanel.init(p);
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
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
			BigDecimal discountValue = discount.getDiscount(currentOrder);
			currentOrder.setDiscount(discountValue);
		} else if(o instanceof PaymentListener) { 
			if(s.equals("cashedOff")) {
				//showRecipt;
				new ReceiptDialog(currentOrder, "Cash");
				till.addOrder(currentOrder);
				till.save();
				currentOrder = null;
				choicesPanel.setVisible(false);
				items.clearAll();
			}  else if(s.equals("paymentCard")) {
				new ReceiptDialog(currentOrder, "Card");
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
				if(window.getTable().getSelectedRow() != -1) { 
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
		}
		BigDecimal totalValue = items.calcTotal();
		total.setValue(totalValue, (currentOrder == null) ? new BigDecimal(0.00) : Manager.round(currentOrder.getDiscount()));

	}
	
	/**
	 * A static method to round the BigDecimal to 2 D.P
	 *
	 * @param r the BigDecimal to be rounded
	 * @return the rounded BigDecimal
	 */
	public static BigDecimal round(BigDecimal r) {
		return r.setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}


	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd == "New Day") {
			Calendar cal = new GregorianCalendar();
			window.setEnabled(true);
			String fileName = "till_"+ cal.get(Calendar.YEAR) + "_"+ (cal.get(Calendar.MONTH) + 1) + "_" + cal.get(Calendar.DAY_OF_MONTH) + ".xml";
			File todayTill = new File(fileName);
			if(!todayTill.exists()) {
				till = new Till();
			} else {
				JOptionPane.showMessageDialog(window, "Day already exists. Loading todays till...");
				try {
					till = Till.load(todayTill);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
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
			String zindex = "Total for the day: £" + round(till.getTotalForDay())+"\n";
			zindex += "Number of orders " + till.getOrders().size();
			JOptionPane.showMessageDialog(window, zindex);
		}
	}
	
	/**
	 * checks if Current Order is set
	 *
	 * @return true, if successful
	 */
	public boolean currentOrderSet() {
		if(this.currentOrder == null) {
			return false;
		}
		return true;
	}
	
	/**
	 * Sets the current order.
	 *
	 * @param o the new current order
	 */
	public void setCurrentOrder(Order o) {
		this.currentOrder = o;
	}
	
	/**
	 * Sets the customer name.
	 *
	 * @param s the new customer name
	 */
	public void setCustomerName(String s) {
		this.currentOrder.setCustomerName(s);
	}
	
}
