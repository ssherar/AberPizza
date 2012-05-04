package uk.ac.aber.dcs.aberpizza.data;

import java.beans.PersistenceDelegate;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JFileChooser;

import uk.ac.aber.dcs.aberpizza.data.*;

// TODO: Auto-generated Javadoc
/**
 * The Class Till.
 */
public class Till {
	
	/** The orders. */
	private ArrayList<Order> orders = new ArrayList<Order>();
	
	/**
	 * Instantiates a new till.
	 */
	public Till() {
		
	}
	
	/**
	 * Adds the order.
	 *
	 * @param o the o
	 */
	public void addOrder (Order o) {
		orders.add(o);
	}
	
	/**
	 * Gets the total for day.
	 *
	 * @return the total for day
	 */
	public BigDecimal getTotalForDay(){
		BigDecimal total = new BigDecimal(0);
		for(Order o : orders) {
			total = total.add(o.getSubtotal());
		}
		return total;
	}
	
	/**
	 * Save.
	 */
	public void save() {
		Calendar date = new GregorianCalendar();
		String fileName = "till_"+ date.get(Calendar.YEAR) + "_"+ (date.get(Calendar.MONTH) + 1) + "_" + date.get(Calendar.DAY_OF_MONTH) + ".xml";
		XMLEncoder encoder;
		try {
			encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(fileName)));
			PersistenceDelegate pd = encoder.getPersistenceDelegate(Double.class);
			encoder.setPersistenceDelegate(BigDecimal.class, pd);
			encoder.writeObject(this);
			encoder.flush();
		} catch(FileNotFoundException e) {
			
		} finally {
			encoder = null;
		}
	}
	
	/**
	 * Load.
	 *
	 * @return the till
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static Till load() throws IOException {
		JFileChooser jc = new JFileChooser();
		int retVal = jc.showOpenDialog(null);
		
		if(retVal == JFileChooser.APPROVE_OPTION) {
			return Till.load(jc.getSelectedFile());
		}
		
		return null;
	}
	
	/**
	 * Load.
	 *
	 * @param fileName the file name
	 * @return the till
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static Till load(File fileName) throws IOException {
		XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(fileName)));
		return (Till) decoder.readObject();
	}

	/**
	 * Gets the orders.
	 *
	 * @return the orders
	 */
	public ArrayList<Order> getOrders() {
		return orders;
	}

	/**
	 * Sets the orders.
	 *
	 * @param orders the new orders
	 */
	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}
	
	
}
