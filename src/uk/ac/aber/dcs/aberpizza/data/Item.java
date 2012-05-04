package uk.ac.aber.dcs.aberpizza.data;

import java.math.BigDecimal;

/**
 * Interface for which the products base themselves upon
 * @author Samuel B Sherar (sbs1)
 *
 */
public interface Item {
	
	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public BigDecimal getPrice();
	
	/**
	 * Sets the price.
	 *
	 * @param d the new price
	 */
	public void setPrice(BigDecimal d);
	
	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription();
	
	/**
	 * Sets the description.
	 *
	 * @param d the new description
	 */
	public void setDescription(String d);
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName();
	
	/**
	 * Sets the name.
	 *
	 * @param n the new name
	 */
	public void setName(String n);
}
