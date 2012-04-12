package uk.ac.aber.dcs.aberpizza.data;

import java.math.BigDecimal;


public class Option {
	private Sizes size;
	private BigDecimal price;
	
	
	public Option(Sizes s, Double p) {
		this.size = s;
		this.price = new BigDecimal(p);
	}
	
	public Sizes getSize() {
		return size;
	}
	
	public void setSize(Sizes size) {
		this.size = size;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public String toString() {
		return "" + this.size + " : " + this.price;
	}
	
}
