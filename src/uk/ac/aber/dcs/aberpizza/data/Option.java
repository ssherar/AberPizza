package uk.ac.aber.dcs.aberpizza.data;

public class Option {
	private Sizes size;
	private Double price;
	
	
	public Option(Sizes s, Double p) {
		this.size = s;
		this.price = p;
	}
	
	public Sizes getSize() {
		return size;
	}
	
	public void setSize(Sizes size) {
		this.size = size;
	}
	
	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public String toString() {
		return "" + this.size + " : " + this.price;
	}
	
}
