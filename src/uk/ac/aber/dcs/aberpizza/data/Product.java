package uk.ac.aber.dcs.aberpizza.data;

public class Product implements Item {
	private Double price;
	private String name;
	private String description;
	
	public Product(String n, Double p, String d) {
		price = p;
		name = n;
		description = d;
	}
	
	@Override
	public Double getPrice() {
		// TODO Auto-generated method stub
		return price;
	}

	@Override
	public void setPrice(Double p) {
		// TODO Auto-generated method stub
		price = p;
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
	
}
