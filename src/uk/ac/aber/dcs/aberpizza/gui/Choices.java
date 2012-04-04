package uk.ac.aber.dcs.aberpizza.gui;
import javax.swing.*;

import uk.ac.aber.dcs.aberpizza.data.Product;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class Choices extends JPanel {
	private ArrayList<Product> c;
	public Choices() {
		super(new GridLayout(0,4));
	}
	
	public void init(ArrayList<Product> choices) {
		System.out.println(choices);
		for(Product p : choices) {
			System.out.println(p.getName());
			this.add(new JButton(p.getName()));
		}
		this.doLayout();
	}
}


