package uk.ac.aber.dcs.aberpizza.data;

import java.util.ArrayList;

/**
 * A data class which holds the quantity and the item
 * @author Samuel B Sherar (sbs1)
 *
 */
public class OrderItem {
	
	/** The item. */
	private Item item;
	
	/** The quantity. */
	private int quantity;
	
	/** The options. */
	private ArrayList<OrderItemOption> options;
	
	/**
	 * Instantiates a new order item. Used for XML Serialisation
	 */
	public OrderItem() {}
	
	/**
	 * Instantiates a new order item with the default values.
	 *
	 * @param item the item
	 */
	public OrderItem(Item item) {
		this.item = item;
		this.quantity = 1;
		options = new ArrayList<OrderItemOption>();
	}
	
	/**
	 * Gets the item.
	 *
	 * @return the item
	 */
	public Item getItem() {
		return item;
	}
	
	/**
	 * Sets the item.
	 *
	 * @param item the new item
	 */
	public void setItem(Item item) {
		this.item = item;
	}
	
	/**
	 * Gets the quantity.
	 *
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	
	/**
	 * Sets the quantity.
	 *
	 * @param quantity the new quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * Gets the options.
	 *
	 * @return the options
	 */
	public ArrayList<OrderItemOption> getOptions() {
		return options;
	}

	/**
	 * Sets the options.
	 *
	 * @param options the new options
	 */
	public void setOptions(ArrayList<OrderItemOption> options) {
		this.options = options;
	}
	
	/**
	 * Adds an option to the product.
	 *
	 * @param o the o
	 */
	public void addOption(OrderItemOption o) {
		int i = findOption(o);
		if(i < 0) {
			options.add(o);
		} else {
			changeOptionQuantity(i, 1);
		}
	}
	
	/**
	 * Finds option.
	 *
	 * @param option the option
	 * @return the int
	 */
	public int findOption(OrderItemOption option) {
		int i = -1;
		for(OrderItemOption o : options) {
			i++;
			if(o.getOption().equals(option.getOption())) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Change option quantity.
	 *
	 * @param index the index
	 * @param quantity the quantity
	 */
	public void changeOptionQuantity(int index, int quantity) {
		options.get(index).setQuantity(options.get(index).getQuantity() + quantity);
	}
}
