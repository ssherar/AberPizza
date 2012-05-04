package uk.ac.aber.dcs.aberpizza.controller;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import uk.ac.aber.dcs.aberpizza.data.*;

/**
 * This class is used to load products.xml. It uses JAXB marsheller
 * with references to the classes.
 * @author Samuel B Sherar (sbs1)
 *
 */
public class XMLParser {
	
	/** The products. */
	private ArrayList<Product> products;
	
	/**
	 * Instantiates a new xML parser.
	 */
	public XMLParser() {
		
	}
	
	/**
	 * Saves product.xml
	 *
	 * @param products the products
	 */
	public void save(ArrayList<Product> products) {
		ProductList pl = new ProductList();
		pl.setProduct(products);
		
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(ProductList.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			jaxbMarshaller.marshal(pl, System.out);
			jaxbMarshaller.marshal(pl, new File("products.xml"));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Loads product.xml.
	 *
	 * @param fileName the file name (product.xml)
	 * @return the parent xml node as a class.
	 */
	public ProductList load(String fileName) {
		File file = new File(fileName);
		ProductList ret = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(ProductList.class);
			Unmarshaller unMarshaller = jaxbContext.createUnmarshaller();
			ret = (ProductList)unMarshaller.unmarshal(file);
		} catch(JAXBException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
}
