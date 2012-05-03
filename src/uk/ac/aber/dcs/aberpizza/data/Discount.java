package uk.ac.aber.dcs.aberpizza.data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import apple.laf.JRSUIConstants.Size;

public class Discount {
	private Hashtable<Sizes, Integer> sizeDiscount;
	private Hashtable<BigDecimal, Integer> amountDiscount;
	
	public Discount() {
		sizeDiscount = new Hashtable<Sizes, Integer>();
		amountDiscount = new Hashtable<BigDecimal, Integer>();
	}
	
	public void createDiscount(Sizes size, int quantity) {
		sizeDiscount.put(size, quantity);
		System.out.println(sizeDiscount.get(size));
	}
	
	public void createDiscount(BigDecimal total, int percent) {
		amountDiscount.put(total, percent);
	}
	
	public BigDecimal getDiscount(Order o) {
		BigDecimal sizeDiscountPrice = calcSize(o);
		BigDecimal percentDiscountPrice = calcPercent(o);
		System.out.println(sizeDiscountPrice);
		System.out.println(percentDiscountPrice);
		return (sizeDiscountPrice.compareTo(percentDiscountPrice) > 0) ? sizeDiscountPrice : percentDiscountPrice;
	}
	
	private BigDecimal calcSize(Order o) {
		BigDecimal total = new BigDecimal(0.00);
		BigDecimal lowestPrice = new BigDecimal(0.00);
		int smallQuantity = 0;
		int medQuantity = 0;
		int largeQuantity = 0;
		ArrayList<OrderItem> orderItem = o.getItems();
		for(OrderItem oi : orderItem) {
			Item item = oi.getItem();
			for(OrderItemOption oio : oi.getOptions()) {
				BigDecimal itemPrice = item.getPrice();
				Option option = oio.getOption();
				if(option.getSize() == Sizes.SMALL) {
					smallQuantity = oio.getQuantity();
				} else if(option.getSize() == Sizes.MEDIUM) {
					medQuantity = oio.getQuantity();
				} else if(option.getSize() == Sizes.LARGE) {
					largeQuantity = oio.getQuantity();
				}

				itemPrice = item.getPrice().add(option.getPrice());

				if(sizeDiscount.get(Sizes.LARGE) != null && sizeDiscount.get(Sizes.LARGE) <= largeQuantity) {
					if(itemPrice.compareTo(lowestPrice) > 0) {
						lowestPrice = itemPrice;
					}
				} else if(sizeDiscount.get(Sizes.MEDIUM) != null && sizeDiscount.get(Sizes.MEDIUM) <= medQuantity) {
					if(itemPrice.compareTo(lowestPrice) > 0) {
						lowestPrice = itemPrice;
					}
				} else if(sizeDiscount.get(Sizes.SMALL) != null && sizeDiscount.get(Sizes.SMALL) <= smallQuantity) {
					if(itemPrice.compareTo(lowestPrice) > 0) {
						lowestPrice = itemPrice;
					}
				}
			}
			return lowestPrice;
		}
		return null;
	}
	
	private BigDecimal calcPercent(Order o) {
		BigDecimal discountAmount = new BigDecimal(0);
		BigDecimal orderTotal = o.getTotal();
		double percent = 0;
		Set set = amountDiscount.entrySet();
	    Iterator it = set.iterator();
	    
	    while(it.hasNext()) {
	    	Map.Entry entry = (Map.Entry) it.next();
	    	if(orderTotal.compareTo((BigDecimal) entry.getKey()) > 0) {
	    		percent = (Integer) entry.getValue();
	    	}
	    }

	    if(percent > 0) {
	    	discountAmount = orderTotal.multiply(new BigDecimal(percent/100));
	    }
		
		return discountAmount;
	}
}