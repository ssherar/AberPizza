package uk.ac.aber.dcs.aberpizza.data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import uk.ac.aber.dcs.aberpizza.controller.Manager;

public class Order {
	/**
	 * 
	 */
	private String customerName;
	private ArrayList<OrderItem> items;
	private BigDecimal discount = new BigDecimal(0);
	private Date date;
	
	public Order() {
		this.customerName = "null";
		items = new ArrayList<OrderItem>();
		discount = new BigDecimal(0);
		setDate(new Date());
	}
	
	public Order(String customer) {
		this.customerName = customer;
		items = new ArrayList<OrderItem>();
		discount = new BigDecimal(0);
		setDate(new Date());
	}
	
	/**
	 * 
	 * @return the customer name
	 */
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public ArrayList<OrderItem> getItems() {
		return items;
	}
	public void setItems(ArrayList<OrderItem> items) {
		this.items = items;
	}
	
	public void addItem(OrderItem item) {
		int i = findItem(item);
		if(i < 0) {
			items.add(item);
		} else {
			this.changeQuantity(i, 1);
		}
	}
	
	public void addOption(Option o, OrderItem item){
		int i = findItem(item);
		if(i > -1) {
			items.get(i).addOption(new OrderItemOption(o));
		}
	}
	
	public int findItem(OrderItem item) {
		int i = -1;
		for(OrderItem o : items) {
			i++;
			if(o.getItem().getName() == item.getItem().getName()) {
				return i;
			}
		}
		return -1;
	}
	
	
	
	public void changeQuantity(int index, int quantity) {
		items.get(index).setQuantity(items.get(index).getQuantity() + quantity);
	}
	
	public void decrement(OrderItem item) {
		int index = findItem(item);
		int quantity = items.get(index).getQuantity();
		
		if((quantity - 1) == 0) {
			items.remove(index);
		} else {
			changeQuantity(index, -1);
		}
	}
	
	public void decrement(String product) {
		int index = findItem(product);
		int quantity = items.get(index).getQuantity();
		
		if((quantity - 1) == 0) {
			items.remove(index);
		} else {
			changeQuantity(index, -1);
		}
	}
	
	@Override
	public String toString() {
		String ret = "";
		for(OrderItem i : items) {
			ret += i.getItem().getDescription() + "\t\t\t" + i.getQuantity()+"\n";
		}
		return ret;
	}
	
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

	public BigDecimal getDiscount() {
		// TODO Auto-generated method stub
		return discount;
	}

	public BigDecimal getTotal() {
		// TODO Auto-generated method stub
		return Manager.round(getSubtotal().subtract(discount));
	}

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
	
	public int findItem(String item) {
		int i = -1;
		for(OrderItem o : items) {
			i++;
			System.out.println(item + " " + o.getItem().getName());
			if (o.getItem().getName().equals(item)) {
				
				return i;
			}
		}
		return -1;
	}
	
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

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public void setDiscount(BigDecimal discountValue) {
		discount = discountValue;
	}
}
