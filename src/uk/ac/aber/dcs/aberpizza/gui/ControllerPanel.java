package uk.ac.aber.dcs.aberpizza.gui;

import java.awt.*;
import javax.swing.*;

public class ControllerPanel extends JPanel {

	public ControllerPanel() {
		super(new BorderLayout());
		setPreferredSize(new Dimension(400,0));
		
		Choices s = new Choices();
		Payment payment = new Payment();
		this.add(s, BorderLayout.CENTER);
		this.add(payment, BorderLayout.SOUTH);
		
	}
	
}
