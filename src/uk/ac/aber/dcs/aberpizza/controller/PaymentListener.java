package uk.ac.aber.dcs.aberpizza.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

public class PaymentListener extends Observable implements ActionListener {
	
	private Till manager;
	
	public PaymentListener(Till m) {
		manager = m;
		this.addObserver(manager);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.equalsIgnoreCase("cash")) {
			this.setChanged();
			this.notifyObservers("cashedOff");
		} else if(cmd.equalsIgnoreCase("cancel order")) {
			this.setChanged();
			this.notifyObservers("cancelOrder");
		}
	}

}
