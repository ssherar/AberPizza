package uk.ac.aber.dcs.aberpizza.data;
import java.util.ArrayList;

import javax.xml.bind.annotation.*;


@XmlRootElement
public class ProductList {
	@XmlElements({
		@XmlElement(name="pizza", type=Pizza.class),
		@XmlElement(name="drink", type=Beverage.class),
		@XmlElement(name="side", type=Side.class)
	})
	private ArrayList<Product> product;
	
	public ProductList() {
		
	}
	
	public void setProduct(ArrayList<Product> p) {
		product = p;
	}
	
	public ArrayList<Product> getProducts() {
		return product;
	}
	
}
