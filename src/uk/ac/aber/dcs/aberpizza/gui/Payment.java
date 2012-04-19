package uk.ac.aber.dcs.aberpizza.gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;

public class Payment extends JPanel {
	public Payment() {
		super(new GridLayout(2, 4));
		this.setPreferredSize(new Dimension(0,200));
		
		String[] cashOptions = {"£5", "£10", "£20"};
		
		
		JButton cashPayments;
		JButton voidItem = new JButton("Void Item");
		JButton cancelOrder = new JButton("Cancel Order");
		JButton otherPayments = new JButton("Other Payments");
		JButton cash = new JButton("Cash");
		
		for(String c : cashOptions)	{
			cashPayments = new JButton(c);
			this.add(cashPayments);
		}
		
		this.add(new JLabel());
		this.add(voidItem);
		this.add(cancelOrder);
		this.add(otherPayments);
		this.add(cash);
		
	}
}
