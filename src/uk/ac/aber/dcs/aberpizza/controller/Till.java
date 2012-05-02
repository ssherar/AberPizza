package uk.ac.aber.dcs.aberpizza.controller;

import java.beans.PersistenceDelegate;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import uk.ac.aber.dcs.aberpizza.data.*;

public class Till {
	private ArrayList<Order> orders = new ArrayList<Order>();
	
	public Till() {
		
	}
	
	public void addOrder (Order o) {
		orders.add(o);
	}
	public BigDecimal getTotalForDay(){
		BigDecimal total = new BigDecimal(0);
		for(Order o : orders) {
			total = total.add(o.getSubtotal());
		}
		return total;
	}
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
	public static Till load(String fileName) throws IOException {
		return null;
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}
	
	
}
