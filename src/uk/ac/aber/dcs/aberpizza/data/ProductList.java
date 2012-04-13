package uk.ac.aber.dcs.aberpizza.data;
import java.util.ArrayList;

import javax.xml.bind.annotation.*;


@XmlRootElement
public class ProductList {
	@XmlElement(name="product")
	private ArrayList<Product> product;
	
	public ProductList() {
		
	}
	
	public void setProduct(ArrayList<Product> p) {
		product = p;
	}
	
	public ArrayList<Product> getProducts() {
		return product;
	}
	
	public void echoProduct() {
		int indent = 0;
		for(Product p : product) {
			System.out.println("Product Start");
			indent++;
			echoTabs(indent);
			System.out.println(p.getName());
			echoTabs(indent);
			System.out.println(p.getDescription());
			echoTabs(indent);
			System.out.println(p.getPrice());
			
			ArrayList<Option> op = p.getOptions();
			if(op.size() > 0) {
				echoTabs(indent);
				System.out.println("Options Start");
				for(Option o : op) {
					indent++;
					echoTabs(indent);
					System.out.println(o.getSize());
					echoTabs(indent);
					System.out.println(o.getPrice());
					indent--;
				}
				echoTabs(indent);
				System.out.println("Options Finished");
				indent--;
				System.out.println("Product Finished");
			}
			
		}
	}
	
	public void echoTabs(int i) {
		for(int j = 0; j < i; j++) {
			System.out.print("\t");
		}
	}
}
