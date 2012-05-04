package uk.ac.aber.dcs.aberpizza.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uk.ac.aber.dcs.aberpizza.data.*;

public class TestOrder {
	private Order o;
	private Pizza pizza;
	private Beverage drink;
	private Side side;
	
	@Before
	public void setup() {
		o = new Order("Barry");
		pizza = new Pizza("Margherita", 9.99, "Margherita");
		drink = new Beverage("Coke", 1.00, "Coke");
		side = new Side("Coleslaw", 1.29, "Coleslaw");
		o.addItem(new OrderItem(pizza));
		o.addItem(new OrderItem(side));
		o.addItem(new OrderItem(drink));
	}
	
	@Test
	public void testOrderSize() {
		assertEquals("Order no != 3", 3, o.getItems().size());
	}
	
	@Test
	public void testFindItem() {
		int index = o.findItem(new OrderItem(pizza));
		assertEquals("index != 0", 0, index);
	}
	
	@Test
	public void testRemovePizza() {
		o.decrement(new OrderItem(pizza));
		assertEquals("order no != 2", 2, o.getItems().size());
	}
	
	@Test
	public void testFindItemWithString() {
		int index = o.findItem(side.getName());
		assertEquals("Index != 2", 1, index);
	}
	
	
	@Test
	public void incrementPizza() {
		o.addItem(new OrderItem(pizza));
		assertEquals("quantity != 2", 2, o.getItems().get(0).getQuantity());
	}
	
	@Test
	public void testGetCustomerName() {
		assertEquals("customerName != Barry", "Barry", o.getCustomerName());
	}
	
	@Test
	public void testSetCustomerName() {
		o.setCustomerName("Trevor");
		assertEquals("customerName != Trevor", "Trevor", o.getCustomerName());
	}

}
