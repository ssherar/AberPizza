package uk.ac.aber.dcs.aberpizza.data;
import java.util.ArrayList;

import javax.xml.bind.annotation.*;


// TODO: Auto-generated Javadoc
/**
 * The Class ProductList.
 */
@XmlRootElement
public class ProductList {
	
	/** The product. */
	@XmlElements({
		@XmlElement(name="pizza", type=Pizza.class),
		@XmlElement(name="drink", type=Beverage.class),
		@XmlElement(name="side", type=Side.class)
	})
	private ArrayList<Product> product;
	
	/**
	 * Instantiates a new product list.
	 */
	public ProductList() {
		
	}
	
	/**
	 * Sets the product.
	 *
	 * @param p the new product
	 */
	public void setProduct(ArrayList<Product> p) {
		product = p;
	}
	
	/**
	 * Gets the products.
	 *
	 * @return the products
	 */
	public ArrayList<Product> getProducts() {
		return product;
	}
	
}
