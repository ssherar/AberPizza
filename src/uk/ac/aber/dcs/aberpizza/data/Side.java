package uk.ac.aber.dcs.aberpizza.data;

// TODO: Auto-generated Javadoc
/**
 * This class extends from Product, where all the manipulation is done.
 * @author Samuel B Sherar (sbs1)
 *
 */
public class Side extends Product {
	
	/**
	 * Instantiates a new side. Used for XML Serialisation
	 */
	public Side() {
		
	}
	
	/**
	 * Instantiates a new side.
	 *
	 * @param n the name
	 * @param p the price
	 * @param d the description
	 */
	public Side(String n, Double p, String d) {
		super(n, p, d);
	}
	
	/* (non-Javadoc)
	 * @see uk.ac.aber.dcs.aberpizza.data.Product#getType()
	 */
	@Override
	public String getType() {
		return Side.class.toString();
	}

}
