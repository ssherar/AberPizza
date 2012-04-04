package uk.ac.aber.dcs.aberpizza.gui;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;


public class Choices extends JPanel {
	public Choices() {
		super(new GridLayout(0,4));
		for(int i = 0; i < 40; i++) {
			this.add(new JButton(""+i));
		}
	}
}
