package uk.ac.aber.dcs.aberpizza.data;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class implements the Item interface, and adds all the functionality for setting
 * the data. Also it holds all the important annotations for the use of JAXB serialisation
 * @author Samuel B Sherar (sbs1)
 *
 */
@XmlRootElement
@XmlType(propOrder = { "name", "description", "price", "options" })
public abstract class Product implements Item {
	
	/** The price. */
	protected BigDecimal price;
	
	/** The name. */
	protected String name;
	
	/** The description. */
	protected String description;
	
	/** The options. */
	@XmlElementWrapper
	@XmlElement(name="option")
	protected ArrayList<Option> options = new ArrayList<Option>();
	
	/**
	 * Instantiates a new product. Used for XML serialisation
	 */
	public Product() {
		
	}
	
	/**
	 * Instantiates a new product.
	 *
	 * @param n the name
	 * @param p the price
	 * @param d the description
	 */
	public Product(String n, Double p, String d) {
		price = new BigDecimal(p);
		name = n;
		description = d;
	}
	
	
	/* (non-Javadoc)
	 * @see uk.ac.aber.dcs.aberpizza.data.Item#getPrice()
	 */
	@Override
	public BigDecimal getPrice() {
		return price;
	}
	
	/* (non-Javadoc)
	 * @see uk.ac.aber.dcs.aberpizza.data.Item#setPrice(java.math.BigDecimal)
	 */
	@XmlElement(type=BigDecimal.class)
	@Override
	public void setPrice(BigDecimal p) {
		price = p;
	}

	/* (non-Javadoc)
	 * @see uk.ac.aber.dcs.aberpizza.data.Item#getDescription()
	 */
	@Override
	public String getDescription() {
		return description;
	}
	
	/* (non-Javadoc)
	 * @see uk.ac.aber.dcs.aberpizza.data.Item#setDescription(java.lang.String)
	 */
	@XmlElement
	@Override
	public void setDescription(String d) {
		description = d;
	}

	/* (non-Javadoc)
	 * @see uk.ac.aber.dcs.aberpizza.data.Item#getName()
	 */
	@Override
	public String getName() {
		return name;
	}
	
	/* (non-Javadoc)
	 * @see uk.ac.aber.dcs.aberpizza.data.Item#setName(java.lang.String)
	 */
	@XmlElement
	@Override
	public void setName(String n) {
		name = n;
	}
	
	/**
	 * Adds the option.
	 *
	 * @param s the size
	 * @param p the price
	 */
	public void addOption(Sizes s, Double p) {
		options.add(new Option(s, p));
	}
	
	/**
	 * Gets the options.
	 *
	 * @return the options
	 */
	public ArrayList<Option> getOptions() {
		return options;
	}
	
	/**
	 * Checks if there is any options
	 *
	 * @return true, if successful
	 */
	public boolean hasOptions() {
		return (this.options.size() == 0) ? false : true;
	}
	
	/**
	 * Find option.
	 *
	 * @param name the name
	 * @return the option
	 */
	public Option findOption(String name) {
		for(Option o : options) {
			if(o.getSize().toString().equals(name)) {
				return o;
			}
		}
		return null;
	}
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public abstract String getType();
	
}
