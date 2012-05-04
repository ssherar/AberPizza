package uk.ac.aber.dcs.aberpizza.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import uk.ac.aber.dcs.aberpizza.controller.ChoiceListener;
import uk.ac.aber.dcs.aberpizza.controller.Manager;
import uk.ac.aber.dcs.aberpizza.data.Order;

/**
 * The parent panel which holds the Choices panel and the Payment panel
 * @author Samuel B Sherar (sbs1)
 * @see uk.ac.aber.dcs.aberpizza.gui.Choices
 * @see uk.ac.aber.dcs.aberpizza.gui.Payment
 */
public class ControllerPanel extends JPanel implements ActionListener{
	
	/** The choices pane. */
	private Choices choicesPane;
	
	/** The manager. */
	private Manager manager;
	
	/** The customer name. */
	private JTextField customerName;
	
	/**
	 * Instantiates a new controller panel.
	 *
	 * @param m the manager
	 */
	public ControllerPanel(Manager m) {
		super(new BorderLayout());
		
		manager = m;
		
		setPreferredSize(new Dimension(400,0));
		
		choicesPane = new Choices(m);
		choicesPane.setVisible(false);
		Payment payment = new Payment(m);
		JPanel dummyPanel = new JPanel();
		dummyPanel.add(new JLabel("Name: "));
		customerName = new JTextField(12);
		dummyPanel.add(customerName);
		JButton create = new JButton("Create Order");
		create.addActionListener(this);
		dummyPanel.add(create);
		this.add(dummyPanel, BorderLayout.NORTH);
		this.add(choicesPane, BorderLayout.CENTER);
		this.add(payment, BorderLayout.SOUTH);
		
		ChoiceListener l = new ChoiceListener();
		
	}
	
	/**
	 * Gets the choices.
	 *
	 * @return the choices
	 */
	public Choices getChoices() {
		return choicesPane;
	}
	
	/**
	 * Sets all components in the pane (and child JPanels) setEnabled() functions
	 *
	 * @param set the set
	 * @param r the pane
	 */
	public void setEnabled(boolean set, Component r) {
		Component[] comps = (r != null) ? ((JPanel) r).getComponents() : this.getComponents();
		for(Component j : comps) {
			if(j instanceof JPanel) {
				setEnabled(set, j);
			} else if(j instanceof JMenu || j instanceof JMenuItem) {
				continue;
			}
			j.setEnabled(set);
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(customerName.getText().length() < 1 && !manager.currentOrderSet()) {
			return;
		}
		manager.setCurrentOrder(new Order(customerName.getText()));
		choicesPane.setVisible(true);
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#setVisible(boolean)
	 */
	@Override
	public void setVisible(boolean set) {
		choicesPane.setVisible(set);
		if(!set)customerName.setText("");
	}
	
}
