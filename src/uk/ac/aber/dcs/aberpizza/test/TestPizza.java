package uk.ac.aber.dcs.aberpizza.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import uk.ac.aber.dcs.aberpizza.controller.Manager;
import uk.ac.aber.dcs.aberpizza.data.*;

import org.junit.Before;
import org.junit.Test;

public class TestPizza {
	
	private Pizza p;
	
	@Before
	public void setup() {
		p = new Pizza("Meatfeast", 9.99, "Meatfeast");
		p.addOption(Sizes.LARGE, 0.01);
	}
	
	@Test
	public void testGetPizzaName() {		
		assertEquals("Pizza not called Meatfeast", "Meatfeast", p.getName());
	}
	
	@Test
	public void testGetPizzaPrice() {
		assertEquals("Pizza price isn't 9.99", new BigDecimal(9.99), p.getPrice());
	}
	
	@Test
	public void testSetPizzaName() {
		p.setName("Texas BBQ");
		assertEquals("Pizza not called Texas BBQ", "Texas BBQ", p.getName());
	}
	
	@Test
	public void testSetPizzaPrice() {
		p.setPrice(new BigDecimal(19.99));
		assertEquals("Pizza price not 19.99", new BigDecimal(19.99), p.getPrice());
	}
	
	@Test
	public void testGetPizzaSubtotal() {
		BigDecimal totalPrice = p.getPrice().add(p.getOptions().get(0).getPrice());
		assertEquals("Total price with a large option isn't £10", new BigDecimal(10.00), totalPrice.setScale(0, BigDecimal.ROUND_HALF_EVEN));
	}
	
	@Test
	public void testFindOption() {
		assertEquals("Option not found", Sizes.LARGE, p.findOption(""+Sizes.LARGE).getSize() );
	}


}
