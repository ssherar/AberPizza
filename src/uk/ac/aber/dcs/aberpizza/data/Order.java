package uk.ac.aber.dcs.aberpizza.data;

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
	
	
}
