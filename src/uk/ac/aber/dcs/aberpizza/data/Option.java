package uk.ac.aber.dcs.aberpizza.data;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The option class to store the Size of the product and the additional price
 * @author Samuel B Sherar (sbs1)
 *
 */
@XmlRootElement
public class Option {
	
	/** The size. */
	private Sizes size;
	
	/** The price. */
	private BigDecimal price;
	
	/**
	 * Instantiates a new option. Used for XMLSerialisation
	 */
	public Option() {
		
	}
	
	/**
	 * Instantiates a new option.
	 *
	 * @param s the size
	 * @param p the price
	 */
	public Option(Sizes s, Double p) {
		this.size = s;
		this.price = new BigDecimal(p);
	}
	
	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	@XmlAttribute
	public Sizes getSize() {
		return size;
	}
	
	/**
	 * Sets the size.
	 *
	 * @param size the new size
	 */
	public void setSize(Sizes size) {
		this.size = size;
	}
	
	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	@XmlAttribute
	public BigDecimal getPrice() {
		return price;
	}
	
	/**
	 * Sets the price.
	 *
	 * @param price the new price
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "" + this.size + " : " + this.price;
	}
	
}
