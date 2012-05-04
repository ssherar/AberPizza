package uk.ac.aber.dcs.aberpizza.data;

/**
 * This class extends from Product, where all the manipulation is done.
 * @author Samuel B Sherar (sbs1)
 *
 */
public class Beverage extends Product{

	/**
	 * Instantiates a new beverage.
	 *
	 * @param n the name of the product
	 * @param p the price
	 * @param d the description
	 */
	public Beverage(String n, Double p, String d) {
		super(n, p, d);
	}
	
	/**
	 * Instantiates a new beverage. Only used for XMLSerialisation
	 */
	public Beverage() {
		
	}

	/* (non-Javadoc)
	 * @see uk.ac.aber.dcs.aberpizza.data.Product#getType()
	 */
	@Override
	public String getType() {
		return Beverage.class.toString();
	}

}
