package uk.ac.aber.dcs.aberpizza.data;

import java.math.BigDecimal;
import java.util.ArrayList;

public abstract class Product implements Item {
	private BigDecimal price;
	private String name;
	private String description;
	private ArrayList<Option> options = new ArrayList<Option>();
	
	
	public Product(String n, Double p, String d) {
		price = new BigDecimal(p);
		name = n;
		description = d;
	}
	
	@Override
	public BigDecimal getPrice() {
		// TODO Auto-generated method stub
		return price;
	}

	@Override
	public void setPrice(Double p) {
		// TODO Auto-generated method stub
		price =  new BigDecimal(p);
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return description;
	}

	@Override
	public void setDescription(String d) {
		// TODO Auto-generated method stub
		description = d;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public void setName(String n) {
		// TODO Auto-generated method stub
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
