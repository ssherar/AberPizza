package uk.ac.aber.dcs.aberpizza.data;

/**
 * The holder class for the options in the OrderItem class. It holds the Option class
 * as well as the quantity
 * @author Samuel B Sherar (sbs1)
 *
 */
public class OrderItemOption {
	
	/** The option. */
	private Option option;
	
	/** The quantity. */
	private int quantity;
	
	/**
	 * Instantiates a new order item option. Used for XML Serialisation
	 */
	public OrderItemOption() {}
	
	/**
	 * Instantiates a new order item option.
	 *
	 * @param o the option
	 */
	public OrderItemOption(Option o) {
		option = o;
		quantity = 1;
	}

	/**
	 * Gets the option.
	 *
	 * @return the option
	 */
	public Option getOption() {
		return option;
	}

	/**
	 * Sets the option.
	 *
	 * @param option the new option
	 */
	public void setOption(Option option) {
		this.option = option;
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
}
