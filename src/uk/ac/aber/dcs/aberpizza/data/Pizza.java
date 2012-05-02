package uk.ac.aber.dcs.aberpizza.data;

import java.math.BigDecimal;


public class Pizza extends Product {

	public Pizza(String n, Double p, String d) {
		super(n, p, d);
		addOption(Sizes.SMALL, 0.00);
		addOption(Sizes.MEDIUM, 2.50);
		addOption(Sizes.LARGE, 5.00);
	}
	
	public Pizza() {
		
	}

	@Override
	public String getType() {
		return Pizza.class.toString();
	}

}
