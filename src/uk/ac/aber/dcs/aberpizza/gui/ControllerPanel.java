package uk.ac.aber.dcs.aberpizza.gui;

import java.awt.*;
import javax.swing.*;

import uk.ac.aber.dcs.aberpizza.controller.ChoiceListener;
import uk.ac.aber.dcs.aberpizza.controller.Manager;

public class ControllerPanel extends JPanel {
	private Choices s;
	private Manager manager;
	private JPanel customerPane;
	
	public ControllerPanel(Manager m) {
		super(new BorderLayout());
		
		manager = m;
		
		setPreferredSize(new Dimension(400,0));
		
		s = new Choices(m);
		Payment payment = new Payment(m);
		this.add(s, BorderLayout.CENTER);
		this.add(payment, BorderLayout.SOUTH);
		
		ChoiceListener l = new ChoiceListener();
		
	}
	
	public Choices getChoices() {
		return s;
	}
	
}
