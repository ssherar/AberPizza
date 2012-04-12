package uk.ac.aber.dcs.aberpizza.data;

import java.util.Observable;
import uk.ac.aber.dcs.aberpizza.data.*;
import uk.ac.aber.dcs.aberpizza.controller.*;

import uk.ac.aber.dcs.aberpizza.controller.Manager;


public class ProductModel extends Observable {
	private Manager manager;
	private Product item;
	private Double runningPrice;
	
	public ProductModel(Manager m) {
		manager = m;
		this.addObserver(m);
	}
	
	public void addItem(Product p) {
		item = p;
		runningPrice = item.getPrice();
		this.setChanged();
		this.notifyObservers("priceChanged");
	}
	
	
	
}
