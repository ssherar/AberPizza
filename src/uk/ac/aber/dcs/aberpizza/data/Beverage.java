package uk.ac.aber.dcs.aberpizza.data;

public class Beverage extends Product{

	public Beverage(String n, Double p, String d) {
		super(n, p, d);
	}
	
	public Beverage() {
		
	}

	@Override
	public String getType() {
		return Beverage.class.toString();
	}

}
