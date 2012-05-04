package uk.ac.aber.dcs.aberpizza.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

/**
 * The Payment listener handles the events thrown by the PaymentPanel, and sends all the commands
 * to the observer (which is in this case the Manager
 * @author Samuel B Sherar (sbs1)
 * @see uk.ac.aber.dcs.aberpizza.controller.Manager.java#update(o, arg)
 *
 */
public class PaymentListener extends Observable implements ActionListener {
	
	/** The manager. */
	private Manager manager;
	
	/**
	 * Instantiates a new payment listener with reference to the manager
	 * and adds a new observer
	 *
	 * @param m the m
	 */
	public PaymentListener(Manager m) {
		manager = m;
		this.addObserver(manager);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.equalsIgnoreCase("cash")) {
			this.setChanged();
			this.notifyObservers("cashedOff");
		} else if(cmd.equalsIgnoreCase("cancel order")) {
			this.setChanged();
			this.notifyObservers("cancelOrder");
		} else if(cmd.equalsIgnoreCase("void item")) {
			this.setChanged();
			this.notifyObservers("itemVoid");
		}else if(cmd.equalsIgnoreCase("card")) {
			this.setChanged();
			this.notifyObservers("paymentCard");
		}
	}

}
