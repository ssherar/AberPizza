package uk.ac.aber.dcs.aberpizza.data;
import java.util.ArrayList;

import javax.xml.bind.annotation.*;


@XmlRootElement
public class ProductList {
	@XmlElements({
		@XmlElement(name="pizza", type=Pizza.class),
		@XmlElement(name="drink", type=Beverage.class)
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
	
	public void echoTabs(int i) {
		for(int j = 0; j < i; j++) {
			System.out.print("\t");
		}
	}
}
