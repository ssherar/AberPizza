package uk.ac.aber.dcs.aberpizza.test;

import static org.junit.Assert.*;
import uk.ac.aber.dcs.aberpizza.data.*;

import org.junit.Test;

public class TestPizza {
	
	@Test
	public void testGetPizzaName() {
		Pizza p = new Pizza("Meatfeast", 9.99, "Meatfeast");
		
		assertEquals("Pizza not called Meatfeast", "Meatfeast", p.getName());
	}
	
	@Test
	public void testSetDrinkName() {
		Beverage b = new Beverage("Coke", 0.7, "Coke");
		b.setName("Pepsi");
		
		assertEquals("Drink still called Coke", "Pepsi", b.getName());
	}

}
