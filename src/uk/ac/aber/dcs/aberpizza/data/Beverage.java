package uk.ac.aber.dcs.aberpizza.data;

public class Beverage extends Product{

	public Beverage(String n, Double p, String d) {
		super(n, p, d);
		addOption(Sizes.CAN, 0.00);
		addOption(Sizes.SMALL_BOTTLE, 0.60);
		addOption(Sizes.LARGE_BOTTLE, 1.30);
	}
	
	public Beverage() {
		
	}

	@Override
	public String getType() {
		return Beverage.class.toString();
	}

}
