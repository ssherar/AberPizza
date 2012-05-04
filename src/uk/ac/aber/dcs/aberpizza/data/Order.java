package uk.ac.aber.dcs.aberpizza.data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import uk.ac.aber.dcs.aberpizza.controller.Manager;

// TODO: Auto-generated Javadoc
/**
 * The Class Order. This holds all the data for the Order,
 * including Customer's name, date and items
 */
public class Order {
	
	/** The customer name. */
	private String customerName;
	
	/** The items. */
	private ArrayList<OrderItem> items;
	
	/** The discount applied. */
	private BigDecimal discount = new BigDecimal(0);
	
	/** The date. */
	private Date date;
	
	/**
	 * Instantiates a new order with default values
	 */
	public Order() {
		this.customerName = "null";
		items = new ArrayList<OrderItem>();
		discount = new BigDecimal(0);
		setDate(new Date());
	}
	
	/**
	 * Instantiates a new order.
	 *
	 * @param customer the customer name
	 */
	public Order(String customer) {
		this.customerName = customer;
		items = new ArrayList<OrderItem>();
		discount = new BigDecimal(0);
		setDate(new Date());
	}
	
	/**
	 * Gets the customer name.
	 *
	 * @return the customer name
	 */
	public String getCustomerName() {
		return customerName;
	}
	
	/**
	 * Sets the customer name.
	 *
	 * @param customerName the new customer name
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	/**
	 * Gets the items.
	 *
	 * @return the items
	 */
	public ArrayList<OrderItem> getItems() {
		return items;
	}
	
	/**
	 * Sets the items.
	 *
	 * @param items the new items
	 */
	public void setItems(ArrayList<OrderItem> items) {
		this.items = items;
	}
	
	/**
	 * Adds an item to the order.
	 *
	 * @param item the item
	 */
	public void addItem(OrderItem item) {
		int i = findItem(item);
		if(i < 0) {
			items.add(item);
		} else {
			this.changeQuantity(i, 1);
		}
	}
	
	/**
	 * Adds the option to a specific item
	 *
	 * @param o the option to add
	 * @param item the item to add to
	 */
	public void addOption(Option o, OrderItem item){
		int i = findItem(item);
		if(i > -1) {
			items.get(i).addOption(new OrderItemOption(o));
		}
	}
	
	/**
	 * Finds an item.
	 *
	 * @param item the item
	 * @return the index in the ArrayList
	 */
	public int findItem(OrderItem item) {
		int i = -1;
		for(OrderItem o : items) {
			i++;
			if(o.getItem().getName().equals(item.getItem().getName())) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Find item.
	 *
	 * @param item the item's name
	 * @return the index in the ArrayList
	 */
	public int findItem(String item) {
		int i = -1;
		for(OrderItem o : items) {
			i++;
			if (o.getItem().getName().equals(item)) {
				
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Find option on a speciffic item.
	 *
	 * @param product the product index in the ArrayList
	 * @param option the option's name
	 * @return the index in the options ArrayList
	 */
	public int findOption(int product, String option) {
		int i = -1;
		for(OrderItemOption oio : items.get(product).getOptions()) {
			i++;
			if(oio.getOption().getSize().toString().equals(option)) {
				return i;
			}
		}
		return -1;
	}
	
	
	/**
	 * Change quantity of an item.
	 *
	 * @param index the index
	 * @param quantity the quantity
	 */
	public void changeQuantity(int index, int quantity) {
		items.get(index).setQuantity(items.get(index).getQuantity() + quantity);
	}
	
	/**
	 * Decrements the item. If there is only 1 left, we remove it
	 *
	 * @param item the item
	 */
	public void decrement(OrderItem item) {
		int index = findItem(item);
		int quantity = items.get(index).getQuantity();
		
		if((quantity - 1) == 0) {
			items.remove(index);
		} else {
			changeQuantity(index, -1);
		}
	}
	
	/**
	 * Decrements the item. If there is only 1 left, we remove it
	 *
	 * @param product the product name
	 */
	public void decrement(String product) {
		int index = findItem(product);
		int quantity = items.get(index).getQuantity();
		
		if((quantity - 1) == 0) {
			items.remove(index);
		} else {
			changeQuantity(index, -1);
		}
	}
	
	/**
	 * Decrements the option.
	 *
	 * @param pName the name of the product
	 * @param oName the name of the option
	 */
	public void decrementOption(String pName, String oName) {
		int pIndex = findItem(pName);
		int oIndex = findOption(pIndex, oName);
		int quantity = items.get(pIndex).getOptions().get(oIndex).getQuantity();
		
		if(quantity == 1) {
			items.get(pIndex).getOptions().remove(oIndex);
			int pQuantity = items.get(pIndex).getQuantity();
			if(pQuantity == 1) {
				items.remove(pIndex);
			} else {
				items.get(pIndex).setQuantity(pQuantity);
			}
		} else {
			quantity -= 1;
			items.get(pIndex).getOptions().get(oIndex).setQuantity(quantity);
			items.get(pIndex).setQuantity(items.get(pIndex).getQuantity() - 1);
		}
	}
	
	/**
	 * Decrements the side.
	 *
	 * @param sName the side's name
	 */
	public void decrementSide(String sName) {
		int pIndex = findItem(sName);
		int quantity = items.get(pIndex).getQuantity();
		System.out.println(items.get(pIndex));
		if(quantity == 1) {
			items.remove(pIndex);
			
		} else {
			quantity -= 1;
			items.get(pIndex).setQuantity(quantity);
		}
	
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String ret = "";
		for(OrderItem i : items) {
			ret += i.getItem().getDescription() + "\t\t\t" + i.getQuantity()+"\n";
		}
		
		return ret;
	}
	
	/**
	 * Gets the subtotal.
	 *
	 * @return the subtotal
	 */
	public BigDecimal getSubtotal() {
		BigDecimal total = new BigDecimal(0.00);
		for(OrderItem oi : items) {
			total = total.add(oi.getItem().getPrice().multiply(new BigDecimal(oi.getQuantity())));
			for(OrderItemOption oio : oi.getOptions()) {
				total = total.add(oio.getOption().getPrice().multiply(new BigDecimal(oio.getQuantity())));
			}
		}
		return total;
	}

	/**
	 * Gets the discount.
	 *
	 * @return the discount
	 */
	public BigDecimal getDiscount() {
		// TODO Auto-generated method stub
		return discount;
	}

	/**
	 * Gets the total.
	 *
	 * @return the total
	 */
	public BigDecimal getTotal() {
		// TODO Auto-generated method stub
		return Manager.round(getSubtotal().subtract(discount));
	}	

	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Sets the discount.
	 *
	 * @param discountValue the new discount
	 */
	public void setDiscount(BigDecimal discountValue) {
		discount = discountValue;
	}
}
