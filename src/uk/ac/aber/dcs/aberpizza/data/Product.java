package uk.ac.aber.dcs.aberpizza.data;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = { "name", "description", "price", "options" })
public abstract class Product implements Item {
	private BigDecimal price;
	private String name;
	private String description;
	@XmlElementWrapper
	@XmlElement(name="option")
	private ArrayList<Option> options = new ArrayList<Option>();
	
	public Product() {
		
	}
	
	public Product(String n, Double p, String d) {
		price = new BigDecimal(p);
		name = n;
		description = d;
	}
	
	@XmlElement
	@Override
	public BigDecimal getPrice() {
		return price;
	}

	@Override
	public void setPrice(Double p) {
		price =  new BigDecimal(p);
	}

	@Override
	public String getDescription() {
		return description;
	}
	
	@XmlElement
	@Override
	public void setDescription(String d) {
		description = d;
	}

	@Override
	public String getName() {
		return name;
	}
	
	@XmlElement
	@Override
	public void setName(String n) {
		name = n;
	}
	
	public void addOption(Sizes s, Double p) {
		options.add(new Option(s, p));
	}
	
	public ArrayList<Option> getOptions() {
		return options;
	}
	
	public boolean hasOptions() {
		return (this.options.size() == 0) ? false : true;
	}
	
	public Option findOption(String name) {
		for(Option o : options) {
			if(o.getSize().toString().equals(name)) {
				return o;
			}
		}
		return null;
	}
	
}
