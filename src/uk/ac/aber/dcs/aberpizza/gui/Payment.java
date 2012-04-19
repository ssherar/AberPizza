package uk.ac.aber.dcs.aberpizza.gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import uk.ac.aber.dcs.aberpizza.controller.Manager;
import uk.ac.aber.dcs.aberpizza.controller.PaymentListener;

import java.awt.*;

public class Payment extends JPanel {
	private Manager manager;
	
	public Payment(Manager m) {
		super(new GridLayout(2, 4));
		manager = m;
		this.setPreferredSize(new Dimension(0,200));
		PaymentListener pl = new PaymentListener(manager); 
		
		String[] cashOptions = {"£5", "£10", "£20"};
		
		
		JButton cashPayments;
		JButton voidItem = new JButton("Void Item");
		voidItem.addActionListener(pl);
		JButton cancelOrder = new JButton("Cancel Order");
		cancelOrder.addActionListener(pl);
		JButton otherPayments = new JButton("Other Payments");
		otherPayments.addActionListener(pl);
		JButton cash = new JButton("Cash");
		cash.addActionListener(pl);
		
		for(String c : cashOptions)	{
			cashPayments = new JButton(c);
			cashPayments.addActionListener(pl);
			this.add(cashPayments);
		}
		
		this.add(new JLabel());
		this.add(voidItem);
		this.add(cancelOrder);
		this.add(otherPayments);
		this.add(cash);
		
	}
}
