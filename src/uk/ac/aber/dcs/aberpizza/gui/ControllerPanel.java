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

public class ControllerPanel extends JPanel implements ActionListener{
	private Choices s;
	private Manager manager;
	private JPanel customerPane;
	private JTextField customerName;
	
	public ControllerPanel(Manager m) {
		super(new BorderLayout());
		
		manager = m;
		
		setPreferredSize(new Dimension(400,0));
		
		s = new Choices(m);
		s.setVisible(false);
		Payment payment = new Payment(m);
		JPanel dummyPanel = new JPanel();
		dummyPanel.add(new JLabel("Name: "));
		customerName = new JTextField(12);
		dummyPanel.add(customerName);
		JButton create = new JButton("Create Order");
		create.addActionListener(this);
		dummyPanel.add(create);
		this.add(dummyPanel, BorderLayout.NORTH);
		this.add(s, BorderLayout.CENTER);
		this.add(payment, BorderLayout.SOUTH);
		
		ChoiceListener l = new ChoiceListener();
		
	}
	
	public Choices getChoices() {
		return s;
	}
	
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if(customerName.getText().length() < 1 && !manager.currentOrderSet()) {
			return;
		}
		manager.setCurrentOrder(new Order(customerName.getText()));
		s.setVisible(true);
	}
	
	@Override
	public void setVisible(boolean set) {
		s.setVisible(set);
		if(!set)customerName.setText("");
	}
	
}
