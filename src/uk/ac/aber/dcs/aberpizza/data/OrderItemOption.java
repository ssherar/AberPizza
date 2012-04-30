package uk.ac.aber.dcs.aberpizza.data;

public class OrderItemOption {
	private Option option;
	private int quantity;
	
	public OrderItemOption() {}
	
	public OrderItemOption(Option o) {
		option = o;
		quantity = 1;
	}

	public Option getOption() {
		return option;
	}

	public void setOption(Option option) {
		this.option = option;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
