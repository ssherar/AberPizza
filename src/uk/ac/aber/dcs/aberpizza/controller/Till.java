package uk.ac.aber.dcs.aberpizza.controller;

import java.beans.PersistenceDelegate;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
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
	public static Till load() throws IOException {
		JFileChooser jc = new JFileChooser();
		int retVal = jc.showOpenDialog(null);
		
		if(retVal == JFileChooser.APPROVE_OPTION) {
			XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(jc.getSelectedFile())));
			return (Till) decoder.readObject();
		}
		
		return null;
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}
	
	
}
