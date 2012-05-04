package uk.ac.aber.dcs.aberpizza.data;

/**
 * This class extends from Product, where all the manipulation is done.
 * @author Samuel B Sherar (sbs1)
 *
 */
public class Pizza extends Product {

	/**
	 * Instantiates a new pizza.
	 *
	 * @param n the n
	 * @param p the p
	 * @param d the d
	 */
	public Pizza(String n, Double p, String d) {
		super(n, p, d);
	}
	
	/**
	 * Instantiates a new pizza. Used for XML Serialisation
	 */
	public Pizza() {
		
	}

	/* (non-Javadoc)
	 * @see uk.ac.aber.dcs.aberpizza.data.Product#getType()
	 */
	@Override
	public String getType() {
		return Pizza.class.toString();
	}

}
