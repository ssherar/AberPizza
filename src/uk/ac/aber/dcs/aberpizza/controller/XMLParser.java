package uk.ac.aber.dcs.aberpizza.controller;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import uk.ac.aber.dcs.aberpizza.data.*;

public class XMLParser {
	private ArrayList<Product> products;
	
	public XMLParser() {
		
	}
	
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
