package uk.ac.aber.dcs.aberpizza.data;

import java.math.BigDecimal;
import java.util.Observable;
import uk.ac.aber.dcs.aberpizza.data.*;
import uk.ac.aber.dcs.aberpizza.controller.*;

import uk.ac.aber.dcs.aberpizza.controller.Manager;


/**
 * This class handles all the data before sending it off to the observer
 * It doesn't change the data, but adds validation methods
 * @author Samuel B Sherar (sbs1)
 *
 */
public class ProductModel extends Observable {
	
	/** The manager. */
	private Manager manager;
	
	/** The option. */
	private Option option;
	
	/** The item. */
	private Product item;
	
	/** The running price. */
	private BigDecimal runningPrice;
	
	/**
	 * Instantiates a new product model.
	 *
	 * @param m the observer
	 */
	public ProductModel(Manager m) {
		manager = m;
		this.addObserver(manager);
		runningPrice = new BigDecimal(0.00);
	}
	
	/**
	 * Adds the item.
	 *
	 * @param p the product
	 */
	public void addItem(Product p) {
		item = p;
		runningPrice = item.getPrice();
		this.setChanged();
		this.notifyObservers("priceIncreased");
	}
	
	/**
	 * Adds the option.
	 *
	 * @param o the option
	 */
	public void addOption(Option o) {
		option = o;
		runningPrice = runningPrice.add(option.getPrice());
		this.setChanged();
		this.notifyObservers("optionAdded");
	}
	
	/**
	 * Cancel an item.
	 */
	public void cancelItem() {
		this.setChanged();
		this.notifyObservers("priceCancelled");
	}
	
	/**
	 * Gets the running price.
	 *
	 * @return the running price
	 */
	public BigDecimal getRunningPrice() {
		return runningPrice;
	}
	
	/**
	 * Gets the product.
	 *
	 * @return the product
	 */
	public Product getProduct() {
		return item;
	}
	
	/**
	 * Gets the option.
	 *
	 * @return the option
	 */
	public Option getOption() {
		return option;
	}
}
