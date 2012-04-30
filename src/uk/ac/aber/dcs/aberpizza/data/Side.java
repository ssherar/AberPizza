package uk.ac.aber.dcs.aberpizza.data;

public class Side extends Product {
	
	public Side() {
		
	}
	
	public Side(String n, Double p, String d) {
		super(n, p, d);
		addOption(Sizes.SMALL, 0.00);
		addOption(Sizes.MEDIUM, 2.50);
		addOption(Sizes.LARGE, 5.00);
	}
	
	@Override
	public String getType() {
		return Side.class.toString();
	}

}
