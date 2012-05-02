package uk.ac.aber.dcs.aberpizza.data;

public class Side extends Product {
	
	public Side() {
		
	}
	
	public Side(String n, Double p, String d) {
		super(n, p, d);
	}
	
	@Override
	public String getType() {
		return Side.class.toString();
	}

}
