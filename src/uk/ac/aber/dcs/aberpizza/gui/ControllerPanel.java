package uk.ac.aber.dcs.aberpizza.gui;

import java.awt.*;
import javax.swing.*;

import uk.ac.aber.dcs.aberpizza.controller.Manager;

public class ControllerPanel extends JPanel {
	private Choices s;
	private Manager manager;
	
	public ControllerPanel(Manager m) {
		super(new BorderLayout());
		
		manager = m;
		
		setPreferredSize(new Dimension(400,0));
		
		s = new Choices(m);
		Payment payment = new Payment();
		this.add(s, BorderLayout.CENTER);
		this.add(payment, BorderLayout.SOUTH);
		
	}
	
	public Choices getChoices() {
		return s;
	}
	
}
