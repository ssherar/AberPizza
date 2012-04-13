package uk.ac.aber.dcs.aberpizza.data;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Option {
	private Sizes size;
	private BigDecimal price;
	
	public Option() {
		
	}
	
	public Option(Sizes s, Double p) {
		this.size = s;
		this.price = new BigDecimal(p);
	}
	
	@XmlAttribute
	public Sizes getSize() {
		return size;
	}
	
	public void setSize(Sizes size) {
		this.size = size;
	}
	
	@XmlAttribute
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
