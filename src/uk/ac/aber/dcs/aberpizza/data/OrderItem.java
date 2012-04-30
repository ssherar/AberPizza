package uk.ac.aber.dcs.aberpizza.data;

import java.util.ArrayList;

public class OrderItem {
	private Item item;
	private int quantity;
	private ArrayList<OrderItemOption> options;
	
	public OrderItem() {}
	
	public OrderItem(Item item) {
		this.item = item;
		this.quantity = 1;
		options = new ArrayList<OrderItemOption>();
	}
	
	public Item getItem() {
		return item;
	}
	
	public void setItem(Item item) {
		this.item = item;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public ArrayList<OrderItemOption> getOptions() {
		return options;
	}

	public void setOptions(ArrayList<OrderItemOption> options) {
		this.options = options;
	}
	
	public void addOption(OrderItemOption o) {
		int i = findOption(o);
		System.out.println("asd");
		if(i < 0) {
			options.add(o);
		} else {
			changeOptionQuantity(i, 1);
		}
	}
	
	public int findOption(OrderItemOption option) {
		int i = -1;
		for(OrderItemOption o : options) {
			i++;
			if(o.getOption().equals(option.getOption())) {
				return i;
			}
		}
		return -1;
	}
	
	public void changeOptionQuantity(int index, int quantity) {
		options.get(index).setQuantity(options.get(index).getQuantity() + quantity);
		System.out.println(options.get(index).getQuantity());
	}
}
