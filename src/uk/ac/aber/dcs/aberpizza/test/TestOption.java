package uk.ac.aber.dcs.aberpizza.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import uk.ac.aber.dcs.aberpizza.data.Option;
import uk.ac.aber.dcs.aberpizza.data.Sizes;

public class TestOption {
	private Option o;
	
	@Before
	public void setup() {
		o = new Option(Sizes.LARGE, 0.5);
	}
	
	@Test
	public void testGetSize() {
		assertEquals("Option != Sizes.LARGE", Sizes.LARGE, o.getSize());
	}
	
	@Test
	public void testGetPrice() {
		assertEquals("Option != 0.5", new BigDecimal(0.5), o.getPrice());
	}
	
	@Test
	public void testSetSize() {
		o.setSize(Sizes.SMALL);
		assertEquals("Option != Sizes.SMALL", Sizes.SMALL, o.getSize());
	}
	
	@Test
	public void testSetPrice() {
		o.setPrice(new BigDecimal(0.10));
		assertEquals("Option != 0.10", new BigDecimal(0.10), o.getPrice());
	}

}
