package uk.ac.aber.dcs.aberpizza.data;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Order {
	private String customerName;
	private ArrayList<OrderItem> items;
	
	public Order() {
		this.customerName = "null";
		items = new ArrayList<OrderItem>();
	}
	
	public Order(String customer) {
		this.customerName = customer;
		items = new ArrayList<OrderItem>();
	}
	
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
			if(o.getItem().equals(item.getItem())) {
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
	
}