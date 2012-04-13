package uk.ac.aber.dcs.aberpizza.data;

import java.io.File;
import java.util.ArrayList;

import javax.xml.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

public class XMLParser {
	private ArrayList<Product> products;
	
	public XMLParser() {
		
	}
	
	public void save(ArrayList<Product> products) {

	}
	
	public void load() {
		try {
			File file = new File("products.xml");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			
			NodeList nodeLst = doc.getElementsByTagName("");

        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
}
