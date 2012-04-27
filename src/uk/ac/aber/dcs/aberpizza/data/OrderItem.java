package uk.ac.aber.dcs.aberpizza.data;

public class OrderItem {
	private Item item;
	private int quantity;
	
	public OrderItem(Item item) {
		this.item = item;
		this.quantity = 1;
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
}
