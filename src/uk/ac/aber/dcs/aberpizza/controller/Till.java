package uk.ac.aber.dcs.aberpizza.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import uk.ac.aber.dcs.aberpizza.data.*;

public class Till {
	private ArrayList<Order> orders;
	
	public Till() {
		orders = new ArrayList<Order>();
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
		
	}
	public static Till load(String fileName) throws IOException {
		return null;
	}
	
	
}
