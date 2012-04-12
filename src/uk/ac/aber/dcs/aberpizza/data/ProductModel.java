package uk.ac.aber.dcs.aberpizza.data;

import java.math.BigDecimal;
import java.util.Observable;
import uk.ac.aber.dcs.aberpizza.data.*;
import uk.ac.aber.dcs.aberpizza.controller.*;

import uk.ac.aber.dcs.aberpizza.controller.Manager;


public class ProductModel extends Observable {
	private Manager manager;
	private Option option;
	private Product item;
	private BigDecimal runningPrice;
	
	public ProductModel(Manager m) {
		manager = m;
		this.addObserver(manager);
		runningPrice = new BigDecimal(0.00);
	}
	
	public void addItem(Product p) {
		item = p;
		runningPrice = item.getPrice();
		this.setChanged();
		this.notifyObservers("priceIncreased");
	}
	
	public void addOption(Option o) {
		option = o;
		runningPrice = runningPrice.add(option.getPrice());
		this.setChanged();
		this.notifyObservers("optionAdded");
	}
	
	public void cancelItem() {
		this.setChanged();
		this.notifyObservers("priceCancelled");
	}
	
	public BigDecimal getRunningPrice() {
		return runningPrice;
	}
	
	public Product getProduct() {
		return item;
	}
	
	public Option getOption() {
		return option;
	}
}
